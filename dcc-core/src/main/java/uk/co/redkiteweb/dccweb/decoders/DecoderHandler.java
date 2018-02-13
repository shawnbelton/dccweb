package uk.co.redkiteweb.dccweb.decoders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.DecoderSetting;
import uk.co.redkiteweb.dccweb.data.model.CV;
import uk.co.redkiteweb.dccweb.data.model.Decoder;
import uk.co.redkiteweb.dccweb.data.repositories.CVRepository;
import uk.co.redkiteweb.dccweb.data.repositories.DccManufacturerRepository;
import uk.co.redkiteweb.dccweb.data.repositories.DecoderRepository;
import uk.co.redkiteweb.dccweb.data.service.NotificationService;
import uk.co.redkiteweb.dccweb.data.store.LogStore;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;
import uk.co.redkiteweb.dccweb.dccinterface.messages.EnterProgramMessage;
import uk.co.redkiteweb.dccweb.dccinterface.messages.ExitProgramMessage;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.exceptions.ProgramModeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by shawn on 07/07/16.
 */
@Component
@Scope("prototype")
public class DecoderHandler {

    private DccInterface dccInterface;
    private DecoderRepository decoderRepository;
    private CVRepository cvRepository;
    private DccManufacturerRepository dccManufacturerRepository;
    private DefinitionReaderFactory definitionReaderFactory;
    private NotificationService notificationService;
    private LogStore logStore;
    private CVHandler cvHandler;

    @Autowired
    public void setNotificationService(final NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Autowired
    public void setCvRepository(final CVRepository cvRepository) {
        this.cvRepository = cvRepository;
    }

    @Autowired
    public void setDecoderRepository(final DecoderRepository decoderRepository) {
        this.decoderRepository = decoderRepository;
    }

    @Autowired
    public void setDccInterface(final DccInterface dccInterface) {
        this.dccInterface = dccInterface;
    }

    @Autowired
    public void setDccManufacturerRepository(final DccManufacturerRepository dccManufacturerRepository) {
        this.dccManufacturerRepository = dccManufacturerRepository;
    }

    @Autowired
    public void setLogStore(final LogStore logStore) {
        this.logStore = logStore;
    }

    @Autowired
    public void setDefinitionReaderFactory(final DefinitionReaderFactory definitionReaderFactory) {
        this.definitionReaderFactory = definitionReaderFactory;
    }

    @Autowired
    public void setCvHandler(final CVHandler cvHandler) {
        this.cvHandler = cvHandler;
    }

    public Decoder readDecoderOnProgram() {
        Decoder decoder = new Decoder();
        try {
            enterProgramMode();
            try {
                final DefinitionReader definitionReader = definitionReaderFactory.getInstance(cvHandler);
                decoder = readDecoder(definitionReader);
            } catch (DecoderNotDetectedException exception) {
                logStore.log("info", "No decoder detected.");
            } catch (DefinitionException exception) {
                logStore.log("error", exception.getMessage());
            }
            exitProgramMode();
        } catch (ProgramModeException exception) {
            logStore.log("error", "Unable to enter program mode.");
        }
        return decoder;
    }

    public List<DecoderSetting> readFullOnProgram() {
        List<DecoderSetting> decoderSettings = new ArrayList<>();
        try {
            enterProgramMode();
            try {
                final DefinitionReader definitionReader = definitionReaderFactory.getInstance(cvHandler);
                final Decoder decoder = readDecoder(definitionReader);
                cvHandler.setDecoder(decoder);
                decoderSettings = definitionReader.readAllValues();
                saveDecoder(cvHandler.getCVCache(), decoder);
            } catch (DecoderNotDetectedException exception) {
                logStore.log("info", "No decoder detected.");
            } catch (DefinitionException exception) {
                logStore.log("error", exception.getMessage());
            }
            exitProgramMode();
        } catch (ProgramModeException exception) {
            logStore.log("error", "Unable to enter program mode.");
        }
        return decoderSettings;
    }

    public void writeSettingsToDecoder(final List<DecoderSetting> decoderSettings) {
        try {
            enterProgramMode();
            try {
                final DefinitionReader definitionReader = definitionReaderFactory.getInstance(cvHandler);
                final Decoder decoder = readDecoder(definitionReader);
                if (correctDecoder(definitionReader, decoderSettings)) {
                    final Map<Integer, Integer> cvMap = definitionReader.buildCVs(decoderSettings);
                    for(CV originalCv : decoder.getCvs()) {
                        if (cvMap.containsKey(originalCv.getCvNumber())) {
                            if (!originalCv.getCvValue().equals(cvMap.get(originalCv.getCvNumber()))) {
                                logStore.log("info", String.format("Writing CV %d with value %d", originalCv.getCvNumber(), cvMap.get(originalCv.getCvNumber())));
                            }
                        }
                    }
                }
            } catch (DecoderNotDetectedException exception) {
                logStore.log("info", "No decoder detected.");
            } catch (DefinitionException exception) {
                logStore.log("error", exception.getMessage());
            }
            exitProgramMode();
        } catch (ProgramModeException exception) {
            logStore.log("error", "Unable to enter program mode.");
        }
    }

    private boolean correctDecoder(final DefinitionReader definitionReader, final List<DecoderSetting> decoderSettings) throws DefinitionException {
        boolean isMatch = true;
        isMatch &= checkValue(definitionReader,"Manufacturer", decoderSettings);
        isMatch &= checkValue(definitionReader,"Revision", decoderSettings);
        isMatch &= checkValue(definitionReader,"Address Mode", decoderSettings);
        isMatch &= checkValue(definitionReader,"Short Address", decoderSettings);
        isMatch &= checkValue(definitionReader,"Long Address", decoderSettings);
        return isMatch;
    }

    private boolean checkValue(final DefinitionReader definitionReader, final String valueName, final List<DecoderSetting> decoderSettings) throws DefinitionException {
        boolean isMatched = false;
        final Integer checkValue = definitionReader.readValue(valueName);
        for (DecoderSetting decoderSetting : decoderSettings) {
            if (valueName.equals(decoderSetting.getName())) {
                isMatched = checkValue.equals(decoderSetting.getValue());
            }
        }
        return isMatched;
    }

    private void enterProgramMode() throws ProgramModeException {
        if (!MessageResponse.MessageStatus.OK.equals(dccInterface.sendMessage(new EnterProgramMessage()).getStatus())) {
            throw new ProgramModeException("Unable to set Program Mode");
        }
    }

    private void exitProgramMode() {
        dccInterface.sendMessage(new ExitProgramMessage());
    }

    private Decoder readDecoderNoSave(final DefinitionReader definitionReader) throws DefinitionException {
        final Decoder decoder = new Decoder();
        decoder.setDccManufacturer(dccManufacturerRepository.findOne(definitionReader.readValue("Manufacturer")));
        decoder.setVersion(definitionReader.readValue("Revision"));
        decoder.setAddressMode(definitionReader.readValue("Address Mode") == 1);
        decoder.setShortAddress(definitionReader.readValue("Short Address"));
        decoder.setLongAddress(definitionReader.readValue("Long Address"));
        if (decoder.getAddressMode()) {
            decoder.setCurrentAddress(decoder.getLongAddress());
        } else {
            decoder.setCurrentAddress(decoder.getShortAddress());
        }
        copyExistingDecoder(decoder);
        return decoder;
    }

    private Decoder readDecoder(final DefinitionReader definitionReader) throws DefinitionException {
        final Decoder decoder = readDecoderNoSave(definitionReader);
        return saveDecoder(cvHandler.getCVCache(), decoder);
    }

    private void copyExistingDecoder(final Decoder decoder) {
        final Decoder existingDecoder = decoderRepository.findByCurrentAddress(decoder.getCurrentAddress());
        if (existingDecoder != null) {
            decoder.setDecoderId(existingDecoder.getDecoderId());
            decoder.setCvs(existingDecoder.getCvs());
            decoder.setLinkedMacros(existingDecoder.getLinkedMacros());
            decoder.setDecoderFunctions(existingDecoder.getDecoderFunctions());
        }
    }

    private Decoder saveDecoder(final Map<Integer, Integer> cachedCvs,final Decoder decoder) {
        decoderRepository.save(decoder);
        for (Map.Entry<Integer, Integer> cvValue : cachedCvs.entrySet()) {
            final CV cv = new CV();
            cv.setDecoderId(decoder.getDecoderId());
            cv.setCvNumber(cvValue.getKey());
            cv.setCvValue(cvValue.getValue());
            cvRepository.save(cv);
        }
        notificationService.createNotification("DECODERS", "");
        return decoderRepository.findOne(decoder.getDecoderId());
    }
}

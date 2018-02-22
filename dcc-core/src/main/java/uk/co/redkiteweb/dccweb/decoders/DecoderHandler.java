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
import uk.co.redkiteweb.dccweb.decoders.types.CVHandler;
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

    private static final String ERROR = "error";
    private static final String INFO = "info";
    private static final String UNABLE_TO_ENTER_PROGRAM_MODE = "Unable to enter program mode.";
    private static final String NO_DECODER_DETECTED = "No decoder detected.";
    private static final String REVISION = "Revision";
    private static final String ADDRESS_MODE = "Address Mode";
    private static final String SHORT_ADDRESS = "Short Address";
    private static final String LONG_ADDRESS = "Long Address";
    private static final String MANUFACTURER = "Manufacturer";
    private static final String DECODERS = "DECODERS";
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
            decoder = getDecoder();
            exitProgramMode();
        } catch (ProgramModeException exception) {
            logStore.log(ERROR, UNABLE_TO_ENTER_PROGRAM_MODE);
        }
        return decoder;
    }

    private Decoder getDecoder() {
        Decoder decoder = new Decoder();
        try {
            final DefinitionReader definitionReader = definitionReaderFactory.getInstance(cvHandler);
            decoder = readDecoder(definitionReader);
        } catch (DecoderNotDetectedException exception) {
            logStore.log(INFO, NO_DECODER_DETECTED);
        } catch (DefinitionException exception) {
            logStore.log(ERROR, exception.getMessage());
        }
        return decoder;
    }

    public List<DecoderSetting> readFullOnProgram() {
        List<DecoderSetting> decoderSettings = new ArrayList<>();
        try {
            enterProgramMode();
            decoderSettings = getDecoderSettings();
            exitProgramMode();
        } catch (ProgramModeException exception) {
            logStore.log(ERROR, UNABLE_TO_ENTER_PROGRAM_MODE);
        }
        return decoderSettings;
    }

    private List<DecoderSetting> getDecoderSettings() {
        List<DecoderSetting> decoderSettings = new ArrayList<>();
        try {
            final DefinitionReader definitionReader = definitionReaderFactory.getInstance(cvHandler);
            final Decoder decoder = readDecoder(definitionReader);
            cvHandler.setDecoder(decoder);
            decoderSettings = definitionReader.readAllValues();
            saveDecoder(cvHandler.getCVCache(), decoder);
        } catch (DecoderNotDetectedException exception) {
            logStore.log(INFO, NO_DECODER_DETECTED);
        } catch (DefinitionException exception) {
            logStore.log(ERROR, exception.getMessage());
        }
        return decoderSettings;
    }

    public void writeSettingsToDecoder(final List<DecoderSetting> decoderSettings) {
        try {
            enterProgramMode();
            writeDecoder(decoderSettings);
            exitProgramMode();
        } catch (ProgramModeException exception) {
            logStore.log(ERROR, UNABLE_TO_ENTER_PROGRAM_MODE);
        }
    }

    private void writeDecoder(final List<DecoderSetting> decoderSettings) {
        try {
            writeToDecoder(decoderSettings);
        } catch (DecoderNotDetectedException exception) {
            logStore.log(INFO, NO_DECODER_DETECTED);
        } catch (DefinitionException exception) {
            logStore.log(ERROR, exception.getMessage());
        }
    }

    private void writeToDecoder(final List<DecoderSetting> decoderSettings) throws DefinitionException {
        final DefinitionReader definitionReader = definitionReaderFactory.getInstance(cvHandler);
        if (correctDecoder(definitionReader, decoderSettings)) {
            writeToDecoder(decoderSettings, definitionReader);
        } else {
            logStore.log(ERROR, "Incorrect decoder detected on Program Track");
        }
    }

    private void writeToDecoder(final List<DecoderSetting> decoderSettings, final DefinitionReader definitionReader) throws DefinitionException {
        final Decoder decoder = readDecoder(definitionReader);
        final Map<Integer, Integer> cvMap = definitionReader.buildCVs(decoderSettings);
        for (CV originalCv : decoder.getCvs()) {
            writeCVToDecoder(cvMap, originalCv);
        }
        cvHandler.setDecoder(decoder);
        readBaseDecoderValues(definitionReader, decoder);
        decoderRepository.save(decoder);
        notificationService.createNotification(DECODERS, "");
    }

    private void writeCVToDecoder(final Map<Integer, Integer> cvMap, final CV originalCv) {
        if (cvMap.containsKey(originalCv.getCvNumber())
                && !originalCv.getCvValue().equals(cvMap.get(originalCv.getCvNumber()))) {
            logStore.log(INFO, String.format("Writing CV %d with value %d", originalCv.getCvNumber(), cvMap.get(originalCv.getCvNumber())));
            originalCv.setCvValue(cvMap.get(originalCv.getCvNumber()));
            cvHandler.writeCV(originalCv);
            cvRepository.save(originalCv);
        }
    }

    private static boolean correctDecoder(final DefinitionReader definitionReader, final List<DecoderSetting> decoderSettings) throws DefinitionException {
        boolean isMatch = true;
        isMatch &= checkValue(definitionReader, MANUFACTURER, decoderSettings);
        isMatch &= checkValue(definitionReader, REVISION, decoderSettings);
        isMatch &= checkValue(definitionReader, ADDRESS_MODE, decoderSettings);
        isMatch &= checkValue(definitionReader, SHORT_ADDRESS, decoderSettings);
        isMatch &= checkValue(definitionReader, LONG_ADDRESS, decoderSettings);
        return isMatch;
    }

    private static boolean checkValue(final DefinitionReader definitionReader, final String valueName, final List<DecoderSetting> decoderSettings) throws DefinitionException {
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
        decoder.setDccManufacturer(dccManufacturerRepository.findOne(definitionReader.readValue(MANUFACTURER)));
        decoder.setVersion(definitionReader.readValue(REVISION));
        readBaseDecoderValues(definitionReader, decoder);
        copyExistingDecoder(decoder);
        return decoder;
    }

    private static void readBaseDecoderValues(final DefinitionReader definitionReader, final Decoder decoder) throws DefinitionException {
        decoder.setAddressMode(definitionReader.readValue(ADDRESS_MODE) == 1);
        decoder.setShortAddress(definitionReader.readValue(SHORT_ADDRESS));
        decoder.setLongAddress(definitionReader.readValue(LONG_ADDRESS));
        if (decoder.getAddressMode()) {
            decoder.setCurrentAddress(decoder.getLongAddress());
        } else {
            decoder.setCurrentAddress(decoder.getShortAddress());
        }
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

    private Decoder saveDecoder(final Map<Integer, Integer> cachedCvs, final Decoder decoder) {
        decoderRepository.save(decoder);
        for (Map.Entry<Integer, Integer> cvValue : cachedCvs.entrySet()) {
            final CV cv = new CV();
            cv.setDecoderId(decoder.getDecoderId());
            cv.setCvNumber(cvValue.getKey());
            cv.setCvValue(cvValue.getValue());
            cvRepository.save(cv);
        }
        notificationService.createNotification(DECODERS, "");
        return decoderRepository.findOne(decoder.getDecoderId());
    }
}

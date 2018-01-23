package uk.co.redkiteweb.dccweb.readers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.DecoderSetting;
import uk.co.redkiteweb.dccweb.data.model.CV;
import uk.co.redkiteweb.dccweb.data.model.Decoder;
import uk.co.redkiteweb.dccweb.data.repositories.CVRepository;
import uk.co.redkiteweb.dccweb.data.repositories.DccManufacturerRepository;
import uk.co.redkiteweb.dccweb.data.repositories.DecoderRepository;
import uk.co.redkiteweb.dccweb.data.store.LogStore;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;
import uk.co.redkiteweb.dccweb.dccinterface.messages.EnterProgramMessage;
import uk.co.redkiteweb.dccweb.dccinterface.messages.ExitProgramMessage;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.decoders.DecoderNotDetectedException;
import uk.co.redkiteweb.dccweb.decoders.DefinitionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by shawn on 07/07/16.
 */
@Component
@Scope("prototype")
public class DecoderReader {

    private DccInterface dccInterface;
    private DecoderRepository decoderRepository;
    private CVRepository cvRepository;
    private DccManufacturerRepository dccManufacturerRepository;
    private DefinitionReaderFactory definitionReaderFactory;
    private LogStore logStore;
    private CVReader cvReader;

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
    public void setCvReader(final CVReader cvReader) {
        this.cvReader = cvReader;
    }

    public Decoder readDecoderOnProgram() {
        Decoder decoder = new Decoder();
        if (MessageResponse.MessageStatus.OK.equals(dccInterface.sendMessage(new EnterProgramMessage()).getStatus())) {
            try {
                final DefinitionReader definitionReader = definitionReaderFactory.getInstance(cvReader);
                decoder = readDecoder(definitionReader);
            } catch (DecoderNotDetectedException exception) {
                logStore.log("info", "No decoder detected.");
            } catch (DefinitionException exception) {
                logStore.log("error", exception.getMessage());
            }
            dccInterface.sendMessage(new ExitProgramMessage());
        } else {
            logStore.log("error", "Unable to enter program mode.");
        }
        return decoder;
    }

    public List<DecoderSetting> readFullOnProgram(final Integer decoderId) {
        List<DecoderSetting> decoderSettings = new ArrayList<>();
        if (MessageResponse.MessageStatus.OK.equals(dccInterface.sendMessage(new EnterProgramMessage()).getStatus())) {
            try {
                final Decoder decoder = decoderRepository.findOne(decoderId);
                cvReader.setDecoder(decoder);
                final DefinitionReader definitionReader = definitionReaderFactory.getInstance(cvReader);
                decoderSettings = definitionReader.readAllValues();
                saveDecoder(cvReader.getCVCache(), decoder);
            } catch (DecoderNotDetectedException exception) {
                logStore.log("info", "No decoder detected.");
            } catch (DefinitionException exception) {
                logStore.log("error", exception.getMessage());
            }
            dccInterface.sendMessage(new ExitProgramMessage());
        } else {
            logStore.log("error", "Unable to enter program mode.");
        }
        return decoderSettings;
    }

    private Decoder readDecoder(final DefinitionReader definitionReader) throws DefinitionException {
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
        return saveDecoder(cvReader.getCVCache(), decoder);
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
        return decoderRepository.findOne(decoder.getDecoderId());
    }
}

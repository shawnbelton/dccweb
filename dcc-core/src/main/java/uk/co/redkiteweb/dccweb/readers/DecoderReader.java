package uk.co.redkiteweb.dccweb.readers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
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
import uk.co.redkiteweb.dccweb.dccinterface.messages.ReadCVMessage;
import uk.co.redkiteweb.dccweb.decoders.DefinitionException;

import java.util.HashMap;
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
                logStore.log("info", "Reading manufacturer");
                final Integer manufacturerId = cvReader.readCV(8);
                if (manufacturerId != null) {
                    decoder = readDecoder(manufacturerId);
                } else {
                    logStore.log("info", "No decoder detected.");
                }
            } catch (DefinitionException exception) {
                logStore.log("error", exception.getMessage());
            }
            dccInterface.sendMessage(new ExitProgramMessage());
        } else {
            logStore.log("error", "Unable to enter program mode.");
        }
        return decoder;
    }

    private Decoder readDecoder(final Integer manufacturerId) throws DefinitionException {
        final Decoder decoder = new Decoder();
        logStore.log("info", "Reading Revision");
        final Integer revision = cvReader.readCV(7);
        final DefinitionReader definitionReader = definitionReaderFactory.getInstance(manufacturerId, revision);
        definitionReader.setCvReader(cvReader);
        decoder.setAddressMode(definitionReader.readValue("Address Mode") == 1);
        decoder.setDccManufacturer(dccManufacturerRepository.findOne(manufacturerId));
        decoder.setVersion(revision);
        decoder.setShortAddress(definitionReader.readValue("Short Address"));
        decoder.setLongAddress(definitionReader.readValue("Long Address"));
        if (decoder.getAddressMode()) {
            decoder.setCurrentAddress(decoder.getLongAddress());
        } else {
            decoder.setCurrentAddress(decoder.getShortAddress());
        }
        final Decoder existingDecoder = decoderRepository.findByCurrentAddress(decoder.getCurrentAddress());
        if (existingDecoder != null) {
            decoder.setDecoderId(existingDecoder.getDecoderId());
            decoder.setCvs(existingDecoder.getCvs());
            decoder.setDecoderFunctions(existingDecoder.getDecoderFunctions());
        }
        //Integer addressMode = readCV(29);  // Default value 12, Long Address value 34 so bit 5 is value 32
        return saveDecoder(cvReader.getCVCache(), decoder);
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

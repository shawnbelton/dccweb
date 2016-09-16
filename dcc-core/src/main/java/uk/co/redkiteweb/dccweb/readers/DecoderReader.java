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

    public Decoder readDecoderOnProgram() {
        Map<Integer, Integer> cachedCvs = new HashMap<Integer, Integer>();
        Decoder decoder = new Decoder();
        if (MessageResponse.MessageStatus.OK.equals(dccInterface.sendMessage(new EnterProgramMessage()).getStatus())) {
            try {
                logStore.log("info", "Reading manufacturer");
                final Integer manufacturerId = readCV(8);
                if (manufacturerId != null) {
                    logStore.log("info", "Reading Revision");
                    final Integer revision = readCV(7);
                    final DefinitionReader definitionReader = definitionReaderFactory.getInstance(manufacturerId, revision);
                    final boolean longAddressMode = definitionReader.readValue("Address Mode") == 1;
                    decoder.setDccManufacturer(dccManufacturerRepository.findOne(manufacturerId));
                    decoder.setVersion(revision);
                    decoder.setShortAddress(definitionReader.readValue("Short Address"));
                    decoder.setLongAddress(definitionReader.readValue("Long Address"));
                    if (longAddressMode) {
                        decoder.setCurrentAddress(decoder.getLongAddress());
                    } else {
                        decoder.setCurrentAddress(decoder.getShortAddress());
                    }
                    final Decoder existingDecoder = decoderRepository.findByCurrentAddress(decoder.getCurrentAddress());
                    if (existingDecoder != null) {
                        decoder.setDecoderId(existingDecoder.getDecoderId());
                    }
                    cachedCvs = definitionReader.getCVCache();
                }
            } catch (DefinitionException exception) {
                logStore.log("error", exception.getMessage());
            }
            //Integer addressMode = readCV(29);  // Default value 12, Long Address value 34 so bit 5 is value 32
            dccInterface.sendMessage(new ExitProgramMessage());
            decoderRepository.save(decoder);
            for (Integer cvNumber : cachedCvs.keySet()) {
                final CV cv = new CV();
                cv.setCvNumber(cvNumber);
                cv.setCvValue(cachedCvs.get(cvNumber));
                cv.setDecoderId(decoder.getDecoderId());
                cvRepository.save(cv);
            }
            decoder = decoderRepository.findOne(decoder.getDecoderId());
        }
        return decoder;
    }

    private Integer readCV(final int cvNumber) {
        final ReadCVMessage readCVMessage = new ReadCVMessage();
        readCVMessage.setCvReg(cvNumber);
        return getCvValue(dccInterface.sendMessage(readCVMessage));
    }

    private static Integer getCvValue(final MessageResponse response) {
        Integer cvValue = null;
        if (MessageResponse.MessageStatus.OK.equals(response.getStatus())) {
            cvValue = (Integer)response.get("CVData");
        }
        return cvValue;
    }
}

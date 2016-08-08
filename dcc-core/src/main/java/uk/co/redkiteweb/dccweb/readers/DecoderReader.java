package uk.co.redkiteweb.dccweb.readers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.model.CV;
import uk.co.redkiteweb.dccweb.data.model.DccManufacturer;
import uk.co.redkiteweb.dccweb.data.model.Decoder;
import uk.co.redkiteweb.dccweb.data.repositories.CVRepository;
import uk.co.redkiteweb.dccweb.data.repositories.DccManufacturerRepository;
import uk.co.redkiteweb.dccweb.data.repositories.DecoderRepository;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;
import uk.co.redkiteweb.dccweb.dccinterface.messages.EnterProgramMessage;
import uk.co.redkiteweb.dccweb.dccinterface.messages.ExitProgramMessage;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.dccinterface.messages.ReadCVMessage;

import java.util.ArrayList;
import java.util.List;

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
    private List<CV> cvs;

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

    public Decoder readDecoderOnProgram() {
        Decoder decoder = new Decoder();
        cvs = new ArrayList<CV>();
        if (MessageResponse.MessageStatus.OK.equals(dccInterface.sendMessage(new EnterProgramMessage()).getStatus())) {
            Integer longAddress = readCV(17);
            if (longAddress!=null) {
                longAddress *= 256;
                longAddress += readCV(18);
            }
            Integer shortAddress = readCV(1);
            final Decoder existingDecoder = decoderRepository.findByShortAddressAndLongAddress(shortAddress, longAddress);
            if (existingDecoder != null) {
                decoder = existingDecoder;
            }
            decoder.setDccManufacturer(readManufacturer());
            decoder.setVersion(readCV(7));
            decoder.setShortAddress(shortAddress);
            decoder.setLongAddress(longAddress);
            dccInterface.sendMessage(new ExitProgramMessage());
            decoderRepository.save(decoder);
            for(CV cv : cvs) {
                cv.setDecoderId(decoder.getDecoderId());
                cvRepository.save(cv);
            }
            decoder = decoderRepository.findOne(decoder.getDecoderId());
        }
        return decoder;
    }

    private DccManufacturer readManufacturer() {
        DccManufacturer dccManufacturer = null;
        final Integer cvValue = readCV(8);
        if (cvValue != null) {
            dccManufacturer = dccManufacturerRepository.findOne(cvValue);
        }
        return dccManufacturer;
    }

    private Integer readCV(final int cvNumber) {
        final ReadCVMessage readCVMessage = new ReadCVMessage();
        readCVMessage.setCvReg(cvNumber);
        final Integer cvValue = getCvValue(dccInterface.sendMessage(readCVMessage));
        if (cvValue != null) {
            final CV cv = new CV();
            cv.setCvNumber(cvNumber);
            cv.setCvValue(cvValue);
            cvs.add(cv);
        }
        return cvValue;
    }

    private static Integer getCvValue(final MessageResponse response) {
        Integer cvValue = null;
        if (MessageResponse.MessageStatus.OK.equals(response.getStatus())) {
            cvValue = (Integer)response.get("CVData");
        }
        return cvValue;
    }
}

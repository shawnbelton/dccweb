package uk.co.redkiteweb.dccweb.readers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.model.CV;
import uk.co.redkiteweb.dccweb.data.model.DccManufacturer;
import uk.co.redkiteweb.dccweb.data.model.Decoder;
import uk.co.redkiteweb.dccweb.data.repositories.DccManufacturerRepository;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;
import uk.co.redkiteweb.dccweb.dccinterface.messages.EnterProgramMessage;
import uk.co.redkiteweb.dccweb.dccinterface.messages.ExitProgramMessage;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.dccinterface.messages.ReadCVMessage;

import java.util.ArrayList;

/**
 * Created by shawn on 07/07/16.
 */
@Component
@Scope("prototype")
public class DecoderReader {

    private DccInterface dccInterface;
    private DccManufacturerRepository dccManufacturerRepository;
    private Decoder decoder;

    @Autowired
    public void setDccInterface(final DccInterface dccInterface) {
        this.dccInterface = dccInterface;
    }

    @Autowired
    public void setDccManufacturerRepository(final DccManufacturerRepository dccManufacturerRepository) {
        this.dccManufacturerRepository = dccManufacturerRepository;
    }

    public Decoder readDecoderOnProgram() {
        decoder = new Decoder();
        decoder.setCvs(new ArrayList<CV>());
        if (MessageResponse.MessageStatus.OK.equals(dccInterface.sendMessage(new EnterProgramMessage()).getStatus())) {
            decoder.setDccManufacturer(readManufacturer());
            decoder.setVersion(readCV(7));
            decoder.setShortAddress(readCV(1));
            Integer longAddress = readCV(17);
            if (longAddress!=null) {
                longAddress *= 256;
                longAddress += readCV(18);
            }
            decoder.setLongAddress(longAddress);
            dccInterface.sendMessage(new ExitProgramMessage());
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
            decoder.getCvs().add(cv);
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

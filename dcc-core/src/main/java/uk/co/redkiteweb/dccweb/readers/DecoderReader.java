package uk.co.redkiteweb.dccweb.readers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.model.DccManufacturer;
import uk.co.redkiteweb.dccweb.data.model.Decoder;
import uk.co.redkiteweb.dccweb.data.repositories.DccManufacturerRepository;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;
import uk.co.redkiteweb.dccweb.dccinterface.messages.EnterProgramMessage;
import uk.co.redkiteweb.dccweb.dccinterface.messages.ExitProgramMessage;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.dccinterface.messages.ReadCVMessage;

/**
 * Created by shawn on 07/07/16.
 */
@Component
public class DecoderReader {

    private DccInterface dccInterface;
    private DccManufacturerRepository dccManufacturerRepository;

    @Autowired
    public void setDccInterface(final DccInterface dccInterface) {
        this.dccInterface = dccInterface;
    }

    @Autowired
    public void setDccManufacturerRepository(final DccManufacturerRepository dccManufacturerRepository) {
        this.dccManufacturerRepository = dccManufacturerRepository;
    }

    public Decoder readDecoderOnProgram() {
        final Decoder decoder = new Decoder();
        if (MessageResponse.MessageStatus.OK.equals(dccInterface.sendMessage(new EnterProgramMessage()).getStatus())) {
            decoder.setDccManufacturer(readManufacturer());
            decoder.setRevision(readCV(7));
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

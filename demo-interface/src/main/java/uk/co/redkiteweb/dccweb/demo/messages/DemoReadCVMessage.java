package uk.co.redkiteweb.dccweb.demo.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.dccinterface.messages.ReadCVMessage;
import uk.co.redkiteweb.dccweb.demo.registers.DecoderRegister;

/**
 * Created by shawn on 25/07/16.
 */
@Component("DemoReadCVMessage")
public class DemoReadCVMessage extends AbstractSingleResponseMessage implements DemoMessage {

    private DecoderRegister decoderRegister;

    @Autowired
    public void setDecoderRegister(final DecoderRegister decoderRegister) {
        this.decoderRegister = decoderRegister;
    }

    @Override
    public MessageResponse process(final Message message) {
        return singleResponse("CVData", decoderRegister.getCV(((ReadCVMessage)message).getCvReg()));
    }
}

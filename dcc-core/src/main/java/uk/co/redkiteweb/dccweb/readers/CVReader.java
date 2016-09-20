package uk.co.redkiteweb.dccweb.readers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.dccinterface.messages.ReadCVMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shawn on 18/09/16.
 */
@Component
@Scope("prototype")
public class CVReader {

    private final Map<Integer, Integer> cachedCVs;
    private DccInterface dccInterface;

    public CVReader() {
        cachedCVs = new HashMap<Integer, Integer>();
    }

    @Autowired
    public void setDccInterface(final DccInterface dccInterface) {
        this.dccInterface = dccInterface;
    }

    public Map<Integer, Integer> getCVCache() {
        return cachedCVs;
    }

    public Integer readCV(final int cvNumber) {
        Integer cvValue;
        if (cachedCVs.containsKey(cvNumber)) {
            cvValue = cachedCVs.get(cvNumber);
        } else {
            final ReadCVMessage readCVMessage = new ReadCVMessage();
            readCVMessage.setCvReg(cvNumber);
            cvValue = getCvValue(dccInterface.sendMessage(readCVMessage));
            cachedCVs.put(cvNumber, cvValue);
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

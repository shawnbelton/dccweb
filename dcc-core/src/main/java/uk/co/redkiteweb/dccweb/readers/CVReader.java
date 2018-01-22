package uk.co.redkiteweb.dccweb.readers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.model.CV;
import uk.co.redkiteweb.dccweb.data.model.Decoder;
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

    private static final Logger LOGGER = LogManager.getLogger(CVReader.class);

    private final Map<Integer, Integer> cachedCVs;
    private DccInterface dccInterface;

    public CVReader() {
        cachedCVs = new HashMap<>();
    }

    @Autowired
    public void setDccInterface(final DccInterface dccInterface) {
        this.dccInterface = dccInterface;
    }

    public void setDecoder(final Decoder decoder) {
        cachedCVs.clear();
        for(CV cv : decoder.getCvs()) {
            addToCache(cv.getCvNumber(), cv.getCvValue());
        }
    }

    public Map<Integer, Integer> getCVCache() {
        return cachedCVs;
    }

    public Integer readCV(final int cvNumber) {
        Integer cvValue;
        if (cachedCVs.containsKey(cvNumber)) {
            cvValue = cachedCVs.get(cvNumber);
        } else {
            cvValue = readFromProgramTrack(cvNumber);
            addToCache(cvNumber, cvValue);
        }
        LOGGER.info(String.format("CV %d read as %d", cvNumber, cvValue));
        return cvValue;
    }

    private Integer readFromProgramTrack(int cvNumber) {
        final ReadCVMessage readCVMessage = new ReadCVMessage();
        readCVMessage.setCvReg(cvNumber);
        return getCvValue(dccInterface.sendMessage(readCVMessage));
    }

    private void addToCache(final int cvNumber, final Integer cvValue) {
        if (cvValue!=null) {
            cachedCVs.put(cvNumber, cvValue);
        }
    }

    private static Integer getCvValue(final MessageResponse response) {
        Integer cvValue = null;
        if (MessageResponse.MessageStatus.OK.equals(response.getStatus())) {
            cvValue = (Integer)response.get("CVData");
        }
        return cvValue;
    }
}

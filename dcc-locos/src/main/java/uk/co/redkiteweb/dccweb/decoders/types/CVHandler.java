package uk.co.redkiteweb.dccweb.decoders.types;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.model.CV;
import uk.co.redkiteweb.dccweb.data.model.Decoder;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.dccinterface.messages.ReadCVMessage;
import uk.co.redkiteweb.dccweb.dccinterface.messages.WriteCVMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shawn on 18/09/16.
 */
@Component
@Scope("prototype")
public class CVHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CVHandler.class);

    private final Map<Integer, Integer> cachedCVs;
    private DccInterface dccInterface;

    public CVHandler() {
        cachedCVs = new HashMap<>();
    }

    @Autowired
    public void setDccInterface(final DccInterface dccInterface) {
        this.dccInterface = dccInterface;
    }

    public void setDecoder(final Decoder decoder) {
        if (decoder.getCvs() != null) {
            for (CV cv : decoder.getCvs()) {
                addToCache(cv.getCvNumber(), cv.getCvValue());
            }
        }
    }

    public Map<Integer, Integer> getCVCache() {
        return cachedCVs;
    }

    public void writeCV(final CV cv) {
        final WriteCVMessage writeCVMessage = new WriteCVMessage();
        writeCVMessage.setCv(cv);
        dccInterface.sendMessage(writeCVMessage);
    }

    public Integer readCV(final int cvNumber) {
        final Integer cvValue = readCVUseCache(cvNumber);
        LOGGER.info("CV {} read as {}", cvNumber, cvValue);
        return cvValue;
    }

    private Integer readCVUseCache(final int cvNumber) {
        Integer cvValue;
        if (cachedCVs.containsKey(cvNumber)) {
            cvValue = cachedCVs.get(cvNumber);
        } else {
            cvValue = readCVDirect(cvNumber);
            addToCache(cvNumber, cvValue);
        }
        return cvValue;
    }

    private Integer readCVDirect(final int cvNumber) {
        return readFromProgramTrack(cvNumber);
    }

    private Integer readFromProgramTrack(int cvNumber) {
        LOGGER.info("Reading CV {} from track.", cvNumber);
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

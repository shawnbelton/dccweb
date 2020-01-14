package uk.co.redkiteweb.dccweb.demo.registers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.model.CV;
import uk.co.redkiteweb.dccweb.data.model.Decoder;

import java.util.ArrayList;

/**
 * Created by shawn on 25/07/16.
 */
@Component
public class DecoderRegister {

    private static final Logger LOGGER = LoggerFactory.getLogger(DecoderRegister.class);

    private Decoder decoder;

    public void initialise() {
        LOGGER.info("Initialising Decoder Register");
        decoder = new Decoder();
        decoder.setCvs(new ArrayList<>());
    }

    public void setCV(final int cvNumber, final int cvValue) {
        LOGGER.info("Setting CV {} to {}", cvNumber, cvValue);
        final CV cv = new CV();
        cv.setCvNumber(cvNumber);
        cv.setCvValue(cvValue);
        decoder.getCvs().add(cv);
    }

    public Integer getCV(final int cvNumber) {
        Integer value = null;
        for (CV cv : decoder.getCvs()) {
            if (cv.getCvNumber()==cvNumber) {
                value = cv.getCvValue();
            }
        }
        LOGGER.info("Reading CV {} has value {}", cvNumber, value);
        return value;
    }

}

package uk.co.redkiteweb.dccweb.demo.registers;

import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.model.CV;
import uk.co.redkiteweb.dccweb.data.model.Decoder;

import java.util.ArrayList;

/**
 * Created by shawn on 25/07/16.
 */
@Component
public class DecoderRegister {

    private Decoder decoder;

    public void initialise() {
        decoder = new Decoder();
        decoder.setCvs(new ArrayList<CV>());
    }

    public void setCV(final int cvNumber, final int cvValue) {
        final CV cv = new CV();
        cv.setCvNumber(cvNumber);
        cv.setCvValue(cvValue);
        decoder.getCvs().add(cv);
    }

    public int getCV(final int cvNumber) {
        Integer value = null;
        for (CV cv : decoder.getCvs()) {
            if (cv.getCvNumber()==cvNumber) {
                value = cv.getCvValue();
            }
        }
        return value;
    }

}

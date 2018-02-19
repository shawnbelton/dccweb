package uk.co.redkiteweb.dccweb.dccinterface.messages;

import uk.co.redkiteweb.dccweb.data.model.CV;

public class WriteCVMessage implements Message {

    private CV cv;

    public CV getCv() {
        return cv;
    }

    public void setCv(final CV cv) {
        this.cv = cv;
    }

    @Override
    public String getLogMessage() {
        return String.format("Writing CV %d with value %d", cv.getCvNumber(), cv.getCvValue());
    }
}

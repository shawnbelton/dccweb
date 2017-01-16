package uk.co.redkiteweb.dccweb.dccinterface.messages;

/**
 * Created by shawn on 11/07/16.
 */
public class ReadCVMessage implements Message {

    private int cvReg;

    public int getCvReg() {
        return cvReg;
    }

    public void setCvReg(int cvReg) {
        this.cvReg = cvReg;
    }

    @Override
    public String getLogMessage() {
        return String.format("Reading CV %d", cvReg);
    }
}

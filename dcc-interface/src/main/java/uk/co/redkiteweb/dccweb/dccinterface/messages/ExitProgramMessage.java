package uk.co.redkiteweb.dccweb.dccinterface.messages;

/**
 * Created by shawn on 11/07/16.
 */
public class ExitProgramMessage implements Message {

    @Override
    public String getLogMessage() {
        return "Exit Program Mode";
    }
}

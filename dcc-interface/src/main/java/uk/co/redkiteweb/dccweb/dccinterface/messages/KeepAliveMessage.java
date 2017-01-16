package uk.co.redkiteweb.dccweb.dccinterface.messages;

/**
 * Created by shawn on 07/07/16.
 */
public class KeepAliveMessage implements Message {

    @Override
    public String getLogMessage() {
        return "Keep Alive";
    }
}

package uk.co.redkiteweb.dccweb.dccinterface.messages;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shawn on 07/07/16.
 */
public class MessageResponse {

    public enum MessageStatus {
        OK,
        ERROR
    }

    private MessageStatus status;
    private final Map<String, Object> data;

    public MessageResponse() {
        data = new HashMap<>();
    }

    public void setStatus(final MessageStatus status) {
        this.status = status;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void put(final String name, final Object value) {
        data.put(name, value);
    }

    public Object get(final String name) {
        Object value = null;
        if (data.containsKey(name)) {
            value = data.get(name);
        }
        return value;
    }

}

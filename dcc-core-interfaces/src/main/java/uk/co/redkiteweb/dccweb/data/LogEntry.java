package uk.co.redkiteweb.dccweb.data;

import java.io.Serializable;

/**
 * Created by shawn on 14/09/16.
 */
public class LogEntry implements Serializable {

    private static final long serialVersionUID = 7000;

    private String level;
    private String message;

    public String getLevel() {
        return level;
    }

    public void setLevel(final String level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}

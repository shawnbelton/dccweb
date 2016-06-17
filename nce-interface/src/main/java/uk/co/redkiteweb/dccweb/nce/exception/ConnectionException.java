package uk.co.redkiteweb.dccweb.nce.exception;

/**
 * Created by shawn on 17/06/16.
 */
public class ConnectionException extends Exception {

    public ConnectionException(final String message) {
        super(message);
    }

    public ConnectionException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}

package uk.co.redkiteweb.dccweb.data.readers;

/**
 * Created by shawn on 30/06/16.
 */
public class ReaderException extends RuntimeException {

    public ReaderException(final String message) {
        super(message);
    }

    public ReaderException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

}

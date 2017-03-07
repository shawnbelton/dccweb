package uk.co.redkiteweb.dccweb.data.readers;

/**
 * Created by shawn on 30/06/16.
 */
class ReaderException extends Exception {

    public ReaderException(final String message) {
        super(message);
    }

    public ReaderException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

}

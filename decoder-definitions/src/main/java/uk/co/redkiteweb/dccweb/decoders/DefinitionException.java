package uk.co.redkiteweb.dccweb.decoders;

/**
 * Created by shawn on 16/09/16.
 */
public class DefinitionException extends Exception {

    public DefinitionException(final String message) {
        super(message);
    }

    public DefinitionException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

    public DefinitionException(final Throwable throwable) {
        super(throwable);
    }
}

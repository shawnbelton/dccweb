package uk.co.redkiteweb.dccweb.exceptions;

public class ProgramModeException extends Exception {

    public ProgramModeException(final String message) {
        super(message);
    }

    public ProgramModeException(final Throwable throwable) {
        super(throwable);
    }

    public ProgramModeException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}

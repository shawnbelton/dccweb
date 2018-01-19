package uk.co.redkiteweb.dccweb.decoders;

public class DecoderNotDetectedException extends DefinitionException {

    public DecoderNotDetectedException(final String message) {
        super(message);
    }

    public DecoderNotDetectedException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

    public DecoderNotDetectedException(final Throwable throwable) {
        super(throwable);
    }

}

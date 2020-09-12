package br.com.helpdev.jynx.core.exception;

public class FailureToPublishException extends Exception {
    public FailureToPublishException(final String message, final Throwable cause) {
        super(message, cause);
    }
}

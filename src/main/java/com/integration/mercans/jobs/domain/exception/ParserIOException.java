package com.integration.mercans.jobs.domain.exception;

public final class ParserIOException extends ParserException {

    public ParserIOException(String message) {
        super(message);
    }

    public ParserIOException(String message, Throwable cause) {
        super(message, cause);
    }
}
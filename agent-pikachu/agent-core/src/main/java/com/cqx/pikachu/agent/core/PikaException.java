package com.cqx.pikachu.agent.core;

public class PikaException extends RuntimeException {

    public PikaException(String message, Throwable cause) {
        super(message, cause);
    }

    public PikaException(String message) {
        super(message);
    }

    public PikaException() {
        super();
    }
}

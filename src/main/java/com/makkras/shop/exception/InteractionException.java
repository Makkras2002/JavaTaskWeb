package com.makkras.shop.exception;

public class InteractionException extends Exception{
    public InteractionException() {
    }
    public InteractionException(String message) {
        super(message);
    }

    public InteractionException(String message, Throwable cause) {
        super(message, cause);
    }

    public InteractionException(Throwable cause) {
        super(cause);
    }
}

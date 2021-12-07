package com.makkras.shop.exception;

public class PoolCustomException extends Exception{
    public PoolCustomException() {
    }

    public PoolCustomException(String message) {
        super(message);
    }

    public PoolCustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public PoolCustomException(Throwable cause) {
        super(cause);
    }
}

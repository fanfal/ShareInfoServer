package com.au.shareinfoserver.model;

public class AuthException extends IllegalArgumentException {
    public AuthException() {
        super();
    }

    public AuthException(String s) {
        super(s);
    }

    public AuthException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthException(Throwable cause) {
        super(cause);
    }
}

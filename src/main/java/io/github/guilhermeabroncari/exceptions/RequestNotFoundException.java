package io.github.guilhermeabroncari.exceptions;

public class RequestNotFoundException extends RuntimeException {
    public RequestNotFoundException() {
        super("Request not found.");
    }
}

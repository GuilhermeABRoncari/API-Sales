package io.github.guilhermeabroncari.exceptions;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(){
        super("Incorrect password.");
    }
}

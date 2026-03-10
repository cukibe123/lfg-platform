package cukibe123.lfg_platform.identity.app.exception;

public class WrongCredentialsException extends RuntimeException {
    public WrongCredentialsException() {
        super("Wrong email or password");
    }
}
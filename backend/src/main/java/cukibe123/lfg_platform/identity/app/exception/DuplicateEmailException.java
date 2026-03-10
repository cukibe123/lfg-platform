package cukibe123.lfg_platform.identity.app.exception;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException() {
        super("Email already used");
    }
}

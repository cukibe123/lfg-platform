package cukibe123.lfg_platform.identity.app;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String email) {
        super("Email already used " + email);
    }
}

package cukibe123.lfg_platform.identity.app;

public interface PasswordHasher {
    
    public String hash(String rawPassword);

    boolean matches(String rawPassword, String hashedPassword);

}

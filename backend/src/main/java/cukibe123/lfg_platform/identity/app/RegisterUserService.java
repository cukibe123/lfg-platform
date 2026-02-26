package cukibe123.lfg_platform.identity.app;

import cukibe123.lfg_platform.identity.domain.User;
import cukibe123.lfg_platform.identity.domain.UserRepository;
import cukibe123.lfg_platform.identity.infra.security.BcryptPasswordHasher;


public class RegisterUserService {
    private final UserRepository userRepository;
    private final BcryptPasswordHasher hasher;

    public RegisterUserService(UserRepository userRepository, BcryptPasswordHasher hasher) {
        this.userRepository = userRepository;
        this.hasher = hasher;
    }

    public User execute(String email, String rawPassword) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalStateException("Email already used");
        }

        User user = new User();
        user.setEmail(email);
        user.setPasswordHash(hasher.hash(rawPassword));

        return user;
    }
}
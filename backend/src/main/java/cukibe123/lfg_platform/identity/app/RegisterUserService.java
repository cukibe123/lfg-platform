package cukibe123.lfg_platform.identity.app;

import org.springframework.stereotype.Service;
import cukibe123.lfg_platform.identity.domain.User;
import cukibe123.lfg_platform.identity.domain.UserRepository;
import cukibe123.lfg_platform.identity.infra.security.BcryptPasswordHasher;

@Service
public class RegisterUserService {
    private final UserRepository userRepository;
    private final PasswordHasher hasher;

    public RegisterUserService(UserRepository userRepository, BcryptPasswordHasher hasher) {
        this.userRepository = userRepository;
        this.hasher = hasher;
    }

    public User execute(String email, String rawPassword) {
        User user = new User();
        user.setEmail(email);
        user.setPasswordHash(hasher.hash(rawPassword));

        try {
            return userRepository.save(user);
        } catch (DuplicateEmailException ex) {
            throw new DuplicateEmailException(email);
        }
    }
}
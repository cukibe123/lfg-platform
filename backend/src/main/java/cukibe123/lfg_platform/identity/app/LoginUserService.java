package cukibe123.lfg_platform.identity.app;

import org.springframework.stereotype.Service;

import cukibe123.lfg_platform.identity.app.exception.WrongCredentialsException;
import cukibe123.lfg_platform.identity.domain.User;
import cukibe123.lfg_platform.identity.domain.UserRepository;

@Service
public class LoginUserService {

    public UserRepository userRepository;
    public PasswordHasher hasher;

    public LoginUserService(UserRepository userRepository, PasswordHasher hasher) {
        this.userRepository = userRepository;
        this.hasher = hasher;
    }

    public User verify(String email, String rawPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(WrongCredentialsException::new);

        if (!hasher.matches(rawPassword, user.getPasswordHash())) {
            throw new WrongCredentialsException();
        }

        return user;
    }

}

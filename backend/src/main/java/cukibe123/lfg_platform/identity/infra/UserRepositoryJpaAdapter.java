package cukibe123.lfg_platform.identity.infra;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import cukibe123.lfg_platform.identity.domain.User;
import cukibe123.lfg_platform.identity.domain.UserRepository;

@Repository
public class UserRepositoryJpaAdapter implements UserRepository {

    private final SpringDataUserRepository springData;

    public UserRepositoryJpaAdapter(SpringDataUserRepository springData) {
        this.springData = springData;
    }

    @Override
    public User save(User user) {
        return springData.save(user);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return springData.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return springData.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return springData.existsByEmail(email);
    }
}
package cukibe123.lfg_platform.identity.infra;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import cukibe123.lfg_platform.identity.domain.User;

interface SpringDataUserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
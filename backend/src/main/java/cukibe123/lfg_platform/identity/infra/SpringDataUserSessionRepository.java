package cukibe123.lfg_platform.identity.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import cukibe123.lfg_platform.identity.domain.UserSession;

import java.util.Optional;
import java.util.UUID;

interface SpringDataUserSessionRepository extends JpaRepository<UserSession, UUID> {
    Optional<UserSession> findByTokenHash(String tokenHash);
}
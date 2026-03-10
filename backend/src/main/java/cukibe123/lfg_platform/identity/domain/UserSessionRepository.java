package cukibe123.lfg_platform.identity.domain;

import java.util.Optional;
import java.util.UUID;

public interface UserSessionRepository {
    UserSession save(UserSession session);
    Optional<UserSession> findByTokenHash(String tokenHash);
    Optional<UserSession> findActiveByTokenHash(String tokenHash);
    void revokeByTokenHash(String tokenHash);
    void revokeAllByUserId(UUID userId);
}
package cukibe123.lfg_platform.identity.domain;

import java.util.Optional;

public interface UserSessionRepository {
    UserSession save(UserSession session);
    Optional<UserSession> findByTokenHash(String tokenHash);
}
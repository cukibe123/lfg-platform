package cukibe123.lfg_platform.identity.infra;

import java.util.Optional;

import cukibe123.lfg_platform.identity.domain.UserSession;
import cukibe123.lfg_platform.identity.domain.UserSessionRepository;

public class UserSessionJpaAdapter implements  UserSessionRepository {

    private SpringDataUserSessionRepository springData;  

    UserSessionJpaAdapter(SpringDataUserSessionRepository springData) {
        this.springData = springData;
    }

    @Override
    public UserSession save(UserSession session) {
        return springData.save(session);
    }
    
    @Override 
    public Optional<UserSession> findByTokenHash(String tokenHash) {
        return springData.findByTokenHash(tokenHash);
    }

}

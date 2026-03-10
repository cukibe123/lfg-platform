CREATE TABLE user_sessions (
    id UUID      PRIMARY KEY DEFAULT gen_random_uuid();
    user_id UUID NOT NULL references users(id) ON DELETE CASCADE; 
    created_at  TIMESTAMPTZ NOT NULL DEFAULT NOW();
    updated_at  TIMESTAMPTZ NOT NULL DEFAULT NOW()
    expires_at  TIMESTAMPTZ NOT NULL;
    revoke_at   TIMESTAMPTZ NOT NULL;
);

CREATE TRIGGER trg_user_sessions_updated_at
BEFORE UPDATE ON user_sessions
FOR EACH ROW
EXECUTE FUNCTION set_updated_at();
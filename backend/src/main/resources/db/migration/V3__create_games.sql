CREATE TABLE games (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    slug VARCHAR(50) NOT NULL UNIQUE,   
    name VARCHAR(100) NOT NULL,           
    created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);
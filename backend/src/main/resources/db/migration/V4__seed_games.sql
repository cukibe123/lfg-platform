INSERT INTO games(slug, name)
VALUES
    ('valorant', 'Valorant'),
    ('cs2', 'Counter-Strike 2')
ON CONFLICT (slug) DO NOTHING;

CREATE TYPE role AS ENUM ('admin', 'user');

ALTER TABLE users
    ADD COLUMN role role NOT NULL DEFAULT 'user',
    ADD COLUMN position_sharing BOOLEAN NOT NULL DEFAULT FALSE,
    ADD COLUMN date_of_birth DATE

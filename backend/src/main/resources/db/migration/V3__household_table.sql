CREATE TABLE IF NOT EXISTS households (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL CHECK ( char_length(trim(name)) != 0 ),
    additional_members INT NOT NULL DEFAULT 0 CHECK ( additional_members >= 0 )
);
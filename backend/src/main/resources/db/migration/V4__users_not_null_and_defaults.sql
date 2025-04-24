ALTER TABLE users
    ALTER COLUMN id SET DEFAULT gen_random_uuid(),
    ALTER COLUMN first_name SET NOT NULL,
    ALTER COLUMN last_name SET NOT NULL,
    ALTER COLUMN email SET NOT NULL,
    ALTER COLUMN password_hash SET NOT NULL;
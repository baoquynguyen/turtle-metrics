CREATE TABLE IF NOT EXISTS accounts (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    last_seen TIMESTAMP,
    amount NUMERIC(19,2),
    interest NUMERIC(19,2),
    deposit BOOLEAN,
    note TEXT
);

CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    account_id BIGINT UNIQUE,

    CONSTRAINT fk_user_account
        FOREIGN KEY(account_id)
        REFERENCES accounts(id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS items (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    amount NUMERIC(19,2) NOT NULL,
    type VARCHAR(10) CHECK (type IN ('INCOME','EXPENSE')),
    account_id BIGINT NOT NULL,

    CONSTRAINT fk_item_account
        FOREIGN KEY(account_id)
        REFERENCES accounts(id)
        ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_account_name ON accounts(name);
CREATE INDEX IF NOT EXISTS idx_item_account ON items(account_id);
CREATE INDEX IF NOT EXISTS idx_user_account ON users(account_id);

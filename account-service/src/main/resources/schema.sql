CREATE TABLE accounts (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    last_seen TIMESTAMP,
    saving_amount NUMERIC(19,2),
    saving_interest NUMERIC(19,2),
    saving_deposit BOOLEAN,
    note TEXT
);

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    account_id BIGINT UNIQUE,

    CONSTRAINT fk_user_account
        FOREIGN KEY(account_id)
        REFERENCES accounts(id)
        ON DELETE CASCADE
)

CREATE TABLE items (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    amount NUMERIC(19,2) NOT NULL,
    type VARCHAR(10) CHECK (type IN ('INCOME','EXPENSE')),
    account_id BIGINT NOT NULL,

    CONSTRAINT fk_item_account
        FOREIGN KEY(account_id)
        REFERENCES accounts(id)
        ON DELETE CASCADE
)

CREATE INDEX idx_account_name ON accounts(name)
CREATE INDEX idx_item_account ON items(account_id)
CREATE INDEX idx_user_account ON users(account_id)

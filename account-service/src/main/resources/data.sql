INSERT INTO accounts (
    id,
    name,
    last_seen,
    amount,
    interest,
    deposit,
    note
)
VALUES
    (
        1,
        'Marcus',
        NOW(),
        15000.00,
        4.50,
        true,
        'Primary investment account'
    ),
    (
        2,
        'Alex',
        NOW(),
        8200.00,
        3.20,
        false,
        'Travel savings account'
    );

INSERT INTO users (
    id,
    username,
    password,
    account_id
)
VALUES
    (
        1,
        'marcus_admin',
        '$2a$10$examplehashedpassword',
        1
    ),
    (
        2,
        'alex_user',
        '$2a$10$examplehashedpassword',
        2
    );

INSERT INTO items (
    id,
    title,
    amount,
    type,
    account_id
)
VALUES
    (
        1,
        'MacBook Pro 16',
        3200.00,
        'INCOME',
        1
    ),
    (
        2,
        'Mechanical Keyboard',
        180.00,
        'EXPENSE',
        2
    ),
    (
        3,
        'Monitor 27 inch',
        420.00,
        'EXPENSE',
        2
    ),
    (
        4,
        'Travel Backpack',
        95.00,
        'EXPENSE',
        1
    ),
    (
        5,
        'Sony WH-1000XM5',
        399.00,
        'INCOME',
        2
    );
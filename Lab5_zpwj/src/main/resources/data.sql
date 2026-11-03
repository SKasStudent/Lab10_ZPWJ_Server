INSERT INTO clients (name, surname, email, phone) VALUES
    ('Szymon','Kasperczuk','skasp@email.com','111111111'),
    ('Antoni','Kedra','akedr@email.com','222222222'),
    ('Napoleon','Bonaparte','Nbona@email.com','333333333')
ON CONFLICT (email) DO NOTHING;

INSERT INTO wood (species, price, quantitykg) VALUES
    ('Oak', 10.50, 99),
    ('Acacia', 20.20, 50),
    ('Birch', 5.99, 120)
ON CONFLICT (species) DO NOTHING;
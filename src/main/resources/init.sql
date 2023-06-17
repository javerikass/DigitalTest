CREATE SCHEMA IF NOT EXISTS player_storage;

CREATE TABLE IF NOT EXISTS player_storage.play_character
(
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(255),
    level    INTEGER NOT NULL,
    health   INTEGER NOT NULL,
    strength INTEGER NOT NULL,
    stamina  INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS player_storage.item
(
    id           BIGSERIAL PRIMARY KEY,
    name         VARCHAR(255),
    quantity     INTEGER          NOT NULL,
    weight       DOUBLE PRECISION NOT NULL,
    description  TEXT,
    price        DOUBLE PRECISION,
    character_id BIGINT,
    FOREIGN KEY (character_id)
        REFERENCES player_storage.play_character (id)
);

INSERT INTO player_storage.play_character (name, level, health, strength, stamina)
VALUES
    ('Warrior', 10, 100, 20, 50),
    ('Wizard', 8, 80, 10, 70),
    ('Rogue', 12, 120, 30, 40),
    ('Cleric', 6, 60, 5, 90),
    ('Paladin', 14, 140, 40, 60);

INSERT INTO player_storage.item (name, quantity, weight, description, price, character_id)
VALUES
    ('Sword', 1, 2.5, 'A sharp and deadly weapon', 50.0, 1),
    ('Potion', 5, 0.5, 'Restores health points', 10.0, 1),
    ('Wand', 1, 1.0, 'A magical wand for casting spells', 100.0, 2),
    ('Lockpick', 10, 0.1, 'Helps to open locked doors', 5.0, 3),
    ('Holy Symbol', 1, 0.5, 'A symbol of faith and divine power', 200.0, 4);

-- Создание таблицы Конвертации
CREATE TABLE IF NOT EXISTS Exchanges
(
    id                  SERIAL PRIMARY KEY,
    amount              INTEGER NOT NULL,
    exchange_from       VARCHAR(3)  NOT NULL,
    exchange_to         VARCHAR(3)  NOT NULL,
    new_currency_amount FLOAT       NOT NULL,
    user_id             INTEGER     NOT NULL REFERENCES Users (id)
);

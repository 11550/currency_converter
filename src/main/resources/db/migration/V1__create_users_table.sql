-- Создание таблицы Пользователи
CREATE TABLE IF NOT EXISTS Users
(
    id       SERIAL PRIMARY KEY,
    login    VARCHAR(45) NOT NULL,
    password VARCHAR(45) NOT NULL
);

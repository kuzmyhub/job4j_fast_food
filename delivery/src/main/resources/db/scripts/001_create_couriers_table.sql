CREATE TABLE couriers(
id SERIAL PRIMARY KEY,
name VARCHAR(50) NOT NULL
phone VARCHAR(11) NOT NULL UNIQUE,
);
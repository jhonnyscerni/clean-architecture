CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       username VARCHAR(50) NOT NULL,
                       email VARCHAR(50) NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       name VARCHAR(100) NOT NULL,
                       role VARCHAR(50) NOT NULL,
                       enabled BOOLEAN NOT NULL
);
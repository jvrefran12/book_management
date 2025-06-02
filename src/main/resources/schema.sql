CREATE SCHEMA IF NOT EXISTS books_schema;

CREATE TABLE IF NOT EXISTS books_schema.books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    description VARCHAR(1000),
    published_date DATE,
    is_available BOOLEAN,
    deleted_at DATE,
    is_deleted BOOLEAN
    is_available BOOLEAN
);
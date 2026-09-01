-- Esquema de base de datos para InventarioBiblioteca
-- Ejecutar contra una base PostgreSQL vacía (ej: CREATE DATABASE biblioteca;)

CREATE TABLE items (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    costo NUMERIC(10,2) NOT NULL,
    stock INTEGER NOT NULL,
    disponibles INTEGER NOT NULL
);

CREATE TABLE libros (
    item_id INTEGER PRIMARY KEY REFERENCES items(id) ON DELETE CASCADE,
    autor VARCHAR(255) NOT NULL,
    genero VARCHAR(100)
);

CREATE TABLE revistas (
    item_id INTEGER PRIMARY KEY REFERENCES items(id) ON DELETE CASCADE,
    num_edicion INTEGER NOT NULL,
    periodicidad VARCHAR(50)
);

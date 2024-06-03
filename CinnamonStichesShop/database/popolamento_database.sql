DROP DATABASE IF EXISTS CinnamonStichesShop;
CREATE DATABASE CinnamonStichesShop;

DROP TABLE IF EXISTS utente;
CREATE TABLE utente (
    cod_ut INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    cognome VARCHAR(255) NOT NULL,
    username VARCHAR(15) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    pass VARCHAR(128) NOT NULL,
    isAdmin BOOLEAN DEFAULT false,
    via VARCHAR(255) NOT NULL,
    citta VARCHAR(255) NOT NULL,
    cap VARCHAR(10) NOT NULL 
);

DROP TABLE IF EXISTS prodotto;
CREATE TABLE prodotto (
    codice INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    costo DECIMAL(10, 2) NOT NULL,
    descrizione TEXT,
    isDisponibile BOOLEAN NOT NULL
);

DROP TABLE IF EXISTS ordine;
CREATE TABLE ordine (
    codice INT PRIMARY KEY AUTO_INCREMENT,
    data DATE NOT NULL,
    stato VARCHAR(255) NOT NULL,
    tot DECIMAL(10, 2) NOT NULL,
    quantità INT NOT NULL,
    ind_spedizione VARCHAR(255) NOT NULL,
    utente_cod INT,
    FOREIGN KEY (utente_cod) REFERENCES utente(cod_ut) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS preferiti;
CREATE TABLE preferiti (
    id INT PRIMARY KEY AUTO_INCREMENT,
    idUtente INT,
    idProdotto INT,
    FOREIGN KEY (idUtente) REFERENCES utente(cod_ut) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idProdotto) REFERENCES prodotto(codice) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS immagine;
CREATE TABLE immagine (
    id INT PRIMARY KEY AUTO_INCREMENT,
    immagine BLOB NOT NULL,
    prodotto_Codice INT,
    FOREIGN KEY (prodotto_Codice) REFERENCES prodotto(codice) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS categoria;
CREATE TABLE categoria (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    descrizione TEXT
);

DROP TABLE IF EXISTS Contiene;
CREATE TABLE Contiene (
    ordine_Codice INT,
    prodotto_Codice INT,
    quantità INT,
    PRIMARY KEY (ordine_Codice, prodotto_Codice),
    FOREIGN KEY (ordine_Codice) REFERENCES ordine(codice) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (prodotto_Codice) REFERENCES prodotto(codice) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS Salva;
CREATE TABLE Salva (
    utente_cod_ut INT,
    preferiti_id INT,
    PRIMARY KEY (utente_cod_ut, preferiti_id),
    FOREIGN KEY (utente_cod_ut) REFERENCES utente(cod_ut) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (preferiti_id) REFERENCES preferiti(id) ON DELETE CASCADE ON UPDATE CASCADE
);
USE CinnamonStichesShop;
LOCK TABLES utente WRITE, prodotto WRITE, ordine WRITE, preferiti WRITE, immagine WRITE, categoria WRITE, Contiene WRITE, Salva WRITE;

INSERT INTO utente (cod_ut, pass, username, email, isAdmin, nome, cognome, via, citta, cap) VALUES
(1, SHA2('admin', 512), 'admin', 'user1@example.com', 1, 'Admin', 'Rossi', 'Via Roma', 'Milano', '20100'),
(2, SHA2('user', 512), 'user', 'user2@example.com', 0, 'User', 'Bianchi', 'Via Milano', 'Torino', '10100');

INSERT INTO prodotto (nome, costo, descrizione, isDisponibile) VALUES
('Prodotto1', 19.99, 'Descrizione prodotto 1', 1),
('Prodotto2', 29.99, 'Descrizione prodotto 2', 1);

INSERT INTO ordine (data, stato, tot, quantità, ind_spedizione, utente_cod) VALUES
('2024-06-01', 'In lavorazione', 49.98, 2, 'Via Roma 1, Milano', 1);

INSERT INTO preferiti (idUtente, idProdotto) VALUES
(1, 1),
(1, 2);

-- Note: Adjust the paths to actual image paths
INSERT INTO immagine (immagine, prodotto_Codice) VALUES
(LOAD_FILE('path_to_image1.jpg'), 1),
(LOAD_FILE('path_to_image2.jpg'), 2);

INSERT INTO categoria (nome, descrizione) VALUES
('Categoria1', 'Descrizione categoria 1'),
('Categoria2', 'Descrizione categoria 2');

INSERT INTO Contiene (ordine_Codice, prodotto_Codice, quantità) VALUES
(1, 1, 1),
(1, 2, 1);

INSERT INTO Salva (utente_cod_ut, preferiti_id) VALUES
(1, 1),
(1, 2);

UNLOCK TABLES;

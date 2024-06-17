DROP DATABASE IF EXISTS CinnamonStichesShop;
CREATE DATABASE CinnamonStichesShop;
USE CinnamonStichesShop;
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
USE CinnamonStichesShop;
DROP TABLE IF EXISTS categoria;
CREATE TABLE categoria(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    descrizione TEXT
);
USE CinnamonStichesShop;
DROP TABLE IF EXISTS prodotto;
CREATE TABLE prodotto (
    codice INT PRIMARY KEY  AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    costo DECIMAL(10, 2) NOT NULL,
    descrizione TEXT,
    categoria INT NOT NULL,
    isDisponibile BOOLEAN NOT NULL,
    immagine TEXT NOT NULL,
    FOREIGN KEY (categoria) REFERENCES categoria(id) ON DELETE CASCADE ON UPDATE CASCADE 
);
USE CinnamonStichesShop;
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
USE CinnamonStichesShop;
DROP TABLE IF EXISTS preferiti;
CREATE TABLE preferiti (
    id INT PRIMARY KEY AUTO_INCREMENT,
    idUtente INT,
    idProdotto INT,
    FOREIGN KEY (idUtente) REFERENCES utente(cod_ut) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idProdotto) REFERENCES prodotto(codice) ON DELETE CASCADE ON UPDATE CASCADE
);

USE CinnamonStichesShop;
DROP TABLE IF EXISTS Contiene;
CREATE TABLE Contiene (
    codiceOrdine INT,
    idProdotto INT,
    quantità INT,
    PRIMARY KEY (codiceOrdine, idProdotto),
    FOREIGN KEY (codiceOrdine) REFERENCES ordine(codice) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idProdotto) REFERENCES prodotto(codice) ON DELETE CASCADE ON UPDATE CASCADE
);
USE CinnamonStichesShop;
DROP TABLE IF EXISTS Salva;
CREATE TABLE Salva (
    idUtente INT,
    idProdotto INT,
    PRIMARY KEY (idUtente, idProdotto),
    FOREIGN KEY (idUtente) REFERENCES utente(cod_ut) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (idProdotto) REFERENCES preferiti(idProdotto) ON DELETE CASCADE ON UPDATE CASCADE
);
USE CinnamonStichesShop;
LOCK TABLES utente WRITE, prodotto WRITE, ordine WRITE, preferiti WRITE, categoria WRITE, Contiene WRITE, Salva WRITE;

INSERT INTO utente (cod_ut, pass, username, email, isAdmin, nome, cognome, via, citta, cap) VALUES
(1, SHA2('admin', 512), 'admin', 'user1@example.com', 1, 'Marco', 'Rossi', 'Via Roma', 'Milano', '20100'),
(2, SHA2('user', 512), 'user', 'user2@example.com', 0, 'Luca', 'Bianchi', 'Via Milano', 'Torino', '10100');
INSERT INTO categoria (nome, descrizione) VALUES
('Collezione estiva', 'Prodotti handmade adatti alla stagione estiva'),
('Collezione invernale', 'Prodotti handmade adatti alla stagione invernale');
INSERT INTO prodotto (nome, costo, descrizione, isDisponibile,categoria,immagine) VALUES
('Bikini ', 19.99, 'Modello unico , con filato stretto, fatto ai ferri.', true,1,'costume.jpg'),
('Cappello in pura lana', 29.99, 'Cappello in pura lana italiana, filato a maglia', true,2,'cappello_lana.jpg'),
('Cardigan ai ferri', 59.99, 'Cardigan di caldo cotone, lavorato ai ferri', true,2,'cardigan.jpg');

INSERT INTO ordine (data, stato, tot, quantità, ind_spedizione, utente_cod) VALUES
('2024-06-01', 'In lavorazione', 49.98, 2, 'Via Roma 1, Milano', 1);

INSERT INTO preferiti (idUtente, idProdotto) VALUES
(2, 1),
(2, 2);

INSERT INTO Contiene (codiceOrdine, idProdotto, quantità) VALUES
(1, 1, 1),
(1, 2, 1);

INSERT INTO Salva (idUtente, idProdotto) VALUES
(1, 1),
(1, 2);
categoria
UNLOCK TABLES ;

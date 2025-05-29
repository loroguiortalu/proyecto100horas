-- ------------------------------------------
-- database for Alquilaria project
-- ------------------------------------------

-- Creation (and drop just in case)
DROP DATABASE IF EXISTS alquilariadb;
CREATE DATABASE alquilariadb;
USE alquilariadb;
-- -----------------------------------------




-- Creation of  table to store house types with translations
DROP TABLE IF EXISTS housetype;
CREATE TABLE housetype (
    id INT AUTO_INCREMENT PRIMARY KEY,
    wenglish VARCHAR(50) NOT NULL,
    wspanish VARCHAR(50) NOT NULL
);

INSERT INTO housetype (wenglish, wspanish) VALUES ('house', 'casa'); -- 1
INSERT INTO housetype (wenglish, wspanish) VALUES ('apartment', 'apartamento'); -- 2
INSERT INTO housetype (wenglish, wspanish) VALUES ('atic', 'ático'); -- 3
-- you have also to insert this options, also you could add more



-- Creation of  table to store contract statuses with translations
DROP TABLE IF EXISTS contractstatus;
CREATE TABLE contractstatus (
    id INT AUTO_INCREMENT PRIMARY KEY,
    wenglish VARCHAR(50) NOT NULL,
    wspanish VARCHAR(50) NOT NULL
);

INSERT INTO contractstatus (wenglish, wspanish) VALUES ('active', 'activo'); -- 1
INSERT INTO contractstatus (wenglish, wspanish) VALUES ('expired', 'vencido'); -- 2
INSERT INTO contractstatus (wenglish, wspanish) VALUES ('pending', 'pendiente'); -- 3






-- Creation of PropertyOwner table 
DROP TABLE IF EXISTS owner;
CREATE TABLE owner (
    dni VARCHAR(10) NOT NULL UNIQUE,
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    phonenumber VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL
);



-- Creation of House table 
DROP TABLE IF EXISTS house;
CREATE TABLE house (
    address VARCHAR(200) NOT NULL,
    rent INT NOT NULL,
    surface INT NOT NULL,
    description VARCHAR(1000) NOT NULL DEFAULT 'sin descripcion',
    allowsPets BOOLEAN NOT NULL,
    code VARCHAR(50) PRIMARY KEY NOT NULL,
    housetyp INT NOT NULL,
    id_owner INT NOT NULL,
    FOREIGN KEY fk_idowner (id_owner) REFERENCES owner(id),
    FOREIGN KEY fk_houset (housetyp) REFERENCES housetype(id)
);



-- Creation of Tenant table 
DROP TABLE IF EXISTS tenant;
CREATE TABLE tenant (
    dni VARCHAR(10) NOT NULL,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    phonenumber VARCHAR(50) NOT NULL,
    haspets BOOLEAN NOT NULL,
    id INT AUTO_INCREMENT PRIMARY KEY
);



-- Creation of Contract table, wich in theory it is not an entity, therefore, primary key is not needed
DROP TABLE IF EXISTS contract;
CREATE TABLE contract (
    initdate DATE NOT NULL,
    enddate DATE NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    status INT NOT NULL,
    code_house VARCHAR(50) NOT NULL,
    id_tenant INT NOT NULL,
    PRIMARY KEY (code_house, id_tenant),
    FOREIGN KEY fk_contractstatus (status) REFERENCES contractstatus(id),
    FOREIGN KEY fk_codehouse (code_house) REFERENCES house(code),
    FOREIGN KEY fk_idtenant (id_tenant) REFERENCES tenant(id)
);







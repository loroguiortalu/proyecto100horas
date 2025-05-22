--------------------------------------------
-- database for Alquilaria project
--------------------------------------------

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
    wspanish VARCHAR(50) NOT NULL,
);

INSERT INTO housetype (wenglish, wspanish) VALUES ('house', 'casa'); -- 1
INSERT INTO housetype (wenglish, wspanish) VALUES ('apartment', 'apartamento'); -- 2
INSERT INTO housetype (wenglish, wspanish) VALUES ('atic', 'ático'); -- 3


-- Creation of  table to store contract statuses with translations
DROP TABLE IF EXISTS contractstatus;
CREATE TABLE contractstatus (
    id INT AUTO_INCREMENT PRIMARY KEY,
    wenglish VARCHAR(50) NOT NULL,
    wspanish VARCHAR(50) NOT NULL,
);

INSERT INTO housetype (wenglish, wspanish) VALUES ('active', 'activo'); -- 1
INSERT INTO housetype (wenglish, wspanish) VALUES ('expired', 'vencido'); -- 2
INSERT INTO housetype (wenglish, wspanish) VALUES ('pending', 'pendiente'); -- 3




-- Creation of PropertyOwner table 
DROP TABLE IF EXISTS powner;
CREATE TABLE powner (
    dni VARCHAR(10),
    id INT AUTO_INCREMENT,
    namee VARCHAR(50) NOT NULL,
    phonenumber VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    PRIMARY KEY(dni, id)
);


-- Creation of House table 
DROP TABLE IF EXISTS house;
CREATE TABLE house (
    addresss VARCHAR(200),
    mrent DECIMAL(10,2),
    surface INT,
    descriptionn VARCHAR(1000),
    allowsPets BOOLEAN,
    code VARCHAR(50) PRIMARY KEY,
    houset INT,
    id_owner INT,
    FOREIGN KEY fk_idowner (id_owner) REFERENCES powner(id),
    FOREIGN KEY fk_houset (houset) REFERENCES housetype(id)
);



-- Creation of Tenant table 
DROP TABLE IF EXISTS tenant;
CREATE TABLE tenant (
    dni VARCHAR(10),
    namee VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    phonenumber VARCHAR(50) NOT NULL,
    haspets BOOLEAN,
    id INT AUTO_INCREMENT
);



-- Creation of Contract table, wich in theory it is not an entity, therefore, primary key is not needed
DROP TABLE IF EXISTS contractt;
CREATE TABLE contractt (
    initdate DATE,
    enddate DATE,
    mprice DECIMAL(10,2),
    statuses INT,
    FOREIGN KEY fk_contractstatus (contractstatus) REFERENCES contractstatus(id)

);







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
    description VARCHAR(1000) NOT NULL DEFAULT 'no description',
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



-- triggers for the dni's to be always in uppercase
DELIMITER //
CREATE TRIGGER tr_before_insert_owner
BEFORE INSERT ON owner
FOR EACH ROW
BEGIN
    SET NEW.dni = UPPER(NEW.dni);
END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER tr_before_insert_tenant
BEFORE INSERT ON tenant
FOR EACH ROW
BEGIN
    SET NEW.dni = UPPER(NEW.dni);
END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER tr_before_modify_owner
BEFORE MODIFY ON owner
FOR EACH ROW
BEGIN
    SET NEW.dni = UPPER(NEW.dni);
END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER tr_before_modify_tenant
BEFORE MODIFY ON tenant
FOR EACH ROW
BEGIN
    SET NEW.dni = UPPER(NEW.dni);
END;
//
DELIMITER ;



-- I could have done like this the modification, but I prefered to use PreparedStatements
DELIMITER //
CREATE PROCEDURE sp_modificar_vivienda(
    IN v_address VARCHAR(200),
    IN v_rent INT,
    IN v_surface INT,
    IN v_description VARCHAR(1000),
    IN v_allowsPets BOOLEAN,
    IN v_code VARCHAR(50),
    IN v_housetyp INT,
    IN v_id_owner INT,
    OUT v_boo BOOLEAN
)
BEGIN
    DECLARE aa INT DEFAULT 0;

    UPDATE house SET address = v_address WHERE code = v_code;
    SET aa = aa + ROW_COUNT();-- long life ROW_COUNT()

    UPDATE house SET rent = v_rent WHERE code = v_code;
    SET aa = aa + ROW_COUNT();

    UPDATE house SET surface = v_surface WHERE code = v_code;
    SET aa = aa + ROW_COUNT();

    UPDATE house SET description = v_description WHERE code = v_code;
    SET aa = aa + ROW_COUNT();

    UPDATE house SET allowsPets = v_allowsPets WHERE code = v_code;
    SET aa = aa + ROW_COUNT();

    UPDATE house SET housetyp = v_housetyp WHERE code = v_code;
    SET aa = aa + ROW_COUNT();

    UPDATE house SET id_owner = v_id_owner WHERE code = v_code;
    SET aa = aa + ROW_COUNT();

    SET v_boo = aa > 0;
END //

DELIMITER ;



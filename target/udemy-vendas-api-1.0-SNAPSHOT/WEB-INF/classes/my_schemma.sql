CREATE TABLE CLIENT (
    ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(100),
    CPF VARCHAR(11)
);
CREATE TABLE PRODUCT (
    ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    DESCRIPTION VARCHAR(255),
    UNITARY_VALUE DECIMAL(20, 2)
);
CREATE TABLE REQUEST (
    ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    CLIENT_ID BIGINT REFERENCES CLIENT (ID),
    REQUEST_DATE TIMESTAMP,
    STATUS VARCHAR(20),
    AMOUNT NUMERIC(20, 2)
);
CREATE TABLE ITEM_REQUEST (
    ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    REQUEST_ID BIGINT REFERENCES REQUEST (ID),
    PRODUCT_ID BIGINT REFERENCES PRODUCT (ID),
    AMOUNT NUMERIC(20, 2)
);
CREATE TABLE USERLOGIN (
    ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    LOGIN VARCHAR(50) NOT NULL UNIQUE,
    PASSWORD VARCHAR(255) NOT NULL,
    ADMIN BOOL DEFAULT FALSE
);
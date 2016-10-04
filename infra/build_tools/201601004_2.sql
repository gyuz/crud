CREATE TABLE PERSON(
    ID INT DEFAULT NEXTVAL('PERSON_ID_SEQ') PRIMARY KEY NOT NULL,
    FIRST_NAME VARCHAR(30) NOT NULL, 
    LAST_NAME VARCHAR(15) NOT NULL, 
    MIDDLE_NAME VARCHAR(15), 
    STREET VARCHAR(50), 
    BRGY INT,
    CITY VARCHAR(20),
    ZIP VARCHAR(6),
    BIRTHDAY DATE NOT NULL,
    GWA DECIMAL(3,2),
    DATE_HIRED DATE,
    EMPLOYED YES_NO NOT NULL,
    TITLE TYP_TITLE NOT NULL
);

CREATE TABLE ROLES(
    ROLE_ID INT DEFAULT NEXTVAL('ROLE_ID_SEQ') PRIMARY KEY NOT NULL,
    ROLE_NAME VARCHAR(20) NOT NULL
);

CREATE TABLE PERSON_ROLES(
    PERSON_ID INT REFERENCES PERSON(ID) ON DELETE RESTRICT NOT NULL, 
    ROLE_ROLE_ID INT REFERENCES ROLES(ROLE_ID) ON DELETE RESTRICT NOT NULL
);

CREATE TABLE CONTACTS(
    PERSON_ID INT REFERENCES PERSON(ID) ON DELETE RESTRICT NOT NULL,  
    CONTACT_ID INT DEFAULT NEXTVAL('CONTACT_SEQ') PRIMARY KEY NOT NULL,
    CONTACT_TYPE TYP_CONTACT NOT NULL, 
    CONTACT_DETAILS VARCHAR(30) NOT NULL,
    UNIQUE (PERSON_ID, CONTACT_TYPE, CONTACT_DETAILS)
);

GRANT ALL PRIVILEGES ON TABLE ROLES TO ecc;
GRANT ALL PRIVILEGES ON TABLE CONTACTS TO ecc;
GRANT ALL PRIVILEGES ON TABLE PERSON TO ecc;

--ADD INDEX
CREATE UNIQUE INDEX ROLE_NAME_IDX ON ROLES(ROLE_NAME); 

--ADD CONSTRAINTS
ALTER TABLE PERSON_ROLES ADD CONSTRAINT FK_PERSON_ROLE (ROLE_ROLE_ID, PERSON_ID);

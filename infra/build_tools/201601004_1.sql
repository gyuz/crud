CREATE USER ecc PASSWORD 'ex1stgl0bal';

CREATE DATABASE HR;
GRANT ALL PRIVILEGES ON DATABASE HR TO ecc;

/*in HR DATABASE*/
--SEQUENCES
CREATE SEQUENCE PERSON_ID_SEQ START 1000000000;
CREATE SEQUENCE ROLE_ID_SEQ;
CREATE SEQUENCE CONTACT_SEQ;

GRANT ALL PRIVILEGES ON SEQUENCE PERSON_ID_SEQ TO ecc;
GRANT ALL PRIVILEGES ON SEQUENCE ROLE_ID_SEQ TO ecc;
GRANT ALL PRIVILEGES ON SEQUENCE CONTACT_SEQ TO ecc;

--ENUMS
CREATE TYPE TYP_TITLE as ENUM ('MR', 'MS', 'MRS', 'DR', 'JR', 'SR');
CREATE TYPE TYP_CONTACT AS ENUM ('LANDLINE', 'MOBILE', 'EMAIL'); 
CREATE TYPE YES_NO AS ENUM ('Y', 'N');

GRANT ALL PRIVILEGES ON TYPE TYP_TITLE TO ecc;
GRANT ALL PRIVILEGES ON TYPE TYP_CONTACT TO ecc;
GRANT ALL PRIVILEGES ON TYPE YES_NO TO ecc;
--TEST QUERIES
INSERT INTO PERSON (
FIRST_NAME, 
LAST_NAME, 
MIDDLE_NAME, 
TITLE, 
STREET, 
BRGY, 
CITY, 
ZIP, 
BIRTH_DATE, 
GWA, 
DATE_HIRED, 
EMPLOYED
) VALUES (
'TEST', 
'LAST',
'MIDDLE', 
'MR',
'123 street', 
204, 
'Manila', 
'1003', 
'1994-10-16', 
1.93, 
'2016-10-04',
'Y');

SELECT * FROM PERSON;

INSERT INTO ROLES (ROLE_NAME) VALUES ('ADMIN'),('DEV'),('QA'),('BA');

SELECT * FROM ROLES;

INSERT INTO PERSON_ROLES (ROLE_ROLE_ID, PERSON_ID) VALUES (3, 1000000003);

SELECT * FROM PERSON_ROLES;

INSERT INTO CONTACTS (
PERSON_ID, 
CONTACT_TYPE, 
CONTACT_EMAIL
) VALUES (
1000000003,
'EMAIL',
'email@test.com');

INSERT INTO CONTACTS (
PERSON_ID, 
CONTACT_TYPE, 
CONTACT_NUMBER
) VALUES (
1000000003,
'MOBILE',
'12345679012');

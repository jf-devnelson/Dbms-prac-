CREATE DATABASE  Student;
USE Student;
CREATE TABLE stDetails (
    admNo INT PRIMARY KEY,
    Name VARCHAR(100),
    Gender VARCHAR(10),
    Address VARCHAR(100),
    Town VARCHAR(50),
    class VARCHAR(20)
);

CREATE TABLE stMarks (
    admNo INT,
    Maths INT,
    Biology INT,
    Chemistry INT,
    Physics INT
);

INSERT INTO stDetails (admNo, Name, Gender, Address, Town, class) VALUES (2000, 'Janet', 'F', 'P.o. Box 100', 'Kitale', 'F4 South');
INSERT INTO stDetails (admNo, Name, Gender, Address, Town, class) VALUES (2001, 'Tabutany', 'F', 'P.o. Box 78', 'Eldoret', 'F4 North');
INSERT INTO stDetails (admNo, Name, Gender, Address, Town, class) VALUES (2002, 'Were', 'M', 'P.o. Box 201', 'Nakuru', 'F4 West');
INSERT INTO stDetails (admNo, Name, Gender, Address, Town, class) VALUES (2003, 'Mambu', 'M', 'P.o. Box 43', 'Eldoret', 'F4 East');
INSERT INTO stDetails (admNo, Name, Gender, Address, Town, class) VALUES (2004, 'Mercy', 'F', 'P.o. Box 20', 'Nakuru', 'F4 East');

INSERT INTO stMarks (admNo, Maths, Biology, Chemistry, Physics) VALUES (2000, 56, 45, 73, 66);
INSERT INTO stMarks (admNo, Maths, Biology, Chemistry, Physics) VALUES (2001, 87, 54, 80, 55);
INSERT INTO stMarks (admNo, Maths, Biology, Chemistry, Physics) VALUES (2002, 90, 78, 85, 74);
INSERT INTO stMarks (admNo, Maths, Biology, Chemistry, Physics) VALUES (2003, 87, 67, 83, 62);
INSERT INTO stMarks (admNo, Maths, Biology, Chemistry, Physics) VALUES (2004, 54, 72, 70, 68);

ALTER TABLE stMarks ADD Total INT;

UPDATE stMarks SET Total = Maths + Biology + Chemistry + Physics;

SELECT stDetails.admNo, stDetails.Name, stMarks.Maths 
FROM stDetails 
INNER JOIN stMarks ON stDetails.admNo = stMarks.admNo 
WHERE stMarks.Maths > 80;

CREATE INDEX idx_admNo ON stDetails(admNo);

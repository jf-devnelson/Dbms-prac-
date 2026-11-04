CREATE TABLE pDetails (
    patNo INT PRIMARY KEY,
    name VARCHAR(50),
    gender VARCHAR(10),
    residence VARCHAR(50),
    age INT
);

CREATE TABLE pAilments (
    patNo INT,
    ailment VARCHAR(50),
    status VARCHAR(20)
);

CREATE TABLE pTreatment (
    patNo INT,
    treatment VARCHAR(100),
    doctor VARCHAR(50),
    treatmentDate DATE
);

INSERT INTO pDetails (patNo, name, gender, residence, age) VALUES (4550, 'Neshe', 'M', 'Umoja', 34);
INSERT INTO pDetails (patNo, name, gender, residence, age) VALUES (4567, 'Mengo', 'F', 'Kajiado', 50);
INSERT INTO pDetails (patNo, name, gender, residence, age) VALUES (7894, 'Wira', 'F', 'Athi River', 45);
INSERT INTO pDetails (patNo, name, gender, residence, age) VALUES (5434, 'Elijah', 'F', 'Nakuru', 23);
INSERT INTO pDetails (patNo, name, gender, residence, age) VALUES (3233, 'Mawingu', 'M', 'Limuru', 12);

INSERT INTO pAilments (patNo, ailment, status) VALUES (4550, 'Malaria', 'severe');
INSERT INTO pAilments (patNo, ailment, status) VALUES (4567, 'Typhoid', 'Mild');
INSERT INTO pAilments (patNo, ailment, status) VALUES (7894, 'TB', 'Mild');
INSERT INTO pAilments (patNo, ailment, status) VALUES (5434, 'Malaria', 'severe');
INSERT INTO pAilments (patNo, ailment, status) VALUES (3233, 'TB', 'severe');

INSERT INTO pTreatment (patNo, treatment, doctor, treatmentDate) VALUES (4550, 'Antimalarial Drugs', 'Dr. Kamau', '2026-05-01');
INSERT INTO pTreatment (patNo, treatment, doctor, treatmentDate) VALUES (4567, 'Antibiotics', 'Dr. Amina', '2026-05-03');
INSERT INTO pTreatment (patNo, treatment, doctor, treatmentDate) VALUES (7894, 'TB Medication', 'Dr. Otieno', '2026-05-04');
INSERT INTO pTreatment (patNo, treatment, doctor, treatmentDate) VALUES (5434, 'Antimalarial Injection', 'Dr. Kamau', '2026-05-05');
INSERT INTO pTreatment (patNo, treatment, doctor, treatmentDate) VALUES (3233, 'TB Therapy', 'Dr. Njeri', '2026-05-06');

SELECT pDetails.patNo, pDetails.name, pDetails.gender, pDetails.residence, pDetails.age 
FROM pDetails 
INNER JOIN pAilments ON pDetails.patNo = pAilments.patNo 
WHERE pDetails.gender = 'M' AND pDetails.residence = 'Umoja' AND pAilments.ailment = 'Malaria';

SELECT pDetails.patNo, pDetails.name, pDetails.gender, pDetails.residence, pDetails.age 
FROM pDetails 
INNER JOIN pAilments ON pDetails.patNo = pAilments.patNo 
WHERE pDetails.gender = 'F' AND pAilments.ailment = 'Typhoid';

SELECT DISTINCT pDetails.patNo, pDetails.name, pDetails.gender, pDetails.residence, pDetails.age 
FROM pDetails 
INNER JOIN pAilments ON pDetails.patNo = pAilments.patNo 
WHERE pAilments.ailment = 'Malaria' OR pAilments.ailment = 'Typhoid';

SELECT pDetails.patNo, pDetails.name, pDetails.gender, pDetails.residence, pDetails.age 
FROM pDetails 
INNER JOIN pAilments ON pDetails.patNo = pAilments.patNo 
WHERE pAilments.ailment = 'TB' AND pAilments.status = 'severe';

SELECT * FROM pDetails ORDER BY age DESC;

SELECT pDetails.patNo, pDetails.name, pAilments.ailment, pAilments.status 
FROM pDetails 
INNER JOIN pAilments ON pDetails.patNo = pAilments.patNo;

SELECT 
    pDetails.name AS patient_name, 
    pTreatment.treatment AS treatment, 
    pTreatment.doctor AS doctor_name, 
    pTreatment.treatmentDate AS treatment_date 
FROM pDetails 
INNER JOIN pTreatment ON pDetails.patNo = pTreatment.patNo;

CREATE INDEX idx_patNo ON pDetails(patNo);

ALTER TABLE pDetails ADD CONSTRAINT trg_patient_age CHECK (age >= 1);

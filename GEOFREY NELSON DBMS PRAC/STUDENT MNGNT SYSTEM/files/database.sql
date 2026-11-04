-- database.sql
-- Run this script in MySQL (e.g. via MySQL Workbench or the mysql CLI)
-- before running the Java application.

CREATE DATABASE IF NOT EXISTS student_management;

USE student_management;

CREATE TABLE IF NOT EXISTS students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    course VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(20)
);

-- Optional: sample data to test the application immediately
INSERT INTO students (name, age, course, email, phone) VALUES
('John Doe', 20, 'Computer Science', 'john.doe@example.com', '0712345678'),
('Jane Smith', 22, 'Information Technology', 'jane.smith@example.com', '0723456789'),
('Amina Hassan', 21, 'Software Engineering', 'amina.hassan@example.com', '0734567890');

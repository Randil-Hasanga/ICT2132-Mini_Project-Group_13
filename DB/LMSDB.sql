CREATE DATABASE if not exists LMSDB;

USE LMSDB;
CREATE USER 'lecturer'@'localhost' IDENTIFIED BY 'lecturer123';

-- admin table

CREATE TABLE if not exists Addmin
(
	User_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    FName VARCHAR(15),
    LName VARCHAR(15),
	Gender ENUM('Male','Female'),
	Address_L1 VARCHAR(50),
    Address_L2 VARCHAR(50),
    DOB DATE,
    Email VARCHAR(50),
    Pro_pic blob,
    Password VARCHAR(20),
    Role_ VARCHAR(20)
);

-- student table

CREATE TABLE if not exists Student
(
	User_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    FName VARCHAR(15),
    LName VARCHAR(15),
	Gender ENUM('Male','Female'),
	Address_L1 VARCHAR(50),
    Address_L2 VARCHAR(50),
    DOB DATE,
    Email VARCHAR(50),
    Pro_pic blob,
    S_type VARCHAR(10),
    Password VARCHAR(20),
    Lecturer_id int,
    FOREIGN KEY (Lecturer_id) REFERENCES Lecturer(User_id)
);

INSERT INTO Student
(User_id, Fname, Lname, Gender, Address_L1, Address_L2, DOB, Email, Pro_pic, S_type, Password, Lecturer_id)
VALUES
(1,'Vinod','Kavinda', 'Male', 'Galle road', 'Matara', '2000-02-15', 'vinod@gmail.com', null, 'Undergraduate','1234',1);

-- lecturer table

CREATE TABLE if not exists Lecturer
(
	User_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    FName VARCHAR(15),
    LName VARCHAR(15),
	Gender ENUM('Male','Female'),
	Address_L1 VARCHAR(50),
    Address_L2 VARCHAR(50),
    DOB DATE,
    Email VARCHAR(50),
    Pro_pic blob,
    Password VARCHAR(20),
    Position VARCHAR(20)
);

CREATE TABLE if not exists Technical_officer
(
	User_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    Dep_id int not null,
    FName VARCHAR(15),
    LName VARCHAR(15),
	Gender ENUM('Male','Female'),
	Address_L1 VARCHAR(50),
    Address_L2 VARCHAR(50),
    DOB DATE,
    Email VARCHAR(50),
    Pro_pic blob,
    Password VARCHAR(20),
    foreign key (Dep_id) references Department (Dep_id)
);

-- department table

CREATE TABLE if not exists Department
(
	Dep_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Dep_Name VARCHAR(30)
    
);
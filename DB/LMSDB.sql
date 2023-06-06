CREATE DATABASE if not exists LMSDB;

USE LMSDB;

-- admin table

CREATE TABLE if not exists Admin
(
	User_id VARCHAR(10) NOT NULL PRIMARY KEY,
    FName VARCHAR(15),
    LName VARCHAR(15),
	Gender ENUM('Male','Female'),
	Address_L1 VARCHAR(50),
    Address_L2 VARCHAR(50),
    DOB DATE,
    Email VARCHAR(50),
    Pro_pic blob,
    Password VARCHAR(500)
);

INSERT INTO Admin
(User_id,FName,LName,Gender,Address_L1,Address_L2,DOB,Email,Pro_pic,Password)
VALUES
('A100','Uthpala','Kumari','Female','Deiyandara','Matara','1988-02-11','uthpala@gmail.com',null,'2345'),
('A200','Wasantha','Kumara','Male','Bomiriya','Kaduwela','1989-01-14','wasantha@gmail.com',null,'3455'),
('A300','Nilantha','Bandara','Male','Palmadulla','Rathnapura','1984-05-17','nilantha@gmail.com',null,'3345'),
('A400','Vinod','Deshan','Male','Malawa','Kuruwita','1985-02-18','deshan@gmail.com',null,'2667');

-- lecturer table

CREATE TABLE if not exists Lecturer
(
	User_id VARCHAR(10) NOT NULL PRIMARY KEY,
    FName VARCHAR(15),
    LName VARCHAR(15),
	Gender ENUM('Male','Female'),
	Address_L1 VARCHAR(50),
    Address_L2 VARCHAR(50),
    DOB DATE,
    Email VARCHAR(50),
    Pro_pic longblob,
    Password VARCHAR(500),
    Position VARCHAR(20)
);

INSERT INTO Lecturer
(User_id,FName,LName,Gender,Address_L1,Address_L2,DOB,Email,Pro_pic,Password,Position)
VALUES
('L001','Saman','Perera','Male','Akuressa','Matara','1989-04-05','sperera@gmail.com',null,'Fv8I3RoTOs9/psCbK5afpQ==','Professor'),
('L002','Kumari','Lanka','Female','Eheliyagoda','Rathnapura','1993-05-24','kumarilanka@gmail.com',null,'+tIaS0rJex1GnK/rCSAUfg==','Senior Professor'),
('L003','Sandya','Gunathilaka','Female','Kamburupitiya','Matara','1982-08-12','sgunathilaka@gmail.com',null,'n6CZ9J0FsCbRCuug8lEwsQ==','Senoir Lecturer'),
('L004','Gihan','Fernando','Male','Kekanadura','Matara','1995-01-01','gihan123@gmail.com',null,'1rvy9AC72pz5JnpfASW+oA==','Professor'),
('L005','Lalin','Perera','Male','Deniyaya','Matara','1990-12-19','lalinperera@gmail.com',null,'XoOawABgtQOMNn4lERvHDw==','Senior Lecturer');

-- student table
CREATE TABLE if not exists Student
(
	User_id VARCHAR(10) NOT NULL PRIMARY KEY,
    FName VARCHAR(15),
    LName VARCHAR(15),
	Gender ENUM('Male','Female'),
	Address_L1 VARCHAR(50),
    Address_L2 VARCHAR(50),
    DOB DATE,
    Email VARCHAR(50),
    Pro_pic blob,
    S_type VARCHAR(20),
    Password VARCHAR(500),
    Lecturer_id VARCHAR(10),
    FOREIGN KEY (Lecturer_id) REFERENCES Lecturer(User_id)
);

INSERT INTO Student
(User_id, Fname, Lname, Gender, Address_L1, Address_L2, DOB, Email, Pro_pic, S_type, Password, Lecturer_id)
VALUES
('S001','Vinod','Kavinda', 'Male', 'Galle road', 'Matara', '2000-02-15', 'vinod@gmail.com', null, 'Undergraduate','1234','L001'),
('S002','Randil','Hasanga', 'Male', 'Deiniyaya road', 'Matara', '2000-11-15', 'randil@gmail.com', null, 'Undergraduate','1357','L002'),
('S003','Nilmi','Ama', 'Female', 'Rathnapura', 'Eheliyagoda', '2000-03-13', 'ama@gmail.com', null, 'Undergraduate','2468','L003'),
('S004','Gauravi','Prabudhdhi', 'Female', 'Eheliyagoda', 'Rathnapura', '2001-02-15', 'gaurawee@gmail.com', null, 'Undergraduate','3568','L004'),
('S005','Dinuka','Kavinda', 'Male', 'Gampaha Road', 'Colombo', '2000-03-15', 'dinuka@gmail.com', null, 'Undergraduate','4569','L001'),
('S006','Lakshitha','Perera', 'Male', 'Thissa', 'Matara', '2000-04-20', 'pereralak@gmail.com', null, 'Undergraduate','9658','L002'),
('S007','Piyumi','Kavindya', 'Female', 'Nugegoda', 'Colombo', '2000-09-15', 'kpiyumi@gmail.com', null, 'Undergraduate','3214','L003'),
('S008','Sineli','Gihansa', 'Female', 'Kiribathgoda', 'Colombo', '2000-03-19', 'sineli@gmail.com', null, 'Undergraduate','4236','L004'),
('S009','Parami','Basnayaka', 'Male', 'Kamburupitiya', 'Matara', '2000-08-13', 'basnayaka@gmail.com', null, 'Undergraduate','1586','L001'),
('S010','Kasun','Bandara', 'Male', 'Yatiyana', 'Matara', '2000-04-25', 'kasun@gmail.com', null, 'Undergraduate','1796','L005'),
('S011','Kavindu','Nilshan', 'Male', 'Akuressa', 'Matara', '2000-08-25', 'kavindu@gmail.com', null, 'Undergraduate','1796','L005');

-- department table
CREATE TABLE if not exists Department
(
	Dep_id VARCHAR(10) NOT NULL  PRIMARY KEY,
    Dep_Name VARCHAR(30)
    
);

INSERT INTO Department
(Dep_id, Dep_name)
VALUES
('DEP01','ICT'),
('DEP02','ET'),
('DEP03','BST'),
('DEP04','Multi diciplinary');

-- Technical_officer table
CREATE TABLE if not exists Technical_officer
(
	User_id VARCHAR(10) NOT NULL PRIMARY KEY,
    Dep_id VARCHAR(10) not null,
    FName VARCHAR(15),
    LName VARCHAR(15),
	Gender ENUM('Male','Female'),
	Address_L1 VARCHAR(50),
    Address_L2 VARCHAR(50),
    DOB DATE,
    Email VARCHAR(50),
    Pro_pic blob,
    Password VARCHAR(500),
    foreign key (Dep_id) references Department (Dep_id)
);

INSERT INTO Technical_officer
(User_id,Dep_id,FName,LName,Gender,Address_L1,Address_L2,DOB,Email,Pro_pic,Password)
VALUES
('TO001','DEP01','Sunanda','Matharage','Female','Thalalla','Mathara','1978-02-05','sumatharage@gmail.com',null,'145@q'),
('TO002','DEP02','Charuni','Munasinghe','Female','Kalawewa Rd','Anuradhapura','1975-04-15','charunimunasinghe@gmail.com',null,'asx#23'),
('TO003','DEP03','Kavinda','Gamage','Male','Eheliyagoda','Rathnapura','1985-02-12','kavinda@gmail.com',null,'bta41'),
('TO004','DEP04','Gihan','Bandara','Male','Dikwella','Mathara','1979-09-12','gihanbandara@gmail.com',null,'@12az'),
('TO005','DEP02','Supun','Malinga','Male','Pelmadulla','Rathnapura','1980-10-18','smalinga@gmail.com',null,'01@abc');



-- course details table

CREATE TABLE if not exists Course_Detail
(
	Course_id VARCHAR(10) NOT NULL PRIMARY KEY,
    Course_Name VARCHAR(30),
    Credit int,
    Level int,
    Semester int,
    Admin_id VARCHAR(10) not null,
    Lecturer_id VARCHAR(10) not null,
    foreign key (admin_id) references Admin (User_id),
    foreign key (Lecturer_id) references lecturer (User_id)
);

INSERT INTO Course_Detail
(Course_id, Course_Name, Credit, Admin_id, Lecturer_id,Level,Semester)
VALUES
('ICT01','Data Structures and Algorithm',2,'A100','L001',1,1),
('ICT02','E-Commerce',2,'A300','L002',1,1),
('ICT03','Software Engineering',3,'A200','L003',1,1),
('ICT04','Object Oriented Programing',3,'A400','L004',1,1),
('ICT05','Web technologies',3,'A300','L001',1,1),
('ICT06','Business economics',3,'A400','L002',1,1);


-- timetable table

CREATE TABLE if not exists TimeTable
(
	T_table_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	Subject_name VARCHAR (50), 
    Start_Time VARCHAR(10),
	End_Time VARCHAR(10),
    Dep_id VARCHAR(10),
    Location VARCHAR (15),
    Admin_id VARCHAR(10) not null,
    foreign key (admin_id) references Admin (User_id),
    foreign key (Dep_id) references Department (Dep_id)
);

INSERT INTO TimeTable
(T_Table_ID, Subject_name, Start_Time, End_Time, Dep_id, Location,Admin_id )
VALUES
(01,'Data Structures and Algorithms','10:00 AM','12:00 PM','DEP01','LAB11','A100'),
(02,'Cloud Computing','08:00 AM','10:00 PM','DEP01','LAB11','A200'),
(03,'Data Base Management Systems','10:00 AM','12:00 PM','DEP01','LAB11','A200'),
(04,'Fundamental of Management','10:00 AM','12:00 PM','DEP04','NBLLT','A300'),
(05,'Nano Technology','12:00 AM','2:00 PM','DEP03','LAB04','A400'),
(06,'Objet Oriented Analysis and Design','10:00 AM','12:00 PM','DEP01','LAB11','A100'),
(07,'Polymer Materials','10:00 AM','12:00 PM','DEP02','LAB8','A200'),
(08,'C Programming','08:00 AM','10:00 PM','DEP01','LAB11','A300'),
(09,'Mechatronics control and Technologists','1:00 AM','3:00 PM','DEP02','LAB8','A100'),
(10,'Gene Technology','10:00 AM','12:00 PM','DEP03','LAB4','A100');


-- Notice table

CREATE TABLE if not exists Notice
(
	Notice_id VARCHAR(10) NOT NULL PRIMARY KEY,
    Subject_ VARCHAR(30),
    Description_ VARCHAR(1000),
    Admin_id VARCHAR(10) not null,
    foreign key (admin_id) references Admin (User_id)
);

INSERT INTO Notice
(Notice_id, Subject_, Description_, Admin_id)
VALUES
('N001','Computer Hardware','CA results has been uploaded','A100'),
('N002','Multimedia Technology','The Quiz has been rescheduled on Next Monday','A200'),
('N003','Computer Programming','Practical session conducted online tomorrow','A100'),
('N004','Data Base Management System','Theory examonation will be held on 10th of March 2023','A300'),
('N005','Discreate Mathematics','Please find CA results of Discreate Mathematics under CA results section','A400'),
('N006','Serevr side Web Development','Quiz 02 will be held online on next Monday ','A200'),
('N007','Computer Networks','Quiz 03 will cover first 3 lectures','A300'),
('N008','Data Structures and algorithms','Practical session will canceld tomorrow','A100'),
('N009','Information Systems','First assignment deadline will be not extended','A300'),
('N010','Software Engineering','Project evaluation will be held on 20th of March 2023','A400');

-- medical table ***WEAK***

CREATE TABLE if not exists Medical
(
	Medical_id VARCHAR(5) not null ,
    Student_id VARCHAR(10) not null,
	Start_Date DATE,
    End_Date Date,
    Medical_Condition VARCHAR(15),
	PRIMARY KEY(Medical_id,Student_id),
    FOREIGN KEY(Student_id) REFERENCES Student(User_id)
);

INSERT INTO Medical
(Medical_id, Student_id, Start_Date,End_Date, Medical_Condition)
VALUES
('M01','S001','2023-02-05','2023-02-19','Approved'),
('M02','S002','2023-02-10','2023-02-24','Approved'),
('M03','S003','2023-03-12','2023-03-15','Reject'),
('M04','S004','2023-03-02','2023-03-04','Reject'),
('M05','S005','2023-02-05','2023-03-07','Reject');

-- Attendance table ***WEAK***

CREATE TABLE if not exists Attendance
(
	Attendance_id int not null auto_increment,
    Student_id VARCHAR(10) not null,
    Date_ Date,
    Course_id VARCHAR(10) not null,
    Status_ Enum('Present','Absent'),
    PRIMARY KEY(Attendance_id,Student_id,Course_id,Date_),
    FOREIGN KEY(Student_id) REFERENCES Student(User_id),
	FOREIGN KEY(Course_id) REFERENCES Course_Detail(Course_id)
);

INSERT INTO Attendance
(Attendance_id,Student_id,Date_,Course_id,Status_ )
VALUES
(01,'S001','2023-03-01','ICT01','Absent'),
(01,'S002','2023-03-01','ICT01','present'),
(01,'S003','2023-03-01','ICT01','present'),
(01,'S004','2023-03-01','ICT01','Present'),
(01,'S005','2023-03-01','ICT01','Present'),
(01,'S006','2023-03-01','ICT01','Present'),
(01,'S007','2023-03-01','ICT01','Present'),
(01,'S008','2023-03-01','ICT01','Absent'),
(01,'S009','2023-03-01','ICT01','Absent'),
(01,'S010','2023-03-01','ICT01','Present'),
(02,'S001','2023-03-02','ICT02','Present'),
(02,'S002','2023-03-02','ICT02','Present'),
(02,'S003','2023-03-02','ICT02','Present'),
(02,'S004','2023-03-02','ICT02','Present'),
(02,'S005','2023-03-02','ICT02','Present'),
(02,'S006','2023-03-02','ICT02','Absent'),
(02,'S007','2023-03-02','ICT02','Absent'),
(02,'S008','2023-03-02','ICT02','Absent'),
(02,'S009','2023-03-02','ICT02','Absent'),
(02,'S010','2023-03-02','ICT02','Present'),
(03,'S001','2023-03-03','ICT03','Present'),
(03,'S002','2023-03-03','ICT03','Present'),
(03,'S003','2023-03-03','ICT03','Present'),
(03,'S004','2023-03-03','ICT03','Present'),
(03,'S005','2023-03-03','ICT03','Present'),
(03,'S006','2023-03-03','ICT03','Present'),
(03,'S007','2023-03-03','ICT03','Present'),
(03,'S008','2023-03-03','ICT03','Present'),
(03,'S009','2023-03-03','ICT03','Present'),
(03,'S010','2023-03-03','ICT03','Present'),
(04,'S001','2023-03-04','ICT04','Present'),
(04,'S002','2023-03-04','ICT04','Absent'),
(04,'S003','2023-03-04','ICT04','Absent'),
(04,'S004','2023-03-04','ICT04','Present'),
(04,'S005','2023-03-04','ICT04','Present'),
(04,'S006','2023-03-04','ICT04','Present'),
(04,'S007','2023-03-04','ICT04','Present'),
(04,'S008','2023-03-04','ICT04','Present'),
(04,'S009','2023-03-04','ICT04','Present'),
(04,'S010','2023-03-04','ICT04','Absent'),
(05,'S001','2023-03-05','ICT05','Present'),
(05,'S002','2023-03-05','ICT05','Present'),
(05,'S003','2023-03-05','ICT05','Present'),
(05,'S004','2023-03-05','ICT05','Present'),
(05,'S005','2023-03-05','ICT05','Present'),
(05,'S006','2023-03-05','ICT05','Present'),
(05,'S007','2023-03-05','ICT05','Present'),
(05,'S008','2023-03-05','ICT05','Present'),
(05,'S009','2023-03-05','ICT05','Present'),
(05,'S010','2023-03-05','ICT05','Present'),
(06,'S001','2023-03-05','ICT06','Present'),
(06,'S002','2023-03-05','ICT06','Present'),
(06,'S003','2023-03-05','ICT06','Present'),
(06,'S004','2023-03-05','ICT06','Present'),
(06,'S005','2023-03-05','ICT06','Present'),
(06,'S006','2023-03-05','ICT06','Present'),
(06,'S007','2023-03-05','ICT06','Present'),
(06,'S008','2023-03-05','ICT06','Present'),
(06,'S009','2023-03-05','ICT06','Present'),
(06,'S010','2023-03-05','ICT06','Present'),
(07,'S001','2023-03-08','ICT01','Present'),
(07,'S002','2023-03-08','ICT01','Present'),
(07,'S003','2023-03-08','ICT01','Present'),
(07,'S004','2023-03-08','ICT01','Present'),
(07,'S005','2023-03-08','ICT01','Present'),
(07,'S006','2023-03-08','ICT01','Present'),
(07,'S007','2023-03-08','ICT01','Present'),
(07,'S008','2023-03-08','ICT01','Present'),
(07,'S009','2023-03-08','ICT01','Present'),
(07,'S010','2023-03-08','ICT01','Absent'),
(08,'S002','2023-03-09','ICT02','present'),
(08,'S003','2023-03-09','ICT02','present'),
(08,'S004','2023-03-09','ICT02','Present'),
(08,'S005','2023-03-09','ICT02','Present'),
(08,'S006','2023-03-09','ICT02','Present'),
(08,'S007','2023-03-09','ICT02','Present'),
(08,'S008','2023-03-09','ICT02','Absent'),
(08,'S009','2023-03-09','ICT02','Absent'),
(08,'S010','2023-03-09','ICT02','Present'),
(09,'S001','2023-03-10','ICT03','Present'),
(09,'S002','2023-03-10','ICT03','Present'),
(09,'S003','2023-03-10','ICT03','Present'),
(09,'S004','2023-03-10','ICT03','Present'),
(09,'S005','2023-03-10','ICT03','Present'),
(09,'S006','2023-03-10','ICT03','Present'),
(09,'S007','2023-03-10','ICT03','Present'),
(09,'S008','2023-03-10','ICT03','Present'),
(09,'S009','2023-03-10','ICT03','Present'),
(09,'S010','2023-03-10','ICT03','Present'),
(10,'S001','2023-03-11','ICT04','Present'),
(10,'S002','2023-03-11','ICT04','Absent'),
(10,'S003','2023-03-11','ICT04','Absent'),
(10,'S004','2023-03-11','ICT04','Present'),
(10,'S005','2023-03-11','ICT04','Present'),
(10,'S006','2023-03-11','ICT04','Present'),
(10,'S007','2023-03-11','ICT04','Present'),
(10,'S008','2023-03-11','ICT04','Present'),
(10,'S009','2023-03-11','ICT04','Present'),
(10,'S010','2023-03-11','ICT04','Absent'),
(11,'S001','2023-03-12','ICT05','Present'),
(11,'S002','2023-03-12','ICT05','Absent'),
(11,'S003','2023-03-12','ICT05','Absent'),
(11,'S004','2023-03-12','ICT05','Present'),
(11,'S005','2023-03-12','ICT05','Present'),
(11,'S006','2023-03-12','ICT05','Present'),
(11,'S007','2023-03-12','ICT05','Present'),
(11,'S008','2023-03-12','ICT05','Present'),
(11,'S009','2023-03-12','ICT05','Present'),
(11,'S010','2023-03-12','ICT05','Absent'),
(12,'S001','2023-03-12','ICT06','Present'),
(12,'S002','2023-03-12','ICT06','Present'),
(12,'S003','2023-03-12','ICT06','Present'),
(12,'S004','2023-03-12','ICT06','Present'),
(12,'S005','2023-03-12','ICT06','Present'),
(12,'S006','2023-03-12','ICT06','Present'),
(12,'S007','2023-03-12','ICT06','Present'),
(12,'S008','2023-03-12','ICT06','Present'),
(12,'S009','2023-03-12','ICT06','Present'),
(12,'S010','2023-03-12','ICT06','Present'),
(13,'S001','2023-03-15','ICT01','Present'),
(13,'S002','2023-03-15','ICT01','Present'),
(13,'S003','2023-03-15','ICT01','Present'),
(13,'S004','2023-03-15','ICT01','Present'),
(13,'S005','2023-03-15','ICT01','Present'),
(13,'S006','2023-03-15','ICT01','Present'),
(13,'S007','2023-03-15','ICT01','Present'),
(13,'S008','2023-03-15','ICT01','Present'),
(13,'S009','2023-03-15','ICT01','Present'),
(13,'S010','2023-03-15','ICT01','Absent'),
(14,'S001','2023-03-16','ICT02','Present'),
(14,'S002','2023-03-16','ICT02','Absent'),
(14,'S003','2023-03-16','ICT02','Absent'),
(14,'S004','2023-03-16','ICT02','Present'),
(14,'S005','2023-03-16','ICT02','Present'),
(14,'S006','2023-03-16','ICT02','Present'),
(14,'S007','2023-03-16','ICT02','Present'),
(14,'S008','2023-03-16','ICT02','Present'),
(14,'S009','2023-03-16','ICT02','Present'),
(14,'S010','2023-03-16','ICT02','Absent'),
(15,'S001','2023-03-17','ICT03','Present'),
(15,'S002','2023-03-17','ICT03','Present'),
(15,'S003','2023-03-17','ICT03','Present'),
(15,'S004','2023-03-17','ICT03','Present'),
(15,'S005','2023-03-17','ICT03','Present'),
(15,'S006','2023-03-17','ICT03','Present'),
(15,'S007','2023-03-17','ICT03','Present'),
(15,'S008','2023-03-17','ICT03','Present'),
(15,'S009','2023-03-17','ICT03','Present'),
(15,'S010','2023-03-17','ICT03','Present'),
(16,'S001','2023-03-18','ICT04','Present'),
(16,'S002','2023-03-18','ICT04','Present'),
(16,'S003','2023-03-18','ICT04','Present'),
(16,'S004','2023-03-18','ICT04','Present'),
(16,'S005','2023-03-18','ICT04','Present'),
(16,'S006','2023-03-18','ICT04','Present'),
(16,'S007','2023-03-18','ICT04','Present'),
(16,'S008','2023-03-18','ICT04','Present'),
(16,'S009','2023-03-18','ICT04','Present'),
(16,'S010','2023-03-18','ICT04','Absent'),
(17,'S001','2023-03-19','ICT05','Present'),
(17,'S002','2023-03-19','ICT05','Absent'),
(17,'S003','2023-03-19','ICT05','Absent'),
(17,'S004','2023-03-19','ICT05','Present'),
(17,'S005','2023-03-19','ICT05','Present'),
(17,'S006','2023-03-19','ICT05','Present'),
(17,'S007','2023-03-19','ICT05','Present'),
(17,'S008','2023-03-19','ICT05','Present'),
(17,'S009','2023-03-19','ICT05','Present'),
(17,'S010','2023-03-19','ICT05','Absent'),
(18,'S001','2023-03-19','ICT06','Present'),
(18,'S002','2023-03-19','ICT06','Present'),
(18,'S003','2023-03-19','ICT06','Present'),
(18,'S004','2023-03-19','ICT06','Present'),
(18,'S005','2023-03-19','ICT06','Absent'),
(18,'S006','2023-03-19','ICT06','Present'),
(18,'S007','2023-03-19','ICT06','Present'),
(18,'S008','2023-03-19','ICT06','Present'),
(18,'S009','2023-03-19','ICT06','Present'),
(18,'S010','2023-03-1','ICT06','Present');


-- Exam_mark table
CREATE TABLE if not exists Exam_mark
(
	Student_id VARCHAR(10) not null,
    Course_id VARCHAR(10) not null,
    Lecturer_id VARCHAR(10),
    Assignment001 DECIMAL(5,2),
    Assignment002 DECIMAL(5,2),
    QUIZ01 DECIMAL(5,2),
    QUIZ02 DECIMAL(5,2),
    QUIZ03 DECIMAL(5,2),
    MID DECIMAL(5,2),
    FINAL_Theory DECIMAL(5,2),
    FINAL_Practical DECIMAL(5,2),
    Quiz_final DECIMAL(5,2),
    final_mark DECIMAL(5,2),
    Grade DECIMAL(5,4),
    Letter_Grade VARCHAR(5),
    Credit_gained DECIMAL(5,3),
    final_ca DECIMAL (5,3),
    Eg VARCHAR(30),
    PRIMARY KEY(Student_id, Course_id),
    FOREIGN KEY (Course_id) REFERENCES Course_Detail(Course_id),
    FOREIGN KEY (Student_id) REFERENCES Student(User_id),
    FOREIGN KEY (Lecturer_id) REFERENCES Lecturer(User_id)
);

INSERT INTO Exam_mark
(Student_id, Course_id, Lecturer_id, Assignment001, Assignment002,QUIZ01,QUIZ02,QUIZ03,MID,FINAL_Theory,FINAL_Practical,Quiz_final,final_mark,Grade,Letter_Grade,Credit_gained,final_ca,eg)
VALUES
('S001','ICT01','L001',null,null,7,7,8,18,30,25,null,null,null,null,null,null,null),
('S002','ICT01','L001',null,null,8,9,10,15,36,20,null,null,null,null,null,null,null),
('S003','ICT01','L001',null,null,5,8,9,12,35,21,null,null,null,null,null,null,null),
('S004','ICT01','L001',null,null,7,8,9,17,29,19,null,null,null,null,null,null,null),
('S005','ICT01','L001',null,null,9,10,10,20,38,20,null,null,null,null,null,null,null),
('S006','ICT01','L001',null,null,7,6,10,10,30,27,null,null,null,null,null,null,null),
('S007','ICT01','L001',null,null,8,7,9,13,33,28,null,null,null,null,null,null,null),
('S008','ICT01','L001',null,null,9,9,8,19,35,26,null,null,null,null,null,null,null),
('S009','ICT01','L001',null,null,6,6,7,9,25,19,null,null,null,null,null,null,null),
('S010','ICT01','L001',null,null,8,7,9,14,32,30,null,null,null,null,null,null,null),
('S001','ICT02','L002',7,6,7,9,8,18,30,null,null,null,null,null,null,null,null),
('S002','ICT02','L002',8,7,8,9,10,13,36,null,null,null,null,null,null,null,null),
('S003','ICT02','L002',4,4,5,8,9,11,35,null,null,null,null,null,null,null,null),
('S004','ICT02','L002',8,6,7,6,9,18,29,null,null,null,null,null,null,null,null),
('S005','ICT02','L002',9,8,9,10,10,12,38,null,null,null,null,null,null,null,null),
('S006','ICT02','L002',2,5,7,6,10,9,30,null,null,null,null,null,null,null,null),
('S007','ICT02','L002',4,8,7,7,9,14,33,null,null,null,null,null,null,null,null),
('S008','ICT02','L002',7,8,9,9,8,13,35,null,null,null,null,null,null,null,null),
('S009','ICT02','L002',7,5,6,6,7,19,25,null,null,null,null,null,null,null,null),
('S010','ICT02','L002',3,7,5,7,9,1,32,null,null,null,null,null,null,null,null),
('S001','ICT03','L003',8,2,7,9,8,null,30,20,null,null,null,null,null,null,null),
('S002','ICT03','L003',5,3,8,9,10,null,36,12,null,null,null,null,null,null,null),
('S003','ICT03','L003',7,7,5,8,9,null,35,15,null,null,null,null,null,null,null),
('S004','ICT03','L003',5,2,7,6,9,NULL,29,17,NULL,NULL,NULL,NULL,null,null,null),
('S005','ICT03','L003',7,8,9,10,10,NULL,38,25,NULL,NULL,NULL,NULL,null,null,null),
('S006','ICT03','L003',2,7,7,6,10,NULL,30,21,NULL,NULL,NULL,NULL,null,null,null),
('S007','ICT03','L003',4,4,7,7,9,NULL,33,19,NULL,NULL,NULL,NULL,null,null,null),
('S008','ICT03','L003',2,8,9,9,8,NULL,35,22,NULL,NULL,NULL,NULL,null,null,null),
('S009','ICT03','L003',7,7,6,6,7,NULL,25,29,NULL,NULL,NULL,NULL,null,null,null),
('S010','ICT03','L003',3,7,5,7,9,NULL,32,11,NULL,NULL,NULL,NULL,null,null,null),
('S001','ICT04','L004',7,2,4,9,10,NULL,30,35,NULL,NULL,NULL,NULL,null,null,null),
('S002','ICT04','L004',5,3,8,6,10,NULL,26,30,NULL,NULL,NULL,NULL,null,null,null),
('S003','ICT04','L004',9,7,1,7,9,NULL,19,18,NULL,NULL,NULL,NULL,null,null,null),
('S004','ICT04','L004',3,8,7,3,9,NULL,14,36,NULL,NULL,NULL,NULL,null,null,null),
('S005','ICT04','L004',7,2,4,10,10,NULL,23,34,NULL,NULL,NULL,NULL,null,null,null),
('S006','ICT04','L004',7,7,3,2,10,NULL,30,30,NULL,NULL,NULL,NULL,null,null,null),
('S007','ICT04','L004',6,4,0,7,9,NULL,15,32,NULL,NULL,NULL,NULL,null,null,null),
('S008','ICT04','L004',2,8,9,4,8,NULL,29,31,NULL,NULL,NULL,NULL,null,null,null),
('S009','ICT04','L004',4,7,0,6,7,NULL,27,15,NULL,NULL,NULL,NULL,null,null,null),
('S010','ICT04','L004',6,7,7,1,9,NULL,17,14,NULL,NULL,NULL,NULL,null,null,null),
('S001','ICT05','L001',NULL,NULL,6,2,8,15,35,25,NULL,NULL,NULL,null,null,null,null),
('S002','ICT05','L001',NULL,NULL,4,3,10,11,36,15,NULL,NULL,NULL,null,null,null,null),
('S003','ICT05','L001',NULL,NULL,6,5,9,14,35,21,NULL,NULL,NULL,null,null,null,null),
('S004','ICT05','L001',NULL,NULL,2,3,9,6,29,10,NULL,NULL,NULL,null,null,null,null),
('S005','ICT05','L001',NULL,NULL,6,6,10,19,38,20,NULL,NULL,NULL,null,null,null,null),
('S006','ICT05','L001',NULL,NULL,7,7,10,13,33,27,NULL,NULL,NULL,null,null,null,null),
('S007','ICT05','L001',null,null,3,2,9,3,33,28,null,null,null,null,null,null,null),
('S008','ICT05','L001',null,null,6,2,8,20,35,22,null,null,null,null,null,null,null),
('S009','ICT05','L001',null,null,8,7,7,11,25,19,null,null,null,null,null,null,null),
('S010','ICT05','L001',null,null,8,7,9,14,32,30,null,null,null,null,null,null,null),
('S001','ICT06','L002',5,5,4,3,8,18,20,null,null,null,null,null,null,null,null),
('S002','ICT06','L002',2,4,7,6,10,13,26,null,null,null,null,null,null,null,null),
('S003','ICT06','L002',7,6,3,3,9,11,25,null,null,null,null,null,null,null,null),
('S004','ICT06','L002',4,2,8,7,9,18,19,null,null,null,null,null,null,null,null),
('S005','ICT06','L002',8,4,4,10,10,12,28,null,null,null,null,null,null,null,null),
('S006','ICT06','L002',4,7,7,4,10,9,34,null,null,null,null,null,null,null,null),
('S007','ICT06','L002',9,3,8,7,9,14,33,null,null,null,null,null,null,null,null),
('S008','ICT06','L002',3,7,3,3,8,13,35,null,null,null,null,null,null,null,null),
('S009','ICT06','L002',7,3,6,6,7,19,22,null,null,null,null,null,null,null,null),
('S010','ICT06','L002',2,6,8,4,9,1,32,null,null,null,null,null,null,null,null);

-- Student_CourseDetail table M-M
CREATE TABLE if not exists Student_CourseDetail
(
	Student_id VARCHAR(10) not null,
    Course_id VARCHAR(10) not null,
    PRIMARY KEY (Student_id,Course_id),
    FOREIGN KEY (Student_id) REFERENCES Student(User_id),
    FOREIGN KEY (Course_id) REFERENCES Course_Detail(Course_id)
);



INSERT INTO Student_CourseDetail
(Student_id,Course_id)
VALUES
('S001','ICT01'),
('S002','ICT02'),
('S003','ICT03'),
('S004','ICT04'),
('S005','ICT02'),
('S006','ICT01'),
('S007','ICT03'),
('S008','ICT04'),
('S009','ICT02'),
('S010','ICT03');

-- Student_Notice table M-M
CREATE TABLE if not exists Student_Notice
(
	Student_id VARCHAR(10) not null,
    Notice_id VARCHAR(10) not null,
    PRIMARY KEY (Student_id,Notice_id),
    FOREIGN KEY(Student_id) REFERENCES Student(User_id),
    FOREIGN KEY(Notice_id) REFERENCES Notice(Notice_id)
);

INSERT INTO Student_Notice
(Student_id,Notice_id)
VALUES
('S001','N001'),
('S002','N002'),
('S003','N003'),
('S004','N004'),
('S005','N005'),
('S006','N006'),
('S007','N007'),
('S008','N008'),
('S009','N009'),
('S010','N010');


-- Lecture_Medical table M-M
CREATE TABLE if not exists Lecturer_Medical
(
	Lecturer_id VARCHAR(10) not null,
    Medical_id VARCHAR(5) not null,
	PRIMARY KEY(Lecturer_id,Medical_id),
    FOREIGN KEY(Lecturer_id) REFERENCES Lecturer(User_id),
    FOREIGN KEY(Medical_id) REFERENCES Medical(Medical_id)
);

INSERT INTO Lecturer_Medical
(Lecturer_id,Medical_id)
VALUES
('L001','M01'),
('L002','M02'),
('L003','M03'),
('L004','M04'),
('L005','M05');

-- Lecture_Notice table M-M
CREATE TABLE if not exists Lecturer_Notice
(
	Lecturer_id VARCHAR(10) not null,
    Notice_id VARCHAR(10) not null,
    PRIMARY KEY(Lecturer_id,Notice_id),
    FOREIGN KEY(Lecturer_id) REFERENCES Lecturer(User_id),
    FOREIGN KEY(Notice_id) REFERENCES Notice(Notice_id)
);

INSERT INTO Lecturer_Notice
(Lecturer_id,Notice_id)
VALUES
('L001','N001'),
('L002','N002'),
('L003','N003'),
('L004','N004'),
('L002','N005'),
('L001','N006'),
('L004','N007'),
('L003','N008'),
('L001','N009'),
('L002','N010');



-- LecturerAttendance table M-M

CREATE TABLE if not exists LectureAttendance
(
	Lecturer_id VARCHAR(10) not null,
    Attendance_id int,
    primary key (Lecturer_id, Attendance_id),
    foreign key (Lecturer_id) references Lecturer (User_id),
    foreign key (Attendance_id) references Attendance (Attendance_id)
);

INSERT INTO LectureAttendance
(Lecturer_id, Attendance_id)
VALUES
('L001',01),
('L001',05),
('L001',07),
('L001',11),
('L001',13),
('L001',17),
('L002',02),
('L002',06),
('L002',08),
('L002',12),
('L002',14),
('L002',18),
('L003',03),
('L003',09),
('L003',15),
('L004',04),
('L004',10),
('L004',16);



-- Tech_OfficerNotice table

CREATE TABLE if not exists Tech_OfficerNotice
(
	T_Officer_Id VARCHAR(10) not null,
    Notice_id VARCHAR(10) not null,
    primary key (T_Officer_id, Notice_id),
    foreign key (T_Officer_Id) references Technical_officer(User_id),
    foreign key (Notice_id) references Notice(Notice_id)
);

INSERT INTO Tech_OfficerNotice
(T_Officer_Id, Notice_id)
VALUES
('TO001','N001'),
('TO002','N002'),
('TO003','N003'),
('TO004','N004'),
('TO005','N005'),
('TO001','N006'),
('TO002','N007'),
('TO003','N008'),
('TO004','N009'),
('TO005','N010');



-- Tech_OfficerTimeTable table

CREATE TABLE if not exists Tech_OfficerTimeTable
(
	T_Officer_Id VARCHAR(10) not null,
    T_table_ID int not null,
    primary key (T_Officer_ID, T_table_ID),
    foreign key (T_Officer_ID) references Technical_officer(User_id),
    foreign key (T_table_ID) references TimeTable(T_table_ID)
);

INSERT INTO Tech_OfficerTimeTable
(T_Officer_Id, T_table_ID)
VALUES
('TO001',01),
('TO002',02),
('TO003',03),
('TO004',04),
('TO005',05),
('TO001',06),
('TO002',07),
('TO003',08),
('TO004',09),
('TO005',10);

-- T_Officer_Medical table M-M

CREATE TABLE if not exists T_Officer_Medical
(
	Medical_id VARCHAR(10) not null,
    T_Officer_ID VARCHAR(10) not null,
    primary key(Medical_id, T_Officer_ID),
    foreign key (Medical_id) references Medical (Medical_id),
    foreign key (T_Officer_ID) references Technical_officer(User_id)
);

INSERT INTO T_Officer_Medical
(Medical_id, T_Officer_ID)
VALUES
('M01','TO001'),
('M02','TO002'),
('M03','TO003');

CREATE TABLE if not exists DefaulImg
(
	imgId int primary key,
    img blob
);

CREATE TABLE if not exists Student_Grades
(
    Student_id VARCHAR(10) not null primary key,
    ICT01 DECIMAL(5,3),
    ICT02 DECIMAL(5,3),
    ICT03 DECIMAL(5,3),
    ICT04 DECIMAL(5,3),
    ICT05 DECIMAL(5,3),
    ICT06 DECIMAL(5,3),
    Grade VARCHAR(5),
    L1_S1_Credit DECIMAL(5,3),
    L1_S2_Credit DECIMAL(5,3),
    L2_S1_Credit DECIMAL(5,3),
    L2_S2_Credit DECIMAL(5,3),
    L3_S1_Credit DECIMAL(5,3),
    L3_S2_Credit DECIMAL(5,3),
    L4_S1_Credit DECIMAL(5,3),
    L4_S2_Credit DECIMAL(5,3),
    L1_S1_GPA DECIMAL(5,3),
    L1_S2_GPA DECIMAL(5,3),
    L2_S1_GPA DECIMAL(5,3),
    L2_S2_GPA DECIMAL(5,3),
    L3_S1_GPA DECIMAL(5,3),
    L3_S2_GPA DECIMAL(5,3),
    L4_S1_GPA DECIMAL(5,3),
    L4_S2_GPA DECIMAL(5,3),
    Total_credits DECIMAL(5,3),
    SGPA Decimal(5,4),
    CGPA DECIMAL(5,4),
    FOREIGN KEY(Student_id) REFERENCES Exam_mark(Student_id)
    
);

CREATE TABLE if not exists T_Table
(
	TT_id VARCHAR(10) primary key not null,
    Level_and_Semester VARCHAR(30),
    TT_pdf longblob
);

CREATE TABLE if not exists Course_materials
(
	C_Material_Id VARCHAR(10) primary key not null,
    Course_id VARCHAR(30),
    Material_Name VARCHAR(30),
    Lecturer_id VARCHAR(30),
    File_ longblob,
    FOREIGN KEY(Course_id) REFERENCES Course_Detail(Course_id),
    FOREIGN KEY(Lecturer_id) REFERENCES Lecturer(User_id)
);


CREATE DATABASE if not exists LMSDB;

USE LMSDB;

-- admin table

CREATE TABLE if not exists Addmin
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
    Password VARCHAR(20),
    Role_ VARCHAR(20)
);

INSERT INTO Addmin
(User_id,FName,LName,Gender,Address_L1,Address_L2,DOB,Email,Pro_pic,Password,Role_)
VALUES
('A100','Uthpala','Kumari','Female','Deiyandara','Matara','1988-02-11','uthpala@gmail.com',null,'2345',' '),
('A200','Wasantha','Kumara','Male','Bomiriya','Kaduwela','1989-01-14','wasantha@gmail.com',null,'3455',' '),
('A300','Nilantha','Bandara','Male','Palmadulla','Rathnapura','1984-05-17','nilantha@gmail.com',null,'3345',' '),
('A400','Vinod','Deshan','Male','Malawa','Kuruwita','1985-02-18','deshan@gmail.com',null,'2667',' ');


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
    S_type VARCHAR(10),
    Password VARCHAR(20),
    Lecturer_id int,
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
('S010','Kasun','Bandara', 'Male', 'Yatiyana', 'Matara', '2000-04-25', 'kasun@gmail.com', null, 'Undergraduate','1796','L005');

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
    Pro_pic blob,
    Password VARCHAR(20),
    Position VARCHAR(20)
);

INSERT INTO Lecturer
(User_id,FName,LName,Gender,Address_L1,Address_L2,DOB,Email,Pro_pic,Password,Position)
VALUES
('L001','Saman','Perera','Male','Akuressa','Matara','sperera@gmail.com',null,'asd123','Professor'),
('L002','Kumari','Lanka','Female','Eheliyagoda','Rathnapura','kumarilanka@gmail.com',null,'75qw6','Senior Professor'),
('L003','Sandya','Gunathilaka','Female','Kamburupitiya','Matara','sgunathilaka@gmail.com',null,'hrq456','Senoir Lecturer'),
('L004','Gihan','Fernando','Male','Kekanadura','Matara','gihan123@gmail.com',null,'201mnb','Professor'),
('L005','Lalin','Perera','Male','Deniyaya','Matara','lalinperera@gmail.com',null,'qwe412','Senior Lecturer');

-- Technical_officer table
CREATE TABLE if not exists Technical_officer
(
	User_id VARCHAR(10) AUTO_INCREMENT NOT NULL PRIMARY KEY,
    Dep_id VARCHAR(8) not null,
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

INSERT INTO Technical_officer
(User_id,Dep_id,FName,LName,Gender,Address_L1,Address_L2,DOB,Email,Pro_pic,Password)
VALUES
('TO001','DEP01','Sunanda','Matharage','Female','Thalalla','Mathara','1978-02-05','sumatharage@gmail.com',null,'145@q'),
('TO002','DEP02','Charuni','Munasinghe','Female','Kalawewa Rd','Anuradhapura','1975-04-15','charunimunasinghe@gmail.com',null,'asx#23'),
('TO003','DEP03','Kavinda','Gamage','Male','Eheliyagoda','Rathnapura','1985-02-12','kavinda@gmail.com',null,'bta41'),
('TO004','DEP04','Gihan','Bandara','Male','Dikwella','Mathara','1979-09-12','gihanbandara@gmail.com',null,'@12az'),
('TO005','DEP02','Supun','Malinga','Male','Pelmadulla','Rathnapura','1980-10-18','smalinga@gmail.com',null,'01@abc');


-- department table
CREATE TABLE if not exists Department
(
	Dep_id VARCHAR(8) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Dep_Name VARCHAR(30)
    
);

INSERT INTO Department
(Dep_id, Dep_name)
VALUES
('DEP01','ICT'),
('DEP02','ET'),
('DEP03','BST'),
('DEP04','Multi diciplinary');


-- course details table

CREATE TABLE if not exists Course_Detail
(
	Course_id VARCHAR(10) NOT NULL PRIMARY KEY,
    Course_Name VARCHAR(30),
    Credit int,
    Admin_id int not null,
    Lecturer_id int not null,
    foreign key (admin_id) references Addmin (User_id),
    foreign key (Lecturer_id) references Lecturer (User_id)
);

INSERT INTO Course_Detail
(Course_id, Course_Name, Credit, Admin_id, Lecturer_id)
VALUES
('CD001','Data Structures and Algorithm',2,'A100','L001'),
('CD002','Software Engineering',3,'A200','L003'),
('CD003','E-Commerce',2,'A300','L002'),
('CD004','Object Oriented Programing','A400','L004');

-- timetable table

CREATE TABLE if not exists TimeTable
(
	T_table_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	Subject_name VARCHAR (50), 
    Start_Time TIME,
	End_Time TIME,
    Dep_id int,
    Location VARCHAR (15),
    Admin_id int not null,
    foreign key (admin_id) references Addmin (User_id),
    foreign key (Dep_id) references Department (Dep_id)
);

INSERT INTO TimeTable
(T_Table_ID, Subject_name, Start_Time, End_Time, Dep_id, Location )
VALUES
(01,'Data Structures and Algorithms','10:00 AM','12:00 PM','DEP01','LAB11'),
(02,'Cloud Computing','08:00 AM','10:00 PM','DEP01','LAB11'),
(03,'Data Base Management Systems','10:00 AM','12:00 PM','DEP01','LAB11'),
(04,'Fundamental of Management','10:00 AM','12:00 PM','DEP04','NBLLT'),
(05,'Nano Technology','12:00 AM','2:00 PM','DEP03','LAB04'),
(06,'Objet Oriented Analysis and Design','10:00 AM','12:00 PM','DEP01','LAB11'),
(07,'Polymer Materials','10:00 AM','12:00 PM','DEP02','LAB8'),
(08,'C Programming','08:00 AM','10:00 PM','DEP01','LAB11'),
(09,'Mechatronics control and Technologists','1:00 AM','3:00 PM','DEP02','LAB8'),
(10,'Gene Technology','10:00 AM','12:00 PM','DEP03','LAB4');


-- Notice table

CREATE TABLE if not exists Notice
(
	Notice_id VARCHAR(10) NOT NULL PRIMARY KEY,
    Subject_ VARCHAR(30),
    Description_ VARCHAR(1000),
    Admin_id int not null,
    foreign key (admin_id) references Addmin (User_id)
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
	Medical_id VARCHAR(5) not null auto_increment,
    Student_id int,
	Start_Date DATE,
    End_Date Date,
    Medical_Condition VARCHAR(15),
	PRIMARY KEY(Medical_id,Student_id),
    FOREIGN KEY(Student_id) REFERENCES Student(User_id)
);

INSERT INTO Medical
(Medical_id, Student_id, Start_Date,End_Date, Medical_Condition)
VALUES
('M01',1,'2023-02-05','2023-02-19','Approved'),
('M02',1,'2023-02-10','2023-02-24','Approved'),
('M03',1,'2023-03-01','2023-03-15','Reject');

-- Attendance table ***WEAK***

CREATE TABLE if not exists Attendance
(
	Attendance_id int not null auto_increment,
    Student_id VARCHAR(10),
    Date_ Date,
    Course_id VARCHAR(10),
    Status_ Enum('Present','Absent'),
    PRIMARY KEY(Attendance_id,Student_id),
    FOREIGN KEY(Student_id) REFERENCES Student(User_id),
	FOREIGN KEY(Course_id) REFERENCES Course_Detail(Course_id)
);

INSERT INTO Attendence
(Attendance_id,  Student_id,Date_ ,Status_ )
VALUES
(20,'S001','2023-03-01','CD001','Absent'),
(20,'S002','2023-03-01','CD001','present'),
(22,'S004','2023-03-01','CD001','Present'),
(22,'S005','2023-03-01','CD001','Present'),
(22,'S006','2023-03-01','CD001','Present'),
(25,'S007','2023-03-01','CD001','Present'),
(25,'S008','2023-03-01','CD001','Absent'),
(25,'S009','2023-03-01','CD001','Absent'),
(25,'S010','2023-03-01','CD001','Present'),
(30,'S001','2023-03-02','CD003','Present'),
(30,'S002','2023-03-02','CD003','Present'),
(30,'S003','2023-03-02','CD003','Present'),
(30,'S004','2023-03-02','CD003','Present'),
(33,'S005','2023-03-02','CD003','Present'),
(33,'S006','2023-03-02','CD003','Absent'),
(33,'S007','2023-03-02','CD003','Absent'),
(33,'S008','2023-03-02','CD003','Absent'),
(33,'S009','2023-03-02','CD003','Absent'),
(25,'S010','2023-03-02','CD003','Present'),
(25,'S001','2023-03-03','CD002','Present'),
(25,'S002','2023-03-03','CD002','Present'),
(12,'S003','2023-03-03','CD002','Present'),
(12,'S004','2023-03-03','CD002','Present'),
(23,'S005','2023-03-03','CD002','Present'),
(23,'S006','2023-03-03','CD002','Present'),
(28,'S007','2023-03-03','CD002','Present'),
(28,'S008','2023-03-03','CD002','Present'),
(14,'S009','2023-03-03','CD002','Present'),
(14,'S010','2023-03-03','CD002','Present'),
(14,'S001','2023-03-04','CD004','Present'),
(15,'S002','2023-03-04','CD004','Absent'),
(15,'S003','2023-03-04','CD004','Absent'),
(32,'S004','2023-03-04','CD004','Present'),
(32,'S005','2023-03-04','CD004','Present'),
(17,'S006','2023-03-04','CD004','Present'),
(17,'S007','2023-03-04','CD004','Present'),
(19,'S008','2023-03-04','CD004','Present'),
(19,'S009','2023-03-04','CD004','Present'),
(19,'S010','2023-03-04','CD004','Absent'),
(21,'S001','2023-03-05','CD001','Present'),
(21,'S002','2023-03-05','CD001','Present'),
(32,'S003','2023-03-05','CD001','Present'),
(32,'S004','2023-03-05','CD001','Present'),
(35,'S005','2023-03-05','CD001','Present'),
(35,'S006','2023-03-05','CD001','Present'),
(15,'S007','2023-03-05','CD001','Present'),
(16,'S008','2023-03-05','CD001','Present'),
(21,'S009','2023-03-05','CD001','Present'),
(15,'S010','2023-03-05','CD001','Present'),
(20,'S001','2023-03-06','CD003','Present'),
(20,'S002','2023-03-06','CD003','Present'),
(23,'S003','2023-03-06','CD003','Present'),
(26,'S004','2023-03-06','CD003','Present'),
(25,'S005','2023-03-06','CD003','Present'),
(14,'S006','2023-03-06','CD003','Present'),
(13,'S007','2023-03-06','CD003','Present'),
(28,'S008','2023-03-06','CD003','Present'),
(20,'S009','2023-03-06','CD003','Present'),
(20,'S010','2023-03-06','CD003','Absent');




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
    Password VARCHAR(20)
);

INSERT INTO Admin
(User_id,FName,LName,Gender,Address_L1,Address_L2,DOB,Email,Pro_pic,Password)
VALUES
('A100','Uthpala','Kumari','Female','Deiyandara','Matara','1988-02-11','uthpala@gmail.com',null,'2345'),
('A200','Wasantha','Kumara','Male','Bomiriya','Kaduwela','1989-01-14','wasantha@gmail.com',null,'3455'),
('A300','Nilantha','Bandara','Male','Palmadulla','Rathnapura','1984-05-17','nilantha@gmail.com',null,'3345'),
('A400','Vinod','Deshan','Male','Malawa','Kuruwita','1985-02-18','deshan@gmail.com',null,'2667');


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
    Pro_pic longblob,
    Password VARCHAR(20),
    Position VARCHAR(20)
);

INSERT INTO Lecturer
(User_id,FName,LName,Gender,Address_L1,Address_L2,DOB,Email,Pro_pic,Password,Position)
VALUES
('L001','Saman','Perera','Male','Akuressa','Matara','1989-04-05','sperera@gmail.com',null,'asd123','Professor'),
('L002','Kumari','Lanka','Female','Eheliyagoda','Rathnapura','1993-05-24','kumarilanka@gmail.com',null,'75qw6','Senior Professor'),
('L003','Sandya','Gunathilaka','Female','Kamburupitiya','Matara','1982-08-12','sgunathilaka@gmail.com',null,'hrq456','Senoir Lecturer'),
('L004','Gihan','Fernando','Male','Kekanadura','Matara','1995-01-01','gihan123@gmail.com',null,'201mnb','Professor'),
('L005','Lalin','Perera','Male','Deniyaya','Matara','1990-12-19','lalinperera@gmail.com',null,'qwe412','Senior Lecturer');

-- Technical_officer table
CREATE TABLE if not exists Technical_officer
(
	User_id VARCHAR(10)  NOT NULL PRIMARY KEY,
    Dep_id VARCHAR(10) not null,
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


-- course details table

CREATE TABLE if not exists Course_Detail
(
	Course_id VARCHAR(10) NOT NULL PRIMARY KEY,
    Course_Name VARCHAR(30),
    Credit int,
    Admin_id VARCHAR(10) not null,
    Lecturer_id VARCHAR(10) not null,
    foreign key (admin_id) references Addmin (User_id),
    foreign key (Lecturer_id) references Lecturer (User_id)
);

INSERT INTO Course_Detail
(Course_id, Course_Name, Credit, Admin_id, Lecturer_id)
VALUES
('ICT01','Data Structures and Algorithm',2,'A100','L001'),
('ICT02','E-Commerce',2,'A300','L002'),
('ICT03','Software Engineering',3,'A200','L003'),
('ICT04','Object Oriented Programing',3,'A400','L004');

-- timetable table

CREATE TABLE if not exists TimeTable
(
	T_table_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	Subject_name VARCHAR (50), 
    Start_Time TIME,
	End_Time TIME,
    Dep_id VARCHAR(10),
    Location VARCHAR (15),
    Admin_id VARCHAR(10) not null,
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
    Admin_id VARCHAR(10) not null,
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
('M02','S001','2023-02-10','2023-02-24','Approved'),
('M03','S001','2023-03-01','2023-03-15','Reject');

-- Attendance table ***WEAK***

CREATE TABLE if not exists Attendance
(
	Attendance_id int not null auto_increment,
    Student_id VARCHAR(10) not null,
    Date_ Date,
    Course_id VARCHAR(10) not null,
    Status_ Enum('Present','Absent'),
    PRIMARY KEY(Attendance_id,Student_id,Course_id),
    FOREIGN KEY(Student_id) REFERENCES Student(User_id),
	FOREIGN KEY(Course_id) REFERENCES Course_Detail(Course_id)
);

INSERT INTO Attendance
(Attendance_id,Student_id,Date_,Course_id,Status_ )
VALUES
(20,'S001','2023-03-01','ICT01','Absent'),
(20,'S002','2023-03-01','ICT01','present'),
(22,'S004','2023-03-01','ICT01','Present'),
(22,'S005','2023-03-01','ICT01','Present'),
(22,'S006','2023-03-01','ICT01','Present'),
(25,'S007','2023-03-01','ICT01','Present'),
(25,'S008','2023-03-01','ICT01','Absent'),
(25,'S009','2023-03-01','ICT01','Absent'),
(25,'S010','2023-03-01','ICT01','Present'),
(30,'S001','2023-03-02','ICT03','Present'),
(30,'S002','2023-03-02','ICT03','Present'),
(30,'S003','2023-03-02','ICT03','Present'),
(30,'S004','2023-03-02','ICT03','Present'),
(33,'S005','2023-03-02','ICT03','Present'),
(33,'S006','2023-03-02','ICT03','Absent'),
(33,'S007','2023-03-02','ICT03','Absent'),
(33,'S008','2023-03-02','ICT03','Absent'),
(33,'S009','2023-03-02','ICT03','Absent'),
(25,'S010','2023-03-02','ICT03','Present'),
(25,'S001','2023-03-03','ICT02','Present'),
(25,'S002','2023-03-03','ICT02','Present'),
(12,'S003','2023-03-03','ICT02','Present'),
(12,'S004','2023-03-03','ICT02','Present'),
(23,'S005','2023-03-03','ICT02','Present'),
(23,'S006','2023-03-03','ICT02','Present'),
(28,'S007','2023-03-03','ICT02','Present'),
(28,'S008','2023-03-03','ICT02','Present'),
(14,'S009','2023-03-03','ICT02','Present'),
(14,'S010','2023-03-03','ICT02','Present'),
(14,'S001','2023-03-04','ICT02','Present'),
(15,'S002','2023-03-04','ICT04','Absent'),
(15,'S003','2023-03-04','ICT04','Absent'),
(32,'S004','2023-03-04','ICT04','Present'),
(32,'S005','2023-03-04','ICT04','Present'),
(17,'S006','2023-03-04','ICT04','Present'),
(17,'S007','2023-03-04','ICT04','Present'),
(19,'S008','2023-03-04','ICT04','Present'),
(19,'S009','2023-03-04','ICT04','Present'),
(19,'S010','2023-03-04','ICT04','Absent'),
(21,'S001','2023-03-05','ICT01','Present'),
(21,'S002','2023-03-05','ICT01','Present'),
(32,'S003','2023-03-05','ICT01','Present'),
(32,'S004','2023-03-05','ICT01','Present'),
(35,'S005','2023-03-05','ICT01','Present'),
(35,'S006','2023-03-05','ICT01','Present'),
(15,'S007','2023-03-05','ICT01','Present'),
(16,'S008','2023-03-05','ICT01','Present'),
(21,'S009','2023-03-05','ICT01','Present'),
(15,'S010','2023-03-05','ICT01','Present'),
(20,'S001','2023-03-06','ICT03','Present'),
(20,'S002','2023-03-06','ICT03','Present'),
(23,'S003','2023-03-06','ICT03','Present'),
(26,'S004','2023-03-06','ICT03','Present'),
(25,'S005','2023-03-06','ICT03','Present'),
(14,'S006','2023-03-06','ICT03','Present'),
(13,'S007','2023-03-06','ICT03','Present'),
(28,'S008','2023-03-06','ICT03','Present'),
(20,'S009','2023-03-06','ICT03','Present'),
(20,'S010','2023-03-06','ICT03','Absent');


-- Exam_mark table
CREATE TABLE if not exists Exam_mark
(
	Mark_id int not null auto_increment primary key,
    Student_id VARCHAR(10) not null,
    Course_id VARCHAR(10) not null,
    Lecturer_id VARCHAR(10),
    Eligibility VARCHAR(15),
    Assignment001 DECIMAL(5,2),
    Assignment002 DECIMAL(5,2),
    Grade VARCHAR(6),
    QUIZ01 DECIMAL(5,2),
    QUIZ02 DECIMAL(5,2),
    QUIZ03 DECIMAL(5,2),
    MID DECIMAL(5,2),
    SGPA DECIMAL(5,4),
    CGPA DECIMAL(5,4),
    FINAL_Practical DECIMAL(5,2),
    FINAL_Theory DECIMAL(5,2),
    FOREIGN KEY (Course_id) REFERENCES Course_Detail(Course_id),
    FOREIGN KEY (Student_id) REFERENCES Student(User_id),
    FOREIGN KEY (Lecturer_id) REFERENCES Lecturer(User_id)
);

INSERT INTO Exam_mark
(Mark_id, Student_id,Course_id,Lecturer_id,Eligibility,Assignment001,Assignment002,Grade,QUIZ01,QUIZ02,QUIZ03,MID,SGPA,CGPA,FINAL_Practical,FINAL_Theory)
VALUES
(1,'S001','ICT01','L001',null,25,20,null,7,7,8,18,null,null,30,50),
(2,'S002','ICT01','L002',null,28,27,null,8,9,10,15,null,null,36,55),
(3,'S003','ICT01','L003',null,20,25,null,5,8,9,12,null,null,35,51),
(4,'S004','ICT01','L004',null,23,24,null,7,8,9,17,null,null,29,45),
(5,'S005','ICT01','L005',null,29,27,null,9,10,10,20,null,null,38,59),
(6,'S006','ICT01','L001',null,24,24,null,7,6,10,10,null,null,30,49),
(7,'S007','ICT01','L004',null,26,27,null,8,7,9,13,null,null,33,50),
(8,'S008','ICT01','L003',null,28,27,null,9,9,8,19,null,null,35,50),
(9,'S009','ICT01','L001',null,19,27,null,6,6,7,9,null,null,25,38),
(10,'S010','ICT01','L001',null,26,28,null,8,7,9,14,null,null,32,55);
-- Student_CourseDetail table M-M
CREATE TABLE if not exists Student_CourseDetail
(
	Student_id VARCHAR(10) not null,
    Course_id VARCHAR(10) not null,
    PRIMARY KEY (Student_id,Course_id),
    FOREIGN KEY (Student_id) REFERENCES Student(User_id),
    FOREIGN KEY (Course_id) REFERENCES  Course_Detail(User_id)
);

INSERT INTO Student_CourseDetail
(Student_id,Course_id)
VALUES
('S001','CD001'),
('S002','CD002'),
('S003','CD003'),
('S004','CD004'),
('S005','CD002'),
('S006','CD001'),
('S007','CD003'),
('S008','CD004'),
('S009','CD002'),
('S010','CD003');

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
('L001',20),
('L002',30),
('L003',12),
('L004',25),
('L005',18),
('L001',23),
('L002',32),
('L003',17),
('L004',19),
('L005',35),
('L001',14),
('L002',21),
('L003',16),
('L004',26),
('L005',14);



-- Tech_OfficerNotice table

CREATE TABLE if not exists Tech_OfficerNotice
(
	T_Officer_Id VARCHAR(10) not null,
    Notice_id VARCHAR(10) not null,
    primary key (T_Officer_id, Notice_id),
    foreign key (T_Officer_Id) references Technical_officer(User_id),
    foreign key (Notice_id) references Technical_officer(User_id)
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
('TO005','N0010');



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


-- T_Officer_Attendance table M-M

CREATE TABLE if not exists T_Officer_Attendance
(
	T_Officer_ID VARCHAR(10) not null,
    Attendance_id int,
    primary key (T_Officer_ID, Attendance_id),
    foreign key (T_Officer_ID) references Technical_officer(User_id),
    foreign key (Attendance_id) references Attendance (Attendance_id)
);

INSERT INTO T_Officer_Attendance
(T_Officer_ID, Attendance_id)
VALUES
('TO001',12),
('TO002',15),
('TO003',17),
('TO004',16),
('TO005',20),
('TO001',22),
('TO002',23),
('TO003',25),
('TO004',27),
('TO005',28),
('TO002',26),
('TO003',30),
('TO004',32),
('TO005',35);


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


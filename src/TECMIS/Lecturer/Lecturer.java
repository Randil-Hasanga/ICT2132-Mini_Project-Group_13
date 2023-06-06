package TECMIS.Lecturer;

import TECMIS.CommonClasses.ViewGradeGPA;
import TECMIS.Lecturer.RemoveCourseMaterials.RemoveCourseMaterial;
import TECMIS.Lecturer.Medical.Medical;
import TECMIS.Lecturer.StudentDetails.StudentDetails;
import TECMIS.Lecturer.StudentEligibility.viewStudentEligibility;
import TECMIS.Lecturer.UpdateCourseMaterials.UpdateCourseMaterials;
import TECMIS.Lecturer.UpdateMarks.UpdateMarks;
import TECMIS.Lecturer.UpdateProfile.UpdateProfileLecturer;
import TECMIS.Lecturer.UploadCourseMaterials.UploadCourseMaterials;
import TECMIS.Lecturer.ViewMarks.ViewMarks;
import TECMIS.MySqlCon;
import TECMIS.Notice;
import TECMIS.User;
import TECMIS.Lecturer.viewAttendance.ViewStudentAttendance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.sql.*;

public class Lecturer extends User implements Calculable{

    private Connection conn = MySqlCon.MysqlMethod();
    private JButton profileButton;
    private JButton UpdateCM;
    private JButton RemoveCM;
    private JButton uploadMarksButton;
    private JButton studentDetailsButton;
    private JButton studentEligibilityButton;
    private JButton viewmarksButton;
    private JButton gradesAndGPAButton;
    private JButton medicalButton;
    private JButton attendanceButton;
    private JButton noticeButton;
    private JButton AddCM;
    private JPanel pnlLecturer;
    private JLabel lblWelcome;
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JLabel lblPic;
    private JPanel pnlPic;
    private JButton logOutButton;
    private JButton updateMarks;
    private String userId;
    private String acc;
    private String Fname;
    private String Lname;
    private int SumCreditSGPA;
    private int SumCreditCGPA;
    private byte[] dImg;
    private int level;
    private int semester;
    private double tt;
    private String lvlSem;


    public void methodLecturer() {
        userId = getUserId();
        acc = getAcc();

        add(pnlLecturer);
        setSize(750, 500);
        pnlPic.setSize(200,200);
        setLocationRelativeTo(null);
        setTitle("Lecturer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to close?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    // Close the application
                    System.exit(0);
                }else {
                    // Do nothing (prevent the window from closing)
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        String sql = "SELECT FName,LName,Pro_pic FROM " + acc + " WHERE User_id = ?";
        String dfIcon = "SELECT img FROM DefaulImg WHERE imgId = 0";
        ResultSet rs;
        ResultSet rs2;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            PreparedStatement pstmt2 = conn.prepareStatement(dfIcon);
            rs2 = pstmt2.executeQuery();
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();

            while (rs2.next()){
                dImg = rs2.getBytes("img");
            }

            while (rs.next()) {
                Fname = rs.getString("FName");
                Lname = rs.getString("LName");
                byte[] imageData = rs.getBytes("Pro_pic");

                try {
                    if ((imageData == null) && (dImg == null)) {
                        lblPic.setText("");
                    } else if (imageData == null) {
                        ImageIcon defaultIcon = new ImageIcon(dImg);
                        Image image = defaultIcon.getImage();
                        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                        Graphics2D g2 = bufferedImage.createGraphics();
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g2.setClip(new Ellipse2D.Float(0, 0, image.getWidth(null), image.getHeight(null)));
                        g2.drawImage(image, 0, 0, null);
                        lblPic.setIcon(new ImageIcon(bufferedImage));
                    } else {
                        ImageIcon icon = new ImageIcon(imageData);
                        Image image = icon.getImage();
                        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                        Graphics2D g2 = bufferedImage.createGraphics();
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g2.setClip(new Ellipse2D.Float(0, 0, image.getWidth(null), image.getHeight(null)));
                        g2.drawImage(image, 0, 0, null);
                        lblPic.setIcon(new ImageIcon(bufferedImage));
                    }

                } catch (RuntimeException ex){
                    throw new RuntimeException();
                }
            }
            pstmt.close();
            rs.close();
            pstmt2.close();
            rs2.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        lblWelcome.setText("Welcome Dr." + Fname + " " + Lname + "!");

        studentEligibilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewStudentEligibility eg = new viewStudentEligibility();
                eg.setVisible(true);
                setVisible(false);
                eg.viewEligibility();
            }
        });

        RemoveCM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveCourseMaterial RCM = new RemoveCourseMaterial();
                RCM.setVisible(true);
                setVisible(false);
                RCM.RemoveCourse();
            }
        });

        viewmarksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewMarks newUM = new ViewMarks();
                newUM.setVisible(true);
                setVisible(false);
                newUM.viewStudentMarks();
            }
        });

        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateProfileLecturer upLec = new UpdateProfileLecturer();
                upLec.setVisible(true);
                setVisible(false);
                upLec.UpdateProfile();
            }
        });

        updateMarks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateMarks um = new UpdateMarks();
                um.setVisible(true);
                setVisible(false);
                um.UpdateMarks();
            }
        });

        gradesAndGPAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewGradeGPA gp = new ViewGradeGPA();
                gp.setVisible(true);
                setVisible(false);
                gp.viewGrades();
            }
        });

        UpdateCM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateCourseMaterials UCM = new UpdateCourseMaterials();
                UCM.setVisible(true);
                setVisible(false);
                UCM.UpdateCourse();
            }
        });

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User u1 = new User();
                u1.setVisible(true);
                setVisible(false);

                try {
                    conn.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        studentDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentDetails sd = new StudentDetails();
                sd.viewStudentDetails();
                sd.setVisible(true);
                setVisible(false);
            }
        });
        noticeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Notice LecNotice = new Notice();
                Lecturer l2 = new Lecturer();
                LecNotice.setVisible(true);
                setVisible(false);
                LecNotice.viewNotice();
            }
        });
        uploadMarksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UploadMarks up = new UploadMarks();
                up.upMarks();
                up.setVisible(true);
                setVisible(false);
            }
        });
        AddCM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UploadCourseMaterials upCourse = new UploadCourseMaterials();
                upCourse.upCourseMaterials();
                upCourse.setVisible(true);
                setVisible(false);
            }
        });

        attendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewStudentAttendance vAttendance = new ViewStudentAttendance();
                vAttendance.viewAttendance();
                vAttendance.setVisible(true);
                setVisible(false);
            }
        });

        medicalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Medical lecMed = new Medical();
                lecMed.viewMedicals();
                lecMed.setVisible(true);
                setVisible(false);
            }
        });

    }

    public void updateExamMarks(){
        // getting 2 highest values from 3 quiz mark columns and insert it in new column
        String upDateQuiz = "Update Exam_Mark SET Quiz_final = (GREATEST(QUIZ01,QUIZ02,QUIZ03) + LEAST(GREATEST(QUIZ01,QUIZ02),GREATEST(QUIZ01,QUIZ03),GREATEST(QUIZ02,QUIZ03)))/2";

        try(Statement stmt = conn.createStatement()){
            stmt.executeUpdate(upDateQuiz);

            stmt.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        // Updating final marks,final ca, Grade letter and CA eligibility of each subject

        String DSA = "UPDATE Exam_Mark SET final_mark = Quiz_final + MID + FINAL_Theory + FINAL_Practical WHERE Course_id = 'ICT01'";
        String fCA = "UPDATE Exam_mark SET final_ca = Quiz_final + MID WHERE Course_id = 'ICT01'";
        String eg6 = "UPDATE Exam_mark SET eg = " +
                "CASE " +
                "WHEN final_ca >= 15 THEN 'CA Eligible' " +
                "ELSE 'CA Not Eligible' " +
                "END " +
                "WHERE Course_id = 'ICT01'";

        try(Statement stmt2 = conn.createStatement()){
            Statement e3 = conn.createStatement();
            Statement ca1 = conn.createStatement();
            stmt2.executeUpdate(DSA);
            ca1.executeUpdate(fCA);
            e3.executeUpdate(eg6);

            stmt2.close();
            e3.close();
            ca1.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        String EC = "UPDATE Exam_Mark SET final_mark = Quiz_final + ((Assignment001 + Assignment002)/2) + MID + FINAL_Theory WHERE Course_id = 'ICT02'";
        String fCA2 = "UPDATE Exam_mark SET final_ca = Quiz_final + ((Assignment001 + Assignment002)/2) + MID WHERE Course_id = 'ICT02'";
        String eg1 = "UPDATE Exam_mark SET eg = " +
                "CASE " +
                "WHEN final_ca >= 10 THEN 'CA Eligible' " +
                "ELSE 'CA Not Eligible' " +
                "END " +
                "WHERE Course_id = 'ICT02'";
        try(Statement stmt3 = conn.createStatement()){
            Statement e2 = conn.createStatement();
            Statement ca2 = conn.createStatement();
            stmt3.executeUpdate(EC);
            ca2.executeUpdate(fCA2);
            e2.executeUpdate(eg1);

            stmt3.close();
            ca2.close();
            e2.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        String SE = "UPDATE Exam_Mark SET final_mark = Quiz_final + Assignment001 + Assignment002 + FINAL_Theory + FINAL_Practical WHERE Course_id = 'ICT03'";
        String fCA3 = "UPDATE Exam_Mark SET final_ca = Quiz_final + Assignment001 + Assignment002 WHERE Course_id = 'ICT03'";
        String eg = "UPDATE Exam_mark SET eg = " +
                "CASE " +
                "WHEN final_ca >= 15 THEN 'CA Eligible' " +
                "ELSE 'CA Not Eligible' " +
                "END " +
                "WHERE Course_id = 'ICT03'";

        try(Statement stmt4 = conn.createStatement()){
            Statement ca3 = conn.createStatement();
            Statement e1 = conn.createStatement();
            stmt4.executeUpdate(SE);
            ca3.executeUpdate(fCA3);
            e1.executeUpdate(eg);

            stmt4.close();
            ca3.close();
            e1.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        String OOP = "UPDATE Exam_Mark SET final_mark = Quiz_final + Assignment001 + Assignment001 + FINAL_Theory + FINAL_Practical WHERE Course_id = 'ICT04'";
        String fCA4 = "UPDATE Exam_Mark SET final_ca = Quiz_final + Assignment001 + Assignment002 WHERE Course_id = 'ICT04'";
        String eg2 = "UPDATE Exam_mark SET eg = " +
                "CASE " +
                "WHEN final_ca >= 15 THEN 'CA Eligible' " +
                "ELSE 'CA Not Eligible' " +
                "END " +
                "WHERE Course_id = 'ICT04'";

        try(Statement stmt5 = conn.createStatement()){
            Statement e4 = conn.createStatement();
            Statement ca4 = conn.createStatement();
            stmt5.executeUpdate(OOP);
            ca4.executeUpdate(fCA4);
            e4.executeUpdate(eg2);

            stmt5.close();
            e4.close();
            ca4.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        String WEB = "UPDATE Exam_Mark SET final_mark = Quiz_final + MID + FINAL_Theory + FINAL_Practical WHERE Course_id = 'ICT05'";
        String fCA5 = "UPDATE Exam_mark SET final_ca = Quiz_final + MID WHERE Course_id = 'ICT05'";
        String eg3 = "UPDATE Exam_mark SET eg = " +
                "CASE " +
                "WHEN final_ca >= 15 THEN 'CA Eligible' " +
                "ELSE 'CA Not Eligible' " +
                "END " +
                "WHERE Course_id = 'ICT05'";

        try(Statement stmt6 = conn.createStatement()){
            Statement e5 = conn.createStatement();
            Statement ca5 = conn.createStatement();
            stmt6.executeUpdate(WEB);
            ca5.executeUpdate(fCA5);
            e5.executeUpdate(eg3);

            stmt6.close();
            e5.close();
            ca5.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        String BE = "UPDATE Exam_Mark SET final_mark = Quiz_final + ((Assignment001 + Assignment002)/2) + MID + FINAL_Theory WHERE Course_id = 'ICT06'";
        String fCA6 = "UPDATE Exam_mark SET final_ca = Quiz_final + ((Assignment001 + Assignment002)/2) + MID WHERE Course_id = 'ICT06'";
        String eg4 = "UPDATE Exam_mark SET eg = " +
                "CASE " +
                "WHEN final_ca >= 10 THEN 'CA Eligible' " +
                "ELSE 'CA Not Eligible' " +
                "END " +
                "WHERE Course_id = 'ICT06'";

        try(Statement stmt7 = conn.createStatement()){
            Statement e6 = conn.createStatement();
            Statement ca6 = conn.createStatement();
            stmt7.executeUpdate(BE);
            ca6.executeUpdate(fCA6);
            e6.executeUpdate(eg4);

            stmt7.close();
            e6.close();
            ca6.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    //Updating credit gained for each course
    public void updateCreditGained(){

        String credit2 = "UPDATE Exam_mark em " +
                "JOIN Course_Detail cd ON em.Course_id = cd.Course_id " +
                "SET em.Credit_gained = em.Grade * 2.0 " +
                "WHERE cd.Credit = 2";

        String credit3 = "UPDATE Exam_mark em " +
                "JOIN Course_Detail cd ON em.Course_id = cd.Course_id " +
                "SET em.Credit_gained = em.Grade * 3.0 " +
                "WHERE cd.Credit = 3";

        try (Statement c2 = conn.createStatement()){
            c2.executeUpdate(credit2);
            c2.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        try(Statement c3 = conn.createStatement()){
            c3.executeUpdate(credit3);
            c3.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    //Updating letter grade for each course marks
    public void updateLetterGrade(){
        String grade = "UPDATE Exam_mark SET Letter_Grade = " +
                "CASE " +
                "WHEN final_mark >= 90 THEN 'A+' " +
                "WHEN final_mark >= 85 THEN 'A' " +
                "WHEN final_mark >= 80 THEN 'A-' " +
                "WHEN final_mark >= 75 THEN 'B+' " +
                "WHEN final_mark >= 70 THEN 'B' " +
                "WHEN final_mark >= 65 THEN 'B-' " +
                "WHEN final_mark >= 60 THEN 'C+' " +
                "WHEN final_mark >= 55 THEN 'C' " +
                "WHEN final_mark >= 50 THEN 'C-' " +
                "WHEN final_mark >= 45 THEN 'D+' " +
                "WHEN final_mark >= 40 THEN 'D' " +
                "WHEN final_mark >= 35 THEN 'E' " +
                "ELSE 'E*' " +
                "END, " +
                "Grade = " +
                "CASE " +
                "WHEN final_mark >= 90 THEN 4.0 " +
                "WHEN final_mark >= 85 THEN 4.0 " +
                "WHEN final_mark >= 80 THEN 3.7 " +
                "WHEN final_mark >= 75 THEN 3.3 " +
                "WHEN final_mark >= 70 THEN 3.0 " +
                "WHEN final_mark >= 65 THEN 2.7 " +
                "WHEN final_mark >= 60 THEN 2.3 " +
                "WHEN final_mark >= 55 THEN 2.0 " +
                "WHEN final_mark >= 50 THEN 1.7 " +
                "WHEN final_mark >= 45 THEN 1.3 " +
                "WHEN final_mark >= 40 THEN 1.0 " +
                "WHEN final_mark >= 35 THEN 0.7 " +
                "ELSE 0.0 " +
                "END";

        try(Statement st = conn.createStatement()){
            st.executeUpdate(grade);
            st.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    //
    public void updateStudentGrades(){

        //Insert Student IDs into Student grades table. If student grades table already have the student id, That student id will be ignored

        String sq = "INSERT INTO Student_Grades (Student_id)" +
                "SELECT DISTINCT User_id " +
                "FROM Student " +
                "WHERE User_id NOT IN (SELECT Student_id FROM Student_grades)";

        //Updating Student grades table with inserting gained credits for each SUBJECT by each student.

        String sql = "UPDATE Student_Grades " +
                "SET " +
                "ICT01 = (SELECT Credit_gained FROM Exam_mark WHERE Exam_mark.Student_id = Student_Grades.Student_id AND Exam_mark.Course_id = 'ICT01' LIMIT 1)," +
                "ICT02 = (SELECT Credit_gained FROM Exam_mark WHERE Exam_mark.Student_id = Student_Grades.Student_id AND Exam_mark.Course_id = 'ICT02' LIMIT 1)," +
                "ICT03 = (SELECT Credit_gained FROM Exam_mark WHERE Exam_mark.Student_id = Student_Grades.Student_id AND Exam_mark.Course_id = 'ICT03' LIMIT 1)," +
                "ICT04 = (SELECT Credit_gained FROM Exam_mark WHERE Exam_mark.Student_id = Student_Grades.Student_id AND Exam_mark.Course_id = 'ICT04' LIMIT 1)," +
                "ICT05 = (SELECT Credit_gained FROM Exam_mark WHERE Exam_mark.Student_id = Student_Grades.Student_id AND Exam_mark.Course_id = 'ICT05' LIMIT 1)," +
                "ICT06 = (SELECT Credit_gained FROM Exam_mark WHERE Exam_mark.Student_id = Student_Grades.Student_id AND Exam_mark.Course_id = 'ICT06' LIMIT 1)" +
                "WHERE EXISTS (SELECT * FROM Exam_mark WHERE Exam_mark.Student_id = Student_Grades.Student_id);";


        try(Statement uid = conn.createStatement()){
            uid.executeUpdate(sq);
            uid.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        try(Statement sendData = conn.createStatement()){
            sendData.executeUpdate(sql);
            sendData.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void sumCredit(){

        //Getting total credit values for semester (for SGPA)

        level = ViewGradeGPA.getCurrent_level();
        semester = ViewGradeGPA.getCurrent_semester();

        String creditSumSGPA = "SELECT SUM(Credit) FROM Course_Detail WHERE (Level = ?) AND (Semester = ?)";
        try(PreparedStatement sm = conn.prepareStatement(creditSumSGPA)){
            sm.setInt(1,level);
            sm.setInt(2,semester);
            ResultSet rsSum = sm.executeQuery();

            while(rsSum.next()){
                SumCreditSGPA = rsSum.getInt(1);
            }
            System.out.println(SumCreditSGPA);

            sm.close();
            rsSum.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Getting total credit value for all the courses (for CGPA)

        String creditSumCGPA = "SELECT SUM(Credit) FROM Course_Detail";
        try(Statement sm = conn.createStatement()){

            ResultSet rsSum = sm.executeQuery(creditSumCGPA);

            while(rsSum.next()){
                SumCreditCGPA = rsSum.getInt(1);
            }
            System.out.println(SumCreditCGPA);

            sm.close();
            rsSum.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void totalCredit(){
        level = ViewGradeGPA.getCurrent_level();
        semester = ViewGradeGPA.getCurrent_semester();

        System.out.println(level);
        System.out.println(semester);

        //Updating gained TOTAL credit values by each student for each SEMESTER in Student grades table

        try (Statement stmt = conn.createStatement()) {
            // Iterate over levels
            for (int level = 1; level <= 4; level++) {
                // Iterate over semesters
                for (int semester = 1; semester <= 2; semester++) {

                    String columnName = "L" + level + "_S" + semester + "_Credit";


                    String updateQuery = "UPDATE Student_Grades " +
                            "JOIN Student ON Student_Grades.Student_id = Student.User_id " +
                            "SET Student_Grades." + columnName + " = (" +
                            "    SELECT SUM(Exam_mark.Credit_gained) " +
                            "    FROM Exam_mark " +
                            "    JOIN Course_Detail ON Exam_mark.Course_id = Course_Detail.Course_id " +
                            "    WHERE Course_Detail.Level = " + level + " " +
                            "    AND Course_Detail.Semester = " + semester + " " +
                            "    AND Exam_mark.Student_id = Student.User_id " +
                            ")";

                    stmt.executeUpdate(updateQuery);
                }
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String t = "SELECT Total_credits FROM Student_Grades";

        try(Statement st = conn.createStatement()){
            ResultSet rs = st.executeQuery(t);
            while(rs.next()){
                tt = rs.getDouble("Total_credits");
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if((tt==0)) {
            String tc = "UPDATE Student_Grades SET Total_credits = ICT01 + ICT02 + ICT03 + ICT04 + ICT05 + ICT06";

            try (Statement upTC = conn.createStatement()) {
                upTC.executeUpdate(tc);
                upTC.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void CalculateGPA(){

        //calculateSGpa
        if(level == 1){
            if(semester == 1){
                lvlSem = "L1_S1_GPA";
            }else if(semester == 2){
                lvlSem = "L1_S2_GPA";
            }
        } else if (level == 2) {
            if(semester == 1){
                lvlSem = "L2_S1_GPA";
            }else if(semester == 2){
                lvlSem = "L2_S2_GPA";
            }
        }else if (level == 3) {
            if(semester == 1){
                lvlSem = "L3_S1_GPA";
            }else if(semester == 2){
                lvlSem = "L3_S2_GPA";
            }
        } else if (level == 4) {
            if(semester == 1){
                lvlSem = "L4_S1_GPA";
            }else if(semester == 2){
                lvlSem = "L4_S2_GPA";
            }
        }

        //Updating gained GPA by each student for each SEMESTER (SGPA) in Student grades table

        try (Statement stmt = conn.createStatement()) {
            // Iterate over levels
            for (int level = 1; level <= 4; level++) {
                // Iterate over semesters
                for (int semester = 1; semester <= 2; semester++) {
                    // Construct the column names
                    String columnName = "L" + level + "_S" + semester + "_GPA";
                    String columnName2 = "L" + level + "_S" + semester + "_Credit";

                    // Construct the UPDATE statement with the column names
                    String updateQuery = "UPDATE Student_Grades SET " + columnName + " = (" + columnName2 + " / " + SumCreditSGPA + ")";

                    stmt.executeUpdate(updateQuery);
                }
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(SumCreditSGPA);

        //Calculating CGPA

        String cgp = "UPDATE Student_Grades SET CGPA = (Total_credits/?)";

        try(PreparedStatement upSGPA = conn.prepareStatement(cgp)){
            upSGPA.setDouble(1,SumCreditCGPA);
            upSGPA.executeUpdate();
            upSGPA.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}

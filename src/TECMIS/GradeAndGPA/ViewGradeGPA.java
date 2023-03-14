package TECMIS.GradeAndGPA;

import TECMIS.MySqlCon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ViewGradeGPA extends JFrame{

    Connection conn = MySqlCon.MysqlMethod();
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JTextField txtSID;
    private JLabel lblSID;
    private JButton searchButton;
    private JTable tblGrade;
    private JPanel pnlGP;

    private String SID;


    public void viewGrades(){

        add(pnlGP);
        setSize(500, 500);
        setTitle("Check grades and GPA");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SID = txtSID.getText();
                String sq = "INSERT INTO Student_Grades (Student_id) VALUES (?)";
                String sql = "UPDATE Student_Grades SET ICT01 = (SELECT final_mark FROM Exam_mark WHERE (Student_id = ?) AND Course_id = 'ICT01' LIMIT 1), " +
                            "ICT02 = (SELECT final_mark FROM Exam_mark WHERE (Student_id = ?) AND Course_id = 'ICT02' LIMIT 1)," +
                        "ICT03 = (SELECT final_mark FROM Exam_mark WHERE (Student_id = ?) AND Course_id = 'ICT03' LIMIT 1)," +
                        "ICT04 = (SELECT final_mark FROM Exam_mark WHERE (Student_id = ?) AND Course_id = 'ICT04' LIMIT 1), " +
                        "ICT05 = (SELECT final_mark FROM Exam_mark WHERE (Student_id = ?) AND Course_id = 'ICT05' LIMIT 1), " +
                        "ICT06 = (SELECT final_mark FROM Exam_mark WHERE (Student_id = ?) AND Course_id = 'ICT06' LIMIT 1)" +
                        "WHERE Student_id = ?";

                try(PreparedStatement pstmt2 = conn.prepareStatement(sq)){
                    pstmt2.setString(1,SID);
                    pstmt2.executeUpdate();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                /*String sql = "UPDATE Student_Grades g " +
                        "JOIN Exam_mark m ON g.Student_id = m.Student_id AND m.Course_id IN ('ICT01', 'ICT02', 'ICT03', 'ICT04', 'ICT05', 'ICT06') " +
                        "SET g.ICT01 = CASE WHEN m.Course_id = 'ICT01' THEN m.final_mark ELSE g.ICT01 END, " +
                        "    g.ICT02 = CASE WHEN m.Course_id = 'ICT02' THEN m.final_mark ELSE g.ICT02 END, " +
                        "    g.ICT03 = CASE WHEN m.Course_id = 'ICT03' THEN m.final_mark ELSE g.ICT03 END, " +
                        "    g.ICT04 = CASE WHEN m.Course_id = 'ICT04' THEN m.final_mark ELSE g.ICT04 END, " +
                        "    g.ICT05 = CASE WHEN m.Course_id = 'ICT05' THEN m.final_mark ELSE g.ICT05 END, " +
                        "    g.ICT06 = CASE WHEN m.Course_id = 'ICT06' THEN m.final_mark ELSE g.ICT06 END " +
                        "WHERE g.Student_id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, SID);
                    pstmt.executeUpdate();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }*/

                try(PreparedStatement pstmt = conn.prepareStatement(sql)){
                    pstmt.setString(1,SID);
                    pstmt.setString(2,SID);
                    pstmt.setString(3,SID);
                    pstmt.setString(4,SID);
                    pstmt.setString(5,SID);
                    pstmt.setString(6,SID);
                    pstmt.setString(7,SID);

                    pstmt.executeUpdate();


                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }

}


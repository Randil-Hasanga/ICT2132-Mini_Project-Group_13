package TECMIS.viewAttendance;

import TECMIS.Lecturer.Lecturer;
import TECMIS.MySqlCon;
import TECMIS.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ViewStudentAttendance extends JFrame {

    Connection conn = MySqlCon.MysqlMethod();
    private JPanel pnlAttendance;
    private JTextField txtDate;
    private JTextField txtSID;
    private JTextField txtCID;
    private JButton searchButton;
    private JTable tblAttendance;
    private JLabel lblDisplay;
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JButton backButton;
    private JButton clearButton;

    private String userId;
    private String acc;
    private String SID;
    private String CID;
    private String Fname;
    private String Lname;
    private String subject;


    public ViewStudentAttendance() {
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void viewAttendance(){
        userId = User.getUserId();
        acc = User.getAcc();

        add(pnlAttendance);
        setSize(600, 600);
        setTitle("Student Attendance");
        tblAttendance.setEnabled(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SID = txtSID.getText();
                CID = txtCID.getText();


                if((SID.isEmpty()||(CID.isEmpty()))){
                    lblDisplay.setText("Please fill all the fields");
                }
                    String sql = "SELECT Student.User_id,Attendance.Date_,Attendance.Status_ FROM Student,Course_detail,Attendance WHERE (Attendance.Student_id = Student.User_id) AND (Attendance.Course_id = Course_detail.Course_id) AND (Student.User_id = ?) AND (Course_detail.Course_id = ?)";
                    String sql2 = "SELECT Student.FName,Student.LName,Course_detail.Course_Name FROM Student,Course_detail,Attendance WHERE (Attendance.Student_id = Student.User_id) AND (Attendance.Course_id = Course_detail.Course_id) AND (Student.User_id = ?) AND (Course_detail.Course_id = ?)";

                    try (PreparedStatement pstmt = conn.prepareStatement(sql)){
                        pstmt.setString(1,SID);
                        pstmt.setString(2,CID);



                        ResultSet rs = pstmt.executeQuery();

                        ResultSetMetaData rsmd2 = rs.getMetaData();
                        int columntCount2 = rsmd2.getColumnCount();

                        DefaultTableModel tableModel2 = new DefaultTableModel();
                        tblAttendance.setModel(tableModel2);

                        for (int i = 1; i <= columntCount2; i++) {
                            tableModel2.addColumn(rsmd2.getColumnName(i));
                        }
                        tableModel2.setRowCount(0);
                        while (rs.next()) {
                            Object[] rowData = new Object[columntCount2];
                            for (int i = 1; i <= columntCount2; i++) {
                                rowData[i-1] = rs.getObject(i);
                            }
                            tableModel2.addRow(rowData);
                        }

                    } catch (SQLException ex) {
                        System.out.println("error 1");
                        throw new RuntimeException(ex);
                    }

                    try (PreparedStatement pstmt2 = conn.prepareStatement(sql2)){

                        pstmt2.setString(1,SID);
                        pstmt2.setString(2,CID);

                        ResultSet rs2 = pstmt2.executeQuery();
                        while (rs2.next()) {
                            Fname = rs2.getString("FName");
                            Lname = rs2.getString("LName");
                            subject = rs2.getString("Course_Name");
                        }
                        lblDisplay.setText("Attendance details of student " + Fname + " " + Lname +" in subject " + subject + ".");
                    } catch (SQLException ex) {
                        System.out.println("error2");
                        throw new RuntimeException(ex);
                    }

            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtSID.setText("");
                txtCID.setText("");
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Lecturer lecBack = new Lecturer();
                lecBack.setVisible(true);
                setVisible(false);
                lecBack.methodLecturer();
            }
        });


    }


}
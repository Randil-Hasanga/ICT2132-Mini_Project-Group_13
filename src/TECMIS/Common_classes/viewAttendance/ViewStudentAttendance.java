package TECMIS.Common_classes.viewAttendance;

import TECMIS.Lecturer.Lecturer;
import TECMIS.MySqlCon;
import TECMIS.User;
import com.toedter.calendar.JCalendar;

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
    private JRadioButton radioIndividual;
    private JRadioButton radioBatch;
    private JLabel lblSid;
    private JLabel lblDate;
    private JLabel lblCid;
    private JCalendar JCalendar1;

    private String userId;
    private String acc;
    private String SID;
    private String CID;
    private String Fname;
    private String Lname;
    private String subject;
    private String date;


    public ViewStudentAttendance() {

    }

    public void viewAttendance(){
        userId = User.getUserId();
        acc = User.getAcc();

        add(pnlAttendance);
        setSize(600, 600);
        setTitle("Student Attendance");
        tblAttendance.setEnabled(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(radioIndividual.isSelected()){
                    lblSid.setVisible(true);
                    txtSID.setVisible(true);
                    lblDate.setVisible(false);
                    txtDate.setVisible(false);
                    txtCID.setVisible(true);
                    lblCid.setVisible(true);
                }else if(radioBatch.isSelected()){
                    lblSid.setVisible(false);
                    txtSID.setVisible(false);
                    lblDate.setVisible(true);
                    txtDate.setVisible(true);
                    txtCID.setVisible(false);
                    lblCid.setVisible(false);
                }
            }
        };
        radioIndividual.addActionListener(listener);
        radioBatch.addActionListener(listener);


        searchButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                tblAttendance.setVisible(true);

                if(radioIndividual.isSelected()){

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

                }else if(radioBatch.isSelected()){
                        String sql3 = "SELECT Student.User_id,CONCAT(Student.FName,' ',Student.LName) AS Name,Attendance.Date_,Attendance.Status_,Course_detail.Course_Name FROM Student,Course_detail,Attendance WHERE (Attendance.Student_id = Student.User_id) AND (Attendance.Course_id = Course_detail.Course_id) AND (Attendance.Date_ = ?) ORDER by Student.User_id";
                        CID = txtCID.getText();
                        date = txtDate.getText();

                    if(date.isEmpty()){
                        lblDisplay.setText("Please fill date field");
                    }

                        try(PreparedStatement pstmt3 = conn.prepareStatement(sql3)){
                            pstmt3.setString(1,date);

                            ResultSet r2d2 = pstmt3.executeQuery();

                            ResultSetMetaData rsmd3 = r2d2.getMetaData();
                            int columntCount3 = rsmd3.getColumnCount();

                            DefaultTableModel tableModel3= new DefaultTableModel();
                            tblAttendance.setModel(tableModel3);

                            for (int i = 1; i <= columntCount3; i++) {
                                tableModel3.addColumn(rsmd3.getColumnName(i));
                            }
                            tableModel3.setRowCount(0);

                            while (r2d2.next()) {
                                Object[] rowData = new Object[columntCount3];
                                for (int i = 1; i <= columntCount3; i++) {
                                    rowData[i-1] = r2d2.getObject(i);
                                }
                                tableModel3.addRow(rowData);
                            }
                            lblDisplay.setText("Attendance details of all the student at "+ date);

                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                }
            }

        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtSID.setText("");
                txtCID.setText("");
                txtDate.setText("");
                tblAttendance.setVisible(false);
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
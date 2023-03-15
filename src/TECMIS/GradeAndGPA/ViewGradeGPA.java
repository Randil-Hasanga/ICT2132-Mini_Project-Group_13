package TECMIS.GradeAndGPA;

import TECMIS.Lecturer.UploadMarks;
import TECMIS.MySqlCon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViewGradeGPA extends JFrame{

    Connection conn = MySqlCon.MysqlMethod();
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JTextField txtSID;
    private JLabel lblSID;
    private JButton searchButton;
    private JTable tblGrade;
    private JPanel pnlGP;
    private JRadioButton radioGrade;
    private JRadioButton radioGPA;
    private JComboBox dropGrd1;
    private JTextField txtCID;
    private JLabel lblCID;
    private JButton clearButton;

    private String SID;



    private String choice;
    private String CID;

    private double sub1_cr;
    private double sub2_cr;
    private double sub3_cr;
    private double sub4_cr;
    private double sub5_cr;
    private double sub6_cr;
    private double total_cr;




    public void viewGrades(){

        add(pnlGP);
        setSize(500, 500);
        setTitle("Check grades and GPA");
        dropGrd1.setVisible(false);
        lblSID.setVisible(false);
        lblCID.setVisible(false);
        txtSID.setVisible(false);
        txtCID.setVisible(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtSID.setText("");
                txtCID.setText("");

            }
        });

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(radioGrade.isSelected()){
                    dropGrd1.setVisible(true);
                }else if(radioGPA.isSelected()){

                }


            }
        };
        radioGrade.addActionListener(listener);
        radioGPA.addActionListener(listener);

        dropGrd1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choice = dropGrd1.getSelectedItem().toString();
                if(choice.equals("View grades for Student")) {
                    txtSID.setVisible(true);
                    lblSID.setVisible(true);
                    lblCID.setVisible(false);
                    txtCID.setVisible(false);
                }else if(choice.equals("View grades for Subject")) {

                    txtCID.setVisible(true);
                    lblCID.setVisible(true);
                    txtSID.setVisible(false);
                    lblSID.setVisible(false);
                }else if(choice.equals("View grades for Batch")) {


                    txtCID.setVisible(false);
                    lblCID.setVisible(false);
                    txtSID.setVisible(false);
                    lblSID.setVisible(false);
                }

            }

            });



                    searchButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (choice.equals("View grades for Student")) {

                                UploadMarks up = new UploadMarks();
                                up.updateExamMarks();
                                SID = txtSID.getText();
                                CID = (txtCID.getText().isEmpty() || txtCID.getText() == null) ? "" : txtCID.getText();

                                String grd = "SELECT Student.User_id,CONCAT(Student.FName,' ',Student.LName) AS Name, Exam_mark.Letter_Grade FROM Course_Detail,Student,Exam_mark " +
                                        "WHERE (Student.User_id = Exam_mark.Student_id) AND (Course_Detail.Course_id = Exam_mark.Course_id) AND Exam_mark.Student_id = ? ";

                                try (PreparedStatement gr = conn.prepareStatement(grd)) {

                                    gr.setString(1, SID);
                                    ResultSet rs = gr.executeQuery();

                                    ResultSetMetaData rsmd = rs.getMetaData();
                                    int columntCount2 = rsmd.getColumnCount();

                                    DefaultTableModel tableModel = new DefaultTableModel();
                                    tblGrade.setModel(tableModel);

                                    for (int i = 1; i <= columntCount2; i++) {
                                        tableModel.addColumn(rsmd.getColumnName(i));
                                    }
                                    tableModel.setRowCount(0);
                                    while (rs.next()) {
                                        Object[] rowData = new Object[columntCount2];
                                        for (int i = 1; i <= columntCount2; i++) {
                                            rowData[i - 1] = rs.getObject(i);
                                        }
                                        tableModel.addRow(rowData);
                                    }
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }


                                String sq = "INSERT INTO Student_Grades (Student_id) " +
                                        "SELECT ? " +
                                        "WHERE NOT EXISTS (" +
                                        "SELECT Student_id FROM Student_Grades " +
                                        "WHERE Student_id = ?)";

                                String sql = "UPDATE Student_Grades SET ICT01 = (SELECT final_mark FROM Exam_mark WHERE (Student_id = ?) AND Course_id = 'ICT01' LIMIT 1), " +
                                        "ICT02 = (SELECT final_mark FROM Exam_mark WHERE (Student_id = ?) AND Course_id = 'ICT02' LIMIT 1)," +
                                        "ICT03 = (SELECT final_mark FROM Exam_mark WHERE (Student_id = ?) AND Course_id = 'ICT03' LIMIT 1)," +
                                        "ICT04 = (SELECT final_mark FROM Exam_mark WHERE (Student_id = ?) AND Course_id = 'ICT04' LIMIT 1), " +
                                        "ICT05 = (SELECT final_mark FROM Exam_mark WHERE (Student_id = ?) AND Course_id = 'ICT05' LIMIT 1), " +
                                        "ICT06 = (SELECT final_mark FROM Exam_mark WHERE (Student_id = ?) AND Course_id = 'ICT06' LIMIT 1)" +
                                        "WHERE Student_id = ?";

                                try (PreparedStatement pstmt2 = conn.prepareStatement(sq)) {
                                    pstmt2.setString(1, SID);
                                    pstmt2.setString(2, SID);
                                    pstmt2.executeUpdate();
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }


                                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                                    pstmt.setString(1, SID);
                                    pstmt.setString(2, SID);
                                    pstmt.setString(3, SID);
                                    pstmt.setString(4, SID);
                                    pstmt.setString(5, SID);
                                    pstmt.setString(6, SID);
                                    pstmt.setString(7, SID);

                                    pstmt.executeUpdate();


                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }


                                // get gained credits values from exam_marks table

                                String totalCredits = "SELECT Credit_gained FROM Exam_mark WHERE Student_id = ?";
                                List<Double> credits = new ArrayList<>();

                                try (PreparedStatement tc = conn.prepareStatement(totalCredits)) {
                                    tc.setString(1, SID);

                                    try (ResultSet RC = tc.executeQuery()) {
                                        while (RC.next()) {
                                            credits.add(RC.getDouble("Credit_gained"));
                                        }
                                    }

                                    if (credits.size() >= 6) {
                                        sub1_cr = credits.get(0);
                                        sub2_cr = credits.get(1);
                                        sub3_cr = credits.get(2);
                                        sub4_cr = credits.get(3);
                                        sub5_cr = credits.get(4);
                                        sub6_cr = credits.get(5);
                                    }

                                    total_cr = credits.stream().mapToDouble(Double::doubleValue).sum();
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }


                                // Update total credits of each student to student_grades table
                                String ttC = "UPDATE Student_Grades SET Total_credits = ? WHERE Student_id = ?";

                                try (PreparedStatement pstmt5 = conn.prepareStatement(ttC)) {
                                    pstmt5.setDouble(1, total_cr);
                                    pstmt5.setString(2, SID);
                                    pstmt5.executeUpdate();
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }

                                String sgp = "UPDATE Student_Grades SET SGPA = (Total_credits/16) WHERE Student_id = ?";
                                try (PreparedStatement pstmtGPA = conn.prepareStatement(sgp)) {

                                    pstmtGPA.setString(1, SID);
                                    pstmtGPA.executeUpdate();

                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }else if (choice.equals("View grades for Subject")) {

                                UploadMarks up = new UploadMarks();
                                up.updateExamMarks();

                                CID = txtCID.getText();
                                SID = (txtSID.getText().isEmpty() || txtSID.getText() == null) ? "" : txtSID.getText();

                                String grdd = "SELECT Student.User_id,CONCAT(Student.FName,' ',Student.LName), Exam_mark.Letter_Grade FROM Course_Detail,Student,Exam_mark " +
                                        "WHERE (Student.User_id = Exam_mark.Student_id) AND (Course_Detail.Course_id = Exam_mark.Course_id) AND Exam_mark.Course_id = ? ";

                                try (PreparedStatement gr = conn.prepareStatement(grdd)) {

                                    gr.setString(1, CID);
                                    ResultSet rs = gr.executeQuery();

                                    ResultSetMetaData rsmd = rs.getMetaData();
                                    int columntCount2 = rsmd.getColumnCount();

                                    DefaultTableModel tableModel2 = new DefaultTableModel();
                                    tblGrade.setModel(tableModel2);

                                    for (int i = 1; i <= columntCount2; i++) {
                                        tableModel2.addColumn(rsmd.getColumnName(i));
                                    }
                                    tableModel2.setRowCount(0);
                                    while (rs.next()) {
                                        Object[] rowData = new Object[columntCount2];
                                        for (int i = 1; i <= columntCount2; i++) {
                                            rowData[i - 1] = rs.getObject(i);
                                        }
                                        tableModel2.addRow(rowData);
                                    }
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }

                            } else if (choice.equals("View grades for Batch")) {

                                UploadMarks up = new UploadMarks();
                                up.updateExamMarks();
                                CID = (txtCID.getText().isEmpty() || txtCID.getText() == null) ? "" : txtCID.getText();
                                SID = (txtSID.getText().isEmpty() || txtSID.getText() == null) ? "" : txtSID.getText();

                                String grd = "SELECT Student.User_id,Course_Detail.Course_Name,Exam_mark.Letter_Grade FROM Course_Detail,Student,Exam_mark " +
                                        "WHERE (Student.User_id = Exam_mark.Student_id) AND (Course_Detail.Course_id = Exam_mark.Course_id)";

                                try (Statement gr = conn.createStatement()) {

                                    ResultSet rs = gr.executeQuery(grd);

                                    ResultSetMetaData rsmd = rs.getMetaData();
                                    int columntCount2 = rsmd.getColumnCount();

                                    DefaultTableModel tableModel3 = new DefaultTableModel();
                                    tblGrade.setModel(tableModel3);

                                    for (int i = 1; i <= columntCount2; i++) {
                                        tableModel3.addColumn(rsmd.getColumnName(i));
                                    }
                                    tableModel3.setRowCount(0);
                                    while (rs.next()) {
                                        Object[] rowData = new Object[columntCount2];
                                        for (int i = 1; i <= columntCount2; i++) {
                                            rowData[i - 1] = rs.getObject(i);
                                        }
                                        tableModel3.addRow(rowData);
                                    }
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        }
        });

    }
}








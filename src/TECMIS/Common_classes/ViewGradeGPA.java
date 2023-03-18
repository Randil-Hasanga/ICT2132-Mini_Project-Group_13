package TECMIS.Common_classes;

import TECMIS.Lecturer.Lecturer;
import TECMIS.MySqlCon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

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
    private JComboBox dropGPA;
    private JComboBox dropLEVEL;
    private JComboBox dropSEM;
    private JButton backButton;

    private String SID;



    private String choice = "demo";
    private String choice1 = "demo";
    private String CID;

    private double sub1_cr;
    private double sub2_cr;
    private double sub3_cr;
    private double sub4_cr;
    private double sub5_cr;
    private double sub6_cr;
    private double total_cr;

    private static int Current_level;
    private static int Current_semester;

    public ViewGradeGPA() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static int getCurrent_level() {
        return Current_level;
    }

    public static int getCurrent_semester(){
        return Current_semester;
    }

    public void viewGrades(){

        add(pnlGP);
        setSize(750, 500);
        setTitle("Check grades and GPA");
        setLocationRelativeTo(null);
        dropGrd1.setVisible(false);
        dropGPA.setVisible(false);
        lblSID.setVisible(false);
        lblCID.setVisible(false);
        txtSID.setVisible(false);
        txtCID.setVisible(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Lecturer gp1 = new Lecturer();


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

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(radioGrade.isSelected()){
                    dropGrd1.setVisible(true);
                    dropGPA.setVisible(false);
                }else if(radioGPA.isSelected()){
                    dropGPA.setVisible(true);
                    dropGrd1.setVisible(false);
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


        dropGPA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choice1 = dropGPA.getSelectedItem().toString();
                if(choice1.equals("View GPA for Student")) {
                    txtSID.setVisible(true);
                    lblSID.setVisible(true);
                    lblCID.setVisible(false);
                    txtCID.setVisible(false);

                }else if(choice1.equals("View GPA for Batch")) {


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
                            Current_level = dropLEVEL.getSelectedIndex();
                            Current_semester = dropSEM.getSelectedIndex();
                            if (choice.equals("View grades for Student") && (choice != null)) {

                                Lecturer up = new Lecturer();
                                up.updateExamMarks();
                                up.updateCreditGained();
                                up.updateLetterGrade();
                                up.updateStudentGrades();
                                up.sumCredit();
                                up.totalCredit();
                                up.CalculateGPA();;
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

                            }else if (choice.equals("View grades for Subject")) {

                                Lecturer up = new Lecturer();
                                up.updateExamMarks();
                                up.updateCreditGained();
                                up.updateLetterGrade();
                                up.updateStudentGrades();
                                up.sumCredit();
                                up.totalCredit();
                                up.CalculateGPA();

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

                                Lecturer up = new Lecturer();
                                up.updateExamMarks();
                                up.updateCreditGained();
                                up.updateLetterGrade();
                                up.updateStudentGrades();
                                up.sumCredit();
                                up.totalCredit();
                                up.CalculateGPA();

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

                            if(choice1.equals("View GPA for Student")){

                                Lecturer up = new Lecturer();
                                up.updateExamMarks();
                                up.updateCreditGained();
                                up.updateLetterGrade();
                                up.updateStudentGrades();
                                up.sumCredit();
                                up.totalCredit();
                                up.CalculateGPA();
                                SID = txtSID.getText();
                                CID = (txtCID.getText().isEmpty() || txtCID.getText() == null) ? "" : txtCID.getText();


                                String ssgpa = "SELECT CONCAT(Student.FName,' ',Student.LName) AS Name, Student_Grades.SGPA, Student_Grades.CGPA  FROM Student_Grades,Exam_mark,Student " +
                                        "WHERE (Student_Grades.Student_id = Exam_mark.Student_id) AND (Student.User_id = Exam_mark.Student_id) AND Student_Grades.Student_id = ? LIMIT 1";

                                try (PreparedStatement getSGPA = conn.prepareStatement(ssgpa)) {

                                    getSGPA.setString(1,SID);
                                    ResultSet result = getSGPA.executeQuery();

                                    ResultSetMetaData rsmd9 = result.getMetaData();
                                    int columntCount9 = rsmd9.getColumnCount();

                                    DefaultTableModel tableModel3 = new DefaultTableModel();
                                    tblGrade.setModel(tableModel3);

                                    for (int i = 1; i <= columntCount9; i++) {
                                        tableModel3.addColumn(rsmd9.getColumnName(i));
                                    }
                                    tableModel3.setRowCount(0);
                                    while (result.next()) {
                                        Object[] rowData = new Object[columntCount9];
                                        for (int i = 1; i <= columntCount9; i++) {
                                            rowData[i - 1] = result.getObject(i);
                                        }
                                        tableModel3.addRow(rowData);
                                    }
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }if(choice1.equals("View GPA for Batch")){

                                Lecturer up = new Lecturer();
                                up.updateExamMarks();
                                up.updateCreditGained();
                                up.updateLetterGrade();
                                up.updateStudentGrades();
                                up.sumCredit();
                                up.totalCredit();
                                up.CalculateGPA();

                                CID = (txtCID.getText().isEmpty() || txtCID.getText() == null) ? "" : txtCID.getText();
                                SID = (txtSID.getText().isEmpty() || txtSID.getText() == null) ? "" : txtSID.getText();

                                String GpaBtc = "SELECT Student.User_id AS 'User id', CONCAT(Student.FName,' ',Student.LName) AS Name, Student_Grades.SGPA , Student_Grades.CGPA " +
                                        "FROM Student,Exam_mark,Student_Grades " +
                                        "WHERE (Student.User_id = Exam_mark.Student_id) AND (Exam_mark.Student_id = Student_Grades.Student_id) GROUP BY Student.User_id";

                                try(Statement showSGPA = conn.createStatement()){
                                    ResultSet rsGP = showSGPA.executeQuery(GpaBtc);

                                    ResultSetMetaData rsmd8 = rsGP.getMetaData();
                                    int columntCount8 = rsmd8.getColumnCount();

                                    DefaultTableModel tableModel3 = new DefaultTableModel();
                                    tblGrade.setModel(tableModel3);

                                    for (int i = 1; i <= columntCount8; i++) {
                                        tableModel3.addColumn(rsmd8.getColumnName(i));
                                    }
                                    tableModel3.setRowCount(0);
                                    while (rsGP.next()) {
                                        Object[] rowData = new Object[columntCount8];
                                        for (int i = 1; i <= columntCount8; i++) {
                                            rowData[i - 1] = rsGP.getObject(i);
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








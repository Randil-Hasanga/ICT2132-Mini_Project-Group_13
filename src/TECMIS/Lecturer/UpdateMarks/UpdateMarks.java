package TECMIS.Lecturer.UpdateMarks;

import TECMIS.Lecturer.Lecturer;
import TECMIS.MySqlCon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateMarks extends JFrame{

    Connection conn = MySqlCon.MysqlMethod();
    private JPanel pnlUpdateMarks;
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JLabel lvlSID;
    private JTextField txtSID;
    private JLabel lblA1;
    private JLabel lblA2;
    private JLabel lblQ1;
    private JLabel lblQ2;
    private JLabel lblQ3;
    private JLabel lblMid;
    private JLabel lblFinalPractical;
    private JTextField txtA1;
    private JTextField txtA2;
    private JTextField txtQ1;
    private JTextField txtQ2;
    private JTextField txtQ3;
    private JTextField txtMid;
    private JTextField txtFinalPractical;
    private JLabel lblFinalTheory;
    private JTextField txtFinalTheory;
    private JButton backButton;
    private JButton updateButton;
    private JButton clearButton;
    private JLabel lblSuccess;
    private JComboBox marksDrop;

    private String CID;
    private String selected;

    private String SID;
    private double Q1;
    private double Q2;
    private double Q3;
    private double A1;
    private double A2;
    private double MID;
    private double F_Theory;
    private double F_Practical;


    public void UpdateMarks(){

        add(pnlUpdateMarks);
        setSize(750, 500);
        setTitle("Update Marks");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    marksDrop.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            selected = marksDrop.getSelectedItem().toString();
            if(selected.equals("Data Structures and Algorithms")){

                CID = "ICT01";
                txtQ1.setVisible(true);
                txtQ2.setVisible(true);
                txtQ3.setVisible(true);
                lblQ1.setVisible(true);
                lblQ2.setVisible(true);
                lblQ3.setVisible(true);
                txtA1.setVisible(false);
                txtA2.setVisible(false);
                lblA1.setVisible(false);
                lblA2.setVisible(false);
                lblMid.setVisible(true);
                txtMid.setVisible(true);
                lblFinalTheory.setVisible(true);
                txtFinalTheory.setVisible(true);
                lblFinalPractical.setVisible(true);
                txtFinalPractical.setVisible(true);
            }else if(selected.equals("E-Commerce")){

                CID = "ICT02";
                txtQ1.setVisible(true);
                txtQ2.setVisible(true);
                txtQ3.setVisible(true);
                lblQ1.setVisible(true);
                lblQ2.setVisible(true);
                lblQ3.setVisible(true);
                txtA1.setVisible(true);
                txtA2.setVisible(true);
                lblA1.setVisible(true);
                lblA2.setVisible(true);
                lblMid.setVisible(true);
                txtMid.setVisible(true);
                lblFinalTheory.setVisible(true);
                txtFinalTheory.setVisible(true);
                lblFinalPractical.setVisible(false);
                txtFinalPractical.setVisible(false);
            }else if(selected.equals("Software Engineering")) {

                CID = "ICT03";
                txtQ1.setVisible(true);
                txtQ2.setVisible(true);
                txtQ3.setVisible(true);
                lblQ1.setVisible(true);
                lblQ2.setVisible(true);
                lblQ3.setVisible(true);
                txtA1.setVisible(true);
                txtA2.setVisible(true);
                lblA1.setVisible(true);
                lblA2.setVisible(true);
                lblMid.setVisible(false);
                txtMid.setVisible(false);
                lblFinalTheory.setVisible(true);
                txtFinalTheory.setVisible(true);
                lblFinalPractical.setVisible(true);
                txtFinalPractical.setVisible(true);
            }else if(selected.equals("Object Oriented Programming")){

                CID = "ICT04";
                txtQ1.setVisible(true);
                txtQ2.setVisible(true);
                txtQ3.setVisible(true);
                lblQ1.setVisible(true);
                lblQ2.setVisible(true);
                lblQ3.setVisible(true);
                txtA1.setVisible(true);
                txtA2.setVisible(true);
                lblA1.setVisible(true);
                lblA2.setVisible(true);
                lblMid.setVisible(false);
                txtMid.setVisible(false);
                lblFinalTheory.setVisible(true);
                txtFinalTheory.setVisible(true);
                lblFinalPractical.setVisible(true);
                txtFinalPractical.setVisible(true);
            }
            else if(selected.equals("Web technologies")){

                CID = "ICT05";
                txtQ1.setVisible(true);
                txtQ2.setVisible(true);
                txtQ3.setVisible(true);
                lblQ1.setVisible(true);
                lblQ2.setVisible(true);
                lblQ3.setVisible(true);
                txtA1.setVisible(false);
                txtA2.setVisible(false);
                lblA1.setVisible(false);
                lblA2.setVisible(false);
                lblMid.setVisible(true);
                txtMid.setVisible(true);
                lblFinalTheory.setVisible(true);
                txtFinalTheory.setVisible(true);
                lblFinalPractical.setVisible(true);
                txtFinalPractical.setVisible(true);
            }else if(selected.equals("Business economics")) {

                CID = "ICT06";

                txtQ1.setVisible(true);
                txtQ2.setVisible(true);
                txtQ3.setVisible(true);
                lblQ1.setVisible(true);
                lblQ2.setVisible(true);
                lblQ3.setVisible(true);
                txtA1.setVisible(true);
                txtA2.setVisible(true);
                lblA1.setVisible(true);
                lblA2.setVisible(true);
                lblMid.setVisible(true);
                txtMid.setVisible(true);
                lblFinalTheory.setVisible(true);
                txtFinalTheory.setVisible(true);
                lblFinalPractical.setVisible(false);
                txtFinalPractical.setVisible(false);
            }
        }
    });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Lecturer up = new Lecturer();


                if((CID.equals("ICT01")) || (CID.equals("ICT05"))) {

                    SID = txtSID.getText();
                    A1 = (txtA1.getText().isEmpty() || txtA1.getText() == null) ? Double.NaN : Double.parseDouble(txtA1.getText());
                    A2 = (txtA2.getText().isEmpty() || txtA2.getText() == null) ? Double.NaN : Double.parseDouble(txtA2.getText());
                    Q1 = Double.parseDouble(txtQ1.getText());
                    Q2 = Double.parseDouble(txtQ2.getText());
                    Q3 = Double.parseDouble(txtQ3.getText());
                    MID = (txtMid.getText().isEmpty() || txtMid.getText() == null) ? Double.NaN : Double.parseDouble(txtMid.getText());
                    F_Theory = Double.parseDouble(txtFinalTheory.getText());
                    F_Practical = (txtFinalPractical.getText().isEmpty() || txtFinalPractical.getText() == null) ? Double.NaN : Double.parseDouble(txtFinalPractical.getText());


                    String sql = "UPDATE Exam_mark SET QUIZ01 = ?, QUIZ02 = ?, QUIZ03 = ? ,MID = ? , FINAL_Practical = ?, " +
                            "FINAL_Theory = ? WHERE (Course_id = ?) AND (Student_id = ? ) ";
                    try(PreparedStatement pst = conn.prepareStatement(sql)){
                        pst.setDouble(1,Q1);
                        pst.setDouble(2,Q2);
                        pst.setDouble(3,Q3);
                        pst.setDouble(4,MID);
                        pst.setDouble(5,F_Practical);
                        pst.setDouble(6,F_Theory);
                        pst.setString(7,CID);
                        pst.setString(8,SID);
                        int rowsAffected = pst.executeUpdate();
                        lblSuccess.setText("Success! " + rowsAffected + " row(s) updated.");

                        up.updateExamMarks();
                        up.updateCreditGained();
                        up.updateLetterGrade();
                        up.updateStudentGrades();
                        up.sumCredit();
                        up.totalCredit();
                        up.CalculateGPA();

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }else if((CID.equals("ICT02")) || (CID.equals("ICT06"))){

                    String sql = "UPDATE Exam_mark SET Assignment001 = ? ,Assignment002 = ?,QUIZ01 = ?, QUIZ02 = ?, QUIZ03 = ? ,MID = ? ," +
                            "FINAL_Theory = ? WHERE (Course_id = ?) AND (Student_id = ? ) ";

                    try(PreparedStatement pst = conn.prepareStatement(sql)){
                        pst.setDouble(1,A1);
                        pst.setDouble(2,A2);
                        pst.setDouble(3,Q1);
                        pst.setDouble(4,Q2);
                        pst.setDouble(5,Q3);
                        pst.setDouble(6,MID);
                        pst.setDouble(7,F_Theory);
                        pst.setString(8,CID);
                        pst.setString(9,SID);
                        int rowsAffected = pst.executeUpdate();
                        lblSuccess.setText("Success! " + rowsAffected + " row(s) updated.");

                        up.updateExamMarks();
                        up.updateCreditGained();
                        up.updateLetterGrade();
                        up.updateStudentGrades();
                        up.sumCredit();
                        up.totalCredit();
                        up.CalculateGPA();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                }

            }else if(CID.equals("ICT03")){

                    String sql = "UPDATE Exam_mark SET Assignment001 = ? ,Assignment002 = ?,QUIZ01 = ?, QUIZ02 = ?, QUIZ03 = ? ," +
                            "FINAL_Practical = ? ,FINAL_Theory = ? WHERE (Course_id = ?) AND (Student_id = ? ) ";

                    try(PreparedStatement pst = conn.prepareStatement(sql)){
                        pst.setDouble(1,A1);
                        pst.setDouble(2,A2);
                        pst.setDouble(3,Q1);
                        pst.setDouble(4,Q2);
                        pst.setDouble(5,Q3);
                        pst.setDouble(6,F_Practical);
                        pst.setDouble(7,F_Theory);
                        pst.setString(8,CID);
                        pst.setString(9,SID);
                        int rowsAffected = pst.executeUpdate();
                        lblSuccess.setText("Success! " + rowsAffected + " row(s) updated.");

                        up.updateExamMarks();
                        up.updateCreditGained();
                        up.updateLetterGrade();
                        up.updateStudentGrades();
                        up.sumCredit();
                        up.totalCredit();
                        up.CalculateGPA();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
            }else if(CID.equals("ICT04")){

                    String sql = "UPDATE Exam_mark SET Assignment001 = ? ,Assignment002 = ?,QUIZ01 = ?, QUIZ02 = ?, QUIZ03 = ? ," +
                            "FINAL_Practical = ? ,FINAL_Theory = ? WHERE (Course_id = ?) AND (Student_id = ? ) ";

                    try(PreparedStatement pst = conn.prepareStatement(sql)){
                        pst.setDouble(1,A1);
                        pst.setDouble(2,A2);
                        pst.setDouble(3,Q1);
                        pst.setDouble(4,Q2);
                        pst.setDouble(5,Q3);
                        pst.setDouble(6,F_Practical);
                        pst.setDouble(7,F_Theory);
                        pst.setString(8,CID);
                        pst.setString(9,SID);
                        int rowsAffected = pst.executeUpdate();
                        lblSuccess.setText("Success! " + rowsAffected + " row(s) updated.");

                        up.updateExamMarks();
                        up.updateCreditGained();
                        up.updateLetterGrade();
                        up.updateStudentGrades();
                        up.sumCredit();
                        up.totalCredit();
                        up.CalculateGPA();

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
                txtA1.setText("");
                txtA2.setText("");
                txtMid.setText("");
                txtQ1.setText("");
                txtQ2.setText("");
                txtQ3.setText("");
                txtFinalPractical.setText("");
                txtFinalTheory.setText("");
                lblSuccess.setText("");
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


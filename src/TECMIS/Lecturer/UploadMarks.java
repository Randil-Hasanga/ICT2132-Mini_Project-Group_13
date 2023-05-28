package TECMIS.Lecturer;

import TECMIS.MySqlCon;
import TECMIS.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UploadMarks extends Lecturer{

    Connection conn = MySqlCon.MysqlMethod();
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JTextField txtSID;
    private JTextField txtA1;
    private JTextField txtA2;
    private JTextField txtQ1;
    private JTextField txtQ2;
    private JTextField txtQ3;
    private JTextField txtMid;
    private JTextField txtFinalPractical;
    private JTextField txtFinalTheory;
    private JButton backButton;
    private JButton uploadButton;
    private JButton clearButton;
    private JTextField txtCID;
    private JLabel lblSID;
    private JPanel pnlUploadMarks;
    private JLabel lblSuccess;
    private JComboBox marksDrop;
    private JLabel lblQ1;
    private JLabel lblQ2;
    private JLabel lblQ3;
    private JLabel lblA1;
    private JLabel lblA2;
    private JLabel lblMid;
    private JLabel lblFinalPractical;
    private JLabel lblFinalTheory;
    private JButton searchButton;
    private JRadioButton existing;
    private JRadioButton newR;
    private JComboBox style;
    private JLabel lblCID;
    private JComboBox dropCourse;


    private String userId;
    private String acc;


    private String SID;
    private String CID;
    private double Q1;
    private double Q2;
    private double Q3;
    private double MID;
    private double F_Theory;
    private double F_Practical;
    private String p40 = "40%";
    private String p30 = "30%";
    private String p60 = "60%";
    private String p20 = "20%";
    private String p10 = "10%";


    double A1;
    double A2;
    private String selected;

    private int newCredit;

    public void upMarks(){

        userId = User.getUserId();
        acc = User.getAcc();

        add(pnlUploadMarks);
        setSize(750, 500);
        setTitle("Upload Marks");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
            style.setVisible(false);
            txtQ1.setVisible(false);
            txtQ2.setVisible(false);
            txtQ3.setVisible(false);
            lblQ1.setVisible(false);
            lblQ2.setVisible(false);
            lblQ3.setVisible(false);
            txtA1.setVisible(false);
            txtA2.setVisible(false);
            lblA1.setVisible(false);
            lblA2.setVisible(false);
            lblMid.setVisible(false);
            txtMid.setVisible(false);
            lblFinalTheory.setVisible(false);
            txtFinalTheory.setVisible(false);
            lblFinalPractical.setVisible(false);
            txtFinalPractical.setVisible(false);
            lblCID.setVisible(false);
            txtCID.setVisible(false);
            txtSID.setVisible(false);
            lblSID.setVisible(false);
            dropCourse.setVisible(false);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(existing.isSelected()){
                    dropCourse.setVisible(true);
                    style.setVisible(false);
                    lblCID.setVisible(false);
                    txtCID.setVisible(false);

                }else if(newR.isSelected()){
                    style.setVisible(true);
                    dropCourse.setVisible(false);
                    lblCID.setVisible(true);
                    txtCID.setVisible(true);
                }
            }
        };
        existing.addActionListener(listener);
        newR.addActionListener(listener);

        style.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = style.getSelectedItem().toString();
                if(selected.equals("Style 1")){
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
                    txtSID.setVisible(true);
                    lblSID.setVisible(true);
                    txtQ1.setText(p10);
                    txtQ2.setText(p10);
                    txtQ3.setText(p10);
                    txtMid.setText(p20);
                    txtFinalTheory.setText(p40);
                    txtFinalPractical.setText(p30);
                }else if(selected.equals("Style 2")){
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
                    txtSID.setVisible(true);
                    lblSID.setVisible(true);
                    txtQ1.setText(p10);
                    txtQ2.setText(p10);
                    txtA2.setText(p10);
                    txtA1.setText(p10);
                    txtQ3.setText(p10);
                    txtMid.setText(p20);
                    txtFinalTheory.setText(p60);

                }else if(selected.equals("Style 3")){
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
                    txtSID.setVisible(true);
                    lblSID.setVisible(true);

                    txtQ1.setText(p10);
                    txtQ2.setText(p10);
                    txtA2.setText(p10);
                    txtA1.setText(p10);
                    txtQ3.setText(p10);
                    txtMid.setText(p20);
                    txtFinalTheory.setText(p40);
                    txtFinalPractical.setText(p30);

                }else if(selected.equals("Style 4")){
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
                    txtSID.setVisible(true);
                    lblSID.setVisible(true);

                    txtQ1.setText(p10);
                    txtQ2.setText(p10);
                    txtA2.setText(p10);
                    txtA1.setText(p10);
                    txtQ3.setText(p10);
                    txtMid.setText(p20);
                    txtFinalTheory.setText(p30);
                    txtFinalPractical.setText(p40);
                }


            }
        });


        dropCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                selected = dropCourse.getSelectedItem().toString();
                if(selected.equals("ICT01")){
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
                    txtSID.setVisible(true);
                    lblSID.setVisible(true);

                    txtQ1.setText(p10);
                    txtQ2.setText(p10);
                    txtQ3.setText(p10);
                    txtMid.setText(p20);
                    txtFinalTheory.setText(p40);
                    txtFinalPractical.setText(p30);
                }else if(selected.equals("ICT02")){
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
                    txtSID.setVisible(true);
                    lblSID.setVisible(true);

                    txtQ1.setText(p10);
                    txtQ2.setText(p10);
                    txtA2.setText(p10);
                    txtA1.setText(p10);
                    txtQ3.setText(p10);
                    txtMid.setText(p20);
                    txtFinalTheory.setText(p60);
                }else if(selected.equals("ICT03")) {
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
                    txtSID.setVisible(true);
                    lblSID.setVisible(true);

                    txtQ1.setText(p10);
                    txtQ2.setText(p10);
                    txtA2.setText(p10);
                    txtA1.setText(p10);
                    txtQ3.setText(p10);
                    txtMid.setText(p20);
                    txtFinalTheory.setText(p40);
                    txtFinalPractical.setText(p30);
                }else if(selected.equals("ICT04")){
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
                    txtSID.setVisible(true);
                    lblSID.setVisible(true);

                    txtQ1.setText(p10);
                    txtQ2.setText(p10);
                    txtA2.setText(p10);
                    txtA1.setText(p10);
                    txtQ3.setText(p10);
                    txtMid.setText(p20);
                    txtFinalTheory.setText(p30);
                    txtFinalPractical.setText(p40);
                }
                else if(selected.equals("ICT05")){
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
                    txtSID.setVisible(true);
                    lblSID.setVisible(true);

                    txtQ1.setText(p10);
                    txtQ2.setText(p10);
                    txtQ3.setText(p10);
                    txtMid.setText(p20);
                    txtFinalTheory.setText(p40);
                    txtFinalPractical.setText(p30);
                }else if(selected.equals("ICT06")) {
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
                    txtSID.setVisible(true);
                    lblSID.setVisible(true);

                    txtQ1.setText(p10);
                    txtQ2.setText(p10);
                    txtA2.setText(p10);
                    txtA1.setText(p10);
                    txtQ3.setText(p10);
                    txtMid.setText(p20);
                    txtFinalTheory.setText(p60);
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtCID.setText("");
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
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(newR.isSelected()){
                    CID = txtCID.getText();
                }
                SID = txtSID.getText();
                A1 = (txtA1.getText().isEmpty() || txtA1.getText() == null) ? Double.NaN : Double.parseDouble(txtA1.getText());
                A2 = (txtA2.getText().isEmpty() || txtA2.getText() == null) ? Double.NaN : Double.parseDouble(txtA2.getText());
                Q1 = Double.parseDouble(txtQ1.getText());
                Q2 = Double.parseDouble(txtQ2.getText());
                Q3 = Double.parseDouble(txtQ3.getText());
                MID = (txtMid.getText().isEmpty() || txtMid.getText() == null) ? Double.NaN : Double.parseDouble(txtMid.getText());
                F_Theory = Double.parseDouble(txtFinalTheory.getText());
                F_Practical = (txtFinalPractical.getText().isEmpty() || txtFinalPractical.getText() == null) ? Double.NaN : Double.parseDouble(txtFinalPractical.getText());


                String upMarks = "INSERT INTO Exam_mark (Student_id,Course_id,Lecturer_id,Assignment001,Assignment002,QUIZ01,QUIZ02,QUIZ03,MID,FINAL_Practical,FINAL_Theory) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                try(PreparedStatement stmt = conn.prepareStatement(upMarks)){
                    stmt.setString(1,SID);
                    stmt.setString(2,CID);
                    stmt.setString(3,userId);
                    if((Double.isNaN(A1))||(Double.isNaN(A2))){
                        stmt.setNull(4, Types.DECIMAL);
                        stmt.setNull(5, Types.DECIMAL);
                    }else{
                        stmt.setDouble(4,A1);
                        stmt.setDouble(5,A2);
                    }
                    stmt.setDouble(6,Q1);
                    stmt.setDouble(7,Q2);
                    stmt.setDouble(8,Q3);
                    if (Double.isNaN(MID)) {
                        stmt.setNull(9, Types.DECIMAL);
                    }else{
                        stmt.setDouble(9,MID);
                    }

                    if(Double.isNaN(F_Practical)){
                        stmt.setNull(10, Types.DECIMAL);
                    }else{
                        stmt.setDouble(10,F_Practical);
                    }
                    stmt.setDouble(11,F_Theory);


                    int rowsInserted = stmt.executeUpdate();
                    System.out.println(rowsInserted + "Rows inserted");

                    lblSuccess.setText(" Student marks successfully added to database ! ");


                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                Lecturer lec = new Lecturer();
                lec.updateExamMarks();


                if(selected.equals("Style 1")) {


                    String DSA = "UPDATE Exam_Mark SET final_mark = Quiz_final + MID + FINAL_Theory + FINAL_Practical WHERE Course_id = ?";
                    String fCA = "UPDATE Exam_mark SET final_ca = Quiz_final + MID WHERE Course_id = ?";
                    String eg6 = "UPDATE Exam_mark SET eg = " +
                            "CASE " +
                            "WHEN final_ca >= 15 THEN 'CA Eligible' " +
                            "ELSE 'CA Not Eligible' " +
                            "END " +
                            "WHERE Course_id = ?";

                    try {
                        PreparedStatement pstmt = conn.prepareStatement(DSA);
                        pstmt.setString(1, CID);
                        pstmt.executeUpdate();
                        PreparedStatement pstmt2 = conn.prepareStatement(fCA);
                        pstmt2.setString(1, CID);
                        pstmt2.executeUpdate();
                        PreparedStatement pstmt3 = conn.prepareStatement(eg6);
                        pstmt3.setString(1, CID);
                        pstmt3.executeUpdate();

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }else if(selected.equals("Style 2")) {


                    String EC = "UPDATE Exam_Mark SET final_mark = Quiz_final + ((Assignment001 + Assignment002)/2) + MID + FINAL_Theory WHERE Course_id = ? ";
                    String fCA2 = "UPDATE Exam_mark SET final_ca = Quiz_final + ((Assignment001 + Assignment002)/2) + MID WHERE Course_id = ? ";
                    String eg1 = "UPDATE Exam_mark SET eg = " +
                            "CASE " +
                            "WHEN final_ca >= 10 THEN 'CA Eligible' " +
                            "ELSE 'CA Not Eligible' " +
                            "END " +
                            "WHERE Course_id = ? ";

                    try {
                        PreparedStatement pstmt = conn.prepareStatement(EC);
                        pstmt.setString(1, CID);
                        pstmt.executeUpdate();
                        PreparedStatement pstmt2 = conn.prepareStatement(fCA2);
                        pstmt2.setString(1, CID);
                        pstmt2.executeUpdate();
                        PreparedStatement pstmt3 = conn.prepareStatement(eg1);
                        pstmt3.setString(1, CID);
                        pstmt3.executeUpdate();

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }else if(selected.equals("Style 3")) {


                    String SE = "UPDATE Exam_Mark SET final_mark = Quiz_final + Assignment001 + Assignment002 + FINAL_Theory + FINAL_Practical WHERE Course_id = ? ";
                    String fCA3 = "UPDATE Exam_Mark SET final_ca = Quiz_final + Assignment001 + Assignment002 WHERE Course_id = ? ";
                    String eg = "UPDATE Exam_mark SET eg = " +
                            "CASE " +
                            "WHEN final_ca >= 15 THEN 'CA Eligible' " +
                            "ELSE 'CA Not Eligible' " +
                            "END " +
                            "WHERE Course_id = ? ";

                    try {
                        PreparedStatement pstmt = conn.prepareStatement(SE);
                        pstmt.setString(1, CID);
                        pstmt.executeUpdate();
                        PreparedStatement pstmt2 = conn.prepareStatement(fCA3);
                        pstmt2.setString(1, CID);
                        pstmt2.executeUpdate();
                        PreparedStatement pstmt3 = conn.prepareStatement(eg);
                        pstmt3.setString(1, CID);
                        pstmt3.executeUpdate();

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }else if(selected.equals("Style 4")) {


                    String oo = "UPDATE Exam_Mark SET final_mark = Quiz_final + Assignment001 + Assignment001 + FINAL_Theory + FINAL_Practical WHERE Course_id = ? ";
                    String fCA4 = "UPDATE Exam_Mark SET final_ca = Quiz_final + Assignment001 + Assignment002 WHERE Course_id = ? ";
                    String eg2 = "UPDATE Exam_mark SET eg = " +
                            "CASE " +
                            "WHEN final_ca >= 15 THEN 'CA Eligible' " +
                            "ELSE 'CA Not Eligible' " +
                            "END " +
                            "WHERE Course_id = ? ";

                    try {
                        PreparedStatement pstmt = conn.prepareStatement(oo);
                        pstmt.setString(1, CID);
                        pstmt.executeUpdate();
                        PreparedStatement pstmt2 = conn.prepareStatement(fCA4);
                        pstmt2.setString(1, CID);
                        pstmt2.executeUpdate();
                        PreparedStatement pstmt3 = conn.prepareStatement(eg2);
                        pstmt.setString(1, CID);
                        pstmt3.executeUpdate();

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                lec.updateLetterGrade();
                lec.updateCreditGained();

                String getCredit = "SELECT Credit FROM Course_Detail WHERE Course_id = ? ";
                try {
                    PreparedStatement pps = conn.prepareStatement(getCredit);
                    pps.setString(1,CID);
                    ResultSet rr = pps.executeQuery();

                    while(rr.next()){
                        newCredit = rr.getInt("Credit");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


                String credit3 = "UPDATE Exam_Mark SET Credit_gained = Grade * "+ newCredit +" WHERE Course_id = ?";
                try{
                    PreparedStatement sss = conn.prepareStatement(credit3);
                    sss.setString(1,CID);
                    sss.executeUpdate();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


                lec.updateStudentGrades();

                String sqql = "UPDATE Student_Grades " +
                        "SET " + CID +" = (SELECT Credit_gained FROM Exam_mark WHERE Exam_mark.Student_id = Student_Grades.Student_id AND Exam_mark.Course_id = ? LIMIT 1)";

                try{
                    PreparedStatement ss = conn.prepareStatement(sqql);
                    ss.setString(1,CID);
                    ss.executeUpdate();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                lec.sumCredit();
                lec.totalCredit();

                String tc = "UPDATE Student_Grades SET Total_credits = Total_credits + "+ CID + " WHERE Student_id = ? ";
                try (PreparedStatement upTC = conn.prepareStatement(tc)) {
                    //upTC.setString(1, CID);
                    upTC.setString(1,SID);
                    upTC.executeUpdate();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                lec.CalculateGPA();


            }
        });
    }



}

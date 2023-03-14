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
    private JLabel lvlSID;
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

    private String userId;
    private String acc;

    String SID;
    String CID;
    double Q1;
    double Q2;
    double Q3;
    double MID;
    double F_Theory;
    double F_Practical;

    double A1;
    double A2;
    private String selected;



    public UploadMarks() {

    }

    public void upMarks(){

        userId = User.getUserId();
        acc = User.getAcc();

        add(pnlUploadMarks);
        setSize(600, 600);
        setTitle("Upload Marks");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

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
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SID = txtSID.getText();
                A1 = (txtA1.getText().isEmpty() || txtA1.getText() == null) ? Double.NaN : Double.parseDouble(txtA1.getText());
                A2 = (txtA2.getText().isEmpty() || txtA2.getText() == null) ? Double.NaN : Double.parseDouble(txtA2.getText());
                Q1 = Double.parseDouble(txtQ1.getText());
                Q2 = Double.parseDouble(txtQ2.getText());
                Q3 = Double.parseDouble(txtQ3.getText());
                MID = (txtMid.getText().isEmpty() || txtMid.getText() == null) ? Double.NaN : Double.parseDouble(txtMid.getText());
                F_Theory = Double.parseDouble(txtFinalTheory.getText());
                F_Practical = (txtFinalPractical.getText().isEmpty() || txtFinalPractical.getText() == null) ? Double.NaN : Double.parseDouble(txtFinalPractical.getText());


                String upMarks = "INSERT INTO Exam_mark (Mark_id,Student_id,Course_id,Lecturer_id,Assignment001,Assignment002,QUIZ01,QUIZ02,QUIZ03,MID,FINAL_Practical,FINAL_Theory) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
                try(PreparedStatement stmt = conn.prepareStatement(upMarks)){
                    stmt.setNull(1, Types.INTEGER);
                    stmt.setString(2,SID);
                    stmt.setString(3,CID);
                    stmt.setString(4,userId);
                    if((Double.isNaN(A1))||(Double.isNaN(A2))){
                        stmt.setNull(5, Types.DECIMAL);
                        stmt.setNull(6, Types.DECIMAL);
                    }else{
                        stmt.setDouble(5,A1);
                        stmt.setDouble(6,A2);
                    }
                    stmt.setDouble(7,Q1);
                    stmt.setDouble(8,Q2);
                    stmt.setDouble(9,Q3);
                    if (Double.isNaN(MID)) {
                        stmt.setNull(10, Types.DECIMAL);
                    }else{
                        stmt.setDouble(10,MID);
                    }

                    if(Double.isNaN(F_Practical)){
                        stmt.setNull(11, Types.DECIMAL);
                    }else{
                        stmt.setDouble(11,F_Practical);
                    }
                    stmt.setDouble(12,F_Theory);


                    int rowsInserted = stmt.executeUpdate();
                    System.out.println(rowsInserted + "Rows inserted");

                    lblSuccess.setText(" Student marks successfully added to database ! ");


                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                // getting 2 highest values from 3 quiz mark columns and insert it in new column

                String upDateQuiz = "Update Exam_Mark SET Quiz_final = (GREATEST(QUIZ01,QUIZ02,QUIZ03) + LEAST(GREATEST(QUIZ01,QUIZ02),GREATEST(QUIZ01,QUIZ03),GREATEST(QUIZ02,QUIZ03)))/2";


                try(Statement stmt = conn.createStatement()){

                    stmt.executeUpdate(upDateQuiz);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                String DSA = "UPDATE Exam_Mark SET final_mark = Quiz_final + MID + FINAL_Theory + FINAL_Practical WHERE Course_id = 'ICT01'";

                try(Statement stmt2 = conn.createStatement()){
                    stmt2.executeUpdate(DSA);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                String EC = "UPDATE Exam_Mark SET final_mark = Quiz_final + ((Assignment001 + Assignment001)/2) + MID + FINAL_Theory WHERE Course_id = 'ICT02'";

                try(Statement stmt3 = conn.createStatement()){
                    stmt3.executeUpdate(EC);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                String SE = "UPDATE Exam_Mark SET final_mark = Quiz_final + Assignment001 + Assignment001 + FINAL_Theory + FINAL_Practical WHERE Course_id = 'ICT03'";

                try(Statement stmt4 = conn.createStatement()){
                    stmt4.executeUpdate(SE);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                String OOP = "UPDATE Exam_Mark SET final_mark = Quiz_final + Assignment001 + Assignment001 + FINAL_Theory + FINAL_Practical WHERE Course_id = 'ICT04'";

                try(Statement stmt5 = conn.createStatement()){
                    stmt5.executeUpdate(OOP);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                String WEB = "UPDATE Exam_Mark SET final_mark = Quiz_final + MID + FINAL_Theory + FINAL_Practical WHERE Course_id = 'ICT05'";

                try(Statement stmt6 = conn.createStatement()){
                    stmt6.executeUpdate(WEB);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                String BE = "UPDATE Exam_Mark SET final_mark = Quiz_final + ((Assignment001 + Assignment001)/2) + MID + FINAL_Theory WHERE Course_id = 'ICT06'";

                try(Statement stmt7 = conn.createStatement()){

                    stmt7.executeUpdate(BE);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                // Updating final marks of each subject
        /*
                if(selected.equals("Data Structures and Algorithms")){
                    String DSA = "UPDATE Exam_Mark SET final_mark = Quiz_final + MID + FINAL_Theory + FINAL_Practical WHERE Course_id = 'ICT02'";

                    try(Statement stmt2 = conn.createStatement()){
                        stmt2.executeUpdate(DSA);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                }else if(selected.equals("E-Commerce")){
                    String EC = "UPDATE Exam_Mark SET final_mark = Quiz_final + ((Assignment001 + Assignment001)/2) + MID + FINAL_Theory WHERE Course_id = 'ICT02'";

                    try(Statement stmt3 = conn.createStatement()){
                        stmt3.executeUpdate(EC);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                }else if(selected.equals("Software Engineering")) {

                    String SE = "UPDATE Exam_Mark SET final_mark = Quiz_final + Assignment001 + Assignment001 + FINAL_Theory + FINAL_Practical WHERE Course_id = 'ICT03'";

                    try(Statement stmt4 = conn.createStatement()){
                        stmt4.executeUpdate(SE);

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                }else if(selected.equals("Object Oriented Programming")){

                    String OOP = "UPDATE Exam_Mark SET final_mark = Quiz_final + Assignment001 + Assignment001 + FINAL_Theory + FINAL_Practical WHERE Course_id = 'ICT04'";

                    try(Statement stmt5 = conn.createStatement()){
                        stmt5.executeUpdate(OOP);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else if(selected.equals("Web technologies")){
                    String WEB = "UPDATE Exam_Mark SET final_mark = Quiz_final + MID + FINAL_Theory + FINAL_Practical WHERE Course_id = 'ICT05'";

                    try(Statement stmt6 = conn.createStatement()){
                        stmt6.executeUpdate(WEB);

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }else if(selected.equals("Business economics")) {

                    String BE = "UPDATE Exam_Mark SET final_mark = Quiz_final + ((Assignment001 + Assignment001)/2) + MID + FINAL_Theory WHERE Course_id = 'ICT06'";

                    try(Statement stmt7 = conn.createStatement()){

                        stmt7.executeUpdate(BE);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                }*/
            }



        });
    }


}

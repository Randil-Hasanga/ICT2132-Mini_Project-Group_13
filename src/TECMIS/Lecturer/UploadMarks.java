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



    public void upMarks(){

        userId = User.getUserId();
        acc = User.getAcc();

        add(pnlUploadMarks);
        setSize(600, 600);
        setTitle("Upload Marks");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtSID.setText("");
                txtCID.setText("");
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
                CID = txtCID.getText();
                A1 = (txtA1.getText().isEmpty() || txtA1.getText() == null) ? Double.NaN : Double.parseDouble(txtA1.getText());
                A2 = (txtA2.getText().isEmpty() || txtA2.getText() == null) ? Double.NaN : Double.parseDouble(txtA2.getText());
                Q1 = Double.parseDouble(txtQ1.getText());
                Q2 = Double.parseDouble(txtQ2.getText());
                Q3 = Double.parseDouble(txtQ3.getText());
                MID = (txtMid.getText().isEmpty() || txtMid.getText() == null) ? Double.NaN : Double.parseDouble(txtMid.getText());
                F_Theory = Double.parseDouble(txtFinalTheory.getText());
                F_Practical = (txtFinalPractical.getText().isEmpty() || txtFinalPractical.getText() == null) ? Double.NaN : Double.parseDouble(txtFinalPractical.getText());

                String upMarks = "INSERT INTO Exam_mark (Mark_id,Student_id,Course_id,Lecturer_id,Eligibility,Assignment001,Assignment002,Grade,QUIZ01,QUIZ02,QUIZ03,MID,SGPA,CGPA,FINAL_Practical,FINAL_Theory) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

                try(PreparedStatement stmt = conn.prepareStatement(upMarks)){
                    stmt.setNull(1, Types.INTEGER);
                    stmt.setString(2,SID);
                    stmt.setString(3,CID);
                    stmt.setString(4,userId);
                    stmt.setNull(5, Types.VARCHAR);
                    if((Double.isNaN(A1))||(Double.isNaN(A2))){
                        stmt.setNull(6, Types.DECIMAL);
                        stmt.setNull(7, Types.DECIMAL);
                    }else{
                        stmt.setDouble(6,A1);
                        stmt.setDouble(7,A2);
                    }
                    stmt.setNull(8, Types.VARCHAR);
                    stmt.setDouble(9,Q1);
                    stmt.setDouble(10,Q2);
                    stmt.setDouble(11,Q3);
                    if (Double.isNaN(MID)) {
                        stmt.setNull(12, Types.DECIMAL);
                    }else{
                        stmt.setDouble(12,MID);
                    }
                    stmt.setNull(13, Types.DECIMAL);
                    stmt.setNull(14, Types.DECIMAL);
                    if(Double.isNaN(F_Practical)){
                        stmt.setNull(15, Types.DECIMAL);
                    }else{
                        stmt.setDouble(15,F_Practical);
                    }
                    stmt.setDouble(16,F_Theory);


                    int rowsInserted = stmt.executeUpdate();
                    System.out.println(rowsInserted + "Rows inserted");

                    lblSuccess.setText(" Student marks successfully added to database ! ");


                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });
    }


}

package TECMIS.Lecturer.UploadCourseMaterials;

import TECMIS.Lecturer.Lecturer;
import TECMIS.MySqlCon;
import TECMIS.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UploadCourseMaterials extends Lecturer{

    Connection conn = MySqlCon.MysqlMethod();
    private JPanel pnlUploadCM;
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JTextField txtCName;
    private JTextField txtCredit;
    private JTextField txtAdminId;
    private JButton backButton;
    private JButton uploadButton;
    private JButton clearButton;
    private JTextField txtCID;
    private JLabel lblSuccess2;
    private JTextField txtLevel;
    private JTextField txtSem;

    private String userId;
    private String acc;
    private String CID;
    private String CourseName;
    private String AdminId;
    private int Credit;
    private int level;
    private int semester;

    public void upCourseMaterials(){
        userId = User.getUserId();
        acc = User.getAcc();

        add(pnlUploadCM);
        setSize(750, 500);
        setTitle("Upload Course Materials");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtCID.setText("");
                txtAdminId.setText("");
                txtCredit.setText("");
                txtCName.setText("");
                txtLevel.setText("");
                txtCredit.setText("");
                txtSem.setText("");
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
                CID = txtCID.getText();
                Credit = Integer.parseInt(txtCredit.getText());
                AdminId = txtAdminId.getText();
                CourseName = txtCName.getText();
                level = Integer.parseInt(txtLevel.getText());
                semester = Integer.parseInt(txtSem.getText());


                String upCD = "INSERT INTO Course_Detail (Course_id, Course_Name, Credit, Admin_id, Lecturer_id, Level, Semester) VALUES (?,?,?,?,?,?,?)";

                try(PreparedStatement stmt = conn.prepareStatement(upCD)){

                    stmt.setString(1,CID);
                    stmt.setString(2,CourseName);
                    stmt.setInt(3,Credit);
                    stmt.setString(4,AdminId);
                    stmt.setString(5,userId);
                    stmt.setInt(6,level);
                    stmt.setInt(7,semester);

                    int rowsInserted = stmt.executeUpdate();
                    System.out.println(rowsInserted + "Rows inserted");

                    // create a new column in Student_Grades for new subject

                    String updColumn = "ALTER TABLE Student_Grades ADD " +CID+ " DECIMAL(5,3)";

                    try(PreparedStatement pstmt2 = conn.prepareStatement(updColumn)){
                        pstmt2.executeUpdate();
                    }

                    lblSuccess2.setText(" New course material successfully added to database ! ");

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }
}

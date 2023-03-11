package TECMIS.Lecturer.UploadCourseMaterials;

import TECMIS.Lecturer.Lecturer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UploadCourseMaterials extends JFrame{
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

    private String userID;
    private String acc;
    private String CID;
    private String CourseName;
    private String AdminId;
    private int Credit;

    public UploadCourseMaterials(String userID,String acc){
        this.userID = userID;
        this.acc = acc;

        add(pnlUploadCM);
        setSize(700, 600);
        setTitle("Upload Course Materials");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtCID.setText("");
                txtAdminId.setText("");
                txtCredit.setText("");
                txtCName.setText("");
            }
        });


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Lecturer lecBack = new Lecturer(userID,acc);
                lecBack.setVisible(true);
                setVisible(false);
            }
        });


        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CID = txtCID.getText();
                Credit = Integer.parseInt(txtCredit.getText());
                AdminId = txtAdminId.getText();
                CourseName = txtCName.getText();

                String upCD = "INSERT INTO Course_Detail (Course_id, Course_Name, Credit, Admin_id, Lecturer_id) VALUES (?,?,?,?,?)";

                try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/LMSDB", "root", "");
                    PreparedStatement stmt = conn.prepareStatement(upCD)){

                    stmt.setString(1,CID);
                    stmt.setString(2,CourseName);
                    stmt.setInt(3,Credit);
                    stmt.setString(4,AdminId);
                    stmt.setString(5,userID);

                    int rowsInserted = stmt.executeUpdate();
                    System.out.println(rowsInserted + "Rows inserted");

                    lblSuccess2.setText(" New course material successfully added to database ! ");

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }
}

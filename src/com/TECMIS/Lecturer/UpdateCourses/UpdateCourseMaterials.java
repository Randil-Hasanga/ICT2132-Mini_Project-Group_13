package com.TECMIS.Lecturer.UpdateCourses;

import com.TECMIS.Lecturer.Lecturer;
import com.TECMIS.MySqlCon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateCourseMaterials extends JFrame{
    Connection conn = MySqlCon.MysqlMethod();
    private JPanel pnlUploadCM;
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JTextField txtCName;
    private JTextField txtCredit;
    private JTextField txtAdminId;
    private JButton backButton;
    private JButton updateButton;
    private JButton clearButton;
    private JTextField txtCID;
    private JLabel lblSuccess2;
    private JTextField txtLevel;
    private JTextField txtSem;

    private String CID;
    private String CourseName;
    private String AdminId;
    private int level;
    private int semester;
    private int Credit;

    public void UpdateCourse(){
        add(pnlUploadCM);
        setSize(750, 500);
        setTitle("Update Course Materials");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                CID = txtCID.getText();
                Credit = Integer.parseInt(txtCredit.getText());
                AdminId = txtAdminId.getText();
                CourseName = txtCName.getText();
                level = Integer.parseInt(txtLevel.getText());
                semester = Integer.parseInt(txtSem.getText());

                String updCD = "UPDATE Course_Detail " +
                        "SET Course_Name = ? , Credit = ? , Admin_id = ? , Level = ? , Semester = ? " +
                        "WHERE Course_id = ? ";

                try (PreparedStatement stmt = conn.prepareStatement(updCD)) {


                    stmt.setString(1, CourseName);
                    stmt.setInt(2, Credit);
                    stmt.setString(3, AdminId);
                    stmt.setInt(4, level);
                    stmt.setInt(5, semester);
                    stmt.setString(6, CID);

                    int rowsInserted = stmt.executeUpdate();
                    System.out.println(rowsInserted + "Rows Updated");

                    lblSuccess2.setText(" Course material successfully updated ! ");

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtCID.setText("");
                txtAdminId.setText("");
                txtCredit.setText("");
                txtCName.setText("");
                txtSem.setText("");
                txtLevel.setText("");

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

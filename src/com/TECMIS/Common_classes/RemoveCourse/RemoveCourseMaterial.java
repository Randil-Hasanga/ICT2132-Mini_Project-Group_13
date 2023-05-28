package com.TECMIS.Common_classes.RemoveCourse;

import com.TECMIS.Lecturer.Lecturer;
import com.TECMIS.MySqlCon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveCourseMaterial extends JFrame{

    Connection conn = MySqlCon.MysqlMethod();
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JTextField txtCID;
    private JButton deleteButton;
    private JButton backButton;
    private JButton clearButton;
    private JPanel pnlRemoveCourse;
    private JLabel lblDisplay;
    private String CID;


    public void RemoveCourse(){

        add(pnlRemoveCourse);
        setSize(750, 500);
        setTitle("Remove Course");
        setLocationRelativeTo(null);
        lblDisplay.setVisible(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CID = txtCID.getText();

                String sql = "DELETE FROM Course_Detail WHERE Course_id = ? ";

                try(PreparedStatement pstmt = conn.prepareStatement(sql)){
                    pstmt.setString(1,CID);
                    int rows = pstmt.executeUpdate();

                    lblDisplay.setVisible(true);
                    lblDisplay.setText(rows + " row(s) deleted.");



                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

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

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtCID.setText("");
            }
        });



    }
}

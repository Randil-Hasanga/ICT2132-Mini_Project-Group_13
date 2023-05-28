package TECMIS.Common_classes.RemoveCourse;

import TECMIS.Lecturer.Lecturer;
import TECMIS.MySqlCon;
import TECMIS.Student.Student;
import TECMIS.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveCourseMaterial extends User{

    Connection conn = MySqlCon.MysqlMethod();
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JTextField txtCID;
    private JButton deleteButton;
    private JButton backButton;
    private JButton clearButton;
    private JPanel pnlRemoveCourse;
    private JLabel lblDisplay;
    private String CID;
    private String acc;
    private String User;


    public void RemoveCourse(){

        acc = getAcc();
        User = getUserId();

        add(pnlRemoveCourse);
        setSize(750, 500);
        setTitle("Remove Course");
        setLocationRelativeTo(null);
        lblDisplay.setVisible(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to close?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    // Close the application
                    System.exit(0);
                }else {
                    // Do nothing (prevent the window from closing)
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

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
                if (acc.equals("lecturer")) {
                    Lecturer lecBack = new Lecturer();
                    lecBack.setVisible(true);
                    setVisible(false);
                    lecBack.methodLecturer();
                }
                else if(acc.equals("student")){
                    Student stuBack = new Student();
                    stuBack.setVisible(true);
                    setVisible(false);
                    stuBack.methodStudent();
                }
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

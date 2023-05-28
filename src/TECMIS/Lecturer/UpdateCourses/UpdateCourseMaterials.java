package TECMIS.Lecturer.UpdateCourses;

import TECMIS.Lecturer.Lecturer;
import TECMIS.MySqlCon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateCourseMaterials extends Lecturer{
    private Connection conn = MySqlCon.MysqlMethod();
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

                try {
                    conn.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });


    }
}

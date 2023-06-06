package TECMIS.Student;

import javax.swing.*;


import TECMIS.MySqlCon;
import TECMIS.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContactDetails extends Student{

    Connection conn = MySqlCon.MysqlMethod();

    private JPanel pnlUpdateCD;
    private JTextField textSID;
    private JButton backButton;
    private JButton updateButton;
    private JButton clearButton;
    private JLabel lblSID;
    private JLabel lblEmail;
    private JLabel lblSuccess;
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JLabel lblAl1;
    private JTextField txtAl1;
    private JLabel lblAl2;
    private JTextField txtAl2;
    private JTextField txtEmail;
    private JPanel pnl1;

    private String userId;
    private String acc;

    String SID;

    String Al1;

    String Al2;

    String Email;

    public void ContactDetails() {

        userId = User.getUserId();
        acc = User.getAcc();

        add(pnlUpdateCD);
        setSize(600, 600);
        setTitle("Update Contact Details");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);


//        lblAl1.setVisible(false);
//        lblAl2.setVisible(false);
//        lblEmail.setVisible(false);
//        txtAl1.setVisible(false);
//        txtAl2.setVisible(false);
//        txtEmail.setVisible(false);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student stBack = new Student();
                stBack.setVisible(true);
                setVisible(false);
                stBack.methodStudent();

            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textSID.setText("");
                txtAl1.setText("");
                txtAl2.setText("");
                txtEmail.setText("");

            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SID = textSID.getText();
                Al1 = txtAl1.getText();
                Al2 = txtAl2.getText();
                Email = txtEmail.getText();

// update query-------
                String updateCD = "UPDATE student SET Address_L1=?, Address_L2=?, Email=? WHERE User_id=?";

                try(PreparedStatement stmt = conn.prepareStatement(updateCD)){


                    stmt.setString(1,Al1);
                    stmt.setString(2,Al2);
                    stmt.setString(3,Email);
                    stmt.setString(4,SID);


                    int rowsInserted = stmt.executeUpdate();
                    System.out.println(rowsInserted + "Rows inserted");

                    lblSuccess.setText(" New Contact Details Successfully Added To Database ! ");

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });





    }
}

package com.TECMIS.Admin.DashBord.UserDashBord.CreateUser;

import com.TECMIS.Admin.DashBord.UserDashBord.createUser;
import com.TECMIS.MySqlCon;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class createTechnicalOffice extends JFrame{

    Connection conn = MySqlCon.MysqlMethod();
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JPanel pnlPic;
    private JLabel lblPic;
    private JButton logOutButton;
    private JTextField txtUser;
    private JTextField txtDep;
    private JTextField txtFname;
    private JTextField txtLname;
    private JComboBox dropGender;
    private JTextField txtAd1;
    private JTextField txtAd2;
    private JTextField txtEmail;
    private JPasswordField txtPwd;
    private JButton submitButton;
    private JButton clearButton;
    private JPanel TecOffPnl;
    private JButton chooseDate;
    private JButton backButton;
    private JButton btnChoosefile;

    private Date selectedDate;
    private String formattedDate;
    private File proPic;
    private String gender;

    private String user_id;
    private String depId;
    private String fname;
    private String lname;
    private String Ad1;
    private String Ad2;
    private String email;
    private String password;


    public createTechnicalOffice() {



    }

    public void methodTecOffi(){

        add(TecOffPnl);
        setVisible(true);
        setSize(750,500);
        setTitle("LMS Software");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        dropGender.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gender = dropGender.getSelectedItem().toString();
            }
        });
        btnChoosefile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    JFileChooser picOpen = new JFileChooser();
                    int result = picOpen.showOpenDialog(null);
                    if(result == JFileChooser.APPROVE_OPTION){
                        proPic = picOpen.getSelectedFile();
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        chooseDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frame = new JFrame("Choose Date");
                JDateChooser dateChooser = new JDateChooser();
                frame.add(dateChooser);
                frame.setType(Window.Type.UTILITY);
                frame.pack();
                frame.setLocationRelativeTo(null); // Center the frame on the screen
                frame.setVisible(true);

                dateChooser.addPropertyChangeListener("date", new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        if (evt.getPropertyName().equals("date")) {
                            selectedDate = dateChooser.getDate(); // get selected date

                            // Format the selected date as YYYY-MM-DD
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            formattedDate = sdf.format(selectedDate);
                            System.out.println(selectedDate);
                            System.out.println(formattedDate);
                            frame.dispose(); // Close the frame after selecting the date
                        }
                    }
                });

            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                user_id = txtUser.getText();
                fname = txtFname.getText();
                lname = txtLname.getText();
                Ad1 = txtAd1.getText();
                Ad2 = txtAd2.getText();
                email = txtEmail.getText();
                password = String.valueOf(txtPwd.getPassword());
                depId = txtDep.getText();


                String sql = "INSERT INTO Technical_officer " +
                        "(User_id,Dep_id,FName,LName,Gender,Address_L1,Address_L2,DOB,Email,Pro_pic,Password) " +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

                try{
                    PreparedStatement pstmt = conn.prepareStatement(sql);

                    byte[] imageData = Files.readAllBytes(proPic.toPath());
                    pstmt.setString(1,user_id);
                    pstmt.setString(2,depId);
                    pstmt.setString(3,fname);
                    pstmt.setString(4,lname);
                    pstmt.setString(5,gender);
                    pstmt.setString(6,Ad1);
                    pstmt.setString(7,Ad2);
                    pstmt.setString(8,formattedDate);
                    pstmt.setString(9,email);
                    pstmt.setBytes(10,imageData);
                    pstmt.setString(11,password);


                    pstmt.executeUpdate();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createUser back = new createUser();
                back.methodUser();
                back.setVisible(true);
                setVisible(false);

            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtFname.setText("");
                txtLname.setText("");
                txtAd1.setText("");
                txtAd2.setText("");
                txtUser.setText("");
                txtEmail.setText("");
                txtPwd.setText("");
                txtDep.setText("");
            }
        });
    }
}

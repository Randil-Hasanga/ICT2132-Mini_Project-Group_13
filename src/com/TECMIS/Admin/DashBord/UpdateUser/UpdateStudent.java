package com.TECMIS.Admin.DashBord.UpdateUser;

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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateStudent extends JFrame{

    Connection conn = MySqlCon.MysqlMethod();
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JTextField txtSid;
    private JButton submitButton;
    private JTextField txtFn;
    private JTextField txtLn;
    private JTextField txtA1;
    private JTextField txtA2;
    private JTextField txtEm;
    private JPasswordField txtpwd;
    private JButton dateButton;
    private JButton profileButton;
    private JButton submitButton1;
    private JButton clearButton;
    private JTextField txtSt;
    private JPanel UpdateStudentPnl;
    private JButton backButton;
    private JPanel newPnl;
    private JLabel JlaDisply;

    private Date selectedDate;
    private String formattedDate;
    private File proPic;


    private String UserId;

    private String fname;
    private String lname;
    private String Ad1;
    private String Ad2;
    private String email;
    private String Stype;
    private String password;
    private byte[] imageData;











    public void UpdateStudentMethod(){

        add(UpdateStudentPnl);
        setVisible(true);
        setSize(800,600);
        setTitle("LMS Software");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        newPnl.setVisible(false);


        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserId = txtSid.getText();
                newPnl.setVisible(true);

                String sql = "SELECT * FROM Student WHERE User_id = ?";

                try{
                    PreparedStatement ss = conn.prepareStatement(sql);
                    ss.setString(1,UserId);
                    ResultSet rs = ss.executeQuery();

                    while(rs.next()){
                        fname = rs.getString("FName");
                        lname = rs.getString("LName");
                        Ad1 = rs.getString("Address_L1");
                        Ad2 = rs.getString("Address_L2");
                        email = rs.getString("Email");
                        Stype = rs.getString("S_type");
                        password = rs.getString("Password");


                    }

                    txtFn.setText(fname);
                    txtLn.setText(lname);
                    txtA1.setText(Ad1);
                    txtA2.setText(Ad2);
                    txtEm.setText(email);
                    txtSt.setText(Stype);
                    txtpwd.setText(password);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        dateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame frame = new JFrame("Choose Date");
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


        profileButton.addActionListener(new ActionListener() {
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

        submitButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fname = txtFn.getText();
                lname = txtLn.getText();
                Ad1 = txtA1.getText();
                Ad2 = txtA2.getText();
                email = txtEm.getText();
                Stype = txtSt.getText();
                password = String.valueOf(txtpwd.getPassword());

                String up = "Update Student " +
                        "SET FName = ?, LName = ?, Address_L1 = ?, Address_L2 = ?, DOB = ?, Email = ?, Pro_pic = ?, S_type = ?, Password = ? " +
                        "WHERE User_id = ?";

                try{
                    imageData = Files.readAllBytes(proPic.toPath());
                    PreparedStatement sss = conn.prepareStatement(up);
                    sss.setString(1,fname);
                    sss.setString(2,lname);
                    sss.setString(3,Ad1);
                    sss.setString(4,Ad2);
                    sss.setString(5,formattedDate);
                    sss.setString(6,email);
                    sss.setBytes(7,imageData);
                    sss.setString(8,Stype);
                    sss.setString(9,password);
                    sss.setString(10,UserId);

                    sss.executeUpdate();

                    JlaDisply.setText("Database successfully updated !");

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });



        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtFn.setText("");
                txtLn.setText("");
                txtA1.setText("");
                txtA2.setText("");
                txtEm.setText("");
                txtSt.setText("");
                txtpwd.setText("");

            }
        });



        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateUserDashbord back = new UpdateUserDashbord();
                back.setVisible(true);
                setVisible(false);
                back.UpdateUserMethod();
            }
        });



    }
}

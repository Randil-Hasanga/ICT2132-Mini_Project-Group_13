package TECMIS.TechnicalOfficer.TechnicalOfficer;

import TECMIS.MySqlCon;
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
import java.util.Arrays;
import java.util.Date;

public class UpdateProfile extends TechnicalOfficer{

    Connection conn = MySqlCon.MysqlMethod();
    private JPanel pnlTOProfile;
    private JTextArea textAreafacultyofTechnologyManagementSystem;
    private JTextField txtFname;
    private JTextField txtLname;
    private JRadioButton maleRbutton;
    private JRadioButton femaleRbutton;
    private JTextField txtAD1;
    private JTextField txtAD2;
    private JTextField txtEmail;
    private JTextField txtPosition;
    private JButton chooseDate;
    private JButton chooseImage;
    private JButton btnBack;
    private JButton btnUpdate;
    private JButton btnClr;
    private JLabel lblUpdatedSuccess;
    private JLabel lblError;
    private JLabel lblDepID;
    private JTextField textFieldDepID;
    private JLabel lblPW;
    private JTextField textFieldPassword;


    private File proPic;
    private Date selectedDate;
    private String formattedDate;
    private String gender;
    private String Fname;
    private String Lname;
    private String AL1;
    private String AL2;
    private String user_id = TechnicalOfficer.getUserId();;
    private String acc = TechnicalOfficer.getAcc();
    private String email;
    private String depID;
    private String pw;
    private byte[] imageData;

    public void UpdateProfile() {

        add(pnlTOProfile);
        setSize(750, 500);
        setTitle("Update profile");
        setLocationRelativeTo(null);
        lblUpdatedSuccess.setVisible(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        user_id = TechnicalOfficer.getUserId();

        String sql = " SELECT * FROM Technical_officer WHERE User_id = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,user_id);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next())
            {
                depID = rs.getString("Dep_id");
                Fname = rs.getString("FName");
                Lname = rs.getString("LName");
                AL1 = rs.getString("Address_L1");
                AL2 = rs.getString("Address_L2");
                email = rs.getString("Email");
                pw = rs.getString("Password");



            }
            textFieldDepID.setText(depID);
            txtFname.setText(Fname);
            txtLname.setText(Lname);
            txtAD1.setText(AL1);
            txtAD2.setText(AL2);
            txtEmail.setText(email);
            textFieldPassword.setText(pw);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (maleRbutton.isSelected()) {
                    gender = "Male";
                } else if (femaleRbutton.isSelected()) {
                    gender = "Female";
                }
            }
        };
        maleRbutton.addActionListener(listener);
        femaleRbutton.addActionListener(listener);

        btnClr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldDepID.setText("");
                txtFname.setText("");
                txtLname.setText("");
                txtAD1.setText("");
                txtAD2.setText("");
                txtEmail.setText("");
                textFieldPassword.setText("");
            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TechnicalOfficer TOBack = new TechnicalOfficer();
                TOBack.setVisible(true);
                setVisible(false);
                TOBack.methodTechnicalOfficer();

                try {
                    conn.close();
                    System.out.println(" Connection is Closed ");
                } catch (SQLException ex) {
                    System.out.println(" Connection Closed is Unsuccessfully "+ex.getMessage());
                }
            }
        });

        chooseImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser imgOpen = new JFileChooser();
                    int result = imgOpen.showOpenDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        proPic = imgOpen.getSelectedFile();
                    }
                } catch (Exception ex) {
                    System.out.println(" Error , Please Re-enter the image "+ex.getMessage());
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



        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                depID = textFieldDepID.getText();
                Fname = txtFname.getText();
                Lname = txtLname.getText();
                AL1 = txtAD1.getText();
                AL2 = txtAD2.getText();
                email = txtEmail.getText();
                pw = textFieldPassword.getText();

                if ( (formattedDate.isEmpty()) || (Fname.isEmpty()) || (Lname.isEmpty()) || (AL1.isEmpty()) || (AL2.isEmpty()) || (email.isEmpty()) || (pw.isEmpty())) {
                    lblError.setVisible(true);
                    lblError.setText("Fill all the fields to proceed !");

                } else {

                    String ProfSql = "UPDATE Technical_officer SET Dep_id = ? ,FName = ?, LName = ?, Gender = ?, Address_L1 = ?, Address_L2 = ?, " +
                            "DOB = ?, Email = ?, Pro_pic = ? WHERE User_id = ?";

                    try (PreparedStatement pstmt = conn.prepareStatement(ProfSql)) {

                         imageData = Files.readAllBytes(proPic.toPath());

                        pstmt.setString(1,depID);
                        pstmt.setString(2, Fname);
                        pstmt.setString(3, Lname);
                        pstmt.setString(4, gender);
                        pstmt.setString(5, AL1);
                        pstmt.setString(6, AL2);
                        pstmt.setString(7, formattedDate);
                        pstmt.setString(8, email);
                        pstmt.setString(9, Arrays.toString(imageData));
                        pstmt.setString(10, user_id);

                        int rows = pstmt.executeUpdate();

                        System.out.println(rows + " row(s) updated.");

                        lblUpdatedSuccess.setVisible(true);
                        lblUpdatedSuccess.setText(" Update is Successful");


                    } catch (SQLException ex) {
                        System.out.println(" Update is successful ");
                    } catch (IOException ex) {
                        System.out.println(" Update is Unsuccessful " + ex.getMessage());
                    }

                }
            }
        });

    }

    }


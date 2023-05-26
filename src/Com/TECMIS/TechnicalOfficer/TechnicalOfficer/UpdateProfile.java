package Com.TECMIS.TechnicalOfficer.TechnicalOfficer;

import Com.TECMIS.MySqlCon;
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


    private File proPic;
    private Date selectedDate;
    private String formattedDate;
    private String gender;
    private String Fname;
    private String Lname;
    private String position;
    private String AL1;
    private String AL2;
    private String user_id = TechnicalOfficer.getUserId();;
    private String acc = TechnicalOfficer.getAcc();
    private String email;







    public void UpdateProfile() {
        add(pnlTOProfile);
        setSize(750, 500);
        setTitle("Update profile");
        setLocationRelativeTo(null);
        lblUpdatedSuccess.setVisible(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JFileChooser picOpen = new JFileChooser();

        btnClr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtFname.setText("");
                txtLname.setText("");
                txtAD1.setText("");
                txtAD2.setText("");
                txtPosition.setText("");
                txtEmail.setText("");
            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TechnicalOfficer TOBack = new TechnicalOfficer();
                TOBack.setVisible(true);
                setVisible(false);
                TOBack.methodTechnicalOfficer();
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


        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Fname = txtFname.getText();
                Lname = txtLname.getText();
                AL1 = txtAD1.getText();
                AL2 = txtAD2.getText();
                position = txtPosition.getText();
                email = txtEmail.getText();

                if((proPic == null) || (formattedDate.isEmpty()) || (Fname.isEmpty()) || (Lname.isEmpty()) || (AL1.isEmpty()) || (AL2.isEmpty()) || (position.isEmpty()) || (email.isEmpty())){
                    lblUpdatedSuccess.setVisible(true);
                    lblUpdatedSuccess.setText("Fill all the fields to proceed !");
                }

                String ProfSql = "UPDATE Technical_Officer SET FName = ?, LName = ?, Gender = ?, Address_L1 = ?, Address_L2 = ?, " +
                        "DOB = ?, Email = ?, Pro_pic = ?, Position_ = ? " +
                        "WHERE User_id = ?";

                try(PreparedStatement pstmt = conn.prepareStatement(ProfSql)){

                    byte[] imageData = Files.readAllBytes(proPic.toPath());

                    pstmt.setString(2,Fname);
                    pstmt.setString(3,Lname);
                    pstmt.setString(4,gender);
                    pstmt.setString(5,AL1);
                    pstmt.setString(6,AL2);
                    pstmt.setString(7,formattedDate);
                    pstmt.setString(8,email);
                    pstmt.setString(9,position);
                    pstmt.setString(1,user_id);

                    int rows = pstmt.executeUpdate();

                    System.out.println(rows + " row(s) updated.");


                } catch (SQLException ex) {
                    System.out.println(" Update is successful ");
                } catch (IOException ex) {
                    System.out.println(" Update is Unsuccessful "+ex.getMessage());
                }
                finally {
                    try {
                        conn.close();
                        System.out.println(" Connection is Closed ");
                    } catch (SQLException ex) {
                        System.out.println(" Connection Closed is Unsuccessful "+ex.getMessage());
                    }
                }
            }
        });

    }

    }


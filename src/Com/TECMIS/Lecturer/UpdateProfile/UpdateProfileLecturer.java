package Com.TECMIS.Lecturer.UpdateProfile;

import Com.TECMIS.Lecturer.Lecturer;
import Com.TECMIS.MySqlCon;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

public class UpdateProfileLecturer extends Lecturer{

    private Connection conn = MySqlCon.MysqlMethod();
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JTextField txtFname;
    private JTextField txtLname;
    private JTextField txtAD1;
    private JTextField txtAd2;
    private JButton chooseDateButton;
    private JTextField txtPOS;
    private JButton choosePic;
    private JPanel pnlUpdateProfile;
    private JButton backButton;
    private JButton updateButton;
    private JButton clearButton;
    private JPanel pnlCalendar;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JLabel lblDisplay;
    private JTextField txtEmail;
    private File proPic;
    private Date selectedDate;
    private String formattedDate;
    private String gender;
    private String Fname;
    private String Lname;
    private String position;
    private String AL1;
    private String AL2;
    private String user_id = Lecturer.getUserId();;
    private String acc = Lecturer.getAcc();
    private String email;


    public void UpdateProfile(){
        add(pnlUpdateProfile);
        setSize(750, 500);
        setTitle("Update profile");
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

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtFname.setText("");
                txtLname.setText("");
                txtAD1.setText("");
                txtAd2.setText("");
                txtPOS.setText("");
                txtEmail.setText("");

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

        choosePic.addActionListener(new ActionListener() {
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

        chooseDateButton.addActionListener(new ActionListener() {
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
                if(maleRadioButton.isSelected()){
                    gender = "Male";
                }else if(femaleRadioButton.isSelected()){
                   gender = "Female";
                }
            }
        };
        maleRadioButton.addActionListener(listener);
        femaleRadioButton.addActionListener(listener);


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Fname = txtFname.getText();
                Lname = txtLname.getText();
                AL1 = txtAD1.getText();
                AL2 = txtAd2.getText();
                position = txtPOS.getText();
                email = txtEmail.getText();

                if((proPic == null) || (formattedDate.isEmpty()) || (Fname.isEmpty()) || (Lname.isEmpty()) || (AL1.isEmpty()) || (AL2.isEmpty()) || (position.isEmpty()) || (email.isEmpty())){
                    lblDisplay.setVisible(true);
                    lblDisplay.setText("Fill all the fields to proceed !");
                }

                String sql = "UPDATE Lecturer SET FName = ?, LName = ?, Gender = ?, Address_L1 = ?, Address_L2 = ?, " +
                        "DOB = ?, Email = ?, Pro_pic = ?, Position = ? " +
                        "WHERE User_id = ?";

                try(PreparedStatement pstmt = conn.prepareStatement(sql)){

                    byte[] imageData = Files.readAllBytes(proPic.toPath());

                    pstmt.setString(1,Fname);
                    pstmt.setString(2,Lname);
                    pstmt.setString(3,gender);
                    pstmt.setString(4,AL1);
                    pstmt.setString(5,AL2);
                    pstmt.setString(6,formattedDate);
                    pstmt.setString(7,email);
                    pstmt.setBytes(8,imageData);
                    pstmt.setString(9,position);
                    pstmt.setString(10,user_id);

                    int rows = pstmt.executeUpdate();

                    System.out.println(rows + " row(s) updated.");


                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
    }
}

package TECMIS.TechnicalOfficer.TechnicalOfficer;

import TECMIS.User;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UploadMedical extends TechnicalOfficer {


    private JPanel pnlUploadMedical;
    private Date selectedDate;
    private String formattedDate;

    private Date selectedDate1;
    private String formattedDate1;
    private JTextArea informationSystemTextArea;
    private JLabel lblMedID;
    private JTextField txtMedID;
    private JLabel lblStartDate;
    private JDateChooser JDateChooserStartDate;
    private JLabel lblEndDate;
    private JDateChooser JDateChooserEndDate;
    private JLabel lblSID;
    private JTextField txtSID;
    private JLabel lblMedCon;
    private JTextField txtMedCon;
    private JLabel lblSuccess;
    private JButton btnBack;
    private JButton btnUpload;
    private JButton btnClr;
    private JButton chooseDateButton;
    private JButton chooseDateButton1;
    private JLabel lblErrorMsg;


    private String userId;
    private String acc;
    private String MedicalID;
    private String Start_Date;
    private String End_Date;
    private String StudentID;
    private String MedCondition;


    public void uploadMedical() {

        userId = User.getUserId();
        acc = User.getAcc();

        add(pnlUploadMedical);
        setSize(700, 600);
        setTitle("Upload Medicals");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


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
        chooseDateButton1.addActionListener(new ActionListener() {
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
                            selectedDate1 = dateChooser.getDate(); // get selected date

                            // Format the selected date as YYYY-MM-DD
                            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                            formattedDate1 = sdf1.format(selectedDate1);
                            System.out.println(selectedDate1);
                            System.out.println(formattedDate1);
                            frame.dispose(); // Close the frame after selecting the date
                        }
                    }
                });

            }
        });

        btnClr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMedID.setText("");
                txtSID.setText("");
                txtMedCon.setText("");
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
                    System.out.println(" Unsuccessfully Connection Closed !");
                }
            }
        });


        btnUpload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MedicalID = txtMedID.getText();
                StudentID = txtSID.getText();
                MedCondition = txtMedCon.getText();

                if ((MedicalID.isEmpty()) || (StudentID.isEmpty()) || (MedCondition.isEmpty())) {
                    lblErrorMsg.setText(" Please Fill Out the all Fields ! ");

                } else {

                    String uploadMed = "INSERT INTO Medical (Medical_id, Student_id,Start_Date,End_Date,Medical_Condition) VALUES (?,?,?,?,?)";

                    try (PreparedStatement pstmt = conn.prepareStatement(uploadMed)) {

                        pstmt.setString(1, MedicalID);
                        pstmt.setString(2, StudentID);
                        pstmt.setString(3, formattedDate);
                        pstmt.setString(4, formattedDate1);
                        pstmt.setString(5, MedCondition);

                        int rowsInserted = pstmt.executeUpdate();
                        System.out.println(rowsInserted + "Rows inserted");

                        lblSuccess.setText(" New Medical successfully Upload to database ! ");

                    } catch (SQLException ex) {
                        System.out.println(" Medical Uploaded is  Unsuccessfully  " + ex.getMessage());
                    }

                }
            }
        });

    }

}

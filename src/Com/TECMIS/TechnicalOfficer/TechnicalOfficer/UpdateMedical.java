package Com.TECMIS.TechnicalOfficer.TechnicalOfficer;

import Com.TECMIS.MySqlCon;
import Com.TECMIS.User;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateMedical extends TechnicalOfficer {

    Connection conn = MySqlCon.MysqlMethod();
    private JPanel pnlUpdateMedical;
    private JLabel lblUpdateMedicalID;
    private JTextField textFieldupdMedicalID;
    private JLabel lblupdStartDate;

    private Date selectedDate;
    private String formattedDate;
    private Date selectedDate1;
    private String formattedDate1;
    private JLabel lblupdEndDate;

    private JLabel lblupdStudentID;
    private JTextField textFieldupdStudentID;
    private JLabel lblupdMedCondition;
    private JTextField textFieldupdMedCondition;
    private JLabel lblupdSuccess;


    private JButton btnBack;
    private JButton btnUpdate;
    private JButton btnClr;
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JButton chooseDateButton;
    private JButton chooseDateButton1;
    private JDateChooser JDateChooser1;
    private JDateChooser JDateChooser2;


    private String userId;
    private String acc;
    private String MedicalID;
    private String Start_Date;
    private String End_Date;
    private String StudentID;
    private String MedCondition;






    public void UpdateMedical() {

        userId = User.getUserId();
        acc = User.getAcc();

        add(pnlUpdateMedical);
        setSize(700, 600);
        setTitle("Update Medicals");
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
                            SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
                            formattedDate = sdf3.format(selectedDate);
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
                textFieldupdMedicalID.setText("");
                textFieldupdStudentID.setText("");
                textFieldupdMedCondition.setText("");
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


        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MedicalID = textFieldupdMedicalID.getText();
                StudentID = textFieldupdStudentID.getText();
                MedCondition = textFieldupdMedCondition.getText();

                String updMed = "UPDATE Medical SET  Student_id = ?,Start_Date = ?,End_Date = ? ,Medical_Condition = ? WHERE Medical_id = ? ";

                try (PreparedStatement stmt = conn.prepareStatement(updMed)) {

                    stmt.setString(1, MedicalID);
                    stmt.setString(2, formattedDate);
                    stmt.setString(3, formattedDate1);
                    stmt.setString(4, StudentID);
                    stmt.setString(5, MedCondition);


                    int rowsInserted = stmt.executeUpdate();
                    System.out.println(rowsInserted + "Rows inserted");

                    lblupdSuccess.setText(" New Medical successfully Updated to database ! ");

                } catch (SQLException ex) {
                    System.out.println(" Update is Unsuccessful"+ex.getMessage());
                }
                finally {
                    try {
                        conn.close();
                        System.out.println("Connection is Closed ");
                    } catch (SQLException ex) {
                        System.out.println(" Connection Closed is Unsuccessful "+ex.getMessage());
                    }
                }
            }


        });


    }

}

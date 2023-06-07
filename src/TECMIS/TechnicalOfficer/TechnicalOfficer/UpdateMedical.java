package TECMIS.TechnicalOfficer.TechnicalOfficer;

import TECMIS.MySqlCon;
import TECMIS.User;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateMedical extends TechnicalOfficer {

    Connection conn = MySqlCon.MysqlMethod();
    private JPanel pnlUpdateMedical;
    private JLabel lblUpdateMedicalID;
    private JTextField textFieldupdMedicalID;

    private Date selectedDate;
    private String formattedDate;
    private Date selectedDate1;
    private String formattedDate1;

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
    private JButton btnSubmit;
    private JPanel pnlMedID;
    private JPanel pnlDetail;
    private JLabel lblStartDate;
    private JLabel lblEndDate;
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
        pnlDetail.setVisible(false);
        pnlMedID.setVisible(true);



        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MedicalID = textFieldupdMedicalID.getText();
                pnlDetail.setVisible(true);

                String sql = " SELECT * FROM Medical WHERE Medical_id = ?";
                try {
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1,MedicalID);

                    ResultSet rs = pstmt.executeQuery();
                    while(rs.next())
                    {
                        StudentID = rs.getString("Student_id");
                        Start_Date = rs.getString("Start_Date");
                        lblStartDate.setText(" Start Date : "+Start_Date);
                        End_Date = rs.getString("End_Date");
                        lblEndDate.setText(" End Date : "+End_Date);
                        MedCondition = rs.getString("Medical_Condition");

                    }
                    textFieldupdStudentID.setText(StudentID);
                    textFieldupdMedCondition.setText(MedCondition);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });





        btnClr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

                try {
                    conn.close();
                    System.out.println(" Connection is Closed ");
                } catch (SQLException ex) {
                    System.out.println(" Connection close is Unsuccessful "+ex.getMessage());
                }
            }
        });


        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentID = textFieldupdStudentID.getText();
                MedCondition = textFieldupdMedCondition.getText();

                if ((MedicalID.isEmpty()) || (StudentID.isEmpty()) || (MedCondition.isEmpty())) {
                    lblupdSuccess.setText(" Please Fill Out the all Fields ! ");

                } else {

                    String updMed = "UPDATE Medical SET  Student_id = ?,Medical_Condition = ? WHERE Medical_id = ? ";

                    try (PreparedStatement stmt = conn.prepareStatement(updMed)) {

                        stmt.setString(1, StudentID);
                        stmt.setString(2, MedCondition);
                        stmt.setString(3, MedicalID);


                        int rowsInserted = stmt.executeUpdate();
                        System.out.println(rowsInserted + "Rows inserted");

                        lblupdSuccess.setText(" New Medical successfully Updated to database ! ");

                    } catch (SQLException ex) {
                        System.out.println(" Update is Unsuccessful" + ex.getMessage());
                    } finally {
                        try {
                            conn.close();
                            System.out.println("Connection is Closed ");
                        } catch (SQLException ex) {
                            System.out.println(" Connection Closed is Unsuccessful " + ex.getMessage());
                        }
                    }
                }
            }


        });


    }

}

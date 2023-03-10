package TECMIS.Lecturer.StudentDetails;

import TECMIS.Lecturer.Lecturer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class StudentDetails extends JFrame{

    String sid;
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JTextField txtSID;
    private JButton btnSearch;
    private JTable tblStudentDetails;
    private JButton btnBack;
    private JPanel pnlStudentDetails;
    private JButton clearButton;

    private String userId;
    private String acc;


    public StudentDetails(String userId,String acc){
        this.userId = userId;
        this.acc = acc;

        add(pnlStudentDetails);
        setSize(600, 600);
        setTitle("Student Details");
        tblStudentDetails.setEnabled(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtSID.setText("");
            }
        });


        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Lecturer lecBack = new Lecturer(userId,acc);
                lecBack.setVisible(true);
                setVisible(false);
            }
        });




        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sid = txtSID.getText();
                String stdTable = "SELECT User_id,CONCAT(FName,' ',LName) AS Full_Name,Gender,CONCAT(Address_L1,' ',Address_L2) AS Address, DOB, Email, S_type AS Student_type,Lecturer_id AS LecturerId_mentor FROM Student WHERE User_Id = ?";

                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/LMSDB", "root", "")) {
                    try (PreparedStatement pstmt = conn.prepareStatement(stdTable)) {
                        pstmt.setString(1, sid);
                        ResultSet rs = pstmt.executeQuery();

                        DefaultTableModel tableModel = new DefaultTableModel();
                        tblStudentDetails.setModel(tableModel);

                        tableModel.addColumn("");
                        tableModel.addColumn("");

                        ResultSetMetaData rsmd = rs.getMetaData();
                        int columntCount = rsmd.getColumnCount();

                        while(rs.next()){
                            for(int i = 1; i <= columntCount; i++){
                                Object[] rowData = new Object[2];
                                rowData[0] = rsmd.getColumnName(i);
                                rowData[1] = rs.getObject(i);
                                tableModel.addRow(rowData);
                            }
                        }
                        rs.close();
                        pstmt.close();
                        conn.close();


                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } catch (SQLException ez) {
                    throw new RuntimeException(ez);
                }
            }
        });
    }



}

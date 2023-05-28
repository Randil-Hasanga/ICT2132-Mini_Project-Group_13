package com.TECMIS.Lecturer.StudentDetails;

import com.TECMIS.Lecturer.Lecturer;
import com.TECMIS.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class StudentDetails extends Lecturer{

    Connection conn = com.TECMIS.MySqlCon.MysqlMethod();
    String sid;
    private JTextField txtSID;
    private JButton btnSearch;
    private JTable tblStudentDetails;
    private JButton btnBack;
    private JPanel pnlStudentDetails;
    private JButton clearButton;
    private JTextArea facultyOfTechnologyManagementTextArea;

    private String userId;
    private String acc;


    public void viewStudentDetails(){
        userId = User.getUserId();
        acc = User.getAcc();

        add(pnlStudentDetails);
        setSize(750, 500);
        setTitle("Student Details");
        tblStudentDetails.setEnabled(false);
        tblStudentDetails.setVisible(false);
        setLocationRelativeTo(null);
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
                Lecturer lecBack = new Lecturer();
                lecBack.setVisible(true);
                setVisible(false);
                lecBack.methodLecturer();
            }
        });




        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tblStudentDetails.setVisible(true);
                sid = txtSID.getText();
                String stdTable = "SELECT User_id,CONCAT(FName,' ',LName) AS Full_Name,Gender,CONCAT(Address_L1,' ',Address_L2) AS Address, DOB, Email, S_type AS Student_type,Lecturer_id AS LecturerId_mentor FROM Student WHERE User_Id = ?";

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
            }
        });
    }



}

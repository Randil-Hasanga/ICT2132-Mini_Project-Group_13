package TECMIS.Student;

import TECMIS.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class CourseDetails extends Student{
    private JPanel pnlCourseDetails;
    private JTextField txtSid;
    private JButton searchButton;
    private JTable CourseDetailsTable;
    private JButton backButton;
    private JButton clearButton1;
    private JTextArea facultyOfTechnologyManagementTextArea;
    private String User_id;
    private String acc;
    private String SID;


    public void viewCourseDetails() {


        setTitle("Student Details");
        add(pnlCourseDetails);
        setSize(750, 500);
        CourseDetailsTable.setEnabled(false);
        CourseDetailsTable.setVisible(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CourseDetailsTable.setVisible(true);
                SID = txtSid.getText();

                String cdTable ="SELECT Course_id,Course_Name,Credit,Level,Semester FROM Course_Detail WHERE Course_id = ?";

                try (PreparedStatement pstmt = conn.prepareStatement(cdTable)){
                    pstmt.setString(1, SID);
                    ResultSet rs = pstmt.executeQuery();

                    DefaultTableModel tableModel = new DefaultTableModel();
                    CourseDetailsTable.setModel(tableModel);

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

                } catch (SQLException ex) {
                    System.out.println("SQL Error" + ex.getMessage());
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student stuBack = new Student();
                stuBack.setVisible(true);
                setVisible(false);
                stuBack.methodStudent();


            }
        });
        clearButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtSid.setText("");

            }
        });

    }
    }


package TECMIS.Student;

import TECMIS.MySqlCon;
import TECMIS.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class StudentMedical extends JFrame{

    Connection connection= MySqlCon.MysqlMethod();
    private JPanel pnlStuMedi;
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JTextField txtSID;
    private JButton btnSearch;
    private JTable tblStuMedi;
    private JPanel pnlTable;
    private JButton btnback;
    private JButton btnclear;

    private String userID;
    private String acc;
    private String SID;


    public void StudentMedicals(){

        acc = User.getAcc();
        userID = User.getUserId();

        add(pnlStuMedi);
        setSize(700,500);
        setTitle("Student Medical");
        tblStuMedi.setEnabled(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SID= txtSID.getText();

                String sql = "SELECT Medical.Medical_id, Medical.Student_id, Medical.Start_Date, Medical.End_Date, Medical.Medical_Condition FROM Medical,Student WHERE (Student.User_id = Medical.Student_id) AND Student.User_id = ?";

                try {
                    PreparedStatement pstmt= connection.prepareStatement(sql);
                    pstmt.setString(1,SID);
                    ResultSet rs3 = pstmt.executeQuery();

                    DefaultTableModel tableModel3 = new DefaultTableModel();
                    tblStuMedi.setModel(tableModel3);

                    ResultSetMetaData rsmd3 = rs3.getMetaData();
                    int columntCount3 = rsmd3.getColumnCount();


                    for (int i = 1; i <= columntCount3; i++) {
                        tableModel3.addColumn(rsmd3.getColumnName(i));
                    }

                    while (rs3.next()) {
                        Object[] rowData = new Object[columntCount3];
                        for (int i = 1; i <= columntCount3; i++) {
                            rowData[i-1] = rs3.getObject(i);
                        }
                        tableModel3.addRow(rowData);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
        btnback.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student stuBack = new Student();
                stuBack.setVisible(true);
                setVisible(false);
                stuBack.methodStudent();

            }
        });

        btnclear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtSID.setText("");

            }
        });

    }
}

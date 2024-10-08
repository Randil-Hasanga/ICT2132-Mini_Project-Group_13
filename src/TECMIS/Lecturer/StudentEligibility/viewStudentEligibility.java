package TECMIS.Lecturer.StudentEligibility;

import TECMIS.Lecturer.Lecturer;
import TECMIS.MySqlCon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;

public class viewStudentEligibility extends Lecturer {
    private Connection conn = MySqlCon.MysqlMethod();
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JTextField txtSID;
    private JButton searchButton;
    private JLabel lblDisplay;
    private JScrollPane pnlScroll;
    private JTable tblEligibility;
    private JPanel pnlEG;
    private JButton backButton;
    private JButton clearButton;

    private String SID;


    public void viewEligibility(){
        add(pnlEG);
        setSize(750, 500);
        setTitle("Student Eligibility");
        setLocationRelativeTo(null);
        tblEligibility.setEnabled(false);
        tblEligibility.setVisible(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        lblDisplay.setVisible(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to close?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtSID.setText("");
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

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SID = txtSID.getText();
                tblEligibility.setVisible(true);

                String sql = "SELECT " +
                        "Attendance.Course_id, " +
                        "CASE WHEN COUNT(CASE WHEN Attendance.Status_ = 'Present' THEN 1 END) / COUNT(*) * 100 > 80 THEN 'Eligible' ELSE 'Not Eligible' END AS Attendance_Eligibility," +
                        "Exam_mark.eg AS CA_Eligibility " +
                        "FROM Attendance,Exam_mark " +
                        "WHERE (Attendance.Student_id = Exam_mark.Student_id) AND Attendance.Student_id = ?" +
                        "GROUP BY Attendance.Course_id, Attendance.Student_id";

                try{
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1,SID);
                    ResultSet rs = pstmt.executeQuery();

                    ResultSetMetaData rsmd2 = rs.getMetaData();
                    int columntCount2 = rsmd2.getColumnCount();

                    DefaultTableModel tableModel2 = new DefaultTableModel();
                    tblEligibility.setModel(tableModel2);

                    for (int i = 1; i <= columntCount2; i++) {
                        tableModel2.addColumn(rsmd2.getColumnName(i));
                        tableModel2.setColumnIdentifiers(new Object[] {"Course", "Attendance_Eligibility", "CA_Eligibility"}); // add this line to set the column names
                    }
                    tableModel2.setRowCount(0);
                    while (rs.next()) {
                        Object[] rowData = new Object[columntCount2];
                        for (int i = 1; i <= columntCount2; i++) {
                            rowData[i-1] = rs.getObject(i);
                        }
                        tableModel2.addRow(rowData);
                    }
                    pstmt.close();
                    rs.close();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });


    }
}

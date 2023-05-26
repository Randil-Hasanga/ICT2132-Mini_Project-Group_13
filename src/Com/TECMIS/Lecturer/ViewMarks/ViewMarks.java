package Com.TECMIS.Lecturer.ViewMarks;

import Com.TECMIS.Lecturer.Lecturer;
import Com.TECMIS.MySqlCon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;

public class ViewMarks extends Lecturer{
    private Connection conn = MySqlCon.MysqlMethod();
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JTextField txtCID;
    private JButton searchButton;
    private JTable tblMarks;
    private JLabel lblDisplay;
    private JPanel pnlViewMarks;
    private JScrollPane pnlScroll;
    private JButton backButton;
    private JButton clearButton;
    private String CID;


    public void viewStudentMarks(){
        add(pnlViewMarks);
        setSize(750, 500);
        setTitle("View Marks");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        tblMarks.setVisible(false);
        lblDisplay.setVisible(false);

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
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtCID.setText("");
                tblMarks.setVisible(false);
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tblMarks.setVisible(true);
                CID = txtCID.getText();

                String sql = "SELECT Student_id,Assignment001,Assignment002 ,QUIZ01,QUIZ02,QUIZ03,MID," +
                        "FINAL_Theory,FINAL_Practical,final_mark FROM Exam_mark " +
                        "WHERE Course_id = ? ORDER BY Student_id";

                try{
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1,CID);
                    ResultSet rs = pstmt.executeQuery();

                    ResultSetMetaData rsmd2 = rs.getMetaData();
                    int columntCount2 = rsmd2.getColumnCount();

                    DefaultTableModel tableModel2 = new DefaultTableModel();
                    tblMarks.setModel(tableModel2);

                    for (int i = 1; i <= columntCount2; i++) {
                        tableModel2.addColumn(rsmd2.getColumnName(i));
                    }
                    tableModel2.setRowCount(0);
                    while (rs.next()) {
                        Object[] rowData = new Object[columntCount2];
                        for (int i = 1; i <= columntCount2; i++) {
                            rowData[i-1] = rs.getObject(i);
                        }
                        tableModel2.addRow(rowData);
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }
}

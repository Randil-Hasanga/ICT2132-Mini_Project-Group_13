package TECMIS.Student;

import TECMIS.User;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class StudentNotice extends Student{

    Connection conn;

    {
        Object MySqlCon = new Object();
        conn = MySqlCon.MysqlMethod();
    }

    private JPanel pnlStuNotice;
    private JTable tblstuNotice;
    private JButton backButton;
    private JTextArea facultyOfTechnologyManagementTextArea;

    private String UserId;

    private String acc;

    public void viewStudentNotice() {
        UserId = User.getUserId();
        acc= User.getAcc();

        add(pnlStuNotice);
        setSize(600, 600);
        setTitle("Student notices");
        tblstuNotice.setEnabled(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        String stuNoticeQuery = "SELECT Notice.Notice_id,Notice.Subject_,Notice.Description_ FROM Notice,Student_Notice,Student WHERE (Notice.Notice_id = Student_Notice.Notice_id) AND (Student_Notice.Student_id = Student.User_id) AND Student.User_id = ?";


        try (PreparedStatement pstmt2 = conn.prepareStatement(stuNoticeQuery)) {

            pstmt2.setString(1,UserId);
            ResultSet rs2 = pstmt2.executeQuery();

            DefaultTableModel tableModel2 = new DefaultTableModel();
            tblstuNotice.setModel(tableModel2);

            ResultSetMetaData rsmd2 = rs2.getMetaData();
            int columntCount2 = rsmd2.getColumnCount();

            for (int i = 1; i <= columntCount2; i++) {
                tableModel2.addColumn(rsmd2.getColumnName(i));
            }

            while (rs2.next()) {
                Object[] rowData = new Object[columntCount2];
                for (int i = 1; i <= columntCount2; i++) {
                    rowData[i-1] = rs2.getObject(i);
                }
                tableModel2.addRow(rowData);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student stuBack = new Student();
                stuBack.setVisible(true);
                setVisible(false);
                stuBack.methodStudent();

            }
        });




    }
}

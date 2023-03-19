package TECMIS;

import TECMIS.Lecturer.Lecturer;
import TECMIS.MySqlCon;
import TECMIS.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Notice extends JFrame {

    Connection conn = MySqlCon.MysqlMethod();
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JPanel pnlLecNotice;
    private JTable tblLecNotice;
    private JButton backButton;

    private String userId;
    private String acc;

    public void viewNotice() {
        userId = User.getUserId();
        acc = User.getAcc();

        add(pnlLecNotice);
        setSize(750, 500);
        setTitle("Lecturer notices");
        setLocationRelativeTo(null);
        tblLecNotice.setEnabled(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        String LecNoticeQuery = "SELECT Notice.Notice_id,Notice.Subject_,Notice.Description_ FROM Notice,Lecturer_Notice,Lecturer WHERE (Notice.Notice_id = Lecturer_Notice.Notice_id) AND (Lecturer_Notice.Lecturer_id = Lecturer.User_id) AND Lecturer.User_id = ?";

        try (PreparedStatement pstmt2 = conn.prepareStatement(LecNoticeQuery)) {

            pstmt2.setString(1, userId);
            ResultSet rs2 = pstmt2.executeQuery();

            DefaultTableModel tableModel2 = new DefaultTableModel();
            tblLecNotice.setModel(tableModel2);

            ResultSetMetaData rsmd2 = rs2.getMetaData();
            int columntCount2 = rsmd2.getColumnCount();

            for (int i = 1; i <= columntCount2; i++) {
                tableModel2.addColumn(rsmd2.getColumnName(i));
            }

            while (rs2.next()) {
                Object[] rowData = new Object[columntCount2];
                for (int i = 1; i <= columntCount2; i++) {
                    rowData[i - 1] = rs2.getObject(i);
                }
                tableModel2.addRow(rowData);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Lecturer lecBack = new Lecturer();
                lecBack.setVisible(true);
                setVisible(false);
                lecBack.methodLecturer();

            }
        });
    }



}
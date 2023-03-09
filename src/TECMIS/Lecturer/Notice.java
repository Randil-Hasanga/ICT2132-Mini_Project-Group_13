package TECMIS.Lecturer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Notice extends JFrame {
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JPanel pnlLecNotice;
    private JTable tblLecNotice;
    private JButton backButton;

    private String email;
    private String acc;

    String LecId;

    public Notice(String email, String acc) {
        this.email = email;
        this.acc = acc;

        add(pnlLecNotice);
        setSize(600, 600);
        setTitle("Student Details");
        tblLecNotice.setEnabled(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        String LecIdQuery = "SELECT User_id FROM Lecturer WHERE Email = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/LMSDB", "root", "")) {
            try (PreparedStatement pstmt = conn.prepareStatement(String.valueOf(LecIdQuery))) {
                pstmt.setString(1, email);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    LecId = rs.getString("User_id");
                }

                String LecNoticeQuery = "SELECT * FROM Notice WHERE (Notice.Notice_id = LecturerNotice.Notice_id) AND (LecturerNotice.Lecturer_id = Lecturer.User_id)";

                try (PreparedStatement pstmt2 = conn.prepareStatement(LecNoticeQuery)) {
                    ResultSet rs2 = pstmt2.executeQuery();

                    DefaultTableModel tableModel2 = new DefaultTableModel();
                    tblLecNotice.setModel(tableModel2);

                    tableModel2.addColumn("");
                    tableModel2.addColumn("");

                    ResultSetMetaData rsmd2 = rs2.getMetaData();
                    int columntCount2 = rsmd2.getColumnCount();

                    while (rs.next()) {
                        for (int i = 1; i <= columntCount2; i++) {
                            Object[] rowData = new Object[2];
                            rowData[0] = rsmd2.getColumnName(i);
                            rowData[1] = rs2.getObject(i);
                            tableModel2.addRow(rowData);
                        }
                    }
                } catch (RuntimeException ex) {
                    throw new RuntimeException(ex);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Lecturer lecBack = new Lecturer(email,acc);
                lecBack.setVisible(true);
                setVisible(false);
            }
        });
    }
}
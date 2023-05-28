package com.TECMIS.TechnicalOfficer.TechnicalOfficer;


import com.TECMIS.MySqlCon;
import com.TECMIS.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Tech_OfficerNotice extends TechnicalOfficer {
    Connection conn = MySqlCon.MysqlMethod();
    private JTable tblTech_OfficerNotices;
    private JPanel pnlTech_OfficerNotice;
    private JTextArea facultyOfTechnologyManagementTextarea;

    private JButton btnBack;

    private String userId;
    private String acc;


    public void viewTONotice() {

        userId = User.getUserId();
        acc = User.getAcc();

        add(pnlTech_OfficerNotice);
        setSize(600, 600);
        setTitle("TechnicalOfficer Notices");
        tblTech_OfficerNotices.setEnabled(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        String TONoticeQuery = "SELECT Notice.Notice_id,Notice.Subject_,Notice.Description_ FROM Notice,Tech_OfficerNotice,TechnicalOfficer WHERE (Notice.Notice_id = Tech_OfficerNotice.Notice_id) AND (Tech_OfficerNotice.T_Officer_id = TechnicalOfficer.User_id) AND TechnicalOfficer.User_id = ?";

        try (PreparedStatement pstmt3 = conn.prepareStatement(TONoticeQuery)) {

            pstmt3.setString(1, userId);
            ResultSet rs2 = pstmt3.executeQuery();

            DefaultTableModel tableModel2 = new DefaultTableModel();
            tblTech_OfficerNotices.setModel(tableModel2);

            ResultSetMetaData rsmd2 = rs2.getMetaData();
            int columnCount2 = rsmd2.getColumnCount();

            for (int i = 1; i <= columnCount2; i++) {
                tableModel2.addColumn(rsmd2.getColumnName(i));
            }

            while (rs2.next()) {
                Object[] rowData = new Object[columnCount2];
                for (int i = 1; i <= columnCount2; i++) {
                    rowData[i - 1] = rs2.getObject(i);
                }
                tableModel2.addRow(rowData);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TechnicalOfficer TOBack = new TechnicalOfficer();
                TOBack.setVisible(true);
                setVisible(false);
                TOBack.methodTechnicalOfficer();

            }
        });
    }
}

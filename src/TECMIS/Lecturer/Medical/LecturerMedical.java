package TECMIS.Lecturer.Medical;

import TECMIS.Lecturer.Lecturer;
import TECMIS.MySqlCon;
import TECMIS.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LecturerMedical extends Lecturer {

    Connection conn = MySqlCon.MysqlMethod();
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JTable tblLecMedical;
    private JButton backButton;
    private JPanel pnlLecMedical;
    private JTextField txtSID;
    private JButton searchButton;

    private String userId;
    private String acc;
    private String SID;

    public void viewMedicals(){
        acc = User.getAcc();
        userId = User.getUserId();

        add(pnlLecMedical);
        setSize(600, 600);
        setTitle("Medicals");
        tblLecMedical.setEnabled(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ex) {
                SID = txtSID.getText();
                String Medi = "SELECT Medical.Medical_id, Medical.Student_id, Medical.Start_Date, Medical.End_Date, Medical.Medical_Condition FROM Medical,Lecturer,Lecturer_Medical,Student WHERE (Student.User_id = Medical.Student_id) AND (Lecturer.User_id = Lecturer_Medical.Lecturer_id) AND (Medical.Medical_id = Lecturer_Medical.Medical_id) AND Student.User_id = ?";

                try{
                    PreparedStatement pstmt3 = conn.prepareStatement(Medi);
                    pstmt3.setString(1,SID);
                    ResultSet rs3 = pstmt3.executeQuery();

                    DefaultTableModel tableModel3 = new DefaultTableModel();
                    tblLecMedical.setModel(tableModel3);

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
                } catch (SQLException e) {
                    throw new RuntimeException(e);
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
            }
        });


    }
}

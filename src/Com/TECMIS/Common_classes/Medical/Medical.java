package Com.TECMIS.Common_classes.Medical;

import Com.TECMIS.Lecturer.Lecturer;
import Com.TECMIS.MySqlCon;
import Com.TECMIS.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;

public class Medical extends Lecturer {

    Connection conn =MySqlCon.MysqlMethod();
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JTable tblLecMedical;
    private JButton backButton;
    private JPanel pnlLecMedical;
    private JTextField txtSID;
    private JButton searchButton;
    private JButton clearButton;

    private String userId;
    private String acc;
    private String SID;


    public void viewMedicals(){
        acc = User.getAcc();
        userId = User.getUserId();

        add(pnlLecMedical);
        setSize(750, 500);
        setTitle("Medicals");
        tblLecMedical.setEnabled(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

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

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtSID.setText("");
            }
        });


    }
}

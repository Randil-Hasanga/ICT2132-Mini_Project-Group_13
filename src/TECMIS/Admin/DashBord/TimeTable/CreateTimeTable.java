package TECMIS.Admin.DashBord.TimeTable;

import TECMIS.Admin.DashBord.Dashbord;
import TECMIS.MySqlCon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTimeTable extends JFrame{

    Connection conn = MySqlCon.MysqlMethod();
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JTextField txtTableId;
    private JButton chooseFileButton;
    private JButton backButton;
    private JButton submitButton;
    private JButton clearButton;
    private JPanel CreateTimeTaPnl;
    private JTextField txtLVL;
    private JLabel lblDisplay;

    private File pdf;
    private String ttId;
    private String lvlsm;




    public void CreateTableMethod(){

        add(CreateTimeTaPnl);
        setVisible(true);
        setSize(750,500);
        setTitle("LMS Software");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ttId = txtTableId.getText();
                lvlsm = txtLVL.getText();

                String sql = "INSERT INTO T_Table " +
                        "(TT_id,Level_and_Semester,TT_pdf) " +
                        "VALUES " +
                        "(?,?,?)";

                try{
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1,ttId);
                    pstmt.setString(2,lvlsm);

                    byte[] pdfBytes = Files.readAllBytes(pdf.toPath());

                    pstmt.setBytes(3,pdfBytes);
                    pstmt.executeUpdate();

                    lblDisplay.setText("PDF file inserted successfully!");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        chooseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    JFileChooser pdfFile = new JFileChooser();
                    int result = pdfFile.showOpenDialog(null);
                    if(result == JFileChooser.APPROVE_OPTION){
                        pdf = pdfFile.getSelectedFile();
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dashbord back = new Dashbord();
                back.setVisible(true);
                setVisible(false);
                back.methodAdmin();
            }
        });


        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtTableId.setText("");
                txtLVL.setText("");
            }
        });
    }
}

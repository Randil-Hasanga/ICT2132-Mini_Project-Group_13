package TECMIS.Admin.DashBord.TimeTable;

import TECMIS.Admin.DashBord.Dashbord;
import TECMIS.MySqlCon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.*;

public class UpdateTimeTable extends JFrame{
    Connection conn = MySqlCon.MysqlMethod();
    private JTextArea facultyOfTechnologyManagementTextArea;
    private JPanel UpTiTabPnl;
    private JTextField TiTaId;
    private JButton submitButton;
    private JTextField LeveSemes;
    private JButton chooseFileButton;
    private JButton backButton;
    private JButton clearButton;
    private JPanel AffterPnl;
    private JButton uploadButton;
    private JLabel scc;
    private String TimeTableId;
    private File pdf;

    private String LeSe;



    public void UpdateTimeTableMethod() {

    add(UpTiTabPnl);
    setVisible(true);
    setSize(750,500);
    setTitle("LMS Software");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    AffterPnl.setVisible(false);



    submitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                TimeTableId= TiTaId.getText();
                AffterPnl.setVisible(true);


                String sql = "SELECT Level_and_Semester FROM t_table WHERE TT_id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);

                pstmt.setString(1,TimeTableId);
                ResultSet rs = pstmt.executeQuery();

                while(rs.next()) {
                    LeSe = rs.getString("Level_and_Semester");
                }

                LeveSemes.setText(LeSe);


            } catch (SQLException ex) {
                System.out.printf("SQL error in my code : " + ex.getMessage());
            }

        }
    });


        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    LeSe = LeveSemes.getText();

                    String UpTiTa = "UPDATE t_table SET Level_and_Semester = ? , TT_pdf = ? WHERE TT_id = ? ";

                    PreparedStatement stmt = conn.prepareStatement(UpTiTa);

                    stmt.setString(1,LeSe);

                    byte[] pdfBytes = Files.readAllBytes(pdf.toPath());

                    stmt.setBytes(2,pdfBytes);

                    stmt.setString(3, TimeTableId);


                    stmt.executeUpdate();

                    scc.setText("Database has been updated !");

                } catch (SQLException ex) {
                    System.out.println("Error in my SQL :" + ex.getMessage());
                } catch (IOException ex) {
                    System.out.printf("Error in pdf converter : " + ex.getMessage());
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
                System.out.printf("Error in pdf choice : " + ex.getMessage());
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
            TiTaId.setText("");
            LeveSemes.setText("");

        }
    });



}
}

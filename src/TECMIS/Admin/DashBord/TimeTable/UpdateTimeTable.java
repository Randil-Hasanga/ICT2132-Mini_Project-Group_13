package TECMIS.Admin.DashBord.TimeTable;

import TECMIS.Admin.DashBord.Dashbord;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class UpdateTimeTable extends JFrame{
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
    private String TimeTableId;
    private File pdf;

public void UpdateTimeTableMethod() {

    add(UpTiTabPnl);
    setVisible(true);
    setSize(750,500);
    setTitle("LMS Software");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    AffterPnl.setVisible(false);

    TimeTableId = TiTaId.getText();

    submitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

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
            TiTaId.setText("");
            LeveSemes.setText("");

        }
    });



}
}

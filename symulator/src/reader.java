import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

public class reader {
    List<Subject> subjectList;

    public reader(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        subjectList = new ArrayList<>();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Student Adder");
        frame.setSize(500, 700);
        frame.setLocationRelativeTo(null);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel finalPanel = new JPanel();
        finalPanel.setSize(150, 100);


        JTextArea subName = new JTextArea(1, 20);
        subName.setEditable(true);
        subName.setLineWrap(true);
        JPanel outerPanel = new JPanel();
        outerPanel.setSize(100, 20);
        outerPanel.add(new JLabel("Subject Name:"));
        outerPanel.add(subName);

        JSpinner ectsA = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        JSpinner predisA = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        JSpinner studiedA = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));

        JPanel ectsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        ectsPanel.setSize(20, 20);
        ectsPanel.add(new JLabel("ECTS: "));
        ectsPanel.add(ectsA);

        JPanel predisPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        predisPanel.setSize(20, 20);
        predisPanel.add(new JLabel("Predispositions: "));
        predisPanel.add(predisA);

        JPanel studiedPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        studiedPanel.setSize(20, 20);
        studiedPanel.add(new JLabel("StudiedTime: "));
        studiedPanel.add(studiedA);

        JButton add = new JButton("Dodaj rzecz");
        add.addActionListener(e -> {
            String name=subName.getText();
            int ects=(int)ectsA.getValue();
            int predis=(int)predisA.getValue();
            int studied=(int)studiedA.getValue();

            this.subjectList.add(new Subject(name,ects,predis,studied));
            subName.setText("");
            ectsA.setValue(0);
            predisA.setValue(0);
            studiedA.setValue(0);
        });


        DefaultTableModel model = new DefaultTableModel(new Object[]{"Subject","ECTS","Predispositions","StudiedTime"},0);
        JTable table = new JTable(model);
        JScrollPane tablePanel = new JScrollPane(table);


        panel.add(outerPanel, BorderLayout.WEST);
//        panel.add(ectsPanel, BorderLayout.WEST);
//        panel.add(predisPanel, BorderLayout.WEST);
//        panel.add(studiedPanel, BorderLayout.WEST);
        panel.add(add);
        panel.add(tablePanel, BorderLayout.SOUTH);


        finalPanel.add(panel);
        frame.add(finalPanel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new reader();
    }
}

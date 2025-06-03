import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.*;
import java.util.List;

public class Reader {
    List<Subject> subjectList;
    int days;
    int mode;

    JLabel error;

    public Reader(){
        JDialog frame = new JDialog((JFrame)null, "Reader", true);
        JPanel panel = new JPanel();
        subjectList = new ArrayList<>();

        ImageIcon icon = new ImageIcon(Reader.class.getResource("/images/icon.jpg"));
        frame.setIconImage(icon.getImage());


        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        frame.setTitle("Odczyt Parametrów Wejściowych");
        frame.setSize(500, 660);
        frame.setLocationRelativeTo(null);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel finalPanel = new JPanel();
        finalPanel.setPreferredSize(new Dimension(100, 500));


        JTextArea subName = new JTextArea(1, 30);
        subName.setEditable(true);
        subName.setLineWrap(true);
        JPanel outerPanel = new JPanel();
        outerPanel.setSize(100, 20);
        outerPanel.add(new JLabel("Nazwa Przedmiotu:"));
        outerPanel.add(subName);

        JSpinner ectsA = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        JSpinner predisA = new JSpinner(new SpinnerNumberModel(50, 0, 100, 1));
        JSpinner studiedA = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));

        JPanel ectsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        ectsPanel.setSize(20, 20);
        ectsPanel.add(new JLabel("ECTS: "));
        ectsPanel.add(ectsA);

        JPanel predisPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        predisPanel.setSize(20, 20);
        predisPanel.add(new JLabel("Predyspozycje: "));
        predisPanel.add(predisA);

        JPanel studiedPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        studiedPanel.setSize(20, 20);
        studiedPanel.add(new JLabel("Czas już spędzony na nauce: "));
        studiedPanel.add(studiedA);

        DefaultTableModel model = new DefaultTableModel(new Object[]{"Przedmiot","ECTS","Predyspozycje","Nauczony Czas"},0);
        JTable table = new JTable(model);
        JScrollPane tablePanel = new JScrollPane(table);
        tablePanel.setPreferredSize(new Dimension(350, 200));


        JButton add = new JButton("Dodaj Przedmiot");
        add.addActionListener(e -> {
            String name=subName.getText();
            if(name.isEmpty()){
                error.setText("Wprowadź Nazwę Przedmiotu");
            }
            else {

                int ects = (int) ectsA.getValue();
                int predis = (int) predisA.getValue();
                int studied = (int) studiedA.getValue();

                this.subjectList.add(new Subject(name, ects, predis, studied));
                model.addRow(new Object[]{name, ects, predis, studied});
                subName.setText("");
                ectsA.setValue(0);
                predisA.setValue(50);
                studiedA.setValue(0);

                error.setText("");
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(add);

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setPreferredSize(new Dimension(300, 40));
        JLabel title = new JLabel("Wprowadź Dane Początkowe");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.add(title);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setPreferredSize(new Dimension(300, 100));
        bottomPanel.setBorder(new LineBorder(Color.GRAY));

        JPanel daysPanel = new JPanel();
        daysPanel.setLayout(new BoxLayout(daysPanel, BoxLayout.Y_AXIS));
        JLabel daysLabel = new JLabel("Dni do sesji");
        daysLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JSpinner daysInput = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));

        JPanel spinnerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        spinnerPanel.setSize(40, 20);
        spinnerPanel.add(daysInput);

        daysPanel.add(daysLabel);
        daysPanel.add(spinnerPanel);



        JPanel modePanel = new JPanel();
        modePanel.setLayout(new BoxLayout(modePanel, BoxLayout.Y_AXIS));
        JLabel modeLabel = new JLabel("Tryb Nauki:");
        modeLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JRadioButton option1 = new JRadioButton("Nauka wielu przedmiotów jednego dnia");
        JRadioButton option2 = new JRadioButton("Nauka po jednym przedmiocie po kolei");
        ButtonGroup buttons = new ButtonGroup();
        buttons.add(option1);
        buttons.add(option2);
        option1.setSelected(true);

        modePanel.add(modeLabel);
        modePanel.add(option1);
        modePanel.add(option2);


        JPanel finishPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton confirm = new JButton("Rozpocznij Naukę");
        confirm.setPreferredSize(new Dimension(300, 70));
        confirm.setFont(new Font("Arial", Font.BOLD, 20));


        JPanel errorPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
        error = new JLabel("");
        error.setFont(new Font("Arial", Font.BOLD, 20));
        error.setForeground(Color.RED);
        errorPanel.add(error);


        confirm.addActionListener(e -> {
            this.days=(int)daysInput.getValue();

            if(option1.isSelected()) this.mode=0;
            else if(option2.isSelected()) this.mode=1;

            if(subjectList.size()==0) {
                error.setText("Brak Przedmiotów");
                panel.revalidate();
                panel.repaint();
            }
            else frame.dispose();

        });

        finishPanel.add(confirm);


        bottomPanel.add(modePanel, BorderLayout.WEST);
        bottomPanel.add(daysPanel, BorderLayout.EAST);


        panel.add(titlePanel);
        panel.add(outerPanel);
        panel.add(ectsPanel);
        panel.add(predisPanel);
        panel.add(studiedPanel);
        panel.add(buttonPanel);
        panel.add(tablePanel);
        panel.add(bottomPanel);
        panel.add(finishPanel);
        panel.add(errorPanel);

        finalPanel.add(panel);
        frame.add(finalPanel);
        frame.setVisible(true);
    }

    public List<Subject> getSubjects() {
        return subjectList;
    }
    public int getDays() {
        return days;
    }
    public int getMode() {
        return mode;
    }

}

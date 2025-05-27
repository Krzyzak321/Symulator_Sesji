import javax.swing.*;
import java.lang.reflect.Array;
import java.util.List;
import java.awt.Color;
import java.util.stream.Collectors;
public class Visualizer {
    private int numberOfSubjects;
    private JFrame window;
    private JPanel panel;
    private JList<String> subjectList;

    //Zwizualizuj studenta? nwm potrzebna liczba przedmiotow
    Visualizer(Student student) {
        this.numberOfSubjects = getNumberOfSubjects(student.getSubjects());
        this.window = new JFrame("Visualizer");
        this.panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.LightBlue);
         //tu pobranie tych przedmiotow a pozniej stworzenie listy z nich
        List<String> subjectNames = student.getSubjects().stream()
                .map(Subject::getName)
                .collect(Collectors.toList());
        subjectList = new JList<>(subjectNames.toArray(new String[0]));
        JScrollPane listScrollPane = new JScrollPane(subjectList);
        //dalem liste przedmiotow na lewac czesc panelu, wstepnie pozniej pomysle
        panel.add(listScrollPane, BorderLayout.WEST);

        window.add(panel);
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    private static int getNumberOfSubjects(List<Subject> subjects) {
        return subjects.size();
    }

    //
    // Zmienic Kolorystyke, jakos naprawic ta biblioteje zajebana
    //
     // rozdzielic to pobranie danych na oddzielna funkcje?
    private void addChart(Subject subject) {
        String subjectName = subject.getName();
        double timeStudied = subject.getStudiedTime();
        int requiredTime = subject.getRequiredTime();
        double timeLeft = requiredTime - timeStudied;
        if (timeLeft < 0) timeLeft = 0;

        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Total Time Studied", timeStudied);
        dataset.setValue("Required Time Left", timeLeft);

        JFreeChart chart = ChartFactory.createPieChart(subjectName, dataset, true, true, false);
        ChartPanel chartPanel = new ChartPanel(chart);
        panel.add(chartPanel);
    }

    private Array calculateWindowSize(){

    }

    private void visualize(){
        window.setVisible(true);
    }
}

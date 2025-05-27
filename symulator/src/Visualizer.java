import javax.swing.*;
import java.lang.reflect.Array;
import java.util.List;

public class Visualizer {
    private int numberOfSubjects;
    private JFrame window;
    private JPanel panel;

    //Zwizualizuj studenta? nwm potrzebna liczba przedmiotow
    Visualizer(Student student) {
        this.numberOfSubjects = getNumberOfSubjects(student.getSubjects());
        this.window = new JFrame();
        this.panel = new JPanel();
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

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class Visualizer{
    JFrame frame;
    JPanel panel;
    JPanel panelPlan;
    int numberOfCharts;
    public List<Subject> listOfSubjects;

    public Visualizer(Student student){
        this.frame = new JFrame();
        this.panel = new JPanel();
        this.panelPlan = new JPanel();
        this.listOfSubjects = student.getSubjects();
        this.numberOfCharts = listOfSubjects.size();

        for ( Subject subject : listOfSubjects ) addChart(subject);
    }

    public void visualize(){
        frame.setTitle("Symulator Sesji");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(calculateWindowWidth(numberOfCharts)+400, 600);


        //Panel z Wykresami-------------------//
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setPreferredSize(new Dimension(800, calculateHeight(numberOfCharts)));
        panel.setBorder(BorderFactory.createLineBorder(Color.RED));

        JPanel outerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        outerPanel.add(panel);

        JScrollPane scrollPane = new JScrollPane(outerPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //-----------------------------------//

        //Panel z planem---------------------//
        panelPlan.setLayout(new BoxLayout(panelPlan, BoxLayout.Y_AXIS));
        panelPlan.setPreferredSize(new Dimension(300, 500));
        panelPlan.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        JPanel outerPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        outerPanel.add(panelPlan);

        //-----------------------------------//

        frame.add(outerPanel2, BorderLayout.EAST);
        frame.add(scrollPane, BorderLayout.WEST);
        frame.setVisible(true);
    }

    public void addChart(Subject subject){
        String name = subject.getName();
        DefaultPieDataset dataset = subject.dataset;
        double currentStudiedTime=(double) subject.dataset.getValue("studiedTime");
        double currentTimeLeft = (double) subject.dataset.getValue("timeLeft");
        int color = calculateGreen(currentStudiedTime, currentTimeLeft+currentStudiedTime);

        JFreeChart chart = ChartFactory.createPieChart(name, dataset, true, true, false);
        subject.chart=chart;
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("studiedTime", new Color(255-color, color, 0));
        plot.setSectionPaint("timeLeft", Color.LIGHT_GRAY);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(250, 250));

        panel.add(chartPanel);
    }

    public void updateGraph(Subject subject, double timeStudied){
        double requiredTime = (double)subject.getRequiredTime();
        double currentStudiedTime=(double) subject.dataset.getValue("studiedTime");
        double currentTimeLeft = (double) subject.dataset.getValue("timeLeft");

        subject.dataset.setValue("studiedTime", currentStudiedTime+timeStudied);
        subject.dataset.setValue("timeLeft", currentTimeLeft-timeStudied);

        int green = calculateGreen(currentStudiedTime, requiredTime);
        int red = 255-green;

        PiePlot plot = (PiePlot) subject.chart.getPlot();
        plot.setSectionPaint("studiedTime", new Color(red, green, 0));

        panel.revalidate();
        panel.repaint();

    }

    //for int
    public static int calculateGreen(int studiedTime, int requiredTime){
        return (int)(((double)studiedTime/(double)requiredTime)*255);
    }

    //for double
    public static int calculateGreen(double studiedTime, double requiredTime){
        int color = (int)((studiedTime/requiredTime)*255);
        if(color>255)return 255;
        return color;
    }

    public static int calculateHeight(int numberOfCharts){
        double rows = (double)numberOfCharts /3;
        int realRows = (int)Math.ceil(rows);
        return 261*realRows;
    }

    public static int calculateWindowWidth(int numberOfCharts){
        if(numberOfCharts<3) return (numberOfCharts*260);
        return 780;
    }

    public void insertPlan(Plan plan) {
        for (int day = 1; day <= plan.getDays(); day++) {
            System.out.println("Dzień " + day + ":");
            Map<Subject, Integer> dayPlan = plan.getDailyPlan(day);
            for (Map.Entry<Subject, Integer> entry : dayPlan.entrySet()) {
                System.out.println("  " + entry.getKey().getName() + " - " + entry.getValue() + "h");
            }
        }
    }

}



////tu pobranie tych przedmiotow a pozniej stworzenie listy z nich
//List<String> subjectNames = student.getSubjects().stream()
//        .map(Subject::getName)
//        .collect(Collectors.toList());
//subjectList = new JList<>(subjectNames.toArray(new String[0]));
//JScrollPane listScrollPane = new JScrollPane(subjectList);
////dalem liste przedmiotow na lewac czesc panelu, wstepnie pozniej pomysle
//        panel.add(listScrollPane, BorderLayout.WEST);

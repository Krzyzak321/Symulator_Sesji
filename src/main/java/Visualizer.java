import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.jfree.chart.*;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class Visualizer{
    JFrame frame;
    JPanel panel;
    JPanel panelPlan;
    JPanel motivationPanel;
    JPanel resultsPanel;
    JProgressBar motivationBar;
    JLabel dayLabel;
    JLabel motivationUpdate;
    int numberOfCharts;
    public List<Subject> listOfSubjects;
    int currentDay;
    int currentTime;
    Plan plan;

    public Visualizer(Student student){
        this.frame = new JFrame();
        this.panel = new JPanel();
        this.dayLabel = new JLabel("Dzień 1 Godzina 10:00");
        this.panelPlan = new JPanel();
        this.resultsPanel = new JPanel();
        this.listOfSubjects = student.getSubjects();
        this.numberOfCharts = listOfSubjects.size();
        this.currentDay=1;
        this.currentTime=10;
        this.plan=student.getPlan();
        this.motivationUpdate = new JLabel("");
        student.setVisualizer(this);

        for ( Subject subject : listOfSubjects ) addChart(subject);
        JLabel result = new JLabel("WYNIKI EGZAMINÓW:");
        result.setFont(new Font("Serif", Font.BOLD, 25));
        resultsPanel.add(result);

//

    }

    // Wyswietla glowne okno z wykresami
    public void visualize(){
        int width = calculateWindowWidth(numberOfCharts);
        int height = calculateHeight(numberOfCharts);

        frame.setTitle("Symulator Sesji");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width+400, height+160);
        frame.setLocationRelativeTo(null);


        //Panel z Wykresami-------------------//
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setPreferredSize(new Dimension(width, height));
        panel.setBorder(BorderFactory.createLineBorder(Color.RED));

        JPanel outerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        outerPanel.add(panel);

        JScrollPane scrollPane = new JScrollPane(outerPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //-----------------------------------//


        //Panel z planem---------------------//
        panelPlan.setLayout(new BoxLayout(panelPlan, BoxLayout.Y_AXIS));
        panelPlan.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        JPanel outerPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        outerPanel2.add(panelPlan);

        JScrollPane scrollPane2 = new JScrollPane(outerPanel2);
        scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //-----------------------------------//


        //Panel z Dniem---------------------//
        JPanel dayPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        dayPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        dayPanel.setPreferredSize(new Dimension(width, 50));
        dayLabel.setFont(new Font("Arial", Font.BOLD, 28));
        if(listOfSubjects.size()==1) dayLabel.setFont(new Font("Arial", Font.BOLD, 20));
        dayPanel.add(dayLabel);
        //-----------------------------------//

        //Panel z napisem PLAN---------------------//
        JPanel planNamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        planNamePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        planNamePanel.setPreferredSize(new Dimension(320, 50));
        JLabel planNameLabel = new JLabel("Plan Nauki");
        planNameLabel.setFont(new Font("Droid Sans Japanese", Font.BOLD, 28));
        planNamePanel.add(planNameLabel);
        //-----------------------------------//

        //Panel Gorny---------------------//
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createLineBorder(Color.yellow));
        topPanel.setPreferredSize(new Dimension(width+400, 50));

        topPanel.add(dayPanel, BorderLayout.WEST);
        topPanel.add(planNamePanel, BorderLayout.EAST);

        JPanel outerPanel3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        outerPanel3.add(topPanel);

        //-----------------------------------//

        //Panel z Motywacją---------------------//
        motivationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        motivationPanel.setBorder(BorderFactory.createLineBorder(Color.yellow));
        motivationPanel.setPreferredSize(new Dimension(width, 70));
        motivationPanel.setLayout(new BoxLayout(motivationPanel, BoxLayout.Y_AXIS));


        JLabel motivationLabel = new JLabel("Motywacja do Nauki");
        motivationLabel.setFont(new Font("Arial", Font.BOLD, 20));
        motivationBar = new JProgressBar(0, 200);
        motivationBar.setValue(75);
        motivationBar.setForeground(Color.magenta);
        motivationBar.setStringPainted(true);
        motivationPanel.add(motivationLabel);
        motivationPanel.add(motivationBar);

        motivationUpdate.setFont(new Font("Arial", Font.BOLD, 12));
        motivationPanel.add(motivationUpdate, BorderLayout.SOUTH);

        JPanel outerPanel4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        outerPanel4.add(motivationPanel);

        //-----------------------------------//



        frame.add(outerPanel3,BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.WEST);
        frame.add(scrollPane2, BorderLayout.EAST);
        frame.add(outerPanel4, BorderLayout.SOUTH);
        frame.setVisible(true);
    }


    //Dodaje wykres podanego przedmiotu do okna
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

    // Aktualizuje wykres i zmienia jego kolor w zaleznosci od stopnia zapelnienia
    public void updateGraph(Subject subject, double timeStudied, int currentDay, int currentHour){
        double requiredTime = (double)subject.getRequiredTime();
        double currentStudiedTime=(double) subject.dataset.getValue("studiedTime");
        double currentTimeLeft = (double) subject.dataset.getValue("timeLeft");

        subject.dataset.setValue("studiedTime", currentStudiedTime+timeStudied);
        subject.dataset.setValue("timeLeft", currentTimeLeft-timeStudied);

        int green = calculateGreen(currentStudiedTime, requiredTime);
        int red = 255-green;

        PiePlot plot = (PiePlot) subject.chart.getPlot();
        plot.setSectionPaint("studiedTime", new Color(red, green, 0));



        this.dayLabel.setText("Dzień "+currentDay+" Godzina "+1+currentHour+":00");

        panel.revalidate();
        panel.repaint();

    }

    // Aktualizuje pasek motywacji (ustawia jego wartosc na podaną)
    public void updateMotivation(int motivation){

        motivationBar.setValue(motivation);
        motivationBar.setForeground(calculateBarColor(motivation));
        motivationPanel.revalidate();
        motivationPanel.repaint();
    }

    // Tworzy panel z podanym planem nauki
    public void insertPlan(Plan plan) {
        int height=105;
        if (plan.getMode()==1){
            height=50;
        }
        panelPlan.setPreferredSize(new Dimension(300, Collections.max(plan.getSchedule().keySet())*height));

        for (int day = 1; day <= Collections.max(plan.getSchedule().keySet()); day++) {
            String dayName="Dzień " + day + ":";

            JLabel label = new JLabel(dayName);
            label.setFont(new Font("Arial", Font.BOLD, 20));
            panelPlan.add(label);

            Map<Subject, Integer> dayPlan = plan.getDailyPlan(day);
            for (Map.Entry<Subject, Integer> entry : dayPlan.entrySet()) {
                String sub="  " + entry.getKey().getName() + " - " + entry.getValue() + "h";
                JLabel label2 = new JLabel(sub);
                label2.setFont(new Font("Arial", Font.PLAIN, 17));

                panelPlan.add(label2);
            }
        }
        panelPlan.revalidate();
        panelPlan.repaint();
    }

    //Dodaje pojedynczy wynik z egzaminu do okna z wynikami
    public void updateResults(ExamResult result){
        JLabel text = new JLabel(String.valueOf(result)+ " %");
        text.setFont(new Font("Arial", Font.PLAIN, 18));
        resultsPanel.add(text);
    }

    //Dodaje tekst do okna z wynikami
    public void updateResults(String result){
        JLabel text = new JLabel(result);
        text.setFont(new Font("Arial", Font.PLAIN, 18));
        resultsPanel.add(text);
    }

    //Wyswietla okno z wynikami egzaminow
    public void showResults(){
        JFrame resultsFrame = new JFrame();
        resultsFrame.setTitle("Wyniki Egzaminów");
        resultsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resultsFrame.setSize(450, 300);
        resultsFrame.setLocationRelativeTo(null);
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));

        JLabel info = new JLabel("");
        info.setFont(new Font("Arial", Font.PLAIN, 18));



        JButton download = new JButton("Pobierz Plan");
        download.setFont(new Font("Arial", Font.PLAIN, 18));
        download.addActionListener(e->{
            try {
                plan.exportTxt("plan.txt");
                info.setText("Wyeksportowanu do pliku \"plan.txt\"");
                info.setForeground(new Color(32, 147, 19));
            } catch (IOException ex) {
                info.setText("Wystapil blad podczas eksportowania");
                info.setForeground(new Color(166, 18, 18));
                throw new RuntimeException(ex);
            }
        });

        resultsFrame.add(info, BorderLayout.SOUTH);
        resultsPanel.add(download);
        resultsFrame.add(resultsPanel);
        resultsFrame.setVisible(true);

    }

    //Liczy kolor paska motywacji na podstawie zapelnienia
    private static Color calculateBarColor(int progress){
        int color = (int)(((double)progress/200)*235);
        return new Color(color, 0, 235);
    }

    //Liczy intensywnosc zielenii zapelnienia wykresu, argumentem jest liczba calkowita
    private static int calculateGreen(int studiedTime, int requiredTime){
        return (int)(((double)studiedTime/(double)requiredTime)*255);
    }

    //Liczy intensywnosc zielenii zapelnienia wykresu, argumentem jest liczba zmiennoprzecinkowa
    private static int calculateGreen(double studiedTime, double requiredTime){
        int color = (int)((studiedTime/requiredTime)*255);
        if(color>255)return 255;
        return color;
    }

    //Liczy wysokosc okna na podstawie liczby wykresow
    private static int calculateHeight(int numberOfCharts){
        double rows = (double)numberOfCharts /3;
        int realRows = (int)Math.ceil(rows);
        return 261*realRows;
    }

    //Liczy szerokosc okna na podstawie liczby wykresow
    private static int calculateWindowWidth(int numberOfCharts){
        if(numberOfCharts<3) return (numberOfCharts*260);
        return 780;
    }

    public void updateMotivator(String motivator){
        motivationUpdate.setText("Ostatni Motywator: " + motivator);
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

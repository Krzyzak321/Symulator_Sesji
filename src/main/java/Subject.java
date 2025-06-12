import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class Subject {
    private String name;
    private int predispositions;
    private int requiredTime;
    private double studiedTime;
    private final int ects;
    public DefaultPieDataset dataset;
    public JFreeChart chart;

    Subject (String name, int ects, int predispositions, int studiedTime) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nazwa przedmiotu nie może być pusta.");
        }
        if (ects <= 0) {
            throw new IllegalArgumentException("Liczba ECTS musi być dodatnia.");
        }
        if (studiedTime < 0) {
            throw new IllegalArgumentException("Czas nauki nie może być ujemny.");
        }
        this.name = name;
        this.ects = ects;
        if(predispositions<=0){ // w razie złego lub wartości zerowej predyspozycji ustaw na default-ową 50%
            this.predispositions = 50;
        }
        else this.predispositions = predispositions;
        requiredTime =  16*ects-(int)(9*ects*(predispositions/100.0))-studiedTime;
        this.studiedTime = studiedTime;
        this.dataset = new DefaultPieDataset();
        this.dataset.setValue("studiedTime", this.studiedTime);
        this.dataset.setValue("timeLeft", requiredTime-studiedTime);
    }
    // polimorfizm
    Subject (String name, int ects, int predispositions) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nazwa przedmiotu nie może być pusta.");
        }
        if (ects <= 0) {
            throw new IllegalArgumentException("Liczba ECTS musi być dodatnia.");
        }
        this.name = name;
        this.ects = ects;
        if(predispositions<=0){
            this.predispositions = 50;
        }
        else this.predispositions = predispositions;
        this.studiedTime = 0;
        requiredTime =  16*ects-(int)(9*ects*(predispositions/100.0));
        this.dataset = new DefaultPieDataset();
        this.dataset.setValue("studiedTime", studiedTime);
        this.dataset.setValue("timeLeft", requiredTime-studiedTime);
    }
    //dodawanie czasu nauki
    public void study(double hours) {
        studiedTime += hours;
    }
    //sprawdzenei czy dany przedmiot jest już nauczony
    public boolean isReady() {
        if (studiedTime >= requiredTime) {
            return true;
        }
        else return false;
    }
    //pobranie procentowego progresu nauki co ma swoje odzwierciedlenie w wynikach z egzaminów
    public double getProgress() {
        if (requiredTime == 0) return 100;
        return ((double) studiedTime / requiredTime) * 100;
    }

    public String getName() {
        return name;
    }

    public int getRequiredTime() {
        return (int)(requiredTime-studiedTime);
    }
    public double getStudiedTime() {
        return studiedTime;
    }
    public int getEcts() {return ects;}

    public int getPredispositions() {
        return predispositions;
    }
}
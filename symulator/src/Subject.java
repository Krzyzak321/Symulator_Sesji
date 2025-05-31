import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class Subject {
    private String name;
    //    private int difficulty; chyba do wywalenia
    private int predispositions;
    private int requiredTime;
    private double studiedTime;
    private final int ects;
    public DefaultPieDataset dataset;
    public JFreeChart chart;

    Subject (String name, int ects, int predispositions, int studiedTime) {
        this.name = name;
        this.ects = ects;
        if(predispositions<=0){
            this.predispositions = 50;
        }
        else this.predispositions = predispositions;
//        this.studiedTime = studiedTime;
        requiredTime =  18*ects-(int)(9*ects*(predispositions/100.0))-studiedTime;
        this.studiedTime = 0;
        this.dataset = new DefaultPieDataset();
        this.dataset.setValue("studiedTime", this.studiedTime);
        this.dataset.setValue("timeLeft", requiredTime-studiedTime);
    }
    // polimorfizm !!! siema
    Subject (String name, int ects, int predispositions) {
        this.name = name;
        this.ects = ects;
        if(predispositions<=0){
            this.predispositions = 50;
        }
        else this.predispositions = predispositions;
        this.studiedTime = 0;
        requiredTime =  18*ects-(int)(9*ects*(predispositions/100.0));
        this.dataset = new DefaultPieDataset();
        this.dataset.setValue("studiedTime", studiedTime);
        this.dataset.setValue("timeLeft", requiredTime-studiedTime);
    }

    public void study(double hours) {
        studiedTime += hours;
    }

    public boolean isReady() {
        if (studiedTime >= requiredTime) {
            return true;
        }
        else return false;
    }

    public double getProgress() {
        if (requiredTime == 0) return 100;
        return ((double) studiedTime / requiredTime) * 100;
    }

    public String getName() {
        return name;
    }

    //    public int getDifficulty() {
//        return difficulty;
//    }
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
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
        this.studiedTime = studiedTime;
        requiredTime =  18*ects*(predispositions/100);

        this.dataset = new DefaultPieDataset();
        this.dataset.setValue("requiredTime", requiredTime);
        this.dataset.setValue("studiedTime", studiedTime);
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
        requiredTime =  18*ects*(predispositions/100);

        this.dataset.setValue("requiredTime", requiredTime);
        this.dataset.setValue("studiedTime", studiedTime);
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
        if(studiedTime >= 0) {
            return (studiedTime / requiredTime)*100;
        }
        return 0.0;
    }

    public String getName() {
        return name;
    }

//    public int getDifficulty() {
//        return difficulty;
//    }
 public int getRequiredTime() {
        return requiredTime;
 }
    public double getStudiedTime() {
        return studiedTime;
    }
    public int getEcts() {return ects;}

    public int getPredispositions() {
        return predispositions;
    }
}
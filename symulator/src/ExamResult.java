import java.util.Random;

public class ExamResult {
    private Subject subject;
    private double score;
    private boolean passed;
    private Random rand = new Random();
    ExamResult(Subject subject) {
        int losowosc = rand.nextInt(16)-10;
        this.subject = subject;
        score=subject.getProgress()+losowosc;
        if (score>=50) {
            passed=true;
        }
        else {passed=false;}
        if(score>100) {score=100;}
        if(score<0) {score=0;}
        score=Math.round(score);
    }
    public boolean isPassed() {
        return passed;
    }

    public String getGrade() {
        String grade;
        if (passed) {
            grade = "Zdany";
        }
        else {grade = "Oblany";}
        return "Przedmiot "+ subject.getName() + " został " + grade;
    }
    public double getScore() {return score;}
    public String getSubjectName() {
        return subject != null ? subject.getName() : "";
    }
    public String toString() {
        return "Przedmiot: " + subject.getName() + ", wynik: " + score;
    }
    //metoda do sumowania ECTS dla przedmiotów, które nie zostały zaliczone
    public static int sumFailedEcts(java.util.List<ExamResult> results) {
        int ectsFailedSum = 0;
        for (ExamResult result : results) {
            if (!result.isPassed()) {
                ectsFailedSum += result.subject.getEcts();
            }
        }
        return ectsFailedSum;
    }
}
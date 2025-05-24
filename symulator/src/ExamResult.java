import java.util.Random;

public class ExamResult {
    private Subject subject;
    private double score;
    private boolean passed;
    private Random rand = new Random();
    ExamResult(Subject subject) {
        int losowosc = rand.nextInt(11)-10;
        this.subject = subject;
        score=subject.getProgress()+losowosc;
        if (score>=50) {
            passed=true;
        }
        else {passed=false;}

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

    public String getSubjectName() {
        return subject != null ? subject.getName() : "";
    }
}
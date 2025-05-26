import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private int motivation;
    private Plan plan;
    private List<Subject> subjects;
    private List<StudyEvent> history;
    Student(String name, int motivation, Plan plan, List<Subject> subjects) {
        this.name = name;

        //kurcze
    }
    public void study(int day) {
        // implementacja

    }

    public List<ExamResult> takeExams(List<Subject> subjects) {
        // implementacja
        List<ExamResult> wyniki = new ArrayList<>();
        for (int i=0; i<subjects.size(); i++) {
            wyniki.add(new ExamResult(subjects.get(i)));
        }
        return wyniki;
    }

    public void applyMotivator(Motivator m) {
        // implementacja
    }

    public int getMotivation() {
        return motivation;
    }

    public void setMotivation(int motivation) {
        this.motivation = motivation;
    }

    public Plan getPlan() {
        return plan;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public List<StudyEvent> getHistory() {
        return history;
    }
}
import java.util.List;

public class Student {
    private String name;
    private int motivation;
    private Plan plan;
    private List<Subject> subjects;
    private List<StudyEvent> history;

    public void study(int day) {
        // implementacja
    }

    public List<ExamResult> takeExams() {
        // implementacja
        return null;
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
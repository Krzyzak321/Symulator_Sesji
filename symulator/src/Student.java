import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Student {
    private String name;
    private int motivation;
    private Plan plan;
    private List<Subject> subjects;
    private List<StudyEvent> history;
    Student(String name, List<Subject> subjects, int mode) {
        this.name = name;
        int days = ExamSimulator.days;
        Plan plan = new Plan(days, subjects, mode);
        //kurcze co ja robie
    }
    public void study(int day) {
        // implementacja
//        StudyEvent study = new StudyEvent(plan.getDailyPlan(day).get(0),plan.getDailyPlan(day).get(1), day);
        Map<Subject, Integer> dailyPlan = plan.getDailyPlan(day);
        for (Map.Entry<Subject, Integer> entry : dailyPlan.entrySet()) {
            Subject subject = entry.getKey();
            int hours = entry.getValue();
            StudyEvent study = new StudyEvent(subject, hours, day);
            history.add(study);
        }
        // kurde nei wiem co z tą motywacją
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Student {
    private String name;
    private int motivation;
    private Plan plan;
    private List<Subject> subjects;
    private List<StudyEvent> history;
    Student(String name, List<Subject> subjects, int mode, int days) {
        this.name = name;
        this.subjects = subjects;
        this.history = new ArrayList<>();
//        int days = ExamSimulator.getda;
        this.plan = new Plan(days, subjects, mode);
        this.motivation = 100;
//        int days = ExamSimulator.days;
//        Plan plan = new Plan(days, subjects, mode);
        //kurcze co ja robie
    }
    public void study(Subject subject, int hours, int day) {

        //        if (plan == null) return;
//        motivation = 100;
//        Map<Subject, Integer> dailyPlan = plan.getDailyPlan(day);
//        for (Map.Entry<Subject, Integer> entry : dailyPlan.entrySet()) {
//            Subject subject = entry.getKey();
//            int hours = entry.getValue();
//            for (int i = 0; i < hours; i++) {
//                motivation -= 1;// odejmuj 10 za każdą godzinę
//            }
            StudyEvent study = new StudyEvent(subject, hours, day, motivation);
            history.add(study);
//        }
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
        m.apply(this);
    }

    public int getMotivation() {
        return motivation;
    }

    public void setMotivation(int motivation) {
        if (motivation > 200) {
            this.motivation = 200;
        } else if (motivation < 0) {
            this.motivation = 0;
        } else {
            this.motivation = motivation;
        }
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
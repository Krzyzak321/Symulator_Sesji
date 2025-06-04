import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Student {
    private String name;
    private int motivation;
    private Plan plan;
    private List<Subject> subjects;
    private List<StudyEvent> history;
    private List<ExamResult> wyniki;
    public Visualizer visualizer;

    Student(String name, List<Subject> subjects, int mode, int days) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Imię studenta nie może być puste.");
        }
        if (subjects == null || subjects.isEmpty()) {
            throw new IllegalArgumentException("Lista przedmiotów nie może być pusta.");
        }
        if (subjects.stream().anyMatch(s -> s == null)) {
            throw new IllegalArgumentException("Lista przedmiotów nie może zawierać nulli.");
        }
        if (mode < 0) {
            throw new IllegalArgumentException("Tryb musi być nieujemny.");
        }
        if (days <= 0) {
            throw new IllegalArgumentException("Liczba dni musi być dodatnia.");
        }
        this.name = name;
        this.subjects = subjects;
        this.history = new ArrayList<>();
        this.plan = new Plan(days, subjects, mode); //stworzenie planu nauki
        this.motivation = 100;

    }
    public void study(Subject subject, int hours, int day) {
            StudyEvent study = new StudyEvent(subject, hours, day, motivation);
            history.add(study); //dodanie okresu nauki do historii nauki
    }
    // podjęcie sie egzaminów z wszystkich przedmiotów
    public List<ExamResult> takeExams(List<Subject> subjects) {
        wyniki = new ArrayList<>();
        for (Subject subject : subjects) {
            wyniki.add(new ExamResult(subject));
        }
        return wyniki;
    }
    //zmiana motywacji w zależnoći od motywatora
    public void applyMotivator(Motivator m) {
        m.apply(this);
    }

    public int getMotivation() {
        return motivation;
    }

    public void setMotivation(int motivation) {
        if (motivation > 200) {
            this.motivation = 200;
            protectedUpdate(visualizer, 200);
        } else if (motivation < 0) {
            this.motivation = 0;
            protectedUpdate(visualizer, 0);
        } else {
            this.motivation = motivation;
            protectedUpdate(visualizer, motivation);
        }
    }

    public Plan getPlan() {
        return plan;
    }
    public void setPlan(Plan plan) {this.plan = plan;}
    public List<Subject> getSubjects() {
        return subjects;
    }

    public List<StudyEvent> getHistory() {
        return history;
    }

    public void setVisualizer(Visualizer visualizer) {
        this.visualizer = visualizer;
    }

    private void protectedUpdate(Visualizer vis, int motivation){
        if (vis != null) {
            vis.updateMotivation(motivation);
        }
    }
}
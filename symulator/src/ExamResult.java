public class ExamResult {
    private Subject subject;
    private int score;
    private boolean passed;

    public boolean isPassed() {
        return passed;
    }

    public String getGrade() {
        // implementacja
        return "";
    }

    public String getSubjectName() {
        return subject != null ? subject.getName() : "";
    }
}
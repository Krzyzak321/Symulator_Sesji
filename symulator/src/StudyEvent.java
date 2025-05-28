public class StudyEvent {
    private Subject subject;
    private double hours;
    private int day;
    StudyEvent(Subject subject, double hours, int day, double motivation) {
        this.subject = subject;
        this.hours = hours;
        this.day = day;
        subject.study((hours*(motivation/100)));
    }
    public String getDescription() {
        return subject.getName() + " " + hours + " " + day;
    }
}
public class StudyEvent {
    private Subject subject;
    private int hours;
    private int day;
    StudyEvent(Subject subject, int hours, int day, double motivation) {
        this.subject = subject;
        this.hours = hours;
        this.day = day;
        subject.study((int)(hours*(motivation/100)));
    }
    public String getDescription() {
        return subject.getName() + " " + hours + " " + day;
    }
}
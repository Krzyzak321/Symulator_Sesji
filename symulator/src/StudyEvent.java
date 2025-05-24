public class StudyEvent {
    private Subject subject;
    private int hours;
    private int day;
    StudyEvent(Subject subject, int hours, int day) {
        this.subject = subject;
        this.hours = hours;
        this.day = day;
        subject.study(hours);
    }
    public String getDescription() {
        return subject.getName() + " " + hours + " " + day;
    }
}
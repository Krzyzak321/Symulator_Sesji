public class StudyEvent {
    private Subject subject;
    private int hours;
    private int day;
    //do zapisu progresu nauki w danym momencie z określoną motywacja motywacja wpływa na to jak bardzo się nauczył w danym czasie
    StudyEvent(Subject subject, int hours, int day, double motivation) {
        this.subject = subject;
        this.hours = hours;
        this.day = day;
        subject.study((hours*(motivation/100.0)));
    }
    public String getDescription() {
        return subject.getName() + " " + hours + " " + day;
    }
}
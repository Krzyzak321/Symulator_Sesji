import java.util.List;
import java.util.Random;

public class ExamSimulator {
    private Student student;
    private List<Motivator> motivators;
    public static int days;

    public ExamSimulator(Student student, List<Motivator> motivators, int days) {
        this.student = student;
        this.motivators = motivators;
        this.days = days;
    }

    public void run() {
        Random random = new Random();
        for (int day = 1; day <= days; day++) {
            // Pobierz plan na dany dzień
            student.setMotivation(100);
            var dailyPlan = student.getPlan().getDailyPlan(day);
            for (var entry : dailyPlan.entrySet()) {
                Subject subject = entry.getKey();
                int hours = entry.getValue();
                double studied = 0.0;
                for (int h = 0; h < hours; h++) {
                    // Losowanie motivatora co godzinę
                    student.setMotivation(student.getMotivation() - 1);
                    Motivator motivator = motivators.get(random.nextInt(motivators.size()));
                    student.applyMotivator(motivator);
//                    System.out.println("Godzina " + (h+1) + " - zastosowano: " + motivator.getName() +
//                            ", motywacja: " + student.getMotivation());
                    student.study(subject, 1, day);
        // implementacja kurde to będzie piekło...

                }

                System.out.println("Przedmiot: " + subject.getName() + " - postęp: " + subject.getProgress());
//                StudyEvent event = new StudyEvent(subject, hours, day, student.getMotivation());
//                student.getHistory().add(event);
            }
        }
    }
}
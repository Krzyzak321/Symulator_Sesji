import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
        Visualizer window = new Visualizer(student);
        window.insertPlan(student.getPlan());
        window.visualize();

        for (int day = 1; day <= days; day++) {
            // Pobierz plan na dany dzień
            student.setMotivation(100);
            var dailyPlan = student.getPlan().getDailyPlan(day);
            for (var entry : dailyPlan.entrySet()) {
                if(student.getMotivation()<=0){
                    break;
                }
                Subject subject = entry.getKey();
                int hours = entry.getValue();
                double studied = 0.0;
                for (int h = 0; h < hours; h++) {
                    // Losowanie motivatora co godzinę
                    student.setMotivation(student.getMotivation() - 1);
                    Motivator motivator = motivators.get(random.nextInt(motivators.size()));
                    student.applyMotivator(motivator);
                    if(student.getMotivation()<=0){
                        break;
                    }
//                    System.out.println("Godzina " + (h+1) + " - zastosowano: " + motivator.getName() +
//                            ", motywacja: " + student.getMotivation());
                    student.study(subject, 1, day);
                    System.out.println("Przedmiot: " + subject.getName() + " - postęp: " + subject.getProgress());
                    if(subject.isReady()){break;}
                    window.updateGraph(subject, 1);



                    try {
                        TimeUnit.MILLISECONDS.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
        // implementacja kurde to będzie piekło...

                }

//                StudyEvent event = new StudyEvent(subject, hours, day, student.getMotivation());
//                student.getHistory().add(event);
            }

        }

    }
    public int getDays() {return days;}
}
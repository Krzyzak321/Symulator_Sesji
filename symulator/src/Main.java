import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        // Ustawiamy liczbę dni symulacji
        int days = 10;

        // Tworzymy przedmioty
        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject("Matematyka", 6, 70, 2));
        subjects.add(new Subject("Fizyka", 5, 60, 10));
        subjects.add(new Subject("Historia", 3, 80, 18));
        subjects.add(new Subject("Programowanie", 4, 50, 15));

        // Tworzymy studenta
        Student student = new Student("Janek", subjects, 0, days); // tryb 0 = wszystkie codziennie

        // Tworzymy motywatory
        List<Motivator> motivators = new ArrayList<>();
        motivators.add(new PrzerwaNaPiwo());
        motivators.add(new Procrastination());

        // Uruchamiamy symulator
        ExamSimulator simulator = new ExamSimulator(student, motivators, days);
        simulator.run();
        student.getPlan().printPlan();
        // Wyświetlamy wyniki egzaminów
        List<ExamResult> results = student.takeExams(subjects);
        System.out.println("\nWYNIKI EGZAMINÓW:");
        for (ExamResult result : results) {
            System.out.println(result);
        }
        // Wyświetlamy sumę ECTS za niezdane przedmioty
        int failedEcts = ExamResult.sumFailedEcts(results);
        System.out.println("Suma ECTS za niezdane przedmioty: " + failedEcts);

        //  historia nauki
//        System.out.println("\nHISTORIA NAUKI:");
//        for (StudyEvent event : student.getHistory()) {
//            System.out.println(event.getDescription());
//        }

//        for(int i=0; i<1000; i++) {
//
//            siema.updateGraph(subjects.get(1), 1);
//        }

    }
}

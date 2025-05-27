import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Ustawiamy liczbę dni symulacji
        int days = 14;

        // Tworzymy przedmioty
        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject("Matematyka", 6, 70));
        subjects.add(new Subject("Fizyka", 5, 60));
        subjects.add(new Subject("Historia", 3, 80));
        subjects.add(new Subject("Programowanie", 4, 50));

        // Tworzymy studenta
        Student student = new Student("Janek", subjects, 0); // tryb 0 = wszystkie codziennie

        // Tworzymy motywatory
        List<Motivator> motivators = new ArrayList<>();
        motivators.add(new PrzerwaNaPiwo());
        motivators.add(new Procrastination());

        // Uruchamiamy symulator
        ExamSimulator simulator = new ExamSimulator(student, motivators, days);
        simulator.run();

        // Wyświetlamy wyniki egzaminów
        List<ExamResult> results = student.takeExams(subjects);
        System.out.println("\nWYNIKI EGZAMINÓW:");
        for (ExamResult result : results) {
            System.out.println(result);
        }

        // Opcjonalnie: pokazujemy historię nauki
        System.out.println("\nHISTORIA NAUKI:");
        for (StudyEvent event : student.getHistory()) {
            System.out.println(event.getDescription());
        }
    }
}

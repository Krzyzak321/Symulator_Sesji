import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Ustawiamy liczbę dni symulacji
        Reader data = new Reader();
        Student student = new Student("Janek", data.getSubjects(), data.getMode(), data.getDays());
        // Tworzymy przedmioty
//        List<Subject> subjects = new ArrayList<>();
//        subjects.add(new Subject("Matematyka", 6, 70, 2));
//        subjects.add(new Subject("Fizyka", 5, 60, 10));
//        subjects.add(new Subject("Historia", 2, 80));
//        subjects.add(new Subject("Programowanie", 4, 50, 15));

        // Tworzymy studenta
//        Student student = new Student("Janek", subjects, 0, days); // tryb 0 = wszystkie codziennie

        // Tworzymy motywatory
//        List<Motivator> motivators = new ArrayList<>();
//        motivators.add(new PrzerwaNaPiwo());
//        motivators.add(new Procrastination());

        // Uruchamiamy symulator
        ExamSimulator simulator = new ExamSimulator(student, data.getDays());
        simulator.run();
        student.getPlan().printPlan();
        // Wyświetlamy wyniki egzaminów
        List<ExamResult> results = student.takeExams(student.getSubjects());
        System.out.println("\nWYNIKI EGZAMINÓW:");
        for (ExamResult result : results) {
            System.out.println(result);
            student.visualizer.updateResults(result);
        }
        student.visualizer.showResults();
        // Wyświetlamy sumę ECTS za niezdane przedmioty
        int failedEcts = ExamResult.sumFailedEcts(results);
        System.out.println("Suma ECTS za niezdane przedmioty: " + failedEcts);
        student.visualizer.updateResults("Suma ECTS za niezdane przedmioty: " + failedEcts);
        if(failedEcts==0)student.visualizer.updateResults("Sesja zaliczona!!");
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

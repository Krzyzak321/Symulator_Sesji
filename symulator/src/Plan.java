import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Plan {
    private int days;
    private Map<Integer, Map<Subject, Integer>> schedule;

    public void generate(List<Subject> subjects, int totalDays) {
        subjects = SubjectSorter.sortSubjects(subjects);
        this.days = totalDays;
        schedule.clear();

        for (int day = 1; day <= days; day++) {
            schedule.put(day, new HashMap<>());
        }

        for (Subject subject : subjects) {
            int required = subject.getRequiredTime()-subject.getStudiedTime();
            int hoursPerDay = required / days;
            int extraHours = required % days;

            for (int day = 1; day <= days; day++) {
                int hours = hoursPerDay;
                // Dodaj 1h w pierwszych extraHours dniach, żeby rozdzielić resztę
                if (day <= extraHours) {
                    hours += 1;
                }
                Map<Subject, Integer> dayPlan = schedule.get(day);
                dayPlan.put(subject, hours);
            }
        }
    }


    public Map<Subject, Integer> getDailyPlan(int day) {
        // implementacja
        return null;
    }
    //kucze trzeba dodac zortowanie nauczyc sie jak działa mapa i zrobic jakoś podział na plan dniowy
    // do tego warto by dodać coa takeigo ze mozan wyrac sobei czy majż przedmioty być uczone na przemian aby sie mniej nudziło i wtedy mneij spada motywacja czycos
}
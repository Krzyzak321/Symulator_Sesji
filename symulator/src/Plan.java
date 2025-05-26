import java.util.*;

public class Plan {
    private int days;
    private Map<Integer, Map<Subject, Integer>> schedule = new HashMap<>();

    /*
     * Generuje plan nauki według wybranego trybu.
     *  subjects lista przedmiotów
     * totalDays liczba dni nauki
     *  mode 0 = każdy przedmiot codziennie; 1 = po kolei każdy przedmiot do końca
     */
    public void generate(List<Subject> subjects, int totalDays, int mode) {
        this.days = totalDays;
        schedule.clear();

        if (mode == 0) {
            generateAllEveryDay(subjects, totalDays);
        } else if (mode == 1) {
            generateOneAtATime(subjects, totalDays);
        } else {
            throw new IllegalArgumentException("Nieznany tryb generowania planu: " + mode);
        }
    }

    private void generateAllEveryDay(List<Subject> subjects, int days) {
        for (int day = 1; day <= days; day++) {
            schedule.put(day, new HashMap<>());
        }
        for (Subject subject : subjects) {
            int required = subject.getRequiredTime();
            int hoursPerDay = required / days;
            int extraHours = required % days;

            for (int day = 1; day <= days; day++) {
                int hours = hoursPerDay;
                if (day <= extraHours) hours++;
                schedule.get(day).put(subject, hours);
            }
        }
    }

    private void generateOneAtATime(List<Subject> subjects, int days) {
        for (int day = 1; day <= days; day++) {
            schedule.put(day, new HashMap<>());
        }

        int currentDay = 1;
        int maxHoursPerDay = 8; // Przykładowo max 8h nauki dziennie

        for (Subject subject : subjects) {
            int hoursLeft = subject.getRequiredTime();
            while (hoursLeft > 0 && currentDay <= days) {
                int alreadyPlanned = schedule.get(currentDay).values().stream().mapToInt(Integer::intValue).sum();
                int available = maxHoursPerDay - alreadyPlanned;
                if (available <= 0) {
                    currentDay++;
                    continue;
                }
                int hoursToday = Math.min(hoursLeft, available);
                schedule.get(currentDay).put(subject, hoursToday);
                hoursLeft -= hoursToday;
                if (available == hoursToday) {
                    currentDay++;
                }
            }
        }
    }

    public Map<Subject, Integer> getDailyPlan(int day) {
        return schedule.getOrDefault(day, Collections.emptyMap());
    }

    // Prosta wizualizacja planu
    public void printPlan() {
        for (int day = 1; day <= days; day++) {
            System.out.println("Dzień " + day + ":");
            Map<Subject, Integer> dayPlan = getDailyPlan(day);
            for (Map.Entry<Subject, Integer> entry : dayPlan.entrySet()) {
                System.out.println("  " + entry.getKey().getName() + " - " + entry.getValue() + "h");
            }
        }
    }
}
import java.util.*;

public class Plan {
    private int days;
    private Map<Integer, Map<Subject, Integer>> schedule ;
    Plan(int days) {
        this.days = days;
        this.schedule = new HashMap<>();
    }
    Plan(int days, List<Subject> subjects, int mode) {
        this.days = days;
        this.schedule = new HashMap<>();
        generate(subjects,days,mode);
    }
    public Plan(int days, Map<Integer, Map<Subject, Integer>> schedule) {
        this.days = days;
        this.schedule = schedule;
    } // jak gotowy harmonorgram

    /*
     * Generuje plan nauki według wybranego trybu.
     *  subjects lista przedmiotów
     * totalDays liczba dni nauki
     *  mode 0 = każdy przedmiot codziennie; 1 = po kolei każdy przedmiot do końca
     * uważem ze to całkiem niezłe bo można dzięki temu rozbudowac troche bardziej motywacje
     */
    public void generate(List<Subject> subjects, int totalDays, int mode) {
        this.days = totalDays;
        schedule.clear();

        if (mode == 0) {
            generateAllEveryDay(subjects, totalDays);
        } else if (mode == 1) {
            generateOneAtATime(subjects, totalDays);
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
                if (day <= extraHours) hours++; // dodatkowe godziny rozkładają sie po kolejnych niach
                schedule.get(day).put(subject, hours);
            }
        }
    }

    private void generateOneAtATime(List<Subject> subjects, int days) {
        for (int day = 1; day <= days; day++) {
            schedule.put(day, new HashMap<>());
        }

        int currentDay = 1;
        int maxHoursPerDay = 8; //  max 8h nauki dziennie

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
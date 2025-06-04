import java.util.*;

public class Plan {
    private int days;
    private Map<Integer, Map<Subject, Integer>> schedule ;
    Plan(int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Liczba dni musi być większa od 0.");
        this.days = days;
        this.schedule = new HashMap<>();
    }
    Plan(int days, List<Subject> subjects, int mode) {
            if (days <= 0) {
                throw new IllegalArgumentException("Liczba dni musi być większa od 0.");
            }
            if (subjects == null || subjects.isEmpty()) {
                throw new IllegalArgumentException("Lista przedmiotów nie może być pusta.");
            }
            if (mode < 0) {
                throw new IllegalArgumentException("Tryb musi być nieujemny.");
            }
        }
        this.days = days;
        this.schedule = new HashMap<>();
        subjects = SubjectSorter.sortSubjects(subjects);
        generate(subjects,days,mode);
    }
    public Plan(int days, Map<Integer, Map<Subject, Integer>> schedule) {
        if (days <= 0) {
            throw new IllegalArgumentException("Liczba dni musi być większa od 0.");
        }
        if (schedule == null) {
            throw new IllegalArgumentException("Harmonogram nie może być nullem.");
        }
        this.days = days;
        this.schedule = schedule;
    } // jak gotowy harmonorgram

    /*
     * generate() - Generuje plan nauki według wybranego trybu.
     *  subjects - lista przedmiotów
     * totalDays - liczba dni nauki
     *  mode 0 = każdy przedmiot codziennie
     * 1 = po kolei każdy przedmiot do końca
     * z piorytetem nauki tych najważniejszych jak będzi emial czas aby sie nauczyć tylko jednego to tak będzie sie uczył  reszat przedmiotów jest ucinana
     * i nei ma możliwości jejj nauki lub w ograniczonym stopniu.
     * dzięki subject sorter przedmioty są posortowane od najwiekszych ects do najmiej oraz w zależlości od predyspozycji do danego przedmiotu
     */
    public void generate(List<Subject> subjects, int totalDays, int mode) {
        for(Subject subject : subjects) {
            if(subject.isReady()) subjects.remove(subject);
        }
        this.days = totalDays;
        schedule.clear();
        for (int day = 1; day <= days; day++) {
            schedule.put(day, new HashMap<>());
        }

        if (days <= 0) {
            throw new IllegalArgumentException("Liczba dni musi być większa od 0");
        }
        if (mode == 0) {
            generateAllEveryDay(subjects, totalDays);
        } else if (mode == 1) {
            generateOneAtATime(subjects, totalDays);
        }
        for(int day=1; day<=totalDays; day++) {
            var dayplan=getDailyPlan(day);
            if(dayplan.isEmpty()) schedule.remove(Integer.valueOf(day));
        }
        // Usuń puste dni niestety nie działa
//        schedule.entrySet().removeIf(entry -> entry.getValue().isEmpty());
    }

private void generateAllEveryDay(List<Subject> subjects, int days) {
    int maxHoursPerDay = 8;
    // Dla każdego przedmiotu rozdziel godziny po równo na każdy a resztę z dzielenia przechowaja aby pododawać po jednej godzinie w bardziej wolnym dniu
    for (Subject subject : subjects) {
        int required = subject.getRequiredTime();
        int hoursPerDay = required / days;
        int extraHours = required % days;

        for (int day = 1; day <= days; day++) {
            int hours = hoursPerDay;
            if (day <= extraHours) hours++;

            // ile już zaplanowano na ten dzień
            int alreadyPlanned = 0;
            for (int value : schedule.get(day).values()) {
                alreadyPlanned += value;
            }
            // przekroczy limit, usuń nadmiar
            int canAssign = Math.min(hours, maxHoursPerDay - alreadyPlanned);

            if (canAssign > 0) {
                schedule.get(day).put(subject, canAssign);
            }
            //w innym przypadku do następengo dnia i pomija nauke tego przedmiotu w tym dniu
        }
    }
}
    private void generateOneAtATime(List<Subject> subjects, int days) {
        int currentDay = 1; //pomocniczo
        int maxHoursPerDay = 8; //  max 8h nauki dziennie
        //iteracja po każdym przedmiocie
        for (Subject subject : subjects) {
            int hoursLeft = subject.getRequiredTime();
            while (hoursLeft > 0 && currentDay <= days) { //pętla trwa do momentu wyczepania godzin przedmiotu lub dostępnych dni
                int alreadyPlanned = 0; //sprawdzenie ile już zostało zaplanowane godzin danego dnia
                for (int value : schedule.get(currentDay).values()) {
                    alreadyPlanned += value;
                }
                int available = maxHoursPerDay - alreadyPlanned; //dostępne godziny oraz ustala limit godzin na dzień
                if (available <= 0) {
                    currentDay++;
                    continue;
                }
                int hoursToday = Math.min(hoursLeft, available); //sprawdzenei ile przydzielić w tym dniu
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
    //wypisanie planu do konsoli
    public void printPlan() {
        for (int day = 1; day <= days; day++) {
            System.out.println("Dzień " + day + ":");
            Map<Subject, Integer> dayPlan = getDailyPlan(day);
            for (Map.Entry<Subject, Integer> entry : dayPlan.entrySet()) {
                System.out.println("  " + entry.getKey().getName() + " - " + entry.getValue() + "h");
            }
        }
    }
    //było potrzebne od wcześniejszej wersji generowania planu
    //    private int countTotalRequired(List<Subject> subjects) {
//        int totalRequired = 0;
//        for (Subject subject : subjects) {
//            totalRequired += subject.getRequiredTime();
//        }
//        return totalRequired;
//    }
    public Map<Integer, Map<Subject, Integer>> getSchedule() {return schedule;}
}
import java.util.*;
import java.util.concurrent.TimeUnit;

public class ExamSimulator {
    private Student student;
    private List<Motivator> motivators;
    public static int days;

    public ExamSimulator(Student student, List<Motivator> motivators, int days) {
        if (student == null) {
            throw new IllegalArgumentException("Student nie może być nullem.");
        }
        if (motivators == null || motivators.isEmpty()) {
            throw new IllegalArgumentException("Lista motywatorów nie może być pusta.");
        }
        if (motivators.stream().anyMatch(m -> m == null)) {
            throw new IllegalArgumentException("Lista motywatorów nie może zawierać nulli.");
        }
        if (days <= 0) {
            throw new IllegalArgumentException("Liczba dni musi być większa od 0.");
        }
        this.student = student;
        this.motivators = motivators;
        this.days = days;
    }

    public void run() {
        Random random = new Random();
        Visualizer window = new Visualizer(student);
        window.insertPlan(student.getPlan());
        window.visualize();
        //int bonusMotivation = 0; miał to zostać zastosowane do tego że ktoś sie wcześniej nauczył danego przedmiotu i miał to być dodatkowy zastrzyk motywacji lecz, zrezygnowane i zrobione uaktualnienei planu tak aby jak najwięcej czasu wykorzystać
        for (int day = 1; day <= days; day++) {
            // Pobierz plan na dany dzień
            Map<Subject, Integer> dailyPlan = new LinkedHashMap<>(student.getPlan().getDailyPlan(day));
            List<Subject> subjectsToday = new ArrayList<>(dailyPlan.keySet());
//            student.setMotivation(100+bonusMotivation);
//            bonusMotivation = 0;
            student.setMotivation(100);
            // iteracaj po kazdym przedmiocie w danym dniu
            for (var subject : subjectsToday) {
                if(student.getMotivation()<=0){
                    break;  //kiedy motywacja spadnie do 0 to przerwij naukę bo nic z tego nie wyjdzie
                }
                int hours = dailyPlan.get(subject);
                for (int h = 1; h <= hours; h++) {
                    // Losowanie motivatora co godzinę i motywacja wpływa an naukę jak ma większą od neutralnej uczy się szybciej a jak mniejszą to wolniej lecz godziny i tak lecą tylko mniej lub bardziej sie nauczy na przedmiot
                    Motivator motivator = motivators.get(random.nextInt(motivators.size()));
                    student.applyMotivator(motivator);
                    if(student.getMotivation()<=0){
                        break;
                    }
//                    System.out.println("Godzina " + (h+1) + " - zastosowano: " + motivator.getName() +
//                            ", motywacja: " + student.getMotivation());
                    student.study(subject, 1, day);
                    System.out.println("Przedmiot: " + subject.getName() + " - postęp: " + subject.getProgress()); //wyśwetlanie postępu nauki
                    if(subject.isReady() && (hours-h)>0) { //w przypadku jak przedmiot już nauczony lecz pozostały wolne godziny
                        // Rozdziel wolne godziny innym przedmiotom z planu na dziś
                        int freeHours = hours - h;
                        for (Subject other : subjectsToday) {
                            if (other == subject || other.isReady()) continue;
                            int current = dailyPlan.get(other);
                            int maxToday = 8;
                            int canGive = Math.min(freeHours, maxToday - current);
                            if (canGive > 0) {
                                dailyPlan.put(other, current + canGive);
                                freeHours -= canGive;
                            }
                            if (freeHours <= 0) break;
                        }

                        //  ROZDZIELANIE GODZIN W PRZYSZŁYCH DNIACH
                        for (int futureDay = day + 1; futureDay <= days; futureDay++) {
                            Map<Subject, Integer> planForDay = student.getPlan().getDailyPlan(futureDay);
                            if (planForDay != null && planForDay.containsKey(subject)) {
                                int subjectHours = planForDay.get(subject);
                                planForDay.remove(subject);

                                int freeFutureHours = subjectHours;
                                for (Subject other : planForDay.keySet()) {
                                    if (other.isReady()) continue;
                                    int current = planForDay.get(other);
                                    int maxToday = 8;
                                    int canGive = Math.min(freeFutureHours, maxToday - current);
                                    if (canGive > 0) {
                                        planForDay.put(other, current + canGive);
                                        freeFutureHours -= canGive;
                                    }
                                    if (freeFutureHours <= 0) break;
                                }
                            }
                        }
                        break;
                    }

                    window.updateGraph(subject, 1);
                    //wielkie spowolneinie kodu 30h symulacja
                    try {
                        TimeUnit.MILLISECONDS.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //zmniejszenie motywacji co godzinę przez zmęczenie nauką
                    student.setMotivation(student.getMotivation() - 5);
                }

            }

        }

    }
    public int getDays() {return days;}
}
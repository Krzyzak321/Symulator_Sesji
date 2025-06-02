import java.util.*;
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
        int bonusMotivation = 0;

        for (int day = 1; day <= days; day++) {
            // Pobierz plan na dany dzień
            Map<Subject, Integer> dailyPlan = new LinkedHashMap<>(student.getPlan().getDailyPlan(day));
            List<Subject> subjectsToday = new ArrayList<>(dailyPlan.keySet());
            student.setMotivation(100+bonusMotivation);
            bonusMotivation = 0;


            for (var subject : subjectsToday) {
                if(student.getMotivation()<=0){
                    break;
                }
                int hours = dailyPlan.get(subject);
                for (int h = 1; h <= hours; h++) {
                    // Losowanie motivatora co godzinę

                    Motivator motivator = motivators.get(random.nextInt(motivators.size()));
                    student.applyMotivator(motivator);
                    if(student.getMotivation()<=0){
                        break;
                    }
//                    System.out.println("Godzina " + (h+1) + " - zastosowano: " + motivator.getName() +
//                            ", motywacja: " + student.getMotivation());
                    student.study(subject, 1, day);
                    System.out.println("Przedmiot: " + subject.getName() + " - postęp: " + subject.getProgress());
                    if(subject.isReady() && (hours-h)>0) {
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
                    //                    if(subject.isReady() && (hours-h)>0){
//                        Subject doEdycji;
//                        for (Subject other : dailyPlan.keySet()) {
//                            if(other.isReady()){continue;}
//                            else {
//                        int ile = dailyPlan.get(other);
//                            dailyPlan.put(other, ile+(hours-h));
//                            doEdycji = other;
//                            break;
//                            }
//                        }
//                    var newSubjectList = student.getSubjects();
//                    newSubjectList.remove(subject);
//
//                        // Generuj nowy plan tylko dla pozostałych przedmiotów i dni
//                        Plan newPlan = new Plan(days-day, newSubjectList, 0); // lub inny tryb
//                        // Skopiuj stare dni do newPlan
//                        for (int d = 1; d < day; d++) {
//                            newPlan.getSchedule().put(d, student.getPlan().getDailyPlan(d));
//                        }
//                        student.setPlan(newPlan);
//
//
//                        break;}
                    window.updateGraph(subject, 1);



                    try {
                        TimeUnit.MILLISECONDS.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
        // implementacja kurde to będzie piekło...
                    student.setMotivation(student.getMotivation() - 5);
                }

//                StudyEvent event = new StudyEvent(subject, hours, day, student.getMotivation());
//                student.getHistory().add(event);
            }

        }

    }
    public int getDays() {return days;}
}
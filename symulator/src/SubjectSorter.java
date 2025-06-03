import java.util.ArrayList;
import java.util.List;

public class SubjectSorter {
    public static  List<Subject> sortSubjects(List<Subject> subjects) {
        List<Subject> sorted = new ArrayList<>(subjects);
        sorted.sort((s1, s2) -> {
            // liczenei ogólnej trudności
            int realDiff1 = s1.getEcts() - (int)(s1.getEcts()/2.0*s1.getPredispositions()/100.0);
            int realDiff2 = s2.getEcts() - (int)(s2.getEcts()/2.0*s2.getPredispositions()/100.0);
            // taki wzór aby zgadzał sie z wzorem na potrzebny czas nauki danego przedmiotu
            if (realDiff2 != realDiff1) {
                return Integer.compare(realDiff2, realDiff1);
            }
            // Jeśli realna trudność taka sama, sortuj po ECTS malejącow
            return Integer.compare(s2.getEcts(), s1.getEcts());
        });
        return sorted;
    }
    //zrobione z pomoca https://www.w3schools.com/java/java_advanced_sorting.asp
    //intiger.compare zwraca -1, 0 , 1 co pozwala w prosty sposób użyć sortowania na obiektach
}
import java.util.ArrayList;
import java.util.List;

public class SubjectSorter {
    public static  List<Subject> sortSubjects(List<Subject> subjects) {
        List<Subject> sorted = new ArrayList<>(subjects);
        sorted.sort((s1, s2) -> {
            // liczenei ogólnej trudności
            int realDiff1 = s1.getEcts() - s1.getPredispositions()/100;
            int realDiff2 = s2.getEcts() - s2.getPredispositions()/100;
            // zrobilem taki wzór bo uważam ze nawej jak ziomek jest zdolny z danego przedmiotu to jednak warto by było że
            // ma jak najwiecej ects w moze wogule nei brać pod uwage tych predyspozycji nwm tzreba by to przemyśleć
            // :(
            if (realDiff2 != realDiff1) {
                return Integer.compare(realDiff2, realDiff1);
            }
            // Jeśli realna trudność taka sama, sortuj po ECTS malejącow
            return Integer.compare(s2.getEcts(), s1.getEcts());
        });
        return sorted;
    }
}
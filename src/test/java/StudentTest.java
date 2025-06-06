import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class StudentTest {

    @Test
    public void testMotivation() {
        // Konstruktor: Student(String name, List<Subject> subjects, int motivation, int intelligence)
        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject("aha", 10, 10));
        Student s = new Student("Jan", subjects, 10, 5);
        s.setMotivation(20);
        assertEquals(20, s.getMotivation());
    }
}
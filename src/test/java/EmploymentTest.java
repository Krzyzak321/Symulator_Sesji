import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class EmploymentTest {

    @Test
    public void testApplyIncreasesMotivation() {
        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject("test", 1, 0));
        Student s = new Student("Anna", subjects, 5, 5);
        Employment e = new Employment();
        e.apply(s);
        assertEquals(130, s.getMotivation());
    }

    @Test
    public void testGetName() {
        Employment e = new Employment();
        assertEquals("Szansa na dobrą pracę", e.getName());
    }
}
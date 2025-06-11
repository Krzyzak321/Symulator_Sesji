import org.junit.Test;
import static org.junit.Assert.*;

public class SubjectTest {
    @Test
    public void testSubjectName() {
        Subject subj = new Subject("Matematyka", 6, 30, 3);
        assertEquals("Matematyka", subj.getName());
    }
}
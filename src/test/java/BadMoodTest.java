import org.junit.Test;
import static org.junit.Assert.*;

public class BadMoodTest {

    @Test
    public void testGetName() {
        BadMood b = new BadMood();
        assertNotNull(b.getName());
    }
}
import org.junit.Test;
import static org.junit.Assert.*;

public class ProcrastinationTest {

    @Test
    public void testGetName() {
        Procrastination p = new Procrastination();
        assertNotNull(p.getName());
    }
}
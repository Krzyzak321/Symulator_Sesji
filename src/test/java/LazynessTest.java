import org.junit.Test;
import static org.junit.Assert.*;

public class LazynessTest {

    @Test
    public void testGetName() {
        Lazyness l = new Lazyness();
        assertNotNull(l.getName());
    }
}
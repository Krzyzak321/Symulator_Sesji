import org.junit.Test;
import static org.junit.Assert.*;

public class DiseaseTest {

    @Test
    public void testGetName() {
        Disease d = new Disease();
        assertNotNull(d.getName());
    }
}
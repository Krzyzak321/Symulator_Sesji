import org.junit.Test;
import static org.junit.Assert.*;

public class PrzerwaNaPiwoTest {

    @Test
    public void testGetName() {
        PrzerwaNaPiwo p = new PrzerwaNaPiwo();
        assertNotNull(p.getName());
    }
}
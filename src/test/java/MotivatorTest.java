import org.junit.Test;
import static org.junit.Assert.*;

public class MotivatorTest {

    @Test
    public void testInterfaceExists() {
        // Interfejsu nie można instancjonować, ale można sprawdzić czy jest typem
        assertTrue(Motivator.class.isInterface());
    }
}
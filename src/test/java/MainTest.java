import org.junit.Test;
import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void testMainRuns() {
        // Sprawdzamy tylko czy main się uruchamia bez błędów
        Main.main(new String[]{});
    }
}
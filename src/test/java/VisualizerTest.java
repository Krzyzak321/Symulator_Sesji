import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class VisualizerTest {

    @Test
    public void testCreateVisualizer() {
        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject("matematyka", 10, 10));
        Student s = new Student("Anna", subjects, 10, 5);
        Visualizer v = new Visualizer(s);
        v.visualize();
        assertNotNull(v);
    }
}
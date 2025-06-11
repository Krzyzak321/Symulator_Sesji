import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class PlanTest {

    @Test
    public void testCreatePlan() {
        // Konstruktor: Plan(int id)
        Plan plan = new Plan(1);

        // Subject(String name, int ects, int hours, int difficulty) lub (String name, int ects, int hours)
        Subject subject = new Subject("Matematyka", 6, 30, 3);

        // Dodaj subject do planu jeśli masz metodę do tego, jeśli nie - pomiń ten test
        // plan.addSubject(subject);

        // Jeśli masz getSubject lub getSubjects, przetestuj poprawność dodania
        // assertTrue(plan.getSubjects().contains(subject));
    }
}
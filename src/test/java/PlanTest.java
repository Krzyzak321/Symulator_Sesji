import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class PlanTest {

    @Test
    public void testCreatePlan() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Plan(0));
        assertEquals("Liczba dni musi być większa od 0.", exception.getMessage());

        // Konstruktor: Plan(int id)
//        Plan plan = new Plan(1);

        // Subject(String name, int ects, int hours, int difficulty) lub (String name, int ects, int hours)
//        Subject subject = new Subject("Matematyka", 6, 30, 3);

        // Dodaj subject do planu jeśli masz metodę do tego, jeśli nie - pomiń ten test
        // plan.addSubject(subject);

        // Jeśli masz getSubject lub getSubjects, przetestuj poprawność dodania
        // assertTrue(plan.getSubjects().contains(subject));
    }
}
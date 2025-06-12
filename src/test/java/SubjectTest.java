import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SubjectTest {

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                new Subject(null, 5, 0, 10)
        );
        assertEquals("Nazwa przedmiotu nie może być pusta.", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                new Subject("   ", 5, 0, 10)
        );
        assertEquals("Nazwa przedmiotu nie może być pusta.", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEctsIsZeroOrNegative() {
        IllegalArgumentException exZero = assertThrows(IllegalArgumentException.class, () ->
                new Subject("Matematyka", 0, 0, 10)
        );
        assertEquals("Liczba ECTS musi być dodatnia.", exZero.getMessage());

        IllegalArgumentException exNegative = assertThrows(IllegalArgumentException.class, () ->
                new Subject("Matematyka", -1, 0, 10)
        );
        assertEquals("Liczba ECTS musi być dodatnia.", exNegative.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenStudiedTimeIsNegative() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                new Subject("Matematyka", 5, 0, -5)
        );
        assertEquals("Czas nauki nie może być ujemny.", ex.getMessage());
    }

    @Test
    void shouldCreateSubjectWhenValidArguments() {
        Subject subject = new Subject("Fizyka", 5, 0, 10);
        assertNotNull(subject);
        assertEquals("Fizyka", subject.getName());
        assertEquals(5, subject.getEcts());
        assertEquals(50, subject.getPredispositions());
        assertEquals(10, subject.getStudiedTime());
    }
}
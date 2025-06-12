import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SubjectSorterTest {

    @Test
    public void testCreateSubjectSorter() {
        List<Subject> subjectList = new ArrayList<Subject>();
        subjectList.add(new Subject("Matematyka", 1, 50));
        subjectList.add(new Subject("Fizyka", 10, 50));

        List<Subject> result = SubjectSorter.sortSubjects(subjectList);
        assertEquals("Fizyka", result.get(0).getName());
        assertEquals("Matematyka", result.get(1).getName());
    }
}
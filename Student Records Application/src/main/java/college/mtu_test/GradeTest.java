package college.mtu_test;

import college.mtu_model.Grade;
import college.mtu_model.Module;
import college.mtu_model.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the Grade class.
 * This class tests the functionality of the Grade class, which represents the grade of a module of a student in a college.
 */
public class GradeTest {

    private Grade grade;
    private Student student;
    private Module module;

    /**
     * Sets up the test environment by creating a Grade object with associated Student and Module objects.
     */
    @Before
    public void setUp() {
        student = new Student("Furqan", "R00224511", "17/02/2002", 3);
        module = new Module("Mathematics", "MT942", 8);
        grade = new Grade(student, module, 79);
    }

    /**
     * Test case for getting the student ID.
     * This test checks if the correct student ID is retrieved from the Grade object.
     * <p>
     * Test output: The expected student object.
     * Preconditions: A valid Grade object with an associated Student object.
     * Test procedure:
     * 1. Call the getStudentId() method on the Grade object.
     * 2. Verify that the returned Student object is the same as the expected Student object.
     */
    @Test
    public void testGetStudentId() {
        Student expectedStudent = student;
        Student actualStudent = grade.getStudentId();
        assertEquals("Expected the correct student ID", expectedStudent, actualStudent);
    }

    /**
     * Test case for setting the student ID.
     * This test checks if the student ID can be updated in the Grade object.
     * <p>
     * Test output: The updated student object.
     * Preconditions: A valid Grade object with an associated Student object.
     * Test procedure:
     * 1. Create a new Student object.
     * 2. Call the setStudentId() method on the Grade object with the new Student object as the argument.
     * 3. Call the getStudentId() method on the Grade object.
     * 4. Verify that the returned Student object is the same as the new Student object.
     */
    @Test
    public void testSetStudentId() {
        Student newStudent = new Student("Ali", "R00784964", "10/09/2000", 3);
        grade.setStudentId(newStudent);
        Student actualStudent = grade.getStudentId();
        assertEquals("Expected the updated student ID", newStudent, actualStudent);
    }

    /**
     * Test case for getting the module code.
     * This test checks if the correct module code is retrieved from the Grade object.
     * <p>
     * Test output: The expected module object.
     * Preconditions: A valid Grade object with an associated Module object.
     * Test procedure:
     * 1. Call the getModuleCode() method on the Grade object.
     * 2. Verify that the returned Module object is the same as the expected Module object.
     */
    @Test
    public void testGetModuleCode() {
        Module expectedModule = module;
        Module actualModule = grade.getModuleCode();
        assertEquals("Expected the correct module code", expectedModule, actualModule);
    }

    /**
     * Test case for setting the module code.
     * This test checks if the module code can be updated in the Grade object.
     * <p>
     * Test output: The updated module object.
     * Preconditions: A valid Grade object with an associated Module object.
     * Test procedure:
     * 1. Create a new Module object.
     * 2. Call the setModuleCode() method on the Grade object with the new Module object as the argument.
     * 3. Call the getModuleCode() method on the Grade object.
     * 4. Verify that the returned Module object is the same as the new Module object.
     */
    @Test
    public void testSetModuleCode() {
        Module newModule = new Module("Physics", "MT564", 9);
        grade.setModuleCode(newModule);
        Module actualModule = grade.getModuleCode();
        assertEquals("Expected the updated module code", newModule, actualModule);
    }

    /**
     * Test case for getting a valid grade.
     * This test checks if the correct grade is retrieved from the Grade object.
     * <p>
     * Test output: The expected grade.
     * Preconditions: A valid Grade object with an associated grade.
     * Test procedure:
     * Call the getGrade() method on the Grade object.
     * Verify that the returned grade is the same as the expected grade.
     */
    @Test
    public void testGetGrade() {
        float expectedGrade = 79;
        float actualGrade = grade.getGrade();
        assertEquals("Expected the correct grade", expectedGrade, actualGrade, 0.001);
    }

    /**
     * Test case for setting a new grade.
     * This test checks if the Grade object's grade is updated correctly after a new grade is set.
     * <p>
     * Test output: The updated grade.
     * Preconditions: A valid Grade object with an associated grade, and a new valid grade.
     * Test procedure:
     * Call the setGrade() method on the Grade object with the new grade.
     * Call the getGrade() method on the Grade object.
     * Verify that the returned grade is the same as the expected updated grade.
     */
    @Test
    public void testSetGrade() {
        float newGrade = 89.9f;
        grade.setGrade(newGrade);
        float actualGrade = grade.getGrade();
        assertEquals("Expected the updated grade", newGrade, actualGrade, 0.001);
    }
}

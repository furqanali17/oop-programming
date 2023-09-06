package college.mtu_test;

import college.mtu_records.Controller;
import college.mtu_model.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for the Student class.
 * This class tests the functionality of the Student class, which represents a student in a college.
 */
public class StudentTest {
    Controller controller;
    Student student = new Student("Furqan", "R00224511", "17/02/2002", 3);

    /**
     * Set up method to initialize the controller object before executing the test cases.
     */
    @Before
    public void setUp() {
        controller = new Controller();
    }

    /**
     * Test case for getting a valid student name.
     * This test checks if the correct student name is retrieved from the Student object.
     * <p>
     * Test output: The expected student name.
     * Preconditions: A valid Student object with an associated name.
     * Test procedure:
     * 1. Call the getStudentName() method on the Student object.
     * 2. Verify that the returned name is the same as the expected student name.
     */
    @Test
    public void testValidStudentName() {
        String testName = "Furqan";
        String name = student.getStudentName();
        assertNotNull(name);
        assertEquals(testName, name);
    }

    /**
     * Test case for getting a valid student ID.
     * This test checks if the correct student ID is retrieved from the Student object.
     * <p>
     * Test output: The expected student ID.
     * Preconditions: A valid Student object with an associated ID.
     * Test procedure:
     * 1. Call the getStudentId() method on the Student object.
     * 2. Verify that the returned ID is the same as the expected student ID.
     */
    @Test
    public void testValidStudentId() {
        String testID = "R00224511";
        String id = student.getStudentId();
        assertEquals(testID, id);
    }

    /**
     * Test case for validating a valid date of birth.
     * This test checks if the given date of birth is valid according to the validation logic in the Controller.
     * <p>
     * Test output: A null value indicating no error.
     * Preconditions: A valid date of birth string.
     * Test procedure:
     * 1. Call the validateDob() method on the Controller object with the valid date of birth string.
     * 2. Verify that the returned error message is null.
     */
    @Test
    public void testValidDateOfBirth() {
        String validDob = "17/02/2002";
        String dobError = controller.validateDob(validDob);
        assertNull("Expected valid date of birth", dobError);
    }

    /**
     * Test case for validating an invalid date of birth.
     * This test checks if the given date of birth is invalid according to the validation logic in the Controller.
     * <p>
     * Test output: A non-null value indicating an error.
     * Preconditions: An invalid date of birth string.
     * Test procedure:
     * 1. Call the validateDob() method on the Controller object with the invalid date of birth string.
     * 2. Verify that the returned error message is not null.
     */
    @Test
    public void testInvalidDateOfBirth() {
        String invalidDob = "17-02-2002";
        String dobError = controller.validateDob(invalidDob);
        assertNotNull("Expected invalid date of birth", dobError);
    }

    /**
     * Test case for validating a valid semester.
     * This test checks if the given semester is valid according to the validation logic in the Controller.
     * <p>
     * Test output: A false value indicating a valid semester.
     * Preconditions: A valid semester integer value.
     * Test procedure:
     * 1. Call the validateSemester() method on the Controller object with the valid semester value.
     * 2. Verify that the returned boolean value is false.
     */
    @Test
    public void testValidSemester() {
        int validSemester = 3;
        boolean isSemesterValid = controller.validateSemester(validSemester);
        assertFalse("Expected valid semester", isSemesterValid);
    }

    /**
     * Test case for validating an invalid semester.
     * This test checks if the given semester is invalid according to the validation logic in the Controller.
     * <p>
     * Test output: A true value indicating an invalid semester.
     * Preconditions: An invalid semester integer value.
     * Test procedure:
     * 1. Call the validateSemester() method on the Controller object with the valid semester value.
     * 2. Verify that the returned boolean value is true.
     */
    @Test
    public void testInvalidSemester() {
        int invalidSemester = 12;
        boolean isSemesterInvalid = controller.validateSemester(invalidSemester);
        assertTrue("Expected invalid semester", isSemesterInvalid);
    }
}
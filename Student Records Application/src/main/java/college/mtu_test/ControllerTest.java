package college.mtu_test;
import college.mtu_records.Controller;


import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ControllerTest {

    private final Controller validation = new Controller();

    /**
     * Test case for validating a valid student name.
     * This test checks if the given student name is valid according to the validation logic in the Controller.
     *
     * Test output: A null value indicating no error.
     * Preconditions: A valid student name string.
     * Test procedure:
     * 1. Call the validateStudentName() method on the Controller object with the valid student name string.
     * 2. Verify that the returned error message is null.
     */
    @Test
    public void testValidateStudentName() {
        assertNull(validation.validateStudentName("Aisha Malik"));
        assertEquals("Student name cannot be empty.", validation.validateStudentName(""));
        assertEquals("Student name cannot be empty.", validation.validateStudentName("   "));
        assertEquals("Student name cannot be empty.", validation.validateStudentName(null));
    }

    /**
     * Test case for validating a valid module name.
     * This test checks if the given module name is valid according to the validation logic in the Controller.
     *
     * Test output: A null value indicating no error.
     * Preconditions: A valid module name string.
     * Test procedure:
     * 1. Call the validateModuleName() method on the Controller object with the valid module name string.
     * 2. Verify that the returned error message is null.
     */
    @Test
    public void testValidateModuleName() {
        assertNull(validation.validateModuleName("Mathematics"));
        assertEquals("Module name cannot be empty.", validation.validateModuleName(""));
        assertEquals("Module name cannot be empty.", validation.validateModuleName("   "));
        assertEquals("Module name cannot be empty.", validation.validateModuleName(null));
    }

    /**
     * Test case for validating a valid student ID.
     * This test checks if the given student ID is valid according to the validation logic in the Controller.
     *
     * Test output: A null value indicating no error.
     * Preconditions: A valid student ID string.
     * Test procedure:
     * 1. Call the validateStudentId() method on the Controller object with the valid student ID string.
     * 2. Verify that the returned error message is null.
     */
    @Test
    public void testValidateStudentId() {
        assertNull(validation.validateStudentId("R00123456"));
        assertEquals("Student ID cannot be empty.", validation.validateStudentId(""));
        assertEquals("Invalid student ID. It must start with 'R00' and be 9 characters long.", validation.validateStudentId("R12345678"));
        assertEquals("Invalid student ID. It must start with 'R00' and be 9 characters long.", validation.validateStudentId("R0012345"));
    }

    /**
     * Test case for validating a valid module code.
     * This test checks if the given module code is valid according to the validation logic in the Controller.
     *
     * Test output: A null value indicating no error.
     * Preconditions: A valid module code string.
     * Test procedure:
     * 1. Call the validateModuleCode() method on the Controller object with the valid module code string.
     * 2. Verify that the returned error message is null.
     */
    @Test
    public void testValidateModuleCode() {
        assertNull(validation.validateModuleCode("MT123"));
        assertEquals("Module code cannot be empty.", validation.validateModuleCode(""));
        assertEquals("Invalid module code. It must start with 'MT' and be 5 characters long.", validation.validateModuleCode("MT12"));
        assertEquals("Invalid module code. It must start with 'MT' and be 5 characters long.", validation.validateModuleCode("MC123"));
    }

    /**
     * Test case for validating a valid grade.
     * This test checks if the given grade is within the valid range of 0 to 100.
     *
     * Test output: A true value indicating a valid grade.
     * Preconditions: A valid grade value within the range of 0 to 100.
     * Test procedure:
     * 1. Call the validateGrade() method on the Controller object with the valid grade value.
     * 2. Verify that the returned boolean value is true.
     */
    @Test
    public void testValidateGrade() {
        assertTrue(validation.validateGrade(0));
        assertTrue(validation.validateGrade(100));
        assertFalse(validation.validateGrade(-1));
        assertFalse(validation.validateGrade(101));
    }

    /**
     * Test case for validating a valid date of birth.
     * This test checks if the given date of birth is valid according to the validation logic in the Controller.
     *
     * Test output: A null value indicating no error.
     * Preconditions: A valid date of birth string.
     * Test procedure:
     * 1. Call the validateDob() method on the Controller object with the valid date of birth string.
     * 2. Verify that the returned error message is null.
     */
    @Test
    public void testValidateDob() {
        assertNull(validation.validateDob("01/01/2000"));
        assertEquals("Student must be at least 16 years old.", validation.validateDob("01/01/2020"));
        assertEquals("Invalid date format. Please use the format DD/MM/YYYY.", validation.validateDob("01-01-2000"));
        assertEquals("Date of birth cannot be greater than today's date.", validation.validateDob("01/01/2050"));
    }

    /**
     * Test case for validating a valid semester.
     * This test checks if the given semester is valid according to the validation logic in the Controller.
     *
     * Test output: A false value indicating a valid semester.
     * Preconditions: A valid semester integer value.
     * Test procedure:
     * 1. Call the validateSemester() method on the Controller object with the valid semester value.
     * 2. Verify that the returned boolean value is false.
     */
    @Test
    public void testValidateSemester() {
        assertFalse(validation.validateSemester(10));
        assertFalse(validation.validateSemester(1));
        assertTrue(validation.validateSemester(11));
    }
}

package college.mtu_test;

import college.mtu_records.Controller;
import college.mtu_model.Module;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for the Module class.
 * This class tests the functionality of the Module class, which represents a module of a student in a college.
 */
public class ModuleTest {
    Controller controller;
    Module module = new Module("Mathematics", "MT942", 8);

    /**
     * Set up method to initialize the controller object before executing the test cases.
     */
    @Before
    public void setUp() {
        controller = new Controller();
    }

    /**
     * Test case for getting a valid module name.
     * This test checks if the correct module name is retrieved from the Module object.
     * <p>
     * Test output: The expected module name.
     * Preconditions: A valid Module object with an associated name.
     * Test procedure:
     * 1. Call the getModuleName() method on the Module object.
     * 2. Verify that the returned name is the same as the expected module name.
     */
    @Test
    public void testValidModuleName() {
        String testName = "Mathematics";
        String name = module.getModuleName();
        assertNotNull(name);
        assertEquals(testName, name);
    }

    /**
     * Test case for getting a valid module code.
     * This test checks if the correct module code is retrieved from the Module object.
     * <p>
     * Test output: The expected module code.
     * Preconditions: A valid Module object with an associated code.
     * Test procedure:
     * 1. Call the getModuleCode() method on the Module object.
     * 2. Call the validateModuleCode() method on the Controller object with the expected module code.
     * 3. Verify that the returned error message is null and the retrieved module code is the same as the expected module code.
     */
    @Test
    public void testValidModuleCode() {
        String testModuleCode = "MT942";
        String code = module.getModuleCode();
        String moduleCodeError = controller.validateModuleCode(testModuleCode);
        assertNull("Expected valid module code", moduleCodeError);
        assertEquals(testModuleCode, code);
    }

    /**
     * Test case for validating an invalid module code.
     * This test checks if an invalid module code is rejected according to the validation logic in the Controller.
     * <p>
     * Test output: A non-null error message indicating an invalid module code.
     * Preconditions: An invalid module object with an associated code.
     * Test procedure:
     * 1. Create a new Module object with an invalid module code.
     * 2. Call the getModuleCode() method on the invalid Module object.
     * 3. Call the validateModuleCode() method on the Controller object with the invalid module code.
     * 4. Verify that the returned error message is not null.
     */
    @Test
    public void testInvalidModuleCode() {
        Module invalidModule = new Module("Mathematics", "MP763", 8);
        String moduleCode = invalidModule.getModuleCode();
        String moduleCodeError = controller.validateModuleCode(moduleCode);
        assertNotNull("Expected invalid module code", moduleCodeError);
    }

    /**
     * Test case for validating a valid module semester.
     * This test checks if a valid semester is accepted according to the validation logic in the Controller.
     * <p>
     * Test output: A false value indicating a valid semester.
     * Preconditions: A valid Module object with an associated semester.
     * Test procedure:
     * 1. Call the getSemester() method on the Module object.
     * 2. Call the validateSemester() method on the Controller object with the retrieved semester value.
     * 3. Verify that the returned boolean value is false.
     */
    @Test
    public void testValidModuleSemester() {
        int semester = module.getSemester();
        boolean isSemesterInvalid = controller.validateSemester(semester);
        assertFalse("Expected valid module semester", isSemesterInvalid);
    }

    /**
     * Test case for validating an invalid module semester.
     * This test checks if the given semester of a Module object is invalid according to the validation logic in the Controller.
     * <p>
     * Test output: A true value indicating an invalid module semester.
     * Preconditions: An invalid semester integer value associated with a Module object.
     * Test procedure:
     * 1. Create a new Module object with an invalid semester value.
     * 2. Call the getSemester() method on the Module object to retrieve the semester value.
     * 3. Call the validateSemester() method on the Controller object with the retrieved semester value.
     * 4. Verify that the returned boolean value is true, indicating an invalid module semester.
     */
    @Test
    public void testInvalidModuleSemester() {
        Module invalidModule = new Module("MT942", "Mathematics", 12);
        int semester = invalidModule.getSemester();
        boolean isSemesterInvalid = controller.validateSemester(semester);
        assertTrue("Expected invalid module semester", isSemesterInvalid);
    }
}

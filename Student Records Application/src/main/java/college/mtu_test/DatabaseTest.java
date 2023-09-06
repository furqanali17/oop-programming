package college.mtu_test;

import college.mtu_database.Database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertNotNull;

/**
 * Test class for the Database class.
 * This class tests the functionality of the database connection.
 */
public class DatabaseTest {

    private Connection connection;

    /**
     * Sets up the test environment by creating a Database object and obtaining a connection.
     * Preconditions: The database must be running and accessible.
     */
    @Before
    public void setUp() {
        Database database = new Database();
        connection = database.getConnection();
    }

    /**
     * Test case for the database connection.
     * This test checks if the Database class can establish a valid connection with the database.
     * <p>
     * Test output: A non-null Connection object.
     * Preconditions: The database must be running and accessible.
     * Test procedure:
     * 1. Call the setUp method to initialize the connection.
     * 2. Verify that the connection is not null.
     */
    @Test
    public void testDatabaseConnection() {
        assertNotNull("Expected a non-null connection", connection);
    }

    /**
     * Cleans up the test environment by closing the connection.
     */
    @After
    public void tearDown() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

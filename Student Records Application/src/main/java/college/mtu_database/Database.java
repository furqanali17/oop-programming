package college.mtu_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The Database class is responsible for establishing and managing a connection to the student record system database.
 * This class provides a method for obtaining a connection object, which can then be used to interact with the database.
 */
public class Database {

    private Connection connection;

    /**
     * The constructor for the Database class.
     * Attempts to establish a connection to the specified database using the provided connection string.
     * In case of an error, it prints an error message and stack trace.
     */
    public Database() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentrecordsystem", "root", "Iamgamer.1!");
        } catch (SQLException e) {
            System.err.println("Error establishing a connection to the database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Returns the Connection object associated with the database.
     * This connection object can be used to interact with the database, such as executing SQL queries and updates.
     *
     * @return the Connection object representing the connection to the database.
     */
    public Connection getConnection() {
        return connection;
    }
}
package com.designpatterns.creational.singleton;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class demonstrating Singleton pattern usage and verification.
 * These tests show common interview scenarios and verification points.
 */
public class DatabaseConnectionTest {

    @Test
    public void testSingletonInstance() {
        // Get two instances
        DatabaseConnection instance1 = DatabaseConnection.getInstance();
        DatabaseConnection instance2 = DatabaseConnection.getInstance();

        // Verify both references point to the same object
        assertSame(instance1, instance2, "Both instances should be the same object");
    }

    @Test
    public void testDatabaseConnection() {
        DatabaseConnection db = DatabaseConnection.getInstance();

        // Test initial state
        assertFalse(db.isConnected(), "Database should not be connected initially");
        assertNull(db.getConnectionUrl(), "Connection URL should be null initially");

        // Test connection
        String testUrl = "jdbc:mysql://localhost:3306/testdb";
        db.connect(testUrl);
        assertTrue(db.isConnected(), "Database should be connected");
        assertEquals(testUrl, db.getConnectionUrl(), "Connection URL should match");

        // Test disconnection
        db.disconnect();
        assertFalse(db.isConnected(), "Database should be disconnected");
        assertNull(db.getConnectionUrl(), "Connection URL should be null after disconnect");
    }

    @Test
    public void testMultipleConnections() {
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();

        // Connect using first instance
        db1.connect("jdbc:mysql://localhost:3306/testdb");

        // Verify second instance reflects the same connection state
        assertTrue(db2.isConnected(), "Second instance should show connected state");
        assertEquals(db1.getConnectionUrl(), db2.getConnectionUrl(),
                "Both instances should have the same connection URL");

        // Disconnect using second instance
        db2.disconnect();

        // Verify first instance reflects the disconnected state
        assertFalse(db1.isConnected(), "First instance should show disconnected state");
        assertNull(db1.getConnectionUrl(), "First instance should have null connection URL");
    }
}
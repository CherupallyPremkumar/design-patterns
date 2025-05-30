package com.designpatterns.creational.singleton;

/**
 * Thread-safe Singleton pattern implementation for database connection.
 * This pattern ensures only one database connection instance exists across the
 * application.
 * 
 * Key points for interviews:
 * 1. Private constructor prevents direct instantiation
 * 2. Double-checked locking for thread safety
 * 3. Volatile keyword ensures proper initialization across threads
 * 4. Lazy initialization for better resource management
 */
public class DatabaseConnection {
    // Volatile ensures visibility across threads
    private static volatile DatabaseConnection instance;
    private String connectionUrl;
    private boolean connected;

    // Private constructor prevents direct instantiation
    private DatabaseConnection() {
        // Prevent reflection instantiation
        if (instance != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    /**
     * Double-checked locking pattern for thread-safe singleton
     * 
     * @return The single instance of DatabaseConnection
     */
    public static DatabaseConnection getInstance() {
        // First check (no synchronization)
        if (instance == null) {
            // Synchronize only when instance is null
            synchronized (DatabaseConnection.class) {
                // Second check (with synchronization)
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    public void connect(String url) {
        if (!connected) {
            this.connectionUrl = url;
            // Simulate database connection
            System.out.println("Connecting to database at " + url);
            connected = true;
        }
    }

    public void disconnect() {
        if (connected) {
            // Simulate database disconnection
            System.out.println("Disconnecting from database at " + connectionUrl);
            connected = false;
            connectionUrl = null;
        }
    }

    public boolean isConnected() {
        return connected;
    }

    public String getConnectionUrl() {
        return connectionUrl;
    }
}
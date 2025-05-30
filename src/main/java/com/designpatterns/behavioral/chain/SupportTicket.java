package com.designpatterns.behavioral.chain;

/**
 * Represents a support ticket in the system.
 */
public class SupportTicket {
    private final String description;
    private final Priority priority;
    private final Type type;
    private final int complexity;

    public SupportTicket(String description, Priority priority, Type type, int complexity) {
        this.description = description;
        this.priority = priority;
        this.type = type;
        if (complexity < 1 || complexity > 10) {
            throw new IllegalArgumentException("Complexity must be between 1 and 10");
        }
        this.complexity = complexity;
    }

    public String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    public Type getType() {
        return type;
    }

    public int getComplexity() {
        return complexity;
    }

    @Override
    public String toString() {
        return String.format("Ticket[type=%s, priority=%s, complexity=%d, description='%s']",
                type, priority, complexity, description);
    }
}

/**
 * Enum representing ticket priority levels
 */
enum Priority {
    LOW,
    MEDIUM,
    HIGH,
    CRITICAL
}

/**
 * Enum representing types of support tickets
 */
enum Type {
    TECHNICAL,
    BILLING,
    GENERAL,
    FEATURE_REQUEST,
    BUG
}
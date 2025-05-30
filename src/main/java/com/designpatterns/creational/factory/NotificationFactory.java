package com.designpatterns.creational.factory;

/**
 * Factory class for creating different types of notifications.
 * This demonstrates the Factory Method pattern.
 * 
 * Key points for interviews:
 * 1. Factory Method encapsulates object creation
 * 2. Allows for flexible creation of different types
 * 3. Follows Open/Closed Principle - easy to add new notification types
 * 4. Provides a common interface for creating objects
 */
public class NotificationFactory {

    public Notification createNotification(String channel) {
        if (channel == null || channel.isEmpty()) {
            throw new IllegalArgumentException("Notification channel cannot be null or empty");
        }

        switch (channel.toUpperCase()) {
            case "SMS":
                return new SMSNotification();
            case "EMAIL":
                return new EmailNotification();
            case "PUSH":
                return new PushNotification();
            default:
                throw new IllegalArgumentException("Unknown channel " + channel);
        }
    }
}
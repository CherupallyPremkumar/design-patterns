package com.designpatterns.creational.factory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class demonstrating Factory Method pattern usage and verification.
 * Shows common interview scenarios and verification points.
 */
public class NotificationFactoryTest {

    private NotificationFactory factory;

    @BeforeEach
    void setUp() {
        factory = new NotificationFactory();
    }

    @Test
    void testCreateEmailNotification() {
        Notification notification = factory.createNotification("EMAIL");
        assertEquals("EMAIL", notification.getNotificationType());
        assertTrue(notification instanceof EmailNotification);
    }

    @Test
    void testCreateSMSNotification() {
        Notification notification = factory.createNotification("SMS");
        assertEquals("SMS", notification.getNotificationType());
        assertTrue(notification instanceof SMSNotification);
    }

    @Test
    void testCreatePushNotification() {
        Notification notification = factory.createNotification("PUSH");
        assertEquals("PUSH", notification.getNotificationType());
        assertTrue(notification instanceof PushNotification);
    }

    @Test
    void testInvalidChannel() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createNotification("INVALID");
        });
        assertTrue(exception.getMessage().contains("Unknown channel"));
    }

    @Test
    void testNullChannel() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createNotification(null);
        });
        assertTrue(exception.getMessage().contains("cannot be null or empty"));
    }

    @Test
    void testEmptyChannel() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createNotification("");
        });
        assertTrue(exception.getMessage().contains("cannot be null or empty"));
    }

    @Test
    void testNotificationUsage() {
        // Demonstrate how to use different notifications
        Notification emailNotif = factory.createNotification("EMAIL");
        Notification smsNotif = factory.createNotification("SMS");
        Notification pushNotif = factory.createNotification("PUSH");

        // Test actual notification sending
        emailNotif.notifyUser("Test email message");
        smsNotif.notifyUser("Test SMS message");
        pushNotif.notifyUser("Test push message");
    }
}
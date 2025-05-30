package com.designpatterns.creational.factory;

public class PushNotification implements Notification {
    @Override
    public void notifyUser(String message) {
        // In real implementation, this would use a push notification service
        System.out.println("Sending Push Notification: " + message);
    }

    @Override
    public String getNotificationType() {
        return "PUSH";
    }
}
package com.designpatterns.creational.factory;

public class SMSNotification implements Notification {
    @Override
    public void notifyUser(String message) {
        // In real implementation, this would use an SMS gateway
        System.out.println("Sending SMS: " + message);
    }

    @Override
    public String getNotificationType() {
        return "SMS";
    }
}
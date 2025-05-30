package com.designpatterns.creational.factory;

public class EmailNotification implements Notification {
    @Override
    public void notifyUser(String message) {
        // In real implementation, this would use an email service
        System.out.println("Sending Email: " + message);
    }

    @Override
    public String getNotificationType() {
        return "EMAIL";
    }
}
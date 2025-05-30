package com.designpatterns.creational.factory;

/**
 * Base interface for all notification types.
 * This demonstrates the product interface in the Factory Method pattern.
 */
public interface Notification {
    void notifyUser(String message);

    String getNotificationType();
}
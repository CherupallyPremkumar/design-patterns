package com.designpatterns.behavioral.observer;

/**
 * NewsSubscriber interface representing the Observer in the Observer pattern.
 * All concrete subscribers must implement this interface.
 */
public interface NewsSubscriber {
    void update(String news);

    String getSubscriberType();
}
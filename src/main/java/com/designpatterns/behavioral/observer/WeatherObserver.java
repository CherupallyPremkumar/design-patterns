package com.designpatterns.behavioral.observer;

/**
 * Observer interface that defines how weather displays will receive updates
 */
public interface WeatherObserver {
    void update(double temperature, double humidity, double pressure);
}
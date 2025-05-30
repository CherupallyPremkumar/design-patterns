package com.designpatterns.behavioral.observer;

/**
 * Subject interface that defines methods for managing observers
 */
public interface WeatherSubject {
    void registerObserver(WeatherObserver observer);

    void removeObserver(WeatherObserver observer);

    void notifyObservers();
}
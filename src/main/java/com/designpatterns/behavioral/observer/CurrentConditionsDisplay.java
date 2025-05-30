package com.designpatterns.behavioral.observer;

/**
 * Concrete Observer that displays current weather conditions
 */
public class CurrentConditionsDisplay implements WeatherObserver {
    private double temperature;
    private double humidity;
    private double pressure;

    @Override
    public void update(double temperature, double humidity, double pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    public void display() {
        System.out.printf("Current conditions: %.1f°C temperature, %.1f%% humidity, %.1f hPa pressure%n",
                temperature, humidity, pressure);
    }

    // Getters for testing
    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }
}
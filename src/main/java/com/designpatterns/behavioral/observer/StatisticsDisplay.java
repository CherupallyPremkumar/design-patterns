package com.designpatterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete Observer that displays weather statistics
 */
public class StatisticsDisplay implements WeatherObserver {
    private final List<Double> temperatures = new ArrayList<>();
    private final List<Double> humidities = new ArrayList<>();
    private final List<Double> pressures = new ArrayList<>();

    @Override
    public void update(double temperature, double humidity, double pressure) {
        temperatures.add(temperature);
        humidities.add(humidity);
        pressures.add(pressure);
        display();
    }

    public void display() {
        System.out.printf("Weather Statistics:%n" +
                "Temperature - Avg: %.1f°C, Min: %.1f°C, Max: %.1f°C%n" +
                "Humidity - Avg: %.1f%%, Min: %.1f%%, Max: %.1f%%%n" +
                "Pressure - Avg: %.1f hPa, Min: %.1f hPa, Max: %.1f hPa%n",
                calculateAverage(temperatures), calculateMin(temperatures), calculateMax(temperatures),
                calculateAverage(humidities), calculateMin(humidities), calculateMax(humidities),
                calculateAverage(pressures), calculateMin(pressures), calculateMax(pressures));
    }

    private double calculateAverage(List<Double> values) {
        return values.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    private double calculateMin(List<Double> values) {
        return values.stream().mapToDouble(Double::doubleValue).min().orElse(0.0);
    }

    private double calculateMax(List<Double> values) {
        return values.stream().mapToDouble(Double::doubleValue).max().orElse(0.0);
    }

    // Getters for testing
    public List<Double> getTemperatures() {
        return new ArrayList<>(temperatures);
    }

    public List<Double> getHumidities() {
        return new ArrayList<>(humidities);
    }

    public List<Double> getPressures() {
        return new ArrayList<>(pressures);
    }
}
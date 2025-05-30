package com.designpatterns.behavioral.observer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WeatherStationTest {
    private WeatherStation weatherStation;
    private CurrentConditionsDisplay currentDisplay;
    private StatisticsDisplay statisticsDisplay;

    @BeforeEach
    void setUp() {
        weatherStation = new WeatherStation();
        currentDisplay = new CurrentConditionsDisplay();
        statisticsDisplay = new StatisticsDisplay();
    }

    @Test
    void testObserverRegistration() {
        weatherStation.registerObserver(currentDisplay);
        assertEquals(1, weatherStation.getObserverCount());

        // Test duplicate registration
        weatherStation.registerObserver(currentDisplay);
        assertEquals(1, weatherStation.getObserverCount());

        weatherStation.registerObserver(statisticsDisplay);
        assertEquals(2, weatherStation.getObserverCount());
    }

    @Test
    void testObserverRemoval() {
        weatherStation.registerObserver(currentDisplay);
        weatherStation.registerObserver(statisticsDisplay);
        assertEquals(2, weatherStation.getObserverCount());

        weatherStation.removeObserver(currentDisplay);
        assertEquals(1, weatherStation.getObserverCount());

        // Test removing non-existent observer
        weatherStation.removeObserver(currentDisplay);
        assertEquals(1, weatherStation.getObserverCount());
    }

    @Test
    void testWeatherUpdates() {
        weatherStation.registerObserver(currentDisplay);
        weatherStation.setMeasurements(25.0, 65.0, 1013.0);

        assertEquals(25.0, currentDisplay.getTemperature(), 0.01);
        assertEquals(65.0, currentDisplay.getHumidity(), 0.01);
        assertEquals(1013.0, currentDisplay.getPressure(), 0.01);
    }

    @Test
    void testStatisticsCalculation() {
        weatherStation.registerObserver(statisticsDisplay);

        // Add multiple measurements
        weatherStation.setMeasurements(20.0, 60.0, 1010.0);
        weatherStation.setMeasurements(22.0, 65.0, 1012.0);
        weatherStation.setMeasurements(24.0, 70.0, 1014.0);

        var temperatures = statisticsDisplay.getTemperatures();
        var humidities = statisticsDisplay.getHumidities();
        var pressures = statisticsDisplay.getPressures();

        // Test size
        assertEquals(3, temperatures.size());
        assertEquals(3, humidities.size());
        assertEquals(3, pressures.size());

        // Test values
        assertEquals(22.0, temperatures.stream().mapToDouble(Double::doubleValue).average().orElse(0.0), 0.01);
        assertEquals(20.0, temperatures.stream().mapToDouble(Double::doubleValue).min().orElse(0.0), 0.01);
        assertEquals(24.0, temperatures.stream().mapToDouble(Double::doubleValue).max().orElse(0.0), 0.01);
    }

    @Test
    void testMultipleObservers() {
        weatherStation.registerObserver(currentDisplay);
        weatherStation.registerObserver(statisticsDisplay);

        weatherStation.setMeasurements(23.0, 67.0, 1015.0);

        // Verify both observers received the update
        assertEquals(23.0, currentDisplay.getTemperature(), 0.01);
        assertEquals(67.0, currentDisplay.getHumidity(), 0.01);
        assertEquals(1015.0, currentDisplay.getPressure(), 0.01);

        var temperatures = statisticsDisplay.getTemperatures();
        assertEquals(1, temperatures.size());
        assertEquals(23.0, temperatures.get(0), 0.01);
    }
}
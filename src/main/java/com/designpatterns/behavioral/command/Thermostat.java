package com.designpatterns.behavioral.command;

/**
 * Thermostat class representing a smart thermostat.
 * This is another receiver in the Command pattern.
 */
public class Thermostat {
    private final String location;
    private int temperature;
    private boolean isOn;

    public Thermostat(String location) {
        this.location = location;
        this.temperature = 72; // Default temperature
        this.isOn = false;
    }

    public void turnOn() {
        isOn = true;
        System.out.println(location + " thermostat is now ON");
    }

    public void turnOff() {
        isOn = false;
        System.out.println(location + " thermostat is now OFF");
    }

    public void setTemperature(int temperature) {
        if (temperature < 60 || temperature > 80) {
            throw new IllegalArgumentException("Temperature must be between 60°F and 80°F");
        }
        this.temperature = temperature;
        System.out.println(location + " temperature is set to " + temperature + "°F");
    }

    public boolean isOn() {
        return isOn;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getLocation() {
        return location;
    }
}
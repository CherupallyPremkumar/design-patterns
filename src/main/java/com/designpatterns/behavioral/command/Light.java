package com.designpatterns.behavioral.command;

/**
 * Light class representing a smart light that can be turned on/off.
 * This is a receiver in the Command pattern.
 */
public class Light {
    private final String location;
    private boolean isOn;
    private int brightness;

    public Light(String location) {
        this.location = location;
        this.isOn = false;
        this.brightness = 0;
    }

    public void turnOn() {
        isOn = true;
        brightness = 100;
        System.out.println(location + " light is now ON");
    }

    public void turnOff() {
        isOn = false;
        brightness = 0;
        System.out.println(location + " light is now OFF");
    }

    public void dim(int level) {
        if (level < 0 || level > 100) {
            throw new IllegalArgumentException("Brightness level must be between 0 and 100");
        }
        brightness = level;
        isOn = level > 0;
        System.out.println(location + " light is now at " + level + "% brightness");
    }

    public boolean isOn() {
        return isOn;
    }

    public int getBrightness() {
        return brightness;
    }

    public String getLocation() {
        return location;
    }
}
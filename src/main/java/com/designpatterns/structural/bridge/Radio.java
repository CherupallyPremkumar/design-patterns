package com.designpatterns.structural.bridge;

/**
 * Concrete implementation for Radio
 */
public class Radio implements Device {
    private boolean on = false;
    private int volume = 20;
    private double frequency = 87.5; // FM frequency
    private int channel = 1; // Preset channel

    @Override
    public boolean isEnabled() {
        return on;
    }

    @Override
    public void enable() {
        on = true;
    }

    @Override
    public void disable() {
        on = false;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int volume) {
        if (volume > 100) {
            this.volume = 100;
        } else if (volume < 0) {
            this.volume = 0;
        } else {
            this.volume = volume;
        }
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public void setChannel(int channel) {
        this.channel = channel;
        // Simulate frequency change based on channel
        this.frequency = 87.5 + (channel - 1) * 0.2;
    }

    @Override
    public String getStatus() {
        return "Radio is " + (on ? "enabled" : "disabled") +
                ", Volume: " + volume +
                ", Channel: " + channel +
                String.format(", Frequency: %.1f FM", frequency);
    }

    // Radio-specific method
    public double getFrequency() {
        return frequency;
    }
}
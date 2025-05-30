package com.designpatterns.structural.bridge;

/**
 * The Implementation interface in the Bridge pattern
 * Defines the interface for all concrete implementations
 */
public interface Device {
    boolean isEnabled();

    void enable();

    void disable();

    int getVolume();

    void setVolume(int volume);

    int getChannel();

    void setChannel(int channel);

    String getStatus();
}
package com.designpatterns.structural.bridge;

/**
 * Refined Abstraction in the Bridge pattern
 * Extends the basic remote with advanced functionality
 */
public class AdvancedRemoteControl extends RemoteControl {
    private int[] favoriteChannels;
    private int lastChannel;

    public AdvancedRemoteControl(Device device) {
        super(device);
        this.favoriteChannels = new int[5];
        this.lastChannel = 1;
    }

    public void mute() {
        device.setVolume(0);
    }

    public void setFavoriteChannel(int slot, int channel) {
        if (slot >= 0 && slot < favoriteChannels.length) {
            favoriteChannels[slot] = channel;
        }
    }

    public void goToFavoriteChannel(int slot) {
        if (slot >= 0 && slot < favoriteChannels.length && favoriteChannels[slot] != 0) {
            lastChannel = device.getChannel();
            device.setChannel(favoriteChannels[slot]);
        }
    }

    public void returnToLastChannel() {
        int currentChannel = device.getChannel();
        device.setChannel(lastChannel);
        lastChannel = currentChannel;
    }

    public void setChannelWithValidation(int channel) {
        if (channel > 0 && channel <= 999) { // Assuming max 999 channels
            lastChannel = device.getChannel();
            device.setChannel(channel);
        }
    }
}
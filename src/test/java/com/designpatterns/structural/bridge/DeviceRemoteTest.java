package com.designpatterns.structural.bridge;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeviceRemoteTest {

    @Test
    void testBasicRemoteWithTV() {
        Device tv = new TV();
        RemoteControl remote = new RemoteControl(tv);

        // Test power toggle
        remote.togglePower();
        assertTrue(tv.isEnabled());
        remote.togglePower();
        assertFalse(tv.isEnabled());

        // Test volume control
        remote.togglePower();
        remote.volumeUp();
        assertEquals(40, tv.getVolume());
        remote.volumeDown();
        assertEquals(30, tv.getVolume());

        // Test channel control
        remote.channelUp();
        assertEquals(2, tv.getChannel());
        remote.channelDown();
        assertEquals(1, tv.getChannel());
    }

    @Test
    void testAdvancedRemoteWithRadio() {
        Device radio = new Radio();
        AdvancedRemoteControl remote = new AdvancedRemoteControl(radio);

        // Test basic functionality
        remote.togglePower();
        assertTrue(radio.isEnabled());

        // Test advanced features
        remote.setFavoriteChannel(0, 5);
        remote.goToFavoriteChannel(0);
        assertEquals(5, radio.getChannel());

        // Test last channel feature
        remote.setChannelWithValidation(10);
        assertEquals(10, radio.getChannel());
        remote.returnToLastChannel();
        assertEquals(5, radio.getChannel());

        // Test mute feature
        remote.volumeUp(); // Set volume to 30
        assertEquals(30, radio.getVolume());
        remote.mute();
        assertEquals(0, radio.getVolume());
    }

    @Test
    void testDeviceIndependence() {
        // Test same remote type with different devices
        Device tv = new TV();
        Device radio = new Radio();

        RemoteControl tvRemote = new RemoteControl(tv);
        RemoteControl radioRemote = new RemoteControl(radio);

        // Both devices should work independently
        tvRemote.togglePower();
        assertTrue(tv.isEnabled());
        assertFalse(radio.isEnabled());

        radioRemote.togglePower();
        assertTrue(radio.isEnabled());

        // Volume changes should be independent
        tvRemote.volumeUp();
        assertEquals(40, tv.getVolume());
        assertEquals(20, radio.getVolume());
    }

    @Test
    void testVolumeLimit() {
        Device tv = new TV();
        RemoteControl remote = new RemoteControl(tv);

        // Test upper limit
        for (int i = 0; i < 15; i++) {
            remote.volumeUp();
        }
        assertEquals(100, tv.getVolume());

        // Test lower limit
        for (int i = 0; i < 15; i++) {
            remote.volumeDown();
        }
        assertEquals(0, tv.getVolume());
    }

    @Test
    void testAdvancedFeatures() {
        Device tv = new TV();
        AdvancedRemoteControl remote = new AdvancedRemoteControl(tv);

        // Test favorite channels
        remote.setFavoriteChannel(0, 5);
        remote.setFavoriteChannel(1, 10);
        remote.setFavoriteChannel(2, 15);

        remote.goToFavoriteChannel(0);
        assertEquals(5, tv.getChannel());
        remote.goToFavoriteChannel(1);
        assertEquals(10, tv.getChannel());

        // Test invalid channel validation
        remote.setChannelWithValidation(1000); // Should not change channel
        assertEquals(10, tv.getChannel());
    }
}
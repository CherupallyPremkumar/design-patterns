package com.designpatterns.behavioral.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class demonstrating Command pattern usage and verification.
 * Shows common interview scenarios and verification points.
 */
public class SmartHomeControllerTest {

    private SmartHomeController controller;
    private Light livingRoomLight;
    private Light kitchenLight;
    private Thermostat livingRoomThermostat;

    @BeforeEach
    void setUp() {
        controller = new SmartHomeController();
        livingRoomLight = new Light("Living Room");
        kitchenLight = new Light("Kitchen");
        livingRoomThermostat = new Thermostat("Living Room");
    }

    @Test
    void testLightOnCommand() {
        Command lightOn = new LightOnCommand(livingRoomLight);
        controller.executeCommand(lightOn);

        assertTrue(livingRoomLight.isOn());
        assertEquals(1, controller.getHistorySize());
        assertEquals("Turn ON Living Room light", controller.getLastCommandDescription());
    }

    @Test
    void testLightDimCommand() {
        Command dimLight = new LightDimCommand(livingRoomLight, 50);
        controller.executeCommand(dimLight);

        assertEquals(50, livingRoomLight.getBrightness());
        assertTrue(livingRoomLight.isOn());
    }

    @Test
    void testThermostatCommand() {
        Command setTemp = new ThermostatSetCommand(livingRoomThermostat, 75);
        controller.executeCommand(setTemp);

        assertEquals(75, livingRoomThermostat.getTemperature());
    }

    @Test
    void testUndoLightCommand() {
        Command lightOn = new LightOnCommand(livingRoomLight);
        controller.executeCommand(lightOn);
        assertTrue(livingRoomLight.isOn());

        controller.undoLastCommand();
        assertFalse(livingRoomLight.isOn());
        assertEquals(0, controller.getHistorySize());
    }

    @Test
    void testUndoDimCommand() {
        Command dimLight = new LightDimCommand(livingRoomLight, 50);
        controller.executeCommand(dimLight);
        assertEquals(50, livingRoomLight.getBrightness());

        controller.undoLastCommand();
        assertEquals(0, livingRoomLight.getBrightness());
    }

    @Test
    void testMultipleCommands() {
        Command lightOn = new LightOnCommand(livingRoomLight);
        Command setTemp = new ThermostatSetCommand(livingRoomThermostat, 75);
        Command dimLight = new LightDimCommand(kitchenLight, 30);

        controller.executeCommand(lightOn);
        controller.executeCommand(setTemp);
        controller.executeCommand(dimLight);

        assertEquals(3, controller.getHistorySize());
        assertEquals("Dim Kitchen light to 30%", controller.getLastCommandDescription());
    }

    @Test
    void testUndoAllCommands() {
        Command lightOn = new LightOnCommand(livingRoomLight);
        Command setTemp = new ThermostatSetCommand(livingRoomThermostat, 75);
        Command dimLight = new LightDimCommand(kitchenLight, 30);

        controller.executeCommand(lightOn);
        controller.executeCommand(setTemp);
        controller.executeCommand(dimLight);

        controller.undoAllCommands();

        assertEquals(0, controller.getHistorySize());
        assertFalse(livingRoomLight.isOn());
        assertEquals(72, livingRoomThermostat.getTemperature()); // Default temperature
        assertEquals(0, kitchenLight.getBrightness());
    }

    @Test
    void testUndoEmptyHistory() {
        controller.undoLastCommand(); // Should not throw exception
        assertEquals(0, controller.getHistorySize());
        assertEquals("No commands in history", controller.getLastCommandDescription());
    }

    @Test
    void testInvalidThermostatTemperature() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Command setTemp = new ThermostatSetCommand(livingRoomThermostat, 90);
            controller.executeCommand(setTemp);
        });
        assertTrue(exception.getMessage().contains("Temperature must be between"));
    }

    @Test
    void testInvalidLightDimLevel() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Command dimLight = new LightDimCommand(livingRoomLight, 150);
            controller.executeCommand(dimLight);
        });
        assertTrue(exception.getMessage().contains("Brightness level must be between"));
    }
}
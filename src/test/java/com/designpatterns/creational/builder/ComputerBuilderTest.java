package com.designpatterns.creational.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class demonstrating Builder pattern usage and verification.
 * Shows common interview scenarios and verification points.
 */
public class ComputerBuilderTest {

    @Test
    void testBuildMinimalComputer() {
        Computer computer = new Computer.ComputerBuilder(
                "Intel i5",
                "8GB",
                "256GB SSD")
                .build();

        // Verify required parameters
        assertEquals("Intel i5", computer.getProcessor());
        assertEquals("8GB", computer.getRam());
        assertEquals("256GB SSD", computer.getStorage());

        // Verify default values for optional parameters
        assertEquals("Integrated Graphics", computer.getGraphicsCard());
        assertEquals("Basic Network Card", computer.getNetworkCard());
        assertEquals("Basic Sound Card", computer.getSoundCard());
        assertFalse(computer.isBluetoothEnabled());
        assertTrue(computer.isWifiEnabled());
        assertEquals("Windows 10", computer.getOperatingSystem());
        assertEquals(1, computer.getWarrantyYears());
    }

    @Test
    void testBuildFullyLoadedComputer() {
        Computer computer = new Computer.ComputerBuilder(
                "Intel i9",
                "32GB",
                "2TB SSD")
                .graphicsCard("NVIDIA RTX 3080")
                .networkCard("Intel Wi-Fi 6")
                .soundCard("Creative Sound Blaster")
                .bluetoothEnabled(true)
                .wifiEnabled(true)
                .operatingSystem("Ubuntu 22.04")
                .warrantyYears(3)
                .build();

        // Verify all parameters
        assertEquals("Intel i9", computer.getProcessor());
        assertEquals("32GB", computer.getRam());
        assertEquals("2TB SSD", computer.getStorage());
        assertEquals("NVIDIA RTX 3080", computer.getGraphicsCard());
        assertEquals("Intel Wi-Fi 6", computer.getNetworkCard());
        assertEquals("Creative Sound Blaster", computer.getSoundCard());
        assertTrue(computer.isBluetoothEnabled());
        assertTrue(computer.isWifiEnabled());
        assertEquals("Ubuntu 22.04", computer.getOperatingSystem());
        assertEquals(3, computer.getWarrantyYears());
    }

    @Test
    void testInvalidProcessor() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new Computer.ComputerBuilder("", "8GB", "256GB SSD").build();
        });
        assertTrue(exception.getMessage().contains("Processor is required"));
    }

    @Test
    void testInvalidRam() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new Computer.ComputerBuilder("Intel i5", "", "256GB SSD").build();
        });
        assertTrue(exception.getMessage().contains("RAM is required"));
    }

    @Test
    void testInvalidStorage() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new Computer.ComputerBuilder("Intel i5", "8GB", "").build();
        });
        assertTrue(exception.getMessage().contains("Storage is required"));
    }

    @Test
    void testInvalidWarrantyYears() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            new Computer.ComputerBuilder("Intel i5", "8GB", "256GB SSD")
                    .warrantyYears(-1)
                    .build();
        });
        assertTrue(exception.getMessage().contains("Warranty years cannot be negative"));
    }

    @Test
    void testMethodChaining() {
        Computer computer = new Computer.ComputerBuilder("Intel i7", "16GB", "512GB SSD")
                .graphicsCard("NVIDIA RTX 3070")
                .bluetoothEnabled(true)
                .operatingSystem("Windows 11")
                .build();

        assertEquals("Intel i7", computer.getProcessor());
        assertEquals("NVIDIA RTX 3070", computer.getGraphicsCard());
        assertTrue(computer.isBluetoothEnabled());
        assertEquals("Windows 11", computer.getOperatingSystem());
    }
}
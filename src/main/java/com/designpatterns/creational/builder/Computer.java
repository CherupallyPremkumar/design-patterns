package com.designpatterns.creational.builder;

/**
 * Computer class demonstrating a complex object with many optional parameters.
 * This is the product class in the Builder pattern.
 */
public class Computer {
    // Required parameters
    private String processor;
    private String ram;
    private String storage;

    // Optional parameters
    private String graphicsCard;
    private String networkCard;
    private String soundCard;
    private boolean bluetoothEnabled;
    private boolean wifiEnabled;
    private String operatingSystem;
    private int warrantyYears;

    private Computer(ComputerBuilder builder) {
        // Required parameters
        this.processor = builder.processor;
        this.ram = builder.ram;
        this.storage = builder.storage;

        // Optional parameters
        this.graphicsCard = builder.graphicsCard;
        this.networkCard = builder.networkCard;
        this.soundCard = builder.soundCard;
        this.bluetoothEnabled = builder.bluetoothEnabled;
        this.wifiEnabled = builder.wifiEnabled;
        this.operatingSystem = builder.operatingSystem;
        this.warrantyYears = builder.warrantyYears;
    }

    // Getters for all parameters
    public String getProcessor() {
        return processor;
    }

    public String getRam() {
        return ram;
    }

    public String getStorage() {
        return storage;
    }

    public String getGraphicsCard() {
        return graphicsCard;
    }

    public String getNetworkCard() {
        return networkCard;
    }

    public String getSoundCard() {
        return soundCard;
    }

    public boolean isBluetoothEnabled() {
        return bluetoothEnabled;
    }

    public boolean isWifiEnabled() {
        return wifiEnabled;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public int getWarrantyYears() {
        return warrantyYears;
    }

    /**
     * Static builder class inside the Computer class.
     * This demonstrates the Builder pattern with method chaining.
     */
    public static class ComputerBuilder {
        // Required parameters
        private final String processor;
        private final String ram;
        private final String storage;

        // Optional parameters - initialized with default values
        private String graphicsCard = "Integrated Graphics";
        private String networkCard = "Basic Network Card";
        private String soundCard = "Basic Sound Card";
        private boolean bluetoothEnabled = false;
        private boolean wifiEnabled = true;
        private String operatingSystem = "Windows 10";
        private int warrantyYears = 1;

        public ComputerBuilder(String processor, String ram, String storage) {
            this.processor = processor;
            this.ram = ram;
            this.storage = storage;
        }

        public ComputerBuilder graphicsCard(String graphicsCard) {
            this.graphicsCard = graphicsCard;
            return this;
        }

        public ComputerBuilder networkCard(String networkCard) {
            this.networkCard = networkCard;
            return this;
        }

        public ComputerBuilder soundCard(String soundCard) {
            this.soundCard = soundCard;
            return this;
        }

        public ComputerBuilder bluetoothEnabled(boolean bluetoothEnabled) {
            this.bluetoothEnabled = bluetoothEnabled;
            return this;
        }

        public ComputerBuilder wifiEnabled(boolean wifiEnabled) {
            this.wifiEnabled = wifiEnabled;
            return this;
        }

        public ComputerBuilder operatingSystem(String operatingSystem) {
            this.operatingSystem = operatingSystem;
            return this;
        }

        public ComputerBuilder warrantyYears(int warrantyYears) {
            this.warrantyYears = warrantyYears;
            return this;
        }

        public Computer build() {
            validateBuild();
            return new Computer(this);
        }

        private void validateBuild() {
            StringBuilder errors = new StringBuilder();

            // Validate required fields
            if (processor == null || processor.trim().isEmpty()) {
                errors.append("Processor is required. ");
            }
            if (ram == null || ram.trim().isEmpty()) {
                errors.append("RAM is required. ");
            }
            if (storage == null || storage.trim().isEmpty()) {
                errors.append("Storage is required. ");
            }

            // Validate warranty years
            if (warrantyYears < 0) {
                errors.append("Warranty years cannot be negative. ");
            }

            if (errors.length() > 0) {
                throw new IllegalStateException(errors.toString());
            }
        }
    }

    @Override
    public String toString() {
        return "Computer{" +
                "processor='" + processor + '\'' +
                ", ram='" + ram + '\'' +
                ", storage='" + storage + '\'' +
                ", graphicsCard='" + graphicsCard + '\'' +
                ", networkCard='" + networkCard + '\'' +
                ", soundCard='" + soundCard + '\'' +
                ", bluetoothEnabled=" + bluetoothEnabled +
                ", wifiEnabled=" + wifiEnabled +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", warrantyYears=" + warrantyYears +
                '}';
    }
}
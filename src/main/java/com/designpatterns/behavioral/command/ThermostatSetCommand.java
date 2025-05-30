package com.designpatterns.behavioral.command;

/**
 * Concrete command for setting thermostat temperature.
 */
public class ThermostatSetCommand implements Command {
    private final Thermostat thermostat;
    private final int temperature;
    private int previousTemperature;

    public ThermostatSetCommand(Thermostat thermostat, int temperature) {
        this.thermostat = thermostat;
        this.temperature = temperature;
    }

    @Override
    public void execute() {
        previousTemperature = thermostat.getTemperature();
        thermostat.setTemperature(temperature);
    }

    @Override
    public void undo() {
        thermostat.setTemperature(previousTemperature);
    }

    @Override
    public String getDescription() {
        return "Set " + thermostat.getLocation() + " temperature to " + temperature + "°F";
    }
}
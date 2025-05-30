package com.designpatterns.behavioral.command;

/**
 * Concrete command for turning on a light.
 */
public class LightOnCommand implements Command {
    private final Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }

    @Override
    public void undo() {
        light.turnOff();
    }

    @Override
    public String getDescription() {
        return "Turn ON " + light.getLocation() + " light";
    }
}
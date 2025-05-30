package com.designpatterns.behavioral.command;

/**
 * Concrete command for dimming a light.
 */
public class LightDimCommand implements Command {
    private final Light light;
    private final int dimLevel;
    private int previousLevel;

    public LightDimCommand(Light light, int dimLevel) {
        this.light = light;
        this.dimLevel = dimLevel;
    }

    @Override
    public void execute() {
        previousLevel = light.getBrightness();
        light.dim(dimLevel);
    }

    @Override
    public void undo() {
        light.dim(previousLevel);
    }

    @Override
    public String getDescription() {
        return "Dim " + light.getLocation() + " light to " + dimLevel + "%";
    }
}
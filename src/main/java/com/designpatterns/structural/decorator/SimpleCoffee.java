package com.designpatterns.structural.decorator;

/**
 * SimpleCoffee class representing a basic coffee without any additions.
 * This is the ConcreteComponent in the Decorator pattern.
 */
public class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Simple Coffee";
    }

    @Override
    public double getCost() {
        return 2.0; // Base price for simple coffee
    }
}
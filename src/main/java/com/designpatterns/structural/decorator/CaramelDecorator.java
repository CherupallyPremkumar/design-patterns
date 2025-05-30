package com.designpatterns.structural.decorator;

/**
 * Concrete decorator adding caramel to coffee.
 */
public class CaramelDecorator extends CoffeeDecorator {
    public CaramelDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", with Caramel";
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 0.6; // Caramel costs $0.60 extra
    }
}
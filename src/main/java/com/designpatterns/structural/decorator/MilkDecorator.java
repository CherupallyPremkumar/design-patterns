package com.designpatterns.structural.decorator;

/**
 * Concrete decorator adding milk to coffee.
 */
public class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", with Milk";
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 0.5; // Milk costs $0.50 extra
    }
}
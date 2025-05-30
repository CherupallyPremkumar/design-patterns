package com.designpatterns.structural.decorator;

/**
 * Concrete decorator adding whipped cream to coffee.
 */
public class WhipDecorator extends CoffeeDecorator {
    public WhipDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", with Whipped Cream";
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 0.7; // Whipped cream costs $0.70 extra
    }
}
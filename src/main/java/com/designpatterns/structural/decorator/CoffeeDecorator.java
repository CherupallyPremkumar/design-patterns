package com.designpatterns.structural.decorator;

/**
 * Abstract decorator class implementing the Coffee interface.
 * This is the base Decorator class in the Decorator pattern.
 */
public abstract class CoffeeDecorator implements Coffee {
    protected final Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        if (coffee == null) {
            throw new NullPointerException("Coffee to be decorated cannot be null");
        }
        this.decoratedCoffee = coffee;
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost();
    }
}
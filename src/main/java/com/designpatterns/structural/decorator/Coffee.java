package com.designpatterns.structural.decorator;

/**
 * Base Coffee interface defining the contract for all coffee types.
 * This is the Component interface in the Decorator pattern.
 */
public interface Coffee {
    String getDescription();

    double getCost();
}
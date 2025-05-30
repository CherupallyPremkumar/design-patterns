package com.designpatterns.structural.decorator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class demonstrating Decorator pattern usage and verification.
 * Shows common interview scenarios and verification points.
 */
public class CoffeeDecoratorTest {

    @Test
    void testSimpleCoffee() {
        Coffee coffee = new SimpleCoffee();
        assertEquals("Simple Coffee", coffee.getDescription());
        assertEquals(2.0, coffee.getCost(), 0.01);
    }

    @Test
    void testCoffeeWithMilk() {
        Coffee coffee = new MilkDecorator(new SimpleCoffee());
        assertEquals("Simple Coffee, with Milk", coffee.getDescription());
        assertEquals(2.5, coffee.getCost(), 0.01);
    }

    @Test
    void testCoffeeWithWhip() {
        Coffee coffee = new WhipDecorator(new SimpleCoffee());
        assertEquals("Simple Coffee, with Whipped Cream", coffee.getDescription());
        assertEquals(2.7, coffee.getCost(), 0.01);
    }

    @Test
    void testCoffeeWithCaramel() {
        Coffee coffee = new CaramelDecorator(new SimpleCoffee());
        assertEquals("Simple Coffee, with Caramel", coffee.getDescription());
        assertEquals(2.6, coffee.getCost(), 0.01);
    }

    @Test
    void testFullyLoadedCoffee() {
        Coffee coffee = new CaramelDecorator(
                new WhipDecorator(
                        new MilkDecorator(
                                new SimpleCoffee())));

        assertEquals("Simple Coffee, with Milk, with Whipped Cream, with Caramel",
                coffee.getDescription());
        assertEquals(3.8, coffee.getCost(), 0.01); // 2.0 + 0.5 + 0.7 + 0.6
    }

    @Test
    void testMultipleDecoratorsOfSameType() {
        Coffee coffee = new MilkDecorator(
                new MilkDecorator(
                        new SimpleCoffee()));

        assertEquals("Simple Coffee, with Milk, with Milk", coffee.getDescription());
        assertEquals(3.0, coffee.getCost(), 0.01); // 2.0 + 0.5 + 0.5
    }

    @Test
    void testDifferentOrderingSameResult() {
        Coffee coffee1 = new CaramelDecorator(new MilkDecorator(new SimpleCoffee()));
        Coffee coffee2 = new MilkDecorator(new CaramelDecorator(new SimpleCoffee()));

        // Order of decorators affects description
        assertNotEquals(coffee1.getDescription(), coffee2.getDescription());

        // But cost should be the same
        assertEquals(coffee1.getCost(), coffee2.getCost(), 0.01);
    }

    @Test
    void testDecoratorWithNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            new MilkDecorator(null);
        });
    }
}
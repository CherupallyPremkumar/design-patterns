package com.designpatterns.behavioral.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class demonstrating Strategy pattern usage and verification.
 * Shows common interview scenarios and verification points.
 */
public class ShippingStrategyTest {
    private ShippingCostCalculator calculator;
    private ShippingStrategy expressStrategy;
    private ShippingStrategy standardStrategy;
    private ShippingStrategy budgetStrategy;

    @BeforeEach
    void setUp() {
        expressStrategy = new ExpressShippingStrategy();
        standardStrategy = new StandardShippingStrategy();
        budgetStrategy = new BudgetShippingStrategy();
        calculator = new ShippingCostCalculator(standardStrategy); // Default to standard
    }

    @Test
    void testDomesticShippingComparison() {
        double weight = 10.0;
        String destination = "domestic";

        calculator.setStrategy(expressStrategy);
        double expressCost = calculator.calculateShippingCost(weight, destination);

        calculator.setStrategy(standardStrategy);
        double standardCost = calculator.calculateShippingCost(weight, destination);

        calculator.setStrategy(budgetStrategy);
        double budgetCost = calculator.calculateShippingCost(weight, destination);

        // Verify cost relationships
        assertTrue(expressCost > standardCost);
        assertTrue(standardCost > budgetCost);
    }

    @Test
    void testInternationalShippingMultipliers() {
        double weight = 5.0;

        calculator.setStrategy(expressStrategy);
        double domesticCost = calculator.calculateShippingCost(weight, "domestic");
        double internationalCost = calculator.calculateShippingCost(weight, "international");

        assertTrue(internationalCost > domesticCost);
    }

    @Test
    void testBulkDiscountForBudgetShipping() {
        calculator.setStrategy(budgetStrategy);

        double smallWeight = 15.0;
        double largeWeight = 25.0;
        String destination = "domestic";

        double costPerKgSmall = calculator.calculateShippingCost(smallWeight, destination) / smallWeight;
        double costPerKgLarge = calculator.calculateShippingCost(largeWeight, destination) / largeWeight;

        assertTrue(costPerKgLarge < costPerKgSmall);
    }

    @Test
    void testShippingQuoteFormat() {
        calculator.setStrategy(expressStrategy);
        ShippingQuote quote = calculator.getShippingQuote(15.0, "international");

        assertEquals("Express Shipping", quote.getServiceName());
        assertEquals(15.0, quote.getWeight());
        assertEquals("international", quote.getDestination());
        assertTrue(quote.toString().contains("Express Shipping"));
    }

    @Test
    void testStrategySwitch() {
        assertEquals("Standard Shipping", calculator.getCurrentStrategyName());

        calculator.setStrategy(expressStrategy);
        assertEquals("Express Shipping", calculator.getCurrentStrategyName());

        calculator.setStrategy(budgetStrategy);
        assertEquals("Budget Shipping", calculator.getCurrentStrategyName());
    }

    @Test
    void testInvalidInputs() {
        calculator.setStrategy(standardStrategy);

        assertThrows(IllegalArgumentException.class, () -> calculator.calculateShippingCost(-1.0, "domestic"));

        assertThrows(IllegalArgumentException.class, () -> calculator.calculateShippingCost(10.0, ""));

        assertThrows(IllegalArgumentException.class, () -> calculator.calculateShippingCost(10.0, null));
    }

    @Test
    void testNullStrategy() {
        assertThrows(IllegalArgumentException.class, () -> new ShippingCostCalculator(null));

        assertThrows(IllegalArgumentException.class, () -> calculator.setStrategy(null));
    }
}
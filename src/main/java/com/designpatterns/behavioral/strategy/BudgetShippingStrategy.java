package com.designpatterns.behavioral.strategy;

/**
 * BudgetShippingStrategy implements low-cost shipping calculations.
 * This is a concrete strategy in the Strategy pattern.
 */
public class BudgetShippingStrategy implements ShippingStrategy {
    private static final double BASE_RATE = 5.0;
    private static final double RATE_PER_KG = 3.0;
    private static final double INTERNATIONAL_MULTIPLIER = 2.5;
    private static final double WEIGHT_THRESHOLD = 20.0;
    private static final double BULK_DISCOUNT = 0.8;

    @Override
    public double calculateCost(double weightInKg, String destination) {
        validateInput(weightInKg, destination);

        double cost = BASE_RATE + (weightInKg * RATE_PER_KG);

        // Apply bulk discount for heavy packages
        if (weightInKg > WEIGHT_THRESHOLD) {
            cost *= BULK_DISCOUNT;
        }

        // Apply international shipping multiplier if not domestic
        if (!destination.equalsIgnoreCase("domestic")) {
            cost *= INTERNATIONAL_MULTIPLIER;
        }

        return Math.round(cost * 100.0) / 100.0; // Round to 2 decimal places
    }

    @Override
    public String getServiceName() {
        return "Budget Shipping";
    }

    private void validateInput(double weightInKg, String destination) {
        if (weightInKg <= 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }
        if (destination == null || destination.trim().isEmpty()) {
            throw new IllegalArgumentException("Destination cannot be empty");
        }
    }
}
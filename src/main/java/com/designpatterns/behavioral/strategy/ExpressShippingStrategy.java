package com.designpatterns.behavioral.strategy;

/**
 * ExpressShippingStrategy implements fast but expensive shipping calculations.
 * This is a concrete strategy in the Strategy pattern.
 */
public class ExpressShippingStrategy implements ShippingStrategy {
    private static final double BASE_RATE = 25.0;
    private static final double RATE_PER_KG = 12.0;
    private static final double INTERNATIONAL_MULTIPLIER = 1.5;

    @Override
    public double calculateCost(double weightInKg, String destination) {
        validateInput(weightInKg, destination);

        double cost = BASE_RATE + (weightInKg * RATE_PER_KG);

        // Apply international shipping multiplier if not domestic
        if (!destination.equalsIgnoreCase("domestic")) {
            cost *= INTERNATIONAL_MULTIPLIER;
        }

        return Math.round(cost * 100.0) / 100.0; // Round to 2 decimal places
    }

    @Override
    public String getServiceName() {
        return "Express Shipping";
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
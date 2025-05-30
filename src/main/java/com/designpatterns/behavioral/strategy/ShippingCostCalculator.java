package com.designpatterns.behavioral.strategy;

/**
 * ShippingCostCalculator is the context class that uses different shipping
 * strategies.
 * This is the Context in the Strategy pattern.
 */
public class ShippingCostCalculator {
    private ShippingStrategy strategy;

    public ShippingCostCalculator(ShippingStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("Shipping strategy cannot be null");
        }
        this.strategy = strategy;
    }

    public void setStrategy(ShippingStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("Shipping strategy cannot be null");
        }
        this.strategy = strategy;
    }

    public double calculateShippingCost(double weightInKg, String destination) {
        return strategy.calculateCost(weightInKg, destination);
    }

    public String getCurrentStrategyName() {
        return strategy.getServiceName();
    }

    public ShippingQuote getShippingQuote(double weightInKg, String destination) {
        double cost = calculateShippingCost(weightInKg, destination);
        return new ShippingQuote(strategy.getServiceName(), cost, weightInKg, destination);
    }
}

/**
 * ShippingQuote represents a complete shipping quote with all relevant details.
 */
class ShippingQuote {
    private final String serviceName;
    private final double cost;
    private final double weight;
    private final String destination;

    public ShippingQuote(String serviceName, double cost, double weight, String destination) {
        this.serviceName = serviceName;
        this.cost = cost;
        this.weight = weight;
        this.destination = destination;
    }

    @Override
    public String toString() {
        return String.format("Shipping Quote [Service: %s, Cost: $%.2f, Weight: %.2f kg, Destination: %s]",
                serviceName, cost, weight, destination);
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }

    public double getWeight() {
        return weight;
    }

    public String getDestination() {
        return destination;
    }
}
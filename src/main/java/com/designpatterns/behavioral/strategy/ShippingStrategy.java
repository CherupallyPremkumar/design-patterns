package com.designpatterns.behavioral.strategy;

/**
 * ShippingStrategy interface defining the contract for different shipping cost
 * calculation strategies.
 * This is the Strategy interface in the Strategy pattern.
 */
public interface ShippingStrategy {
    /**
     * Calculate shipping cost based on weight and destination.
     * 
     * @param weightInKg  the weight of the package in kilograms
     * @param destination the destination country or region
     * @return calculated shipping cost
     * @throws IllegalArgumentException if weight is negative or destination is
     *                                  null/empty
     */
    double calculateCost(double weightInKg, String destination);

    /**
     * Get the name of the shipping service.
     * 
     * @return name of the shipping service
     */
    String getServiceName();
}
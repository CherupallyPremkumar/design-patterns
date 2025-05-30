package com.designpatterns.behavioral.state;

import java.util.HashMap;
import java.util.Map;

/**
 * VendingMachine class that maintains the current state and delegates actions
 * to it.
 * This is the Context class in the State pattern.
 */
public class VendingMachine {
    private VendingMachineState currentState;
    private final Map<String, Product> inventory;
    private double currentBalance;
    private String selectedProduct;

    public VendingMachine() {
        this.inventory = new HashMap<>();
        this.currentState = new IdleState();
        initializeInventory();
    }

    private void initializeInventory() {
        inventory.put("A1", new Product("Cola", 2.50, 5));
        inventory.put("A2", new Product("Water", 1.50, 5));
        inventory.put("B1", new Product("Chips", 1.75, 5));
        inventory.put("B2", new Product("Candy", 1.00, 5));
    }

    // State transitions
    public void setState(VendingMachineState state) {
        this.currentState = state;
    }

    // Actions delegated to current state
    public void insertMoney(double amount) {
        currentState.insertMoney(this, amount);
    }

    public void selectProduct(String productCode) {
        currentState.selectProduct(this, productCode);
    }

    public void dispenseProduct() {
        currentState.dispenseProduct(this);
    }

    public void cancelTransaction() {
        currentState.cancelTransaction(this);
    }

    // Getters and setters for state management
    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double balance) {
        this.currentBalance = balance;
    }

    public String getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(String productCode) {
        this.selectedProduct = productCode;
    }

    public Product getProduct(String productCode) {
        return inventory.get(productCode);
    }

    public void updateInventory(String productCode) {
        Product product = inventory.get(productCode);
        if (product != null) {
            product.decrementQuantity();
        }
    }

    public String getCurrentStateName() {
        return currentState.getStateName();
    }

    public void returnMoney() {
        if (currentBalance > 0) {
            System.out.printf("Returning $%.2f\n", currentBalance);
            currentBalance = 0;
        }
    }
}

/**
 * Product class representing items in the vending machine
 */
class Product {
    private final String name;
    private final double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void decrementQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }

    @Override
    public String toString() {
        return String.format("%s - $%.2f (Qty: %d)", name, price, quantity);
    }
}
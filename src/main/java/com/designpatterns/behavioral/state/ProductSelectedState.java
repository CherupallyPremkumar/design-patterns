package com.designpatterns.behavioral.state;

/**
 * State when a product has been selected and ready to dispense.
 */
public class ProductSelectedState implements VendingMachineState {

    @Override
    public void insertMoney(VendingMachine machine, double amount) {
        System.out.println("Product already selected. Please complete or cancel transaction");
    }

    @Override
    public void selectProduct(VendingMachine machine, String productCode) {
        System.out.println("Product already selected. Please complete or cancel transaction");
    }

    @Override
    public void dispenseProduct(VendingMachine machine) {
        Product product = machine.getProduct(machine.getSelectedProduct());
        if (product == null) {
            System.out.println("Error: Selected product not found");
            machine.returnMoney();
            machine.setState(new IdleState());
            return;
        }

        // Process transaction
        double change = machine.getCurrentBalance() - product.getPrice();
        machine.updateInventory(machine.getSelectedProduct());

        System.out.printf("Dispensing %s\n", product.getName());
        if (change > 0) {
            System.out.printf("Returning change: $%.2f\n", change);
        }

        // Reset machine state
        machine.setCurrentBalance(0);
        machine.setSelectedProduct(null);
        machine.setState(new IdleState());
    }

    @Override
    public void cancelTransaction(VendingMachine machine) {
        machine.returnMoney();
        machine.setSelectedProduct(null);
        machine.setState(new IdleState());
    }

    @Override
    public String getStateName() {
        return "PRODUCT_SELECTED";
    }
}
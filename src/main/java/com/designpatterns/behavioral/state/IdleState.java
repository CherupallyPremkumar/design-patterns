package com.designpatterns.behavioral.state;

/**
 * Idle state when the machine is waiting for money.
 */
public class IdleState implements VendingMachineState {

    @Override
    public void insertMoney(VendingMachine machine, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount");
            return;
        }
        machine.setCurrentBalance(amount);
        System.out.printf("Inserted $%.2f\n", amount);
        machine.setState(new HasMoneyState());
    }

    @Override
    public void selectProduct(VendingMachine machine, String productCode) {
        System.out.println("Please insert money first");
    }

    @Override
    public void dispenseProduct(VendingMachine machine) {
        System.out.println("Please insert money and select a product first");
    }

    @Override
    public void cancelTransaction(VendingMachine machine) {
        System.out.println("No transaction to cancel");
    }

    @Override
    public String getStateName() {
        return "IDLE";
    }
}
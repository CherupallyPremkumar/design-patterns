package com.designpatterns.behavioral.state;

/**
 * State interface defining all possible actions in the vending machine.
 * This is the State interface in the State pattern.
 */
public interface VendingMachineState {
    void insertMoney(VendingMachine machine, double amount);

    void selectProduct(VendingMachine machine, String productCode);

    void dispenseProduct(VendingMachine machine);

    void cancelTransaction(VendingMachine machine);

    String getStateName();
}
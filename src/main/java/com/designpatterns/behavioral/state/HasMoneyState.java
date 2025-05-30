package com.designpatterns.behavioral.state;

/**
 * State when money has been inserted but no product selected.
 */
public class HasMoneyState implements VendingMachineState {

    @Override
    public void insertMoney(VendingMachine machine, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount");
            return;
        }
        machine.setCurrentBalance(machine.getCurrentBalance() + amount);
        System.out.printf("Added $%.2f. Total: $%.2f\n",
                amount, machine.getCurrentBalance());
    }

    @Override
    public void selectProduct(VendingMachine machine, String productCode) {
        Product product = machine.getProduct(productCode);
        if (product == null) {
            System.out.println("Invalid product code");
            return;
        }

        if (product.getQuantity() <= 0) {
            System.out.println("Product out of stock");
            return;
        }

        if (machine.getCurrentBalance() < product.getPrice()) {
            System.out.printf("Insufficient funds. Need $%.2f more\n",
                    product.getPrice() - machine.getCurrentBalance());
            return;
        }

        machine.setSelectedProduct(productCode);
        machine.setState(new ProductSelectedState());
        System.out.printf("Selected %s - $%.2f\n",
                product.getName(), product.getPrice());
    }

    @Override
    public void dispenseProduct(VendingMachine machine) {
        System.out.println("Please select a product first");
    }

    @Override
    public void cancelTransaction(VendingMachine machine) {
        machine.returnMoney();
        machine.setState(new IdleState());
    }

    @Override
    public String getStateName() {
        return "HAS_MONEY";
    }
}
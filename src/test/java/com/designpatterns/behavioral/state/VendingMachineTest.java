package com.designpatterns.behavioral.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class demonstrating State pattern usage and verification.
 * Shows common interview scenarios and verification points.
 */
public class VendingMachineTest {
    private VendingMachine machine;

    @BeforeEach
    void setUp() {
        machine = new VendingMachine();
    }

    @Test
    void testInitialState() {
        assertEquals("IDLE", machine.getCurrentStateName());
        assertEquals(0.0, machine.getCurrentBalance());
        assertNull(machine.getSelectedProduct());
    }

    @Test
    void testInsertMoney() {
        machine.insertMoney(2.0);
        assertEquals("HAS_MONEY", machine.getCurrentStateName());
        assertEquals(2.0, machine.getCurrentBalance());
    }

    @Test
    void testInvalidMoneyInput() {
        machine.insertMoney(-1.0);
        assertEquals("IDLE", machine.getCurrentStateName());
        assertEquals(0.0, machine.getCurrentBalance());
    }

    @Test
    void testSelectProductWithoutMoney() {
        machine.selectProduct("A1");
        assertEquals("IDLE", machine.getCurrentStateName());
        assertNull(machine.getSelectedProduct());
    }

    @Test
    void testSelectProductWithInsufficientFunds() {
        machine.insertMoney(1.0);
        machine.selectProduct("A1"); // Cola costs 2.50
        assertEquals("HAS_MONEY", machine.getCurrentStateName());
        assertNull(machine.getSelectedProduct());
    }

    @Test
    void testSuccessfulPurchase() {
        // Buy Cola (A1) which costs 2.50
        machine.insertMoney(3.0);
        machine.selectProduct("A1");
        assertEquals("PRODUCT_SELECTED", machine.getCurrentStateName());
        assertEquals("A1", machine.getSelectedProduct());

        // Complete transaction
        machine.dispenseProduct();
        assertEquals("IDLE", machine.getCurrentStateName());
        assertEquals(0.0, machine.getCurrentBalance());
        assertNull(machine.getSelectedProduct());
    }

    @Test
    void testCancelTransaction() {
        machine.insertMoney(5.0);
        assertEquals(5.0, machine.getCurrentBalance());

        machine.cancelTransaction();
        assertEquals("IDLE", machine.getCurrentStateName());
        assertEquals(0.0, machine.getCurrentBalance());
    }

    @Test
    void testAddingMoreMoney() {
        machine.insertMoney(1.0);
        machine.insertMoney(2.0);
        assertEquals(3.0, machine.getCurrentBalance());
    }

    @Test
    void testInventoryUpdate() {
        Product cola = machine.getProduct("A1");
        int initialQuantity = cola.getQuantity();

        machine.insertMoney(3.0);
        machine.selectProduct("A1");
        machine.dispenseProduct();

        assertEquals(initialQuantity - 1, cola.getQuantity());
    }

    @Test
    void testInvalidProductCode() {
        machine.insertMoney(5.0);
        machine.selectProduct("X1"); // Invalid code
        assertEquals("HAS_MONEY", machine.getCurrentStateName());
        assertNull(machine.getSelectedProduct());
    }

    @Test
    void testStateTransitionSequence() {
        assertEquals("IDLE", machine.getCurrentStateName());

        machine.insertMoney(3.0);
        assertEquals("HAS_MONEY", machine.getCurrentStateName());

        machine.selectProduct("A1");
        assertEquals("PRODUCT_SELECTED", machine.getCurrentStateName());

        machine.dispenseProduct();
        assertEquals("IDLE", machine.getCurrentStateName());
    }

    @Test
    void testDispenseWithoutSelection() {
        machine.insertMoney(2.0);
        machine.dispenseProduct();
        assertEquals("HAS_MONEY", machine.getCurrentStateName());
        assertEquals(2.0, machine.getCurrentBalance());
    }
}
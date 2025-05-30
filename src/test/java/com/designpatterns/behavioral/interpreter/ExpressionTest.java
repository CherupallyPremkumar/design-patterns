package com.designpatterns.behavioral.interpreter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

public class ExpressionTest {
    private ExpressionParser parser;
    private Map<String, Integer> context;

    @BeforeEach
    void setUp() {
        parser = new ExpressionParser();
        context = new HashMap<>();
    }

    @Test
    void testNumberExpression() {
        Expression exp = new NumberExpression(5);
        assertEquals(5, exp.interpret(context));
    }

    @Test
    void testVariableExpression() {
        context.put("x", 10);
        Expression exp = new VariableExpression("x");
        assertEquals(10, exp.interpret(context));
    }

    @Test
    void testAddExpression() {
        Expression left = new NumberExpression(5);
        Expression right = new NumberExpression(3);
        Expression add = new AddExpression(left, right);
        assertEquals(8, add.interpret(context));
    }

    @Test
    void testSubtractExpression() {
        Expression left = new NumberExpression(5);
        Expression right = new NumberExpression(3);
        Expression subtract = new SubtractExpression(left, right);
        assertEquals(2, subtract.interpret(context));
    }

    @Test
    void testComplexExpression() {
        context.put("x", 5);
        context.put("y", 10);
        // Expression: x y + 3 -
        // Equivalent to: (x + y) - 3
        Expression exp = parser.parse("x y + 3 -");
        assertEquals(12, exp.interpret(context));
    }

    @Test
    void testParserWithNumbers() {
        // Expression: 5 3 +
        // Equivalent to: 5 + 3
        Expression exp = parser.parse("5 3 +");
        assertEquals(8, exp.interpret(context));
    }

    @Test
    void testParserWithVariables() {
        context.put("a", 5);
        context.put("b", 3);
        // Expression: a b +
        // Equivalent to: a + b
        Expression exp = parser.parse("a b +");
        assertEquals(8, exp.interpret(context));
    }

    @Test
    void testComplexCalculation() {
        context.put("x", 10);
        context.put("y", 5);
        // Expression: x y - 3 +
        // Equivalent to: (x - y) + 3
        Expression exp = parser.parse("x y - 3 +");
        assertEquals(8, exp.interpret(context));
    }

    @Test
    void testUndefinedVariable() {
        Expression exp = new VariableExpression("undefined");
        assertThrows(IllegalArgumentException.class, () -> exp.interpret(context));
    }

    @Test
    void testInvalidExpression() {
        assertThrows(IllegalArgumentException.class, () -> parser.parse(""));
    }

    @Test
    void testMultipleOperations() {
        // Expression: 10 5 - 3 - 2 +
        // Equivalent to: ((10 - 5) - 3) + 2
        Expression exp = parser.parse("10 5 - 3 - 2 +");
        assertEquals(4, exp.interpret(context));
    }
}
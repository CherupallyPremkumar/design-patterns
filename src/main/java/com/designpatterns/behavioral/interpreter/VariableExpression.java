package com.designpatterns.behavioral.interpreter;

import java.util.Map;

/**
 * Terminal Expression for variables
 */
public class VariableExpression implements Expression {
    private final String name;

    public VariableExpression(String name) {
        this.name = name;
    }

    @Override
    public int interpret(Map<String, Integer> context) {
        if (!context.containsKey(name)) {
            throw new IllegalArgumentException("Variable not defined: " + name);
        }
        return context.get(name);
    }
}
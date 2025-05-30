package com.designpatterns.behavioral.interpreter;

import java.util.Stack;

/**
 * Parser that builds the Abstract Syntax Tree from expression strings
 */
public class ExpressionParser {
    public Expression parse(String expression) {
        Stack<Expression> stack = new Stack<>();
        String[] tokens = expression.split(" ");

        for (String token : tokens) {
            switch (token) {
                case "+":
                    Expression right = stack.pop();
                    Expression left = stack.pop();
                    stack.push(new AddExpression(left, right));
                    break;
                case "-":
                    right = stack.pop();
                    left = stack.pop();
                    stack.push(new SubtractExpression(left, right));
                    break;
                default:
                    try {
                        int number = Integer.parseInt(token);
                        stack.push(new NumberExpression(number));
                    } catch (NumberFormatException e) {
                        stack.push(new VariableExpression(token));
                    }
                    break;
            }
        }

        if (stack.isEmpty()) {
            throw new IllegalArgumentException("Invalid expression: " + expression);
        }
        return stack.pop();
    }
}
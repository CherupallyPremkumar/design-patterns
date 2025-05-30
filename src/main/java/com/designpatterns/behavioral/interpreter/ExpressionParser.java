package com.designpatterns.behavioral.interpreter;

import java.util.Stack;

/**
 * Parser that builds the Abstract Syntax Tree from expression strings
 */
public class ExpressionParser {
    public Expression parse(String expression) {
        if (expression == null || expression.trim().isEmpty()) {
            throw new IllegalArgumentException("Expression cannot be empty");
        }

        Stack<Expression> stack = new Stack<>();
        String[] tokens = expression.trim().split("\\s+");

        // Validate minimum length for a valid expression (at least 3 tokens for a
        // simple operation)
        if (tokens.length < 1) {
            throw new IllegalArgumentException("Expression must contain at least one token");
        }

        for (String token : tokens) {
            try {
                switch (token) {
                    case "+":
                        if (stack.size() < 2) {
                            throw new IllegalArgumentException(
                                    "Invalid expression: insufficient operands for addition");
                        }
                        Expression right = stack.pop();
                        Expression left = stack.pop();
                        stack.push(new AddExpression(left, right));
                        break;
                    case "-":
                        if (stack.size() < 2) {
                            throw new IllegalArgumentException(
                                    "Invalid expression: insufficient operands for subtraction");
                        }
                        right = stack.pop();
                        left = stack.pop();
                        stack.push(new SubtractExpression(left, right));
                        break;
                    default:
                        try {
                            int number = Integer.parseInt(token);
                            stack.push(new NumberExpression(number));
                        } catch (NumberFormatException e) {
                            if (!token.matches("[a-zA-Z][a-zA-Z0-9]*")) {
                                throw new IllegalArgumentException("Invalid variable name: " + token);
                            }
                            stack.push(new VariableExpression(token));
                        }
                        break;
                }
            } catch (Exception e) {
                throw new IllegalArgumentException("Error parsing token '" + token + "': " + e.getMessage());
            }
        }

        if (stack.isEmpty()) {
            throw new IllegalArgumentException("Invalid expression: empty result");
        }
        if (stack.size() > 1) {
            throw new IllegalArgumentException("Invalid expression: too many operands");
        }
        return stack.pop();
    }
}
package com.designpatterns.behavioral.interpreter;

import java.util.Map;

/**
 * Abstract Expression interface declaring the interpret operation
 */
public interface Expression {
    int interpret(Map<String, Integer> context);
}
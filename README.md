# Design Patterns for Google Interviews

This project demonstrates implementations of essential design patterns commonly discussed in Google interviews. Each pattern is implemented with practical examples and includes explanations of when and how to use them.

## Design Patterns Included

### Creational Patterns

1. Singleton Pattern - Ensures a class has only one instance
2. Factory Method Pattern - Creates objects without specifying the exact class
3. Builder Pattern - Constructs complex objects step by step

### Structural Patterns

1. Adapter Pattern - Allows incompatible interfaces to work together
2. Decorator Pattern - Adds new functionality to objects dynamically
3. Composite Pattern - Composes objects into tree structures

### Behavioral Patterns

1. Observer Pattern - Defines one-to-many dependency between objects
2. Strategy Pattern - Defines a family of algorithms
3. Command Pattern - Encapsulates a request as an object

## Project Structure

```
src/
├── main/java/com/designpatterns/
│   ├── creational/
│   ├── structural/
│   └── behavioral/
└── test/java/com/designpatterns/
    ├── creational/
    ├── structural/
    └── behavioral/
```

## How to Use

1. Each pattern is in its own package with example implementations
2. Unit tests demonstrate usage and expected behavior
3. Comments explain the pattern's purpose and use cases

## Common Interview Questions

- When would you use a Singleton vs a Static Class?
- How does the Builder pattern help with object construction?
- What's the difference between Strategy and Command patterns?
- How does the Observer pattern implement loose coupling?

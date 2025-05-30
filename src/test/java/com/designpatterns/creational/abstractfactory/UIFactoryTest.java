package com.designpatterns.creational.abstractfactory;

import com.designpatterns.creational.abstractfactory.light.LightThemeFactory;
import com.designpatterns.creational.abstractfactory.dark.DarkThemeFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UIFactoryTest {
    private UIFactory lightFactory;
    private UIFactory darkFactory;

    @BeforeEach
    void setUp() {
        lightFactory = new LightThemeFactory();
        darkFactory = new DarkThemeFactory();
    }

    @Test
    void testButtonCreation() {
        Button lightButton = lightFactory.createButton();
        Button darkButton = darkFactory.createButton();

        assertEquals("Light Button", lightButton.getType());
        assertEquals("Dark Button", darkButton.getType());

        // Test button functionality
        assertTrue(lightButton.isEnabled());
        lightButton.setEnabled(false);
        assertFalse(lightButton.isEnabled());
    }

    @Test
    void testTextBoxCreation() {
        TextBox lightTextBox = lightFactory.createTextBox();
        TextBox darkTextBox = darkFactory.createTextBox();

        assertEquals("Light TextBox", lightTextBox.getType());
        assertEquals("Dark TextBox", darkTextBox.getType());

        // Test text box functionality
        lightTextBox.setText("Hello World");
        assertEquals("Hello World", lightTextBox.getText());

        // Test max length functionality
        lightTextBox.setMaxLength(5);
        lightTextBox.setText("Hello World");
        assertEquals("Hello", lightTextBox.getText());
    }

    @Test
    void testCheckboxCreation() {
        Checkbox lightCheckbox = lightFactory.createCheckbox();
        Checkbox darkCheckbox = darkFactory.createCheckbox();

        assertEquals("Light Checkbox", lightCheckbox.getType());
        assertEquals("Dark Checkbox", darkCheckbox.getType());

        // Test checkbox functionality
        assertFalse(lightCheckbox.isChecked());
        lightCheckbox.setChecked(true);
        assertTrue(lightCheckbox.isChecked());

        lightCheckbox.setLabel("Accept terms");
        assertEquals("Accept terms", lightCheckbox.getLabel());
    }

    @Test
    void testThemeConsistency() {
        // Light theme components
        UIComponent[] lightComponents = {
                lightFactory.createButton(),
                lightFactory.createTextBox(),
                lightFactory.createCheckbox()
        };

        for (UIComponent component : lightComponents) {
            assertTrue(component.getType().startsWith("Light"));
        }

        // Dark theme components
        UIComponent[] darkComponents = {
                darkFactory.createButton(),
                darkFactory.createTextBox(),
                darkFactory.createCheckbox()
        };

        for (UIComponent component : darkComponents) {
            assertTrue(component.getType().startsWith("Dark"));
        }
    }

    @Test
    void testComponentInteractions() {
        Button button = lightFactory.createButton();
        TextBox textBox = lightFactory.createTextBox();
        Checkbox checkbox = lightFactory.createCheckbox();

        // Set up components
        textBox.setText("Test Input");
        checkbox.setLabel("Enable button");
        checkbox.setChecked(true);

        // Test component states
        assertEquals("Test Input", textBox.getText());
        assertTrue(checkbox.isChecked());
        assertEquals("Enable button", checkbox.getLabel());
        assertTrue(button.isEnabled());

        // Test component rendering (doesn't verify output, just ensures no exceptions)
        button.render();
        textBox.render();
        checkbox.render();
    }

    @Test
    void testFactoryThemeIdentification() {
        assertEquals("Light Theme", lightFactory.getTheme());
        assertEquals("Dark Theme", darkFactory.getTheme());
    }
}
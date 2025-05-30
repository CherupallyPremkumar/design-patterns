package com.designpatterns.behavioral.template;

/**
 * Concrete implementation for processing Word documents.
 */
public class WordDocumentProcessor extends DocumentProcessor {

    public WordDocumentProcessor(String filePath) {
        super(filePath);
    }

    @Override
    protected void validateDocument() {
        if (!filePath.toLowerCase().endsWith(".docx") && !filePath.toLowerCase().endsWith(".doc")) {
            throw new IllegalArgumentException("Not a Word document");
        }
        metadata.setFormat("Word");
        System.out.println("Validating Word document: " + filePath);
    }

    @Override
    protected void parseContent() {
        System.out.println("Parsing Word content using document parser");
        // Simulate Word parsing
        metadata.setSize(1536); // Mock size
    }

    @Override
    protected void transform() {
        System.out.println("Transforming Word content");
        // Simulate Word transformation
    }

    @Override
    protected void save() {
        System.out.println("Saving processed Word document");
    }

    @Override
    protected boolean requiresAuthentication() {
        return true; // Word documents require authentication in this example
    }

    @Override
    protected void authenticate() {
        System.out.println("Authenticating Word document");
        // Simulate Word authentication
    }

    @Override
    protected void compress() {
        // Custom compression for Word documents
        System.out.println("Applying Word-specific compression");
        metadata.setCompressed(true);
    }
}
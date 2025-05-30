package com.designpatterns.behavioral.template;

/**
 * Concrete implementation for processing PDF documents.
 */
public class PdfDocumentProcessor extends DocumentProcessor {

    public PdfDocumentProcessor(String filePath) {
        super(filePath);
    }

    @Override
    protected void validateDocument() {
        if (!filePath.toLowerCase().endsWith(".pdf")) {
            throw new IllegalArgumentException("Not a PDF file");
        }
        metadata.setFormat("PDF");
        System.out.println("Validating PDF document: " + filePath);
    }

    @Override
    protected void parseContent() {
        System.out.println("Parsing PDF content using PDF parser");
        // Simulate PDF parsing
        metadata.setSize(1024); // Mock size
    }

    @Override
    protected void transform() {
        System.out.println("Transforming PDF content");
        // Simulate PDF transformation
    }

    @Override
    protected void save() {
        System.out.println("Saving processed PDF document");
    }

    @Override
    protected boolean requiresAuthentication() {
        return true; // PDFs require authentication in this example
    }

    @Override
    protected void authenticate() {
        System.out.println("Authenticating PDF document");
        // Simulate PDF authentication
    }

    @Override
    protected void compress() {
        System.out.println("Applying PDF-specific compression");
        metadata.setCompressed(true);
    }
}
package com.designpatterns.behavioral.template;

/**
 * Concrete implementation for processing Excel documents.
 */
public class ExcelDocumentProcessor extends DocumentProcessor {

    public ExcelDocumentProcessor(String filePath) {
        super(filePath);
    }

    @Override
    protected void validateDocument() {
        if (!filePath.toLowerCase().endsWith(".xlsx") && !filePath.toLowerCase().endsWith(".xls")) {
            throw new IllegalArgumentException("Not an Excel file");
        }
        metadata.setFormat("Excel");
        System.out.println("Validating Excel document: " + filePath);
    }

    @Override
    protected void parseContent() {
        System.out.println("Parsing Excel content using spreadsheet parser");
        // Simulate Excel parsing
        metadata.setSize(2048); // Mock size
    }

    @Override
    protected void transform() {
        System.out.println("Transforming Excel content");
        // Simulate Excel transformation
    }

    @Override
    protected void save() {
        System.out.println("Saving processed Excel document");
    }

    @Override
    protected boolean shouldCompress() {
        return false; // Excel files are already compressed
    }

    @Override
    protected void extractMetadata() {
        super.extractMetadata();
        // Add Excel-specific metadata extraction
        System.out.println("Extracting Excel-specific metadata");
    }
}
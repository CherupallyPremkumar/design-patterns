package com.designpatterns.behavioral.template;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base class defining the template method for document processing.
 * This is the Abstract Class in the Template Method pattern.
 */
public abstract class DocumentProcessor {
    protected String filePath;
    protected List<String> processingErrors;
    protected DocumentMetadata metadata;

    public DocumentProcessor(String filePath) {
        this.filePath = filePath;
        this.processingErrors = new ArrayList<>();
        this.metadata = new DocumentMetadata();
    }

    /**
     * Template method defining the document processing algorithm.
     * This method is final to prevent overriding the template structure.
     */
    public final DocumentResult processDocument() {
        try {
            // Steps of the algorithm
            validateDocument();
            extractMetadata();
            if (requiresAuthentication()) {
                authenticate();
            }
            parseContent();
            transform();
            if (shouldCompress()) {
                compress();
            }
            save();

            return new DocumentResult(true, metadata, processingErrors);
        } catch (Exception e) {
            processingErrors.add("Processing failed: " + e.getMessage());
            return new DocumentResult(false, metadata, processingErrors);
        }
    }

    // Required operations - must be implemented by subclasses
    protected abstract void validateDocument();

    protected abstract void parseContent();

    protected abstract void transform();

    protected abstract void save();

    // Optional operations with default implementations
    protected boolean requiresAuthentication() {
        return false; // Default: no authentication required
    }

    protected void authenticate() {
        // Default implementation does nothing
    }

    protected boolean shouldCompress() {
        return true; // Default: compression enabled
    }

    protected void compress() {
        metadata.setCompressed(true);
        System.out.println("Applying default compression to document");
    }

    protected void extractMetadata() {
        metadata.setFileName(filePath.substring(filePath.lastIndexOf('/') + 1));
        metadata.setProcessedTime(System.currentTimeMillis());
    }

    // Getter for testing and verification
    public List<String> getProcessingErrors() {
        return new ArrayList<>(processingErrors);
    }
}

/**
 * Class to hold document metadata
 */
class DocumentMetadata {
    private String fileName;
    private long processedTime;
    private boolean compressed;
    private String format;
    private long size;

    // Getters and setters
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getProcessedTime() {
        return processedTime;
    }

    public void setProcessedTime(long processedTime) {
        this.processedTime = processedTime;
    }

    public boolean isCompressed() {
        return compressed;
    }

    public void setCompressed(boolean compressed) {
        this.compressed = compressed;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}

/**
 * Class representing the result of document processing
 */
class DocumentResult {
    private final boolean success;
    private final DocumentMetadata metadata;
    private final List<String> errors;

    public DocumentResult(boolean success, DocumentMetadata metadata, List<String> errors) {
        this.success = success;
        this.metadata = metadata;
        this.errors = new ArrayList<>(errors);
    }

    public boolean isSuccess() {
        return success;
    }

    public DocumentMetadata getMetadata() {
        return metadata;
    }

    public List<String> getErrors() {
        return new ArrayList<>(errors);
    }

    @Override
    public String toString() {
        return String.format("DocumentResult [success=%s, file=%s, errors=%d]",
                success, metadata.getFileName(), errors.size());
    }
}
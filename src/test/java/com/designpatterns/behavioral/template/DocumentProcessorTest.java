package com.designpatterns.behavioral.template;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class demonstrating Template Method pattern usage and verification.
 * Shows common interview scenarios and verification points.
 */
public class DocumentProcessorTest {

    @Test
    void testPdfProcessing() {
        DocumentProcessor processor = new PdfDocumentProcessor("test.pdf");
        DocumentResult result = processor.processDocument();

        assertTrue(result.isSuccess());
        assertEquals("PDF", result.getMetadata().getFormat());
        assertTrue(result.getMetadata().isCompressed());
        assertEquals(0, result.getErrors().size());
    }

    @Test
    void testExcelProcessing() {
        DocumentProcessor processor = new ExcelDocumentProcessor("test.xlsx");
        DocumentResult result = processor.processDocument();

        assertTrue(result.isSuccess());
        assertEquals("Excel", result.getMetadata().getFormat());
        assertFalse(result.getMetadata().isCompressed()); // Excel skips compression
        assertEquals(0, result.getErrors().size());
    }

    @Test
    void testWordProcessing() {
        DocumentProcessor processor = new WordDocumentProcessor("test.docx");
        DocumentResult result = processor.processDocument();

        assertTrue(result.isSuccess());
        assertEquals("Word", result.getMetadata().getFormat());
        assertTrue(result.getMetadata().isCompressed());
        assertEquals(0, result.getErrors().size());
    }

    @Test
    void testInvalidFileExtensions() {
        assertThrows(IllegalArgumentException.class, () -> {
            DocumentProcessor processor = new PdfDocumentProcessor("test.txt");
            processor.processDocument();
        });

        assertThrows(IllegalArgumentException.class, () -> {
            DocumentProcessor processor = new ExcelDocumentProcessor("test.doc");
            processor.processDocument();
        });

        assertThrows(IllegalArgumentException.class, () -> {
            DocumentProcessor processor = new WordDocumentProcessor("test.pdf");
            processor.processDocument();
        });
    }

    @Test
    void testMetadataExtraction() {
        DocumentProcessor processor = new PdfDocumentProcessor("test.pdf");
        DocumentResult result = processor.processDocument();

        assertNotNull(result.getMetadata().getFileName());
        assertEquals("test.pdf", result.getMetadata().getFileName());
        assertTrue(result.getMetadata().getProcessedTime() > 0);
        assertTrue(result.getMetadata().getSize() > 0);
    }

    @Test
    void testAuthenticationBehavior() {
        // PDF requires authentication
        DocumentProcessor pdfProcessor = new PdfDocumentProcessor("test.pdf");
        assertTrue(pdfProcessor.requiresAuthentication());

        // Excel doesn't require authentication
        DocumentProcessor excelProcessor = new ExcelDocumentProcessor("test.xlsx");
        assertFalse(excelProcessor.requiresAuthentication());
    }

    @Test
    void testCompressionBehavior() {
        // PDF uses custom compression
        DocumentProcessor pdfProcessor = new PdfDocumentProcessor("test.pdf");
        DocumentResult pdfResult = pdfProcessor.processDocument();
        assertTrue(pdfResult.getMetadata().isCompressed());

        // Excel skips compression
        DocumentProcessor excelProcessor = new ExcelDocumentProcessor("test.xlsx");
        DocumentResult excelResult = excelProcessor.processDocument();
        assertFalse(excelResult.getMetadata().isCompressed());
    }

    @Test
    void testErrorHandling() {
        // Create a processor with an invalid file path to trigger an error
        DocumentProcessor processor = new PdfDocumentProcessor("");
        DocumentResult result = processor.processDocument();

        assertFalse(result.isSuccess());
        assertTrue(result.getErrors().size() > 0);
        assertTrue(result.getErrors().get(0).contains("Processing failed"));
    }
}
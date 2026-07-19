package com.cognizant.mockito.file;

/**
 * FileWriter interface representing a component responsible for writing
 * content to a file-based data destination.
 * <p>
 * This interface is used in Exercise 3 (Mocking File I/O) to demonstrate how
 * Mockito can be used to simulate file write operations without performing
 * actual disk I/O during unit testing.
 * </p>
 * <p>
 * Note: This interface is named {@code FileWriter} to align with the
 * exercise specification. It is declared in the
 * {@code com.cognizant.mockito.file} package to avoid clashing with
 * {@code java.io.FileWriter}.
 * </p>
 *
 * @author Cognizant Digital Nurture 5.0
 */
public interface FileWriter {

    /**
     * Writes the given content to a file.
     *
     * @param content the {@link String} content to write to the file
     */
    void write(String content);
}

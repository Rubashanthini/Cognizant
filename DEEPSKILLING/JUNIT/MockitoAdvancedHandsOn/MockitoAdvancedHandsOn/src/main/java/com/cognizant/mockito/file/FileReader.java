package com.cognizant.mockito.file;

/**
 * FileReader interface representing a component responsible for reading
 * content from a file-based data source.
 * <p>
 * This interface is used in Exercise 3 (Mocking File I/O) to demonstrate how
 * Mockito can be used to simulate file read operations without performing
 * actual disk I/O during unit testing.
 * </p>
 * <p>
 * Note: This interface is named {@code FileReader} to align with the
 * exercise specification. It is declared in the
 * {@code com.cognizant.mockito.file} package to avoid clashing with
 * {@code java.io.FileReader}.
 * </p>
 *
 * @author Cognizant Digital Nurture 5.0
 */
public interface FileReader {

    /**
     * Reads and returns the content of a file.
     *
     * @return a {@link String} representing the content read from the file
     */
    String read();
}

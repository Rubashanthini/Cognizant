package com.cognizant.mockito;

import com.cognizant.mockito.file.FileReader;
import com.cognizant.mockito.file.FileWriter;
import com.cognizant.mockito.service.FileService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Exercise 3 - Mocking File I/O.
 * <p>
 * This test class demonstrates how to mock a {@link FileReader} and a
 * {@link FileWriter} using Mockito, stub the reader to simulate file
 * content, and verify both the processed file content and the read/write
 * interactions with the mocks.
 * </p>
 *
 * @author Cognizant Digital Nurture 5.0
 */
public class FileServiceTest {

    /**
     * Verifies that {@link FileService#processFile()} correctly processes
     * content returned by a mocked {@link FileReader}, writes the processed
     * content via a mocked {@link FileWriter}, and returns the expected
     * result. Also verifies that both the read and write interactions
     * occurred exactly once.
     */
    @Test
    @DisplayName("FileService should read, process, and write mock file content correctly")
    public void testServiceWithMockFileIO() {
        // Arrange: create mock file reader/writer and stub the reader's behavior
        FileReader mockFileReader = mock(FileReader.class);
        FileWriter mockFileWriter = mock(FileWriter.class);
        when(mockFileReader.read()).thenReturn("Mock File Content");

        FileService fileService = new FileService(mockFileReader, mockFileWriter);

        // Act: invoke the method under test
        String result = fileService.processFile();

        // Assert: validate the processed file content
        assertEquals("Processed Mock File Content", result);

        // Verify: confirm the read interaction occurred exactly once
        verify(mockFileReader, times(1)).read();

        // Verify: confirm the write interaction occurred exactly once with the processed content
        verify(mockFileWriter, times(1)).write("Processed Mock File Content");
    }
}

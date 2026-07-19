package com.cognizant.mockito.service;

import com.cognizant.mockito.file.FileReader;
import com.cognizant.mockito.file.FileWriter;

/**
 * FileService class that depends on a {@link FileReader} and a
 * {@link FileWriter} to read content from a file, process it, and write the
 * processed content back out.
 * <p>
 * This class is used in Exercise 3 (Mocking File I/O). Both the
 * {@link FileReader} and {@link FileWriter} dependencies are injected via
 * the constructor, which allows mocked implementations to be substituted
 * during unit testing so that no actual disk I/O is performed.
 * </p>
 *
 * @author Cognizant Digital Nurture 5.0
 */
public class FileService {

    /** The file reader dependency used to read file content. */
    private final FileReader fileReader;

    /** The file writer dependency used to write processed content. */
    private final FileWriter fileWriter;

    /**
     * Constructs a new {@code FileService} with the given {@link FileReader}
     * and {@link FileWriter} dependencies.
     *
     * @param fileReader the file reader used to read file content
     * @param fileWriter the file writer used to write processed content
     */
    public FileService(FileReader fileReader, FileWriter fileWriter) {
        this.fileReader = fileReader;
        this.fileWriter = fileWriter;
    }

    /**
     * Reads content using the {@link FileReader}, processes it by prefixing
     * it with {@code "Processed "}, writes the processed content using the
     * {@link FileWriter}, and returns the processed content.
     *
     * @return the processed file content as a {@link String}
     */
    public String processFile() {
        String content = fileReader.read();
        String processedContent = "Processed " + content;
        fileWriter.write(processedContent);
        return processedContent;
    }
}

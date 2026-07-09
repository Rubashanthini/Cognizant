package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Main Spring Boot application entry point.
 *
 * @ImportResource loads the legacy XML bean configuration files
 * (country.xml and date-format.xml) alongside the standard
 * Java/annotation-based configuration, satisfying the
 * "XML Bean Configuration" requirement of this hands-on.
 */
@SpringBootApplication
@ImportResource({"classpath:country.xml", "classpath:date-format.xml"})
public class SpringLearnApplication {

    /** SLF4J Logger for application startup events. */
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

    /**
     * Application entry point.
     *
     * @param args command-line arguments passed to the Spring Boot application
     */
    public static void main(String[] args) {
        LOGGER.info("START - SpringLearnApplication.main()");
        SpringApplication.run(SpringLearnApplication.class, args);
        LOGGER.info("END - SpringLearnApplication.main() - Application started successfully");
    }
}

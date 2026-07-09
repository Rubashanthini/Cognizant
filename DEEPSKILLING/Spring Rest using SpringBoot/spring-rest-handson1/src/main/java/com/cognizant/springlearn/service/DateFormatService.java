package com.cognizant.springlearn.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DateFormatService.java
 *
 * Exercise 2: Loads the "dateFormatBean" (java.text.SimpleDateFormat) defined
 * in date-format.xml using a ClassPathXmlApplicationContext, and uses it to
 * parse a sample date string "31/12/2018", logging the result.
 */
public class DateFormatService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateFormatService.class);

    /**
     * Loads the Spring XML configuration, retrieves the SimpleDateFormat bean,
     * parses a hard-coded date string and logs the parsed result.
     */
    public void displayDate() {
        LOGGER.info("START - displayDate()");

        // Create ApplicationContext from the date-format.xml classpath resource
        ApplicationContext context = new ClassPathXmlApplicationContext("date-format.xml");
        LOGGER.debug("DEBUG - ApplicationContext loaded from date-format.xml");

        // Retrieve the SimpleDateFormat bean defined in the XML file
        SimpleDateFormat dateFormatBean = (SimpleDateFormat) context.getBean("dateFormatBean");
        LOGGER.debug("DEBUG - Retrieved bean 'dateFormatBean' of type: {}", dateFormatBean.getClass().getName());

        String sampleDate = "31/12/2018";
        try {
            Date parsedDate = dateFormatBean.parse(sampleDate);
            LOGGER.info("Parsed date '{}' using pattern 'dd/MM/yyyy' => {}", sampleDate, parsedDate);
        } catch (Exception e) {
            LOGGER.error("ERROR - Failed to parse date '{}': {}", sampleDate, e.getMessage(), e);
        }

        // Close the standalone context since it was created independently of the Boot context
        ((ClassPathXmlApplicationContext) context).close();

        LOGGER.info("END - displayDate()");
    }
}

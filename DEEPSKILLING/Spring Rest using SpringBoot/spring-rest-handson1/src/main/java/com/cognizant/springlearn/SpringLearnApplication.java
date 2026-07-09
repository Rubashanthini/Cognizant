package com.cognizant.springlearn;

import com.cognizant.springlearn.service.BeanScopeService;
import com.cognizant.springlearn.service.CountryListService;
import com.cognizant.springlearn.service.CountryService;
import com.cognizant.springlearn.service.DateFormatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringLearnApplication.java
 *
 * Exercise 1: Main entry point of the Spring Boot application.
 *
 * This class bootstraps the Spring Boot application context using
 * {@link SpringApplication#run(Class, String...)} and, via the
 * {@link CommandLineRunner} bean, sequentially executes every hands-on
 * exercise (2 through 6) so that their output is visible in the console
 * as soon as the application starts.
 */
@SpringBootApplication
public class SpringLearnApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

    /**
     * Main method - entry point of the Java application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        LOGGER.info("START - main()");
        SpringApplication.run(SpringLearnApplication.class, args);
        LOGGER.info("END - main()");
    }

    /**
     * Executed automatically after the Spring Boot application context has
     * fully started. Runs each hands-on exercise demo in order, using plain
     * {@code new} instantiation for the exercise services since those
     * services deliberately manage their own standalone
     * ClassPathXmlApplicationContext instances (Exercises 2, 4, 5 and 6
     * specifically require ClassPathXmlApplicationContext / context.getBean()).
     *
     * @param args command line arguments passed to the application
     */
    @Override
    public void run(String... args) {
        LOGGER.info("START - run()");

        LOGGER.info("=====================================================");
        LOGGER.info("Exercise 2: Spring XML Configuration - Date Format Bean");
        LOGGER.info("=====================================================");
        DateFormatService dateFormatService = new DateFormatService();
        dateFormatService.displayDate();

        LOGGER.info("=====================================================");
        LOGGER.info("Exercise 4: Load Country Bean from Spring XML");
        LOGGER.info("=====================================================");
        CountryService countryService = new CountryService();
        countryService.displayCountry();

        LOGGER.info("=====================================================");
        LOGGER.info("Exercise 5: Bean Scope - Singleton vs Prototype");
        LOGGER.info("=====================================================");
        BeanScopeService beanScopeService = new BeanScopeService();
        beanScopeService.demonstrateBeanScope();

        LOGGER.info("=====================================================");
        LOGGER.info("Exercise 6: Load List of Countries");
        LOGGER.info("=====================================================");
        CountryListService countryListService = new CountryListService();
        countryListService.displayCountries();

        LOGGER.info("END - run()");
    }
}

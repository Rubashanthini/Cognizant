package com.cognizant.springlearn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller implementing Exercise 2 (Hello World REST Service).
 *
 * Exposes a single GET endpoint at /hello which returns a plain
 * text greeting. @RestController combines @Controller and
 * @ResponseBody so the returned String is written directly to the
 * HTTP response body rather than being resolved as a view name.
 */
@RestController
public class HelloController {

    /** SLF4J Logger for this controller. */
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    /**
     * Handles GET /hello requests.
     *
     * @return the greeting message "Hello World!!"
     */
    @GetMapping("/hello")
    public String sayHello() {
        LOGGER.info("START - sayHello()");
        LOGGER.debug("Preparing greeting response");

        String response = "Hello World!!";

        LOGGER.info("END - sayHello()");
        return response;
    }
}

package com.library.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * AnnotationConfig - supporting class for Exercise 6.
 *
 * This is a pure Java-based equivalent of applicationContext-annotation.xml.
 * It is not required to run the XML-based demos (SpringCoreMainApp uses
 * applicationContext.xml, AnnotationConfigMainApp uses
 * applicationContext-annotation.xml directly) but it is included here to
 * show how the same "annotation based configuration" requirement from
 * Exercise 6 can alternatively be expressed with zero XML, using
 * {@code AnnotationConfigApplicationContext}.
 *
 * Enables:
 *   - Component scanning of the whole com.library package, so classes
 *     annotated with @Service, @Repository, @Component, @Aspect are
 *     automatically registered as beans.
 *   - AspectJ auto-proxying, required for the @Aspect based LoggingAspect
 *     (Exercises 3 and 8) to actually intercept method calls.
 */
@Configuration
@ComponentScan(basePackages = "com.library")
@EnableAspectJAutoProxy
public class AnnotationConfig {
    // No bean methods required - component scanning takes care of
    // registering BookService, BookRepository and LoggingAspect.
}

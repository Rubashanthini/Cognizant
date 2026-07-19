# SLF4J Logging Exercises

**Cognizant Digital Nurture 5.0 — SLF4J Logging Exercises**

A complete, professional Maven project demonstrating SLF4J logging with a
Logback backend, covering basic log levels, parameterized logging, and
multiple appenders (Console + File).

---

## 1. Project Overview

This project contains three standalone exercises, each showing a different
aspect of logging with **SLF4J** (Simple Logging Facade for Java) and
**Logback** (the logging framework used as SLF4J's implementation):

| Exercise | Class | Demonstrates |
|---|---|---|
| 1 | `LoggingExample` | Basic log levels: `error`, `warn`, `info`, `debug`, `trace`, and exception logging |
| 2 | `ParameterizedLoggingExample` | Parameterized ("`{}`" placeholder) logging with multiple arguments and data types |
| 3 | `AppenderLoggingExample` | Logging simultaneously to the **console** and a **file** (`app.log`) via Logback appenders |

A JUnit 5 test class (`LoggingExampleTest`) verifies that all three exercise
classes run without throwing exceptions.

---

## 2. Folder Structure

```
SLF4JLoggingExercises/
├── pom.xml
├── README.md
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── cognizant
    │   │           └── logging
    │   │               ├── LoggingExample.java
    │   │               ├── ParameterizedLoggingExample.java
    │   │               └── AppenderLoggingExample.java
    │   └── resources
    │       └── logback.xml
    └── test
        └── java
            └── com
                └── cognizant
                    └── logging
                        └── LoggingExampleTest.java
```

---

## 3. What is SLF4J?

**SLF4J (Simple Logging Facade for Java)** is an abstraction layer that sits
in front of various logging frameworks (Logback, Log4j2, java.util.logging,
etc.). Application code programs against the SLF4J API only
(`org.slf4j.Logger` / `org.slf4j.LoggerFactory`), which means the underlying
logging implementation can be swapped without changing any application code
— only the dependency and configuration file change.

---

## 4. What is Logback?

**Logback** is a logging framework created by the same author as Log4j, and
is designed as a native implementation of the SLF4J API (no adapter needed).
It is fast, highly configurable via XML (`logback.xml`), and supports
multiple appenders, rolling file policies, filters, and more. In this
project, Logback is the concrete logging engine behind the SLF4J facade.

---

## 5. Logger Levels

SLF4J defines five logging levels, from least to most severe:

| Level | Purpose |
|---|---|
| `TRACE` | Extremely fine-grained diagnostic information |
| `DEBUG` | Detailed information useful during development/debugging |
| `INFO`  | General informational messages about application progress |
| `WARN`  | Something unexpected happened, but the application can continue |
| `ERROR` | A serious problem occurred; some functionality may be unavailable |

Only messages at or above the configured root level (`TRACE` in this
project's `logback.xml`) are actually emitted.

---

## 6. Parameterized Logging

Instead of concatenating strings manually (`"User " + user + " logged in"`),
SLF4J allows placeholders:

```java
logger.info("User {} logged in from {}", username, location);
```

**Benefits:**
- The message string is only built if the log level is enabled, avoiding
  unnecessary string concatenation overhead.
- Cleaner, more readable log statements.
- Supports an arbitrary number of arguments, including an optional trailing
  `Throwable` for exception logging.

See `ParameterizedLoggingExample.java` for examples using `String`, `int`,
`double`, `boolean`, and `List` arguments.

---

## 7. Console Appender

The `ConsoleAppender` writes formatted log output to standard output
(the terminal/console). It is configured in `logback.xml` as:

```xml
<appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
    <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
        <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
    </encoder>
</appender>
```

---

## 8. File Appender

The `FileAppender` writes the same formatted log output to a file named
`app.log`, created automatically in the project's working directory the
first time a message is logged:

```xml
<appender name="FILE" class="ch.qos.logback.core.FileAppender">
    <file>app.log</file>
    <append>true</append>
    <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
        <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
    </encoder>
</appender>
```

---

## 9. Logback Configuration (`logback.xml`)

Located at `src/main/resources/logback.xml`. Both the `CONSOLE` and `FILE`
appenders are wired to the **root logger** with level `TRACE`, so every
exercise class emits its log statements to both destinations:

```xml
<root level="TRACE">
    <appender-ref ref="CONSOLE" />
    <appender-ref ref="FILE" />
</root>
```

---

## 10. Maven Commands

| Command | Purpose |
|---|---|
| `mvn clean` | Removes the `target/` build directory |
| `mvn compile` | Compiles the main source code |
| `mvn test` | Runs the JUnit 5 test suite |
| `mvn clean install` | Cleans, compiles, tests, and installs the artifact into your local `.m2` repository |
| `mvn exec:java` | Runs the application (default main class: `LoggingExample`) |
| `mvn exec:java -Dexec.mainClass="com.cognizant.logging.ParameterizedLoggingExample"` | Runs Exercise 2 explicitly |
| `mvn exec:java -Dexec.mainClass="com.cognizant.logging.AppenderLoggingExample"` | Runs Exercise 3 explicitly |

> **Note:** The `exec-maven-plugin` is included in `pom.xml`, so `mvn exec:java`
> works out of the box without any additional setup.

---

## 11. Running the Project

1. Ensure **Java 8+** and **Maven 3.6+** are installed and on your `PATH`.
2. From the project root directory, build the project:
   ```bash
   mvn clean install
   ```
3. Run each exercise (Exercise 1 runs by default):
   ```bash
   mvn exec:java
   mvn exec:java -Dexec.mainClass="com.cognizant.logging.ParameterizedLoggingExample"
   mvn exec:java -Dexec.mainClass="com.cognizant.logging.AppenderLoggingExample"
   ```
4. Alternatively, run the packaged JAR directly:
   ```bash
   java -jar target/slf4j-logging-exercises.jar
   ```
5. Check the generated **`app.log`** file in the project root directory to
   see the same output that was printed to the console.

---

## 12. Expected Output

Running `LoggingExample` produces console output similar to:

```
2026-07-19 10:15:32.101 [main] TRACE com.cognizant.logging.LoggingExample - This is a TRACE message - very detailed diagnostic information
2026-07-19 10:15:32.105 [main] DEBUG com.cognizant.logging.LoggingExample - This is a DEBUG message - useful for debugging the application
2026-07-19 10:15:32.106 [main] INFO  com.cognizant.logging.LoggingExample - This is an INFO message - general informational message
2026-07-19 10:15:32.106 [main] WARN  com.cognizant.logging.LoggingExample - This is a WARNING message - something unexpected happened, but the application can continue
2026-07-19 10:15:32.107 [main] ERROR com.cognizant.logging.LoggingExample - This is an ERROR message - a serious problem has occurred
2026-07-19 10:15:32.110 [main] ERROR com.cognizant.logging.LoggingExample - An arithmetic exception occurred while simulating a failure
java.lang.ArithmeticException: / by zero
    at com.cognizant.logging.LoggingExample.simulateFailure(LoggingExample.java:60)
    at com.cognizant.logging.LoggingExample.main(LoggingExample.java:41)
2026-07-19 10:15:32.111 [main] INFO  com.cognizant.logging.LoggingExample - LoggingExample execution completed successfully
```

The identical lines (minus ANSI coloring, if any) are appended to `app.log`.

---

## 13. Import into Eclipse

1. Open Eclipse and select **File → Import…**
2. Choose **Maven → Existing Maven Projects**, click **Next**.
3. Click **Browse…**, select the `SLF4JLoggingExercises` root folder
   (the one containing `pom.xml`).
4. Ensure the project (`slf4j-logging-exercises`) is checked, then click **Finish**.
5. Eclipse will automatically resolve Maven dependencies. Right-click the
   project → **Maven → Update Project…** if needed.
6. Run any class by right-clicking it → **Run As → Java Application**.

---

## 14. Import into IntelliJ IDEA

1. Open IntelliJ IDEA and select **File → Open…**
2. Navigate to and select the `SLF4JLoggingExercises` folder (the one
   containing `pom.xml`), then click **OK**.
3. IntelliJ will detect it as a Maven project and automatically import
   dependencies (watch the Maven progress indicator in the bottom-right).
4. Once indexing completes, open any of the exercise classes and click the
   green **Run** arrow next to the `main` method.

---

## 15. Import into VS Code

1. Install the **Extension Pack for Java** and **Maven for Java** extensions
   from the VS Code Marketplace (if not already installed).
2. Open VS Code and select **File → Open Folder…**, then choose the
   `SLF4JLoggingExercises` folder.
3. VS Code will detect the `pom.xml` and automatically configure the Java
   project (check the **Java Projects** panel in the Explorer sidebar).
4. Open any exercise class file; click the **Run** CodeLens link above the
   `main` method, or use the integrated terminal to run Maven commands
   directly (e.g., `mvn clean install`, `mvn exec:java`).

---

## 16. Tech Stack Summary

- **Java 8**
- **Maven** (build & dependency management)
- **SLF4J 1.7.30** (`slf4j-api`)
- **Logback Classic 1.2.3** (`logback-classic`)
- **JUnit Jupiter 5.8.2** (unit testing)

---

*Generated for Cognizant Digital Nurture 5.0 — SLF4J Logging Exercises.*

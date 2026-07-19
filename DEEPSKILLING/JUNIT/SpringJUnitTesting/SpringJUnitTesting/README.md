# SpringJUnitTesting

Cognizant Digital Nurture 5.0 — **JUnit Spring Test Exercises**

A complete, self-contained Spring Boot Maven project demonstrating unit
testing, mocking, MockMvc controller testing, full-stack integration
testing, exception handling, custom repository queries, and parameterized
testing — covering all 9 exercises from the official exercise sheet.

---

## 1. Project Overview

This project implements a small **User** management REST API
(`GET /users/{id}`, `POST /users`) backed by Spring Data JPA and an H2
in-memory database, and it is accompanied by a comprehensive test suite
that demonstrates every major Spring testing technique:

| # | Exercise                                   | Demonstrated In                                  |
|---|---------------------------------------------|---------------------------------------------------|
| 1 | Basic unit test for a service method         | `CalculatorServiceTest`                            |
| 2 | Mocking a repository in a service test        | `UserServiceTest`                                  |
| 3 | Testing a REST controller with MockMvc        | `UserControllerTest`                               |
| 4 | Integration test with Spring Boot             | `IntegrationTest`                                  |
| 5 | Testing a controller POST endpoint            | `CreateUserControllerTest`                         |
| 6 | Testing service exception handling            | `UserServiceExceptionTest`                         |
| 7 | Testing a custom repository query             | `UserRepositoryTest`                               |
| 8 | Testing controller exception handling          | `GlobalExceptionHandlerTest`                       |
| 9 | Parameterized testing with JUnit              | `ParameterizedCalculatorTest`                      |

---

## 2. Folder Structure

```
SpringJUnitTesting/
├── pom.xml
├── README.md
└── src
    ├── main
    │   ├── java
    │   │   └── com/cognizant/testing/
    │   │       ├── SpringJUnitTestingApplication.java
    │   │       ├── entity/User.java
    │   │       ├── repository/UserRepository.java
    │   │       ├── service/CalculatorService.java
    │   │       ├── service/UserService.java
    │   │       ├── controller/UserController.java
    │   │       └── exception/GlobalExceptionHandler.java
    │   └── resources
    │       ├── application.properties
    │       ├── schema.sql
    │       └── data.sql
    └── test
        └── java
            └── com/cognizant/testing/
                ├── CalculatorServiceTest.java
                ├── UserServiceTest.java
                ├── UserControllerTest.java
                ├── IntegrationTest.java
                ├── CreateUserControllerTest.java
                ├── UserServiceExceptionTest.java
                ├── UserRepositoryTest.java
                ├── GlobalExceptionHandlerTest.java
                ├── ParameterizedCalculatorTest.java
                └── AllTests.java
```

---

## 3. Technologies Used

- **Java 8**
- **Spring Boot 2.7.18**
- **Spring Web** — REST controllers
- **Spring Data JPA** — repository abstraction over Hibernate
- **H2 Database** — in-memory relational database for tests and local runs
- **JUnit 5 (Jupiter)** — test engine and assertions
- **Mockito** (`mockito-core`, `mockito-junit-jupiter`) — mocking framework
- **MockMvc** — Spring's test framework for exercising controllers without a running server
- **Maven** — build and dependency management
- **Maven Surefire Plugin** — runs the test suite during `mvn test`

---

## 4. Spring Testing Concepts

### 4.1 Unit Testing
Plain JUnit 5 tests that instantiate a class directly (e.g. `new CalculatorService()`)
with no Spring context involved. Fast and isolated. See `CalculatorServiceTest`.

### 4.2 Mockito
`@Mock` creates a fake implementation of a dependency (e.g. `UserRepository`);
`@InjectMocks` wires that mock into the class under test (e.g. `UserService`).
`when(...).thenReturn(...)` stubs behavior, and `verify(...)` confirms an
interaction actually happened. See `UserServiceTest` and `UserServiceExceptionTest`.

### 4.3 MockMvc
`MockMvcBuilders.standaloneSetup(controller)` builds a MockMvc instance
around a controller (with mocked service dependencies) without booting an
HTTP server or the full Spring context — ideal for fast controller-layer
tests. See `UserControllerTest`, `CreateUserControllerTest`, and
`GlobalExceptionHandlerTest`.

### 4.4 Integration Testing
`@SpringBootTest` combined with `@AutoConfigureMockMvc` boots the entire
Spring application context — controller, service, repository, and the
real H2 database — and exercises the full request/response cycle through
MockMvc. See `IntegrationTest`.

### 4.5 Parameterized Tests
`@ParameterizedTest` runs the same test logic across multiple sets of
inputs, supplied via `@CsvSource` (multiple named parameters) or
`@ValueSource` (a single parameter). See `ParameterizedCalculatorTest`.

### 4.6 Exception Handling
- **Service layer:** `UserService.getUserByIdOrThrow()` throws
  `NoSuchElementException` when a user isn't found; tested with
  `assertThrows()` in `UserServiceExceptionTest`.
- **Controller layer:** `GlobalExceptionHandler` (annotated
  `@ControllerAdvice`) intercepts that same exception and converts it into
  an HTTP 404 response; tested via MockMvc in `GlobalExceptionHandlerTest`.

### 4.7 Repository Testing
`@DataJpaTest` loads only the JPA-related slice of the Spring context
(repositories + an embedded database), making repository tests fast and
focused. `TestEntityManager` is used to insert data directly, and
`UserRepository.findByName(...)` is verified against it. See
`UserRepositoryTest`.

---

## 5. Maven Commands

```bash
# Compile the project
mvn compile

# Run the full test suite
mvn test

# Build the project (compiles, tests, and packages a jar)
mvn clean install

# Skip tests during a build (not recommended, but sometimes useful)
mvn clean install -DskipTests

# Run the application
mvn spring-boot:run
```

---

## 6. Running Tests

Run everything:

```bash
mvn test
```

Run a single test class:

```bash
mvn -Dtest=CalculatorServiceTest test
mvn -Dtest=UserServiceTest test
mvn -Dtest=IntegrationTest test
```

Run the aggregated suite (`AllTests`, powered by `junit-platform-suite`):

```bash
mvn -Dtest=AllTests test
```

> Note: Maven Surefire also auto-discovers and runs every class matching
> `**/*Test.java` / `**/*Tests.java` on `mvn test`, so `AllTests` is a
> convenience aggregator rather than a strict requirement — all 9 test
> classes run either way.

---

## 7. Expected Output

Running `mvn test` should produce a Surefire summary similar to:

```
Tests run: 22, Failures: 0, Errors: 0, Skipped: 0

BUILD SUCCESS
```

(Exact test count may vary slightly by JUnit/Mockito minor versions but
all listed test classes are expected to pass with zero failures/errors.)

---

## 8. Import into Eclipse

1. Open Eclipse → **File → Import...**
2. Choose **Maven → Existing Maven Projects** → **Next**
3. Click **Browse...** and select the `SpringJUnitTesting` project folder
4. Ensure the `pom.xml` is checked → click **Finish**
5. Wait for Eclipse to download dependencies and build the workspace
6. Right-click the project → **Run As → Maven test** to run the test suite,
   or run `SpringJUnitTestingApplication.java` as a **Java Application** to
   start the app

---

## 9. Import into IntelliJ IDEA

1. Open IntelliJ IDEA → **File → Open...**
2. Select the `SpringJUnitTesting` folder (the one containing `pom.xml`)
3. IntelliJ will detect it as a Maven project and prompt to load it — click
   **Load Maven Project** (or it may happen automatically)
4. Wait for indexing and dependency resolution to complete
5. Run tests via the green gutter icons next to any test class/method, or
   right-click the `test` folder → **Run 'All Tests'**
6. Run the app via the green run icon next to `SpringJUnitTestingApplication`

---

## 10. Import into VS Code

1. Install the **Extension Pack for Java** and **Spring Boot Extension Pack**
   from the VS Code Marketplace
2. Open VS Code → **File → Open Folder...** → select `SpringJUnitTesting`
3. VS Code will detect the Maven project automatically (via the Java
   extensions) and resolve dependencies
4. Use the **Testing** panel (flask icon) in the sidebar to discover and
   run all JUnit 5 tests, or use CodeLens **Run Test** links above each
   `@Test` method
5. Use the **Spring Boot Dashboard** panel, or the terminal
   (`mvn spring-boot:run`), to start the application

---

## 11. REST API Quick Reference

| Method | Endpoint       | Description                              | Success Response |
|--------|----------------|-------------------------------------------|-------------------|
| GET    | `/users/{id}`  | Fetch a user by id                        | `200 OK` + JSON user |
| POST   | `/users`       | Create a new user (JSON body: `{"name":"..."}`) | `200 OK` + JSON user |

If a `GET /users/{id}` is requested for a non-existent id, the API returns
`404 Not Found` with body `User not found`, courtesy of
`GlobalExceptionHandler`.

H2 console (for local manual inspection while the app is running):
`http://localhost:8080/h2-console` — JDBC URL: `jdbc:h2:mem:testdb`, user: `sa`, no password.

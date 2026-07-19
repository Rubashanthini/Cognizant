# SpringMockitoTesting

**Cognizant Digital Nurture 5.0 — "Mocking Dependencies in Spring Tests using Mockito"**

A complete, professional Spring Boot Maven project demonstrating three levels of
dependency mocking with Mockito and JUnit 5: controller unit tests, service unit
tests, and full Spring Boot integration tests.

---

## 1. Project Overview

This project implements a simple `User` REST API (`GET /users/{id}`, `GET /users`,
`POST /users`, `DELETE /users/{id}`) backed by Spring Data JPA and an H2 in-memory
database. The core learning objective is **not** the API itself, but the three
distinct testing strategies built around it:

| Exercise | Focus | Annotation(s) Used |
|---|---|---|
| 1 | Mock a **service** inside a **controller** test | `@WebMvcTest`, `@MockBean` |
| 2 | Mock a **repository** inside a **service** test | `@ExtendWith(MockitoExtension.class)`, `@Mock`, `@InjectMocks` |
| 3 | Mock a **service** inside a full **integration** test | `@SpringBootTest`, `@AutoConfigureMockMvc`, `@MockBean` |

---

## 2. Folder Structure

```
SpringMockitoTesting/
├── pom.xml
├── README.md
└── src
    ├── main
    │   ├── java
    │   │   └── com/cognizant/testing
    │   │       ├── SpringMockitoTestingApplication.java
    │   │       ├── entity/User.java
    │   │       ├── repository/UserRepository.java
    │   │       ├── service/UserService.java
    │   │       ├── controller/UserController.java
    │   │       └── exception/GlobalExceptionHandler.java
    │   └── resources
    │       ├── application.properties
    │       ├── schema.sql
    │       └── data.sql
    └── test
        └── java
            └── com/cognizant/testing
                ├── UserControllerTest.java
                ├── UserServiceTest.java
                ├── UserIntegrationTest.java
                └── AllTests.java
```

---

## 3. Technologies Used

- **Java 8**
- **Spring Boot 2.7.18** (Spring Web, Spring Data JPA)
- **H2 Database** (in-memory)
- **JUnit 5 (Jupiter)**
- **Mockito Core** + **Mockito JUnit Jupiter**
- **Spring Boot Test** (MockMvc, `@WebMvcTest`, `@SpringBootTest`)
- **Maven** (Compiler Plugin, Surefire Plugin, Spring Boot Plugin)

---

## 4. Mockito Concepts Covered

### 4.1 `@MockBean`
Used in `UserControllerTest` and `UserIntegrationTest` to replace the real
`UserService` bean in the Spring `ApplicationContext` with a Mockito mock,
without touching any other bean wiring.

### 4.2 MockMvc
Used across all controller-facing tests to simulate HTTP requests
(`GET /users/{id}`) and assert on status codes, headers, and JSON response
bodies — without starting a real servlet container.

### 4.3 Mockito Verification
`verify(mock, times(n)).method(args)` is used throughout to assert that
mocked dependencies were invoked the expected number of times with the
expected arguments (e.g. `verify(userService, times(1)).getUserById(1L)`).

### 4.4 Spring Boot Testing
Demonstrates the full spectrum of Spring Boot's test slices:
- `@WebMvcTest` — loads only the web layer.
- Plain Mockito (`MockitoExtension`) — loads no Spring context at all.
- `@SpringBootTest` — loads the entire application context.

### 4.5 Integration Testing
`UserIntegrationTest` uses `@SpringBootTest` + `@AutoConfigureMockMvc` to
exercise the complete request pipeline (dispatcher servlet, controller
advice, JSON message converters) while still mocking the service layer
to keep the test deterministic and fast.

### 4.6 Repository Mocking
`UserServiceTest` mocks `UserRepository` directly with `@Mock` and injects
it into `UserService` with `@InjectMocks`, isolating the service's business
logic from any real database interaction.

### 4.7 Controller Testing
`UserControllerTest` isolates `UserController` from the rest of the
application, verifying routing, status codes, and JSON serialization in
complete isolation from the service and persistence layers.

---

## 5. Maven Commands

| Command | Purpose |
|---|---|
| `mvn clean install` | Clean, compile, run tests, and package the application |
| `mvn clean install -DskipTests` | Build the JAR without running tests |
| `mvn test` | Run all unit and integration tests |
| `mvn test -Dtest=AllTests` | Run the aggregated `AllTests` suite |
| `mvn test -Dtest=UserControllerTest` | Run only Exercise 1 tests |
| `mvn test -Dtest=UserServiceTest` | Run only Exercise 2 tests |
| `mvn test -Dtest=UserIntegrationTest` | Run only Exercise 3 tests |
| `mvn spring-boot:run` | Run the application locally on port 8080 |

---

## 6. Running Tests

```bash
cd SpringMockitoTesting
mvn clean test
```

Expected result: **all test classes pass** (`UserControllerTest`,
`UserServiceTest`, `UserIntegrationTest`, and the `AllTests` suite that
aggregates all three).

---

## 7. Expected Output

Running `mvn clean install` should produce output similar to:

```
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.cognizant.testing.UserControllerTest
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
[INFO] Running com.cognizant.testing.UserServiceTest
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
[INFO] Running com.cognizant.testing.UserIntegrationTest
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 11, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] BUILD SUCCESS
```

Starting the app with `mvn spring-boot:run` and hitting
`GET http://localhost:8080/users/1` should return:

```json
{ "id": 1, "name": "John Doe", "email": "john.doe@example.com" }
```

---

## 8. Import into Eclipse

1. Open Eclipse → **File → Import → Maven → Existing Maven Projects**.
2. Browse to the `SpringMockitoTesting` folder and select it.
3. Eclipse will detect `pom.xml` and import the project automatically.
4. Right-click the project → **Maven → Update Project** to force a dependency refresh.
5. Run tests via right-click on a test class → **Run As → JUnit Test**.

---

## 9. Import into IntelliJ IDEA

1. **File → Open** and select the `SpringMockitoTesting` folder (containing `pom.xml`).
2. IntelliJ auto-detects the Maven project and imports dependencies.
3. Ensure **Project SDK** is set to Java 8 (or a compatible JDK) under
   **File → Project Structure → Project**.
4. Run tests via right-click on `AllTests.java` → **Run 'AllTests'**.

---

## 10. Import into VS Code

1. Install the **Extension Pack for Java** and **Spring Boot Extension Pack** from the marketplace.
2. **File → Open Folder** and select `SpringMockitoTesting`.
3. VS Code detects the `pom.xml` and configures the Java project automatically.
4. Use the **Testing** sidebar icon (flask icon) to discover and run all JUnit 5 tests,
   or run `mvn clean test` from the integrated terminal.

---

## 11. Notes

- The H2 database is configured as an in-memory database (`schema.sql` / `data.sql`
  are loaded automatically on startup) purely to support the `@SpringBootTest`
  context in `UserIntegrationTest`; the service layer itself is mocked in
  Exercises 1 and 3, so no real database calls occur during those tests.
- `GlobalExceptionHandler` centralizes error responses and is not directly
  exercised by the three exercises, but is included for completeness and
  production-quality error handling.

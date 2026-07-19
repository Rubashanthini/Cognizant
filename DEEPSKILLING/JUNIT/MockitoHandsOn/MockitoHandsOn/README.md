# MockitoHandsOn

Cognizant Digital Nurture 5.0 — **Mockito Hands-On Exercises**, implemented as a complete, professional Maven project using Java 8, JUnit 5 (Jupiter), and Mockito 5.x.

---

## 1. Project Overview

This project contains a small service layer (`MyService`) that depends on an external dependency (`ExternalApi`). The dependency is never implemented for real in this project — instead, every test in `src/test/java` uses **Mockito** to create a mock of `ExternalApi`, so that `MyService` can be tested completely in isolation.

Each of the seven Mockito hands-on exercises is implemented as its own dedicated JUnit 5 test class.

---

## 2. Folder Structure

```
MockitoHandsOn/
├── pom.xml
├── README.md
└── src
    ├── main
    │   └── java
    │       └── com
    │           └── cognizant
    │               └── mockito
    │                   ├── api
    │                   │     └── ExternalApi.java
    │                   ├── service
    │                   │     └── MyService.java
    │                   ├── model
    │                   │     └── User.java
    │                   └── util
    │                         └── Utility.java
    └── test
        └── java
            └── com
                └── cognizant
                    └── mockito
                        ├── MyServiceTest.java
                        ├── VerifyInteractionTest.java
                        ├── ArgumentMatcherTest.java
                        ├── VoidMethodTest.java
                        ├── MultipleReturnTest.java
                        ├── InteractionOrderTest.java
                        └── VoidExceptionTest.java
```

---

## 3. Mockito Concepts Covered

### 3.1 Mocking (`MyServiceTest`)
A mock of `ExternalApi` is created with `Mockito.mock(ExternalApi.class)` and injected into `MyService`, allowing the service to be tested without any real external dependency.

### 3.2 Stubbing (`MyServiceTest`)
`when(mockApi.getData()).thenReturn("Mock Data")` configures the mock to return a predefined value whenever `getData()` is called.

### 3.3 Verification (`VerifyInteractionTest`)
- `verify(mockApi).getData()` — confirms the method was called.
- `verify(mockApi, times(1)).getData()` — confirms the exact number of invocations.
- `verifyNoMoreInteractions(mockApi)` — confirms no other interactions took place.

### 3.4 Argument Matchers (`ArgumentMatcherTest`)
- `any()`, `anyString()`, `anyInt()` — match arguments of any value/type.
- `eq()` — matches an exact value.
- `argThat()` — matches arguments using a custom predicate/condition.

### 3.5 Void Methods (`VoidMethodTest`)
- `doNothing().when(mockApi).saveData(anyString())` explicitly stubs a void method.
- `verify(mockApi).saveData("Test Data")` confirms the void method was invoked with the expected argument.

### 3.6 Multiple Returns (`MultipleReturnTest`)
- `when(mockApi.getStatus()).thenReturn("Active").thenReturn("Inactive").thenReturn("Disconnected")` configures different return values for consecutive calls, each of which is individually asserted.

### 3.7 Interaction Order (`InteractionOrderTest`)
- `InOrder inOrder = inOrder(mockApi);` followed by `inOrder.verify(mockApi).connect(); inOrder.verify(mockApi).getData(); inOrder.verify(mockApi).disconnect();` confirms methods were invoked in the exact expected sequence.

### 3.8 Exception Testing (`VoidExceptionTest`)
- `doThrow(new IllegalArgumentException(...)).when(mockApi).saveData(null)` stubs a void method to throw.
- `assertThrows(IllegalArgumentException.class, () -> service.processAndSave(null))` validates that the exception propagates as expected.

---

## 4. Maven Commands

Build the project and run all tests:

```bash
mvn clean install
```

Run only the tests:

```bash
mvn test
```

Run a single test class:

```bash
mvn -Dtest=MyServiceTest test
```

---

## 5. Importing the Project

### 5.1 Eclipse
1. `File` → `Import...` → `Maven` → `Existing Maven Projects`.
2. Browse to the `MockitoHandsOn` folder and select it.
3. Click `Finish`. Eclipse will download dependencies and configure the project automatically.
4. Right-click the project → `Run As` → `Maven test` to run the test suite.

### 5.2 IntelliJ IDEA
1. `File` → `Open...` and select the `MockitoHandsOn` folder (or the `pom.xml` file inside it).
2. IntelliJ will detect it as a Maven project and prompt to load it — accept/enable auto-import.
3. Once indexing completes, right-click the `src/test/java` folder → `Run 'All Tests'`.

### 5.3 VS Code
1. Install the **Extension Pack for Java** and **Test Runner for Java** extensions (if not already installed).
2. Open the `MockitoHandsOn` folder via `File` → `Open Folder...`.
3. VS Code will detect the Maven project automatically.
4. Use the Testing sidebar (flask icon) to run all tests, or run `mvn test` from the integrated terminal.

---

## 6. Running Tests

```bash
cd MockitoHandsOn
mvn clean test
```

---

## 7. Expected Output

Running `mvn clean test` should produce output similar to:

```
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.cognizant.mockito.MyServiceTest
[INFO] Running com.cognizant.mockito.VerifyInteractionTest
[INFO] Running com.cognizant.mockito.ArgumentMatcherTest
[INFO] Running com.cognizant.mockito.VoidMethodTest
[INFO] Running com.cognizant.mockito.MultipleReturnTest
[INFO] Running com.cognizant.mockito.InteractionOrderTest
[INFO] Running com.cognizant.mockito.VoidExceptionTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 11, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] BUILD SUCCESS
```

---

## 8. Tech Stack

| Component            | Version   |
|-----------------------|-----------|
| Java                   | 8         |
| JUnit Jupiter (API + Engine) | 5.10.2 |
| Mockito Core           | 5.11.0    |
| Mockito JUnit Jupiter  | 5.11.0    |
| Maven Compiler Plugin  | 3.13.0    |
| Maven Surefire Plugin  | 3.2.5     |

---

## 9. Notes

- All main-source classes (`ExternalApi`, `MyService`, `User`, `Utility`) live under the base package `com.cognizant.mockito`.
- Every test class mocks `ExternalApi` fresh in a `@BeforeEach` setup method, keeping tests independent and repeatable.
- No real network or I/O calls are made anywhere in this project — everything is mocked, making the test suite fast and deterministic.

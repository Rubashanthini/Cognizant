# JUnitAdvancedTesting

**Cognizant Digital Nurture 5.0 — Advanced JUnit Testing Exercises**

A complete, professional Maven project demonstrating advanced JUnit 5 (Jupiter) testing
techniques: Parameterized Tests, Test Suites, Test Execution Order, Exception Testing,
and Timeout/Performance Testing.

---

## 1. Project Overview

| Detail          | Value                          |
|-----------------|---------------------------------|
| Project Name    | JUnitAdvancedTesting            |
| Group Id        | com.cognizant                   |
| Artifact Id     | junit-advanced-testing          |
| Base Package    | com.cognizant.junit             |
| Java Version    | 8                                |
| Test Framework  | JUnit 5 (Jupiter) 5.10.2         |
| Build Tool      | Maven                           |

This project implements five exercises, each pairing a small production class
(under `src/main/java`) with a corresponding JUnit 5 test class (under `src/test/java`)
that demonstrates a specific advanced testing feature.

---

## 2. Folder Structure

```
JUnitAdvancedTesting/
├── pom.xml
├── README.md
└── src
    ├── main
    │   └── java
    │       └── com
    │           └── cognizant
    │               └── junit
    │                   ├── EvenChecker.java
    │                   ├── ExceptionThrower.java
    │                   ├── PerformanceTester.java
    │                   └── Utility.java
    └── test
        └── java
            └── com
                └── cognizant
                    └── junit
                        ├── EvenCheckerTest.java
                        ├── OrderedTests.java
                        ├── ExceptionThrowerTest.java
                        ├── PerformanceTesterTest.java
                        ├── BasicUtilityTest.java
                        └── AllTests.java
```

---

## 3. JUnit 5 Features Used

### 3.1 Parameterized Tests (Exercise 1)
- **Classes:** `EvenChecker.java`, `EvenCheckerTest.java`
- **Annotations:** `@ParameterizedTest`, `@ValueSource`
- `EvenChecker.isEven(int number)` is tested against the even values
  `2, 4, 6, 8, 10, 0, -2` (all expected `true`) and the odd values
  `1, 3, 5, 7, 9, -1, -3` (all expected `false`) — all without writing
  a separate `@Test` method for each value.

### 3.2 Test Suites (Exercise 2)
- **Class:** `AllTests.java`
- **Annotations:** `@Suite`, `@SelectClasses`
- Aggregates `EvenCheckerTest`, `OrderedTests`, `ExceptionThrowerTest`,
  `PerformanceTesterTest`, and `BasicUtilityTest` so all of them can be
  executed together from a single entry point.

### 3.3 Ordered Tests (Exercise 3)
- **Class:** `OrderedTests.java`
- **Annotations:** `@TestMethodOrder(MethodOrderer.OrderAnnotation.class)`, `@Order`
- Five test methods (`@Order(1)` through `@Order(5)`) record their execution
  sequence in a shared list; the final method asserts the recorded order
  exactly matches the declared `@Order` sequence.

### 3.4 Exception Testing (Exercise 4)
- **Classes:** `ExceptionThrower.java`, `ExceptionThrowerTest.java`
- **API:** `assertThrows()`
- `ExceptionThrower.throwException()` always throws `IllegalArgumentException`.
  The test validates both the exception **type** (`IllegalArgumentException.class`)
  and the exact exception **message**.

### 3.5 Timeout & Performance Testing (Exercise 5)
- **Classes:** `PerformanceTester.java`, `PerformanceTesterTest.java`
- **API:** `assertTimeout()`, `assertTimeoutPreemptively()`
- `PerformanceTester.performTask()` simulates a short-running task (~200ms
  via `Thread.sleep`). Tests confirm it finishes within a 1-second budget
  using both timeout assertion styles, and a dedicated test proves
  `assertTimeoutPreemptively()` correctly aborts/fails a task that
  deliberately overruns its allotted time.

### Bonus: Basic Utility Tests
- **Classes:** `Utility.java`, `BasicUtilityTest.java`
- Standard `assertEquals` / `assertTrue` / `assertFalse` / `assertThrows`
  style tests (organized with `@Nested`) for simple helper methods
  (`add`, `multiply`, `isPrime`, `reverse`, `isNullOrEmpty`), rounding
  out the suite with classic JUnit 5 assertions.

---

## 4. Maven Commands

Run these from the project root (the folder containing `pom.xml`):

```bash
# Compile the project
mvn clean compile

# Run all tests
mvn clean test

# Full build (compile + test + package into a JAR)
mvn clean install

# Run a single test class
mvn test -Dtest=EvenCheckerTest

# Run the aggregated test suite
mvn test -Dtest=AllTests
```

---

## 5. How to Import into Eclipse

1. Open Eclipse → **File → Import…**
2. Choose **Maven → Existing Maven Projects** → **Next**.
3. Click **Browse…**, select the `JUnitAdvancedTesting` folder, click **Finish**.
4. Eclipse will detect `pom.xml` and automatically download dependencies
   (requires internet access) and configure the build path.
5. Right-click the project → **Run As → Maven test** (or right-click any
   test class → **Run As → JUnit Test**).

---

## 6. How to Import into IntelliJ IDEA

1. Open IntelliJ IDEA → **File → Open…**
2. Select the `JUnitAdvancedTesting` folder (the one containing `pom.xml`)
   and click **OK**.
3. IntelliJ will detect it as a Maven project and prompt to **Load Maven Project** — accept it.
4. Wait for IntelliJ to index and download dependencies.
5. Right-click the `src/test/java` folder (or any individual test class)
   → **Run 'All Tests'** or **Run '<ClassName>'**.
6. Alternatively, open the **Maven** tool window (right sidebar) →
   **Lifecycle → test** (double-click).

---

## 7. How to Import into VS Code

1. Install the **Extension Pack for Java** and **Maven for Java** extensions
   from the VS Code Marketplace (if not already installed).
2. Open VS Code → **File → Open Folder…** → select `JUnitAdvancedTesting`.
3. VS Code will detect `pom.xml` and automatically load the Maven project
   (check the **Java Projects** panel in the Explorer sidebar).
4. Open the **Testing** panel (flask icon in the sidebar) to see all
   discovered JUnit 5 tests, or use **Ctrl+Shift+P → "Java: Run Tests"**.
5. Alternatively, open an integrated terminal (**Ctrl+`**) and run:
   ```bash
   mvn clean test
   ```

---

## 8. Running Tests

```bash
mvn clean install
```

This single command will:
1. Clean any previous build artifacts (`clean`).
2. Compile all classes in `src/main/java` and `src/test/java`.
3. Execute every test class via the **Maven Surefire Plugin**
   (`EvenCheckerTest`, `OrderedTests`, `ExceptionThrowerTest`,
   `PerformanceTesterTest`, `BasicUtilityTest`, and `AllTests`).
4. Package the compiled classes into `target/junit-advanced-testing.jar`
   if all tests pass.

> **Note:** An active internet connection is required the first time you
> build, so Maven can download the JUnit 5 dependencies (`junit-jupiter-api`,
> `junit-jupiter-engine`, `junit-jupiter-params`, `junit-platform-suite`,
> `junit-platform-launcher`) from Maven Central into your local `~/.m2` repository.

---

## 9. Expected Output

Running `mvn clean install` should produce output similar to:

```
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.cognizant.junit.AllTests
[INFO] Running com.cognizant.junit.BasicUtilityTest
[INFO] Running com.cognizant.junit.EvenCheckerTest
[INFO] Running com.cognizant.junit.ExceptionThrowerTest
[INFO] Running com.cognizant.junit.OrderedTests
[INFO] Running com.cognizant.junit.PerformanceTesterTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: XX, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] BUILD SUCCESS
[INFO] -------------------------------------------------------
```

All tests should pass (`BUILD SUCCESS`), and a JAR file will be generated at:

```
target/junit-advanced-testing.jar
```

---

## 10. Summary of Exercises

| # | Exercise                          | Production Class      | Test Class              | Key Annotations/APIs                                   |
|---|------------------------------------|------------------------|--------------------------|---------------------------------------------------------|
| 1 | Parameterized Tests                | `EvenChecker`          | `EvenCheckerTest`         | `@ParameterizedTest`, `@ValueSource`                     |
| 2 | Test Suites and Categories          | —                      | `AllTests`                | `@Suite`, `@SelectClasses`                               |
| 3 | Test Execution Order                | —                      | `OrderedTests`            | `@TestMethodOrder`, `@Order`                             |
| 4 | Exception Testing                   | `ExceptionThrower`     | `ExceptionThrowerTest`    | `assertThrows()`                                         |
| 5 | Timeout and Performance Testing      | `PerformanceTester`    | `PerformanceTesterTest`   | `assertTimeout()`, `assertTimeoutPreemptively()`         |

---

*Generated for Cognizant Digital Nurture 5.0 — Advanced JUnit Testing module.*

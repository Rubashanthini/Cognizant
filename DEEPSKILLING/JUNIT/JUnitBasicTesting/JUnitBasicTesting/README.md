# JUnitBasicTesting

Cognizant Digital Nurture 5.0 — **JUnit Basic Testing Exercises**

## Project Overview

This is a complete, self-contained Maven project that implements all four
JUnit Basic Testing exercises from the Cognizant Digital Nurture 5.0
curriculum:

1. **Exercise 1** — Setting up JUnit in a Maven project.
2. **Exercise 2** — Writing basic JUnit tests for simple Java classes
   (`Calculator`, `StringUtility`).
3. **Exercise 3** — Demonstrating all major JUnit assertions
   (`assertEquals`, `assertTrue`, `assertFalse`, `assertNull`,
   `assertNotNull`, `assertSame`, `assertNotSame`, `assertArrayEquals`,
   `assertNotEquals`).
4. **Exercise 4** — Organizing tests using the Arrange-Act-Assert (AAA)
   pattern, and using `@Before` / `@After` for test fixture setup and
   teardown.

- **Project Name:** JUnitBasicTesting
- **Group Id:** com.cognizant
- **Artifact Id:** junit-basic-testing
- **Base Package:** com.cognizant.junit
- **Java Version:** 8
- **JUnit Version:** 4.13.2
- **Build Tool:** Maven

## Folder Structure

```
JUnitBasicTesting/
├── pom.xml
├── README.md
└── src
    ├── main
    │   └── java
    │       └── com
    │           └── cognizant
    │               └── junit
    │                   ├── Calculator.java
    │                   ├── StringUtility.java
    │                   └── MathUtility.java
    └── test
        └── java
            └── com
                └── cognizant
                    └── junit
                        ├── CalculatorTest.java
                        ├── AssertionsTest.java
                        ├── AAAPatternTest.java
                        └── SetupTeardownTest.java
```

## Class Overview

| Class                | Type | Purpose                                                                 |
|----------------------|------|--------------------------------------------------------------------------|
| `Calculator`         | Main | add, subtract, multiply, divide                                          |
| `StringUtility`      | Main | reverse, isPalindrome                                                    |
| `MathUtility`        | Main | isEven, isPrime, factorial, squareArray, gcd, shared/new instance helpers|
| `CalculatorTest`     | Test | Basic tests for every method in `Calculator` and `StringUtility`        |
| `AssertionsTest`     | Test | Demonstrates all major JUnit assertion methods                          |
| `AAAPatternTest`     | Test | Demonstrates the Arrange-Act-Assert pattern                             |
| `SetupTeardownTest`  | Test | Demonstrates `@Before` / `@After` test fixture lifecycle                |

## Prerequisites

Before building or running this project, ensure you have the following
installed:

- **JDK 8** or later (`java -version` / `javac -version`)
- **Apache Maven 3.6+** (`mvn -version`)
- An IDE of your choice: Eclipse, IntelliJ IDEA, or VS Code (optional)

## Maven Commands

Run these commands from the root of the `JUnitBasicTesting` folder
(where `pom.xml` is located):

| Command                | Description                                            |
|-------------------------|--------------------------------------------------------|
| `mvn clean`             | Removes the `target` directory (previous build output) |
| `mvn compile`           | Compiles the main source code                          |
| `mvn test`              | Runs all JUnit test cases                               |
| `mvn clean install`     | Cleans, compiles, runs tests, and packages the JAR      |
| `mvn test -Dtest=ClassName` | Runs a single test class, e.g. `AssertionsTest`     |

To build the entire project and run all tests:

```bash
mvn clean install
```

## How to Import into Eclipse

1. Open Eclipse.
2. Go to **File → Import → Maven → Existing Maven Projects**.
3. Click **Next**, then **Browse** and select the `JUnitBasicTesting`
   folder (the one containing `pom.xml`).
4. Click **Finish**. Eclipse will import the project and download the
   JUnit dependency automatically.
5. Right-click the project → **Run As → Maven test** to run all tests,
   or right-click an individual test class → **Run As → JUnit Test**.

## How to Import into IntelliJ IDEA

1. Open IntelliJ IDEA.
2. Select **File → Open**.
3. Navigate to and select the `JUnitBasicTesting` folder (containing
   `pom.xml`), then click **OK**.
4. IntelliJ will automatically detect it as a Maven project and import
   all dependencies.
5. To run tests: right-click on the `src/test/java` folder (or an
   individual test class) and select **Run 'All Tests'** or
   **Run 'ClassNameTest'**.

## How to Import into VS Code

1. Install the **Extension Pack for Java** and **Maven for Java**
   extensions from the VS Code marketplace (if not already installed).
2. Open VS Code and select **File → Open Folder**, then choose the
   `JUnitBasicTesting` folder.
3. VS Code will automatically detect the `pom.xml` and configure the
   Java project.
4. Open the terminal in VS Code (`` Ctrl+` ``) and run:
   ```bash
   mvn clean install
   ```
5. Alternatively, use the **Testing** icon in the sidebar (flask icon)
   to view and run individual tests, or click the **Run Test** /
   **Debug Test** CodeLens links that appear above each `@Test` method.

## How to Run Tests

From the command line, in the project root directory:

```bash
mvn test
```

To run a specific test class:

```bash
mvn test -Dtest=AssertionsTest
```

To run the full build lifecycle (compile, test, package):

```bash
mvn clean install
```

## Expected Test Output

When you run `mvn clean install`, Maven's Surefire plugin will execute
all four test classes. You should see output similar to:

```
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running com.cognizant.junit.AAAPatternTest
Tests run: 6, Failures: 0, Errors: 0, Skipped: 0

Running com.cognizant.junit.AssertionsTest
Tests run: 9, Failures: 0, Errors: 0, Skipped: 0

Running com.cognizant.junit.CalculatorTest
Tests run: 15, Failures: 0, Errors: 0, Skipped: 0

Running com.cognizant.junit.SetupTeardownTest
Tests run: 6, Failures: 0, Errors: 0, Skipped: 0

Results:

Tests run: 36, Failures: 0, Errors: 0, Skipped: 0

BUILD SUCCESS
```

(Exact numbering/ordering may vary slightly depending on your Maven and
Surefire versions, but all tests should pass with **zero failures and
zero errors**.)

## Explanation of JUnit Assertions

JUnit assertions are used to verify that the actual outcome of a piece
of code matches the expected outcome. If an assertion fails, the test
fails and JUnit reports the mismatch.

| Assertion              | Purpose                                                                 |
|--------------------------|--------------------------------------------------------------------------|
| `assertEquals(expected, actual)` | Passes if `expected` and `actual` are equal.                    |
| `assertTrue(condition)`          | Passes if `condition` evaluates to `true`.                       |
| `assertFalse(condition)`         | Passes if `condition` evaluates to `false`.                      |
| `assertNull(object)`             | Passes if `object` is `null`.                                    |
| `assertNotNull(object)`          | Passes if `object` is **not** `null`.                             |
| `assertSame(expected, actual)`   | Passes if `expected` and `actual` refer to the **exact same object** (reference equality). |
| `assertNotSame(expected, actual)`| Passes if `expected` and `actual` refer to **different objects**, even if their contents are equal. |
| `assertArrayEquals(expected, actual)` | Passes if two arrays contain the same elements in the same order. |
| `assertNotEquals(unexpected, actual)` | Passes if `unexpected` and `actual` are **not** equal.       |

These assertions are demonstrated in `AssertionsTest.java`.

## Explanation of AAA Pattern

The **Arrange-Act-Assert (AAA)** pattern is a widely used convention for
structuring unit tests in a clear, readable, and consistent way:

1. **Arrange** — Set up the objects, input data, and any preconditions
   required for the test.
2. **Act** — Execute the method or behavior that is being tested.
3. **Assert** — Verify that the actual outcome matches the expected
   outcome.

Following this pattern makes tests easier to read, understand, and
maintain, because each test clearly separates *setup*, *execution*, and
*verification* into distinct, well-labeled sections. This pattern is
demonstrated in `AAAPatternTest.java`.

### Test Fixtures, Setup, and Teardown

JUnit provides two annotations to manage the lifecycle of test fixtures
(the shared objects and state used by tests):

- **`@Before`** — Marks a method that runs **before every** `@Test`
  method in the class. It is typically used to initialize objects,
  ensuring each test starts with a clean, predictable state.
- **`@After`** — Marks a method that runs **after every** `@Test`
  method in the class. It is typically used to release resources or
  reset state, ensuring no test affects another.

This setup/teardown lifecycle is demonstrated in
`SetupTeardownTest.java`, where `setUp()` (annotated with `@Before`)
initializes fresh `Calculator`, `StringUtility`, and `MathUtility`
instances before each test, and `tearDown()` (annotated with `@After`)
releases them after each test completes.

---

**Author:** Cognizant Digital Nurture 5.0 Training Program
**License:** For educational/training purposes only.

# MockitoAdvancedHandsOn

## Project Overview

This project is a complete, professional Maven implementation of the **Cognizant Digital Nurture 5.0 – Advanced Mockito Hands-On Exercises**. It demonstrates advanced unit testing techniques using **JUnit 5** and **Mockito 5.x**, covering repository mocking, REST API mocking, file I/O mocking, network interaction mocking, and stubbing of multiple consecutive return values.

- **Group ID:** `com.cognizant`
- **Artifact ID:** `mockito-advanced-handson`
- **Base Package:** `com.cognizant.mockito`
- **Java Version:** 8
- **Build Tool:** Maven
- **Testing Frameworks:** JUnit 5 (Jupiter), Mockito 5.x

---

## Folder Structure

```
MockitoAdvancedHandsOn/
├── pom.xml
├── README.md
└── src
    ├── main
    │   └── java
    │       └── com
    │           └── cognizant
    │               └── mockito
    │                   ├── repository
    │                   │     └── Repository.java
    │                   ├── rest
    │                   │     └── RestClient.java
    │                   ├── file
    │                   │     ├── FileReader.java
    │                   │     └── FileWriter.java
    │                   ├── network
    │                   │     └── NetworkClient.java
    │                   ├── service
    │                   │     ├── Service.java
    │                   │     ├── ApiService.java
    │                   │     ├── FileService.java
    │                   │     └── NetworkService.java
    │                   └── model
    │                         └── User.java
    │
    └── test
        └── java
            └── com
                └── cognizant
                    └── mockito
                        ├── ServiceTest.java
                        ├── ApiServiceTest.java
                        ├── FileServiceTest.java
                        ├── NetworkServiceTest.java
                        ├── MultiReturnServiceTest.java
                        └── AllTests.java
```

---

## Mockito Concepts Covered

### 1. Repository Mocking (Exercise 1)
- `Repository` interface is mocked using `mock(Repository.class)`.
- `getData()` is stubbed with `when(...).thenReturn(...)`.
- `Service.processData()` logic is verified using `assertEquals()`.
- The interaction with the mock is verified using `verify(mockRepository, times(1)).getData()`.

### 2. REST API Mocking (Exercise 2)
- `RestClient` interface is mocked to simulate calls to an external RESTful API.
- `getResponse()` is stubbed to return predefined mock responses.
- `ApiService.fetchData()` output is verified against the expected processed value.
- Interaction verification confirms the REST client method was called exactly once.

### 3. File I/O Mocking (Exercise 3)
- Both `FileReader` and `FileWriter` interfaces are mocked.
- `FileReader.read()` is stubbed to simulate reading file content.
- `FileService.processFile()` reads, processes, and writes content.
- Both read and write interactions are verified, and the final processed content is validated.

### 4. Network Mocking (Exercise 4)
- `NetworkClient` interface is mocked to simulate network connections.
- `connect()` is stubbed to return a predefined mock connection string.
- `NetworkService.connectToServer()` result is verified.
- Interaction order is verified where appropriate using Mockito's `InOrder` API.

### 5. Multiple Return Values (Exercise 5)
- `Repository.getData()` is stubbed with chained `thenReturn()` calls to return different values on consecutive invocations:
  ```java
  when(repository.getData())
      .thenReturn("First Mock Data")
      .thenReturn("Second Mock Data");
  ```
- Both the first and second returned/processed values are verified using `assertEquals()`.
- The total interaction count is verified using `verify(mockRepository, times(2)).getData()`.

### Verification
Across all exercises, Mockito's `verify()` API is used in combination with `times()` to confirm:
- The exact number of times a mocked method was invoked.
- That the correct arguments were passed to mocked methods (e.g., `FileWriter.write(...)`).
- The order of invocations where relevant, using `InOrder`.

---

## Maven Commands

| Command | Description |
|---|---|
| `mvn clean` | Removes the `target/` build directory. |
| `mvn compile` | Compiles the main source code. |
| `mvn test` | Runs all unit tests using Surefire. |
| `mvn clean install` | Cleans, compiles, runs tests, and installs the artifact into the local repository. |
| `mvn package` | Builds the project and packages it into a JAR file. |

---

## Running Tests

To run the full test suite from the command line, navigate to the project root directory (where `pom.xml` is located) and run:

```bash
mvn clean test
```

To build the entire project, including running all tests and installing the artifact locally:

```bash
mvn clean install
```

To run a single test class:

```bash
mvn -Dtest=ServiceTest test
```

To run the aggregated suite (from an IDE supporting the JUnit Platform Suite engine):

```bash
mvn -Dtest=AllTests test
```

---

## Expected Output

When running `mvn clean test`, all six test classes should execute successfully, and Maven Surefire should report a result similar to:

```
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.cognizant.mockito.ApiServiceTest
[INFO] Running com.cognizant.mockito.FileServiceTest
[INFO] Running com.cognizant.mockito.MultiReturnServiceTest
[INFO] Running com.cognizant.mockito.NetworkServiceTest
[INFO] Running com.cognizant.mockito.ServiceTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 7, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] BUILD SUCCESS
```

---

## Import into Eclipse

1. Open Eclipse IDE.
2. Go to **File → Import...**
3. Select **Maven → Existing Maven Projects**, then click **Next**.
4. Click **Browse** and select the `MockitoAdvancedHandsOn` project root folder (the one containing `pom.xml`).
5. Ensure the project (`mockito-advanced-handson`) is checked, then click **Finish**.
6. Eclipse will automatically download dependencies via the Maven build.
7. Right-click the project → **Run As → Maven test** to execute all tests.

---

## Import into IntelliJ IDEA

1. Open IntelliJ IDEA.
2. Select **File → Open...**
3. Navigate to and select the `MockitoAdvancedHandsOn` folder (containing `pom.xml`), then click **OK**.
4. IntelliJ will detect the Maven project automatically and prompt to load it — click **Load Maven Project** (or it may load automatically).
5. Wait for IntelliJ to download all dependencies and index the project.
6. Right-click on the `src/test/java` folder → **Run 'All Tests'**, or right-click any individual test class and select **Run**.

---

## Import into VS Code

1. Open VS Code.
2. Install the **Extension Pack for Java** and **Test Runner for Java** extensions from the marketplace (if not already installed).
3. Go to **File → Open Folder...** and select the `MockitoAdvancedHandsOn` folder.
4. VS Code will automatically detect the `pom.xml` file and configure the Java project.
5. Open the **Testing** panel (flask icon) in the sidebar to view and run all discovered JUnit 5 tests.
6. Alternatively, open the integrated terminal (**Terminal → New Terminal**) and run:
   ```bash
   mvn clean test
   ```

---

## Tech Stack Summary

| Component | Version |
|---|---|
| Java | 8 |
| Maven Compiler Plugin | 3.11.0 |
| Maven Surefire Plugin | 3.2.5 |
| JUnit Jupiter (API & Engine) | 5.10.2 |
| JUnit Platform Suite | 1.10.2 |
| Mockito Core | 5.11.0 |
| Mockito JUnit Jupiter | 5.11.0 |

---

*Prepared as part of the Cognizant Digital Nurture 5.0 program — Advanced Mockito Hands-On Exercises.*

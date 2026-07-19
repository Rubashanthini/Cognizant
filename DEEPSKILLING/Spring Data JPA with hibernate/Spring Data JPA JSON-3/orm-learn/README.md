# orm-learn — Spring Data JPA Hands-on Project

**Cognizant Digital Nurture 5.0 — Spring Data JPA Hands-on Assignment**

A complete Maven / Spring Boot project demonstrating HQL, JPQL, Native SQL and the
Criteria API against a MySQL database, built with Spring Data JPA + Hibernate.

---

## 1. Project Overview

| Hands-on | Topic | Key Classes |
|---|---|---|
| 1 | Introduction to HQL and JPQL | `EmployeeRepository`, `EmployeeService` |
| 2 | Permanent Employees — JOIN vs FETCH JOIN | `EmployeeRepository`, `EmployeeService` |
| 3 | Quiz Application — multi-entity FETCH JOIN | `AttemptRepository`, `QuizService` |
| 4 | Average Salary — Aggregate functions, `@Param` | `EmployeeRepository`, `EmployeeService` |
| 5 | Native Query | `EmployeeRepository`, `EmployeeService` |
| 6 | Criteria API — dynamic Product search | `ProductService`, `ProductController` |

---

## 2. Folder Structure

```
orm-learn/
├── pom.xml
├── README.md
└── src
    ├── main
    │   ├── java/com/cognizant/ormlearn
    │   │   ├── OrmLearnApplication.java      (main class + CommandLineRunner demo)
    │   │   ├── model/                        (JPA entities)
    │   │   │   ├── Employee.java, Department.java, Skill.java, Product.java
    │   │   │   └── User.java, Question.java, Option.java, Attempt.java,
    │   │   │       AttemptQuestion.java, AttemptOption.java
    │   │   ├── repository/                   (Spring Data JPA repositories)
    │   │   ├── service/                      (business logic, HQL/native/criteria calls)
    │   │   ├── controller/                   (REST endpoints)
    │   │   ├── exception/                    (ResourceNotFoundException, GlobalExceptionHandler)
    │   │   └── util/                         (ProductSearchCriteria DTO)
    │   └── resources
    │       ├── application.properties
    │       ├── schema.sql
    │       └── data.sql
    └── test/java/com/cognizant/ormlearn
        └── OrmLearnApplicationTests.java
```

---

## 3. Database Setup

1. Install MySQL 8 and ensure the server is running on `localhost:3306`.
2. Create the schema manually **or** let the app create it:
   - The `application.properties` file is configured with
     `spring.jpa.hibernate.ddl-auto=update`, so Hibernate will create/update
     tables automatically from the entity classes on startup.
   - If you prefer to run `schema.sql` / `data.sql` yourself instead, either:
     - execute both files manually against your MySQL instance, **or**
     - set `spring.jpa.hibernate.ddl-auto=none`, uncomment
       `spring.jpa.defer-datasource-initialization=true`, and set
       `spring.sql.init.mode=always` in `application.properties`.
3. Update credentials in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.username=root
   spring.datasource.password=YOUR_MYSQL_PASSWORD
   ```
   The database `orm_learn_db` is auto-created (`createDatabaseIfNotExist=true`).

---

## 4. Execution Steps

### Import into an IDE
- **Eclipse**: File → Import → Maven → Existing Maven Projects → select `orm-learn` folder.
- **IntelliJ IDEA**: File → Open → select the `orm-learn` folder (auto-detects `pom.xml`).
- **VS Code**: Open Folder → `orm-learn` (requires the "Extension Pack for Java").

### Build
```bash
cd orm-learn
mvn clean install
```

### Run
```bash
mvn spring-boot:run
```
or run `OrmLearnApplication.java` directly from your IDE.

### Run Tests
```bash
mvn test
```

---

## 5. Expected Output

On startup, `OrmLearnApplication` (which implements `CommandLineRunner`) automatically
executes a demo of every hands-on and logs the results, e.g.:

```
----- HANDS-ON 1: Introduction to HQL and JPQL -----
Employee -> id=1, name=Arun Kumar, salary=75000.0
...
----- HANDS-ON 2: Get all Permanent Employees using HQL -----
>> Simple WHERE clause:
Permanent Employee -> Arun Kumar
>> Using normal JOIN:
Permanent Employee (JOIN) -> Arun Kumar
>> Using JOIN FETCH (optimized, avoids N+1):
Employee: Arun Kumar | Dept: Engineering | Skills: [Java, Spring Boot, Hibernate]
...
----- HANDS-ON 3: Quiz Application -----
================ QUIZ RESULT ================
User      : rahul.dev
Attempt ID: 1
Score     : 66.67
1. What does JPA stand for?
     -> Java Persistence API [CORRECT]
...
----- HANDS-ON 4: Average Salary using HQL -----
Average Salary (all employees) = 70125.0
----- HANDS-ON 5: Native Query -----
Total employees (native) = 8
----- HANDS-ON 6: Criteria Query - Dynamic Product Search -----
Product Match -> MacBook Air M2 | RAM=16GB | OS=macOS | Price=114900.0
```

### REST Endpoints (once running on `http://localhost:8080`)

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/employees` | All employees (HQL) |
| GET | `/api/employees/permanent` | Permanent employees (WHERE clause) |
| GET | `/api/employees/permanent/join` | Permanent employees (JOIN) |
| GET | `/api/employees/permanent/fetch-join` | Permanent employees (JOIN FETCH) |
| GET | `/api/employees/average-salary` | Average salary — all employees |
| GET | `/api/employees/average-salary/department/{deptId}` | Average salary — by department |
| GET | `/api/employees/native` | All employees (native SQL) |
| GET | `/api/quiz/result/{userId}/{attemptId}` | Quiz result (multi-entity FETCH JOIN) |
| GET | `/api/products/search?ram=16&os=Windows%2011` | Criteria API dynamic product search |

---

## 6. Screenshots Placeholder

> _Insert IDE console output / Postman screenshots here after running the project locally._

- `screenshots/handson1-console-output.png`
- `screenshots/handson2-fetch-join-sql.png`
- `screenshots/handson3-quiz-result.png`
- `screenshots/handson4-average-salary.png`
- `screenshots/handson5-native-query.png`
- `screenshots/handson6-criteria-search.png`

---

## 7. Notes on Key Concepts

- **HQL vs JPQL**: HQL is Hibernate's own query language (superset of JPQL) while JPQL
  is the JPA-specification-standard, portable query language. Spring Data JPA's `@Query`
  (without `nativeQuery = true`) accepts both, since Hibernate is the underlying JPA provider.
- **JOIN vs JOIN FETCH**: a plain `JOIN` only affects filtering; the associated entity
  stays a lazy proxy (extra SELECTs when accessed later → N+1 problem). `JOIN FETCH`
  eagerly populates the association in the same SQL query, avoiding N+1 selects.
- **Native Query**: raw SQL executed directly against the underlying tables/columns —
  no HQL/JPQL translation, so it's DB-specific.
- **Criteria API**: a fully programmatic, type-safe query-building API
  (`CriteriaBuilder` → `CriteriaQuery` → `Root` → `Predicate` → `TypedQuery`), ideal for
  building queries whose filters are only known at runtime (e.g. optional search filters).

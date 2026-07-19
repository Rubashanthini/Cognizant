# Employee Management System

**Cognizant Digital Nurture 5.0 — "Spring Data JPA and Hibernate" exercises (1–10)**

A complete Spring Boot + Spring Data JPA + Hibernate + H2 REST application implementing every exercise in the module: entity mapping, repositories, CRUD, derived/@Query/@NamedQuery methods, pagination & sorting, JPA auditing, projections, multi-datasource configuration, and Hibernate-specific batch/performance tuning.

---

## 1. Project Overview

| | |
|---|---|
| **Project name** | EmployeeManagementSystem |
| **Group Id** | com.cognizant |
| **Artifact Id** | employee-management-system |
| **Base package** | com.cognizant.employeesystem |
| **Java** | 8 |
| **Spring Boot** | 2.7.18 |
| **Database** | H2 (in-memory) |
| **Build tool** | Maven |

The application manages **Employees** and **Departments**, where each Department has many Employees (one-to-many / many-to-one).

---

## 2. Folder Structure

```
EmployeeManagementSystem/
├── pom.xml
├── README.md
└── src
    ├── main
    │   ├── java/com/cognizant/employeesystem
    │   │   ├── EmployeeManagementSystemApplication.java
    │   │   ├── audit
    │   │   │   ├── entity/AuditLog.java
    │   │   │   └── repository/AuditLogRepository.java
    │   │   ├── config
    │   │   │   ├── AuditorAwareImpl.java
    │   │   │   ├── JpaAuditingConfig.java
    │   │   │   ├── PrimaryDataSourceConfig.java
    │   │   │   └── SecondaryDataSourceConfig.java
    │   │   ├── controller
    │   │   │   ├── DepartmentController.java
    │   │   │   └── EmployeeController.java
    │   │   ├── dto
    │   │   │   ├── DepartmentDTO.java
    │   │   │   └── EmployeeDTO.java
    │   │   ├── entity
    │   │   │   ├── Auditable.java
    │   │   │   ├── Department.java
    │   │   │   └── Employee.java
    │   │   ├── exception
    │   │   │   ├── ErrorResponse.java
    │   │   │   ├── GlobalExceptionHandler.java
    │   │   │   └── ResourceNotFoundException.java
    │   │   ├── projection
    │   │   │   ├── EmployeeDepartmentView.java
    │   │   │   ├── EmployeeNameOnly.java
    │   │   │   └── EmployeeSummary.java
    │   │   ├── repository
    │   │   │   ├── DepartmentRepository.java
    │   │   │   └── EmployeeRepository.java
    │   │   └── service
    │   │       ├── AuditService.java
    │   │       ├── DepartmentService.java
    │   │       ├── EmployeeService.java
    │   │       └── impl
    │   │           ├── DepartmentServiceImpl.java
    │   │           └── EmployeeServiceImpl.java
    │   └── resources
    │       ├── application.properties
    │       ├── schema.sql
    │       └── data.sql
    └── test
        └── java/com/cognizant/employeesystem
            └── EmployeeManagementSystemApplicationTests.java
```

---

## 3. Features Implemented (Exercise Map)

| Exercise | Topic | Where |
|---|---|---|
| 1 | Spring Boot project, Maven, H2, application.properties | `pom.xml`, `application.properties` |
| 2 | Employee / Department entities, `@Entity/@Table/@Id/@GeneratedValue/@Column/@OneToMany/@ManyToOne` | `entity/*` |
| 3 | `EmployeeRepository`, `DepartmentRepository` extending `JpaRepository`, derived queries | `repository/*` |
| 4 | Full CRUD via services + REST controllers | `service/*`, `controller/*` |
| 5 | Derived query methods, `@Query`, `@NamedQuery`/`@NamedQueries` | `repository/*`, `entity/*` |
| 6 | Pagination & sorting — `Page`, `Pageable`, `PageRequest`, `Sort` | `EmployeeController` (`/page-sort`, `/sort`), `EmployeeServiceImpl` |
| 7 | JPA Auditing — `@EnableJpaAuditing`, `@CreatedDate/@LastModifiedDate/@CreatedBy/@LastModifiedBy`, `AuditingEntityListener`, `AuditorAware` | `entity/Auditable.java`, `config/JpaAuditingConfig.java`, `config/AuditorAwareImpl.java` |
| 8 | Projections — interface, class/DTO, `@Value`, constructor expressions | `projection/*`, `EmployeeRepository` |
| 9 | Customized/externalized data source config, multiple data sources | `config/PrimaryDataSourceConfig.java`, `config/SecondaryDataSourceConfig.java`, `audit/*` |
| 10 | Hibernate-specific annotations, dialect, batch processing | `entity/Employee.java` (`@DynamicInsert/@DynamicUpdate/@BatchSize`), `application.properties`, `EmployeeServiceImpl.createEmployeesInBatch()` |

### Notes on Exercise 9 (Multiple Data Sources)
Two independent H2 databases are configured:
* **Primary (`testdb`)** — holds `Employee` / `Department`, wired explicitly in `PrimaryDataSourceConfig` (`@Primary` beans named `dataSource`, `entityManagerFactory`, `transactionManager`).
* **Secondary (`auditdb`)** — holds `AuditLog` records, wired independently in `SecondaryDataSourceConfig` (`secondaryDataSource`, `secondaryEntityManagerFactory`, `secondaryTransactionManager`).

Every Employee/Department create/update/delete call also writes an audit trail row into the **secondary** database via `AuditService`, running in its own transaction (`@Transactional("secondaryTransactionManager")`). Because the two data sources are non-XA, the audit write is **not** atomic with the primary write — this is a deliberate, documented simplification for the exercise, not a bug.

### Notes on Exercise 10 (Batch Processing)
`hibernate.jdbc.batch_size`, `hibernate.order_inserts` and `hibernate.order_updates` are set both in `application.properties` and in `PrimaryDataSourceConfig`. `EmployeeServiceImpl.createEmployeesInBatch()` (exposed via `POST /employees/batch`) uses `repository.saveAll(...)` to demonstrate Hibernate grouping the resulting INSERT statements into JDBC batches instead of one round-trip per row.

---

## 4. Prerequisites

* **JDK 8** (or higher, compiled with `--release 8` compatibility)
* **Maven 3.6+**
* An IDE: Eclipse, IntelliJ IDEA, or VS Code (all supported — see below)
* Internet access the first time you build, so Maven can download dependencies from Maven Central

---

## 5. How to Import

### Eclipse
1. `File → Import → Maven → Existing Maven Projects`
2. Browse to the `EmployeeManagementSystem` folder and select it
3. Eclipse resolves dependencies from `pom.xml` automatically
4. Right-click the project → `Run As → Maven Build...` → Goals: `spring-boot:run`
5. **Lombok**: run the Lombok installer once (`java -jar lombok.jar`) and point it at your Eclipse installation so `@Getter/@Setter/@Data/@Builder` etc. are recognized by the Eclipse compiler

### IntelliJ IDEA
1. `File → Open` and select the `EmployeeManagementSystem` folder (or the `pom.xml` file)
2. IntelliJ detects it as a Maven project and imports it automatically
3. Enable annotation processing: `Settings → Build, Execution, Deployment → Compiler → Annotation Processors → Enable annotation processing` (needed for Lombok)
4. Install the **Lombok plugin** from the JetBrains Marketplace if not already installed
5. Run `EmployeeManagementSystemApplication.main()` or use the Maven side panel → `Lifecycle → spring-boot:run`

### VS Code
1. Install the **Extension Pack for Java** and the **Spring Boot Extension Pack**
2. `File → Open Folder` and select `EmployeeManagementSystem`
3. VS Code's Java extension automatically picks up the Maven project
4. Install the **Lombok Annotations Support for VS Code** extension (or ensure `lombok.jar` is on the annotation processor path — recent versions of the Java extension bundle this automatically)
5. Use the Run/Debug panel on `EmployeeManagementSystemApplication`, or open a terminal and run the Maven commands below

---

## 6. Run Commands

```bash
# Build (compiles, runs tests, packages the jar)
mvn clean install

# Run the application
mvn spring-boot:run

# Or, after building, run the packaged jar directly
java -jar target/employee-management-system.jar
```

The application starts on **http://localhost:8080**.

H2 console (browse the live in-memory primary database): **http://localhost:8080/h2-console**
* JDBC URL: `jdbc:h2:mem:testdb`
* Username: `sa`
* Password: `password`

---

## 7. API Documentation

### Employee

| Method | Endpoint | Description |
|---|---|---|
| GET | `/employees` | List all employees |
| GET | `/employees?page=0&size=5` | Paginated list (Exercise 6) |
| GET | `/employees/{id}` | Get one employee |
| POST | `/employees` | Create an employee |
| PUT | `/employees/{id}` | Update an employee |
| DELETE | `/employees/{id}` | Delete an employee |
| GET | `/employees/search?keyword=` | Search by name/email substring |
| GET | `/employees/department/{departmentId}` | Employees in a department (by id) |
| GET | `/employees/department-name/{departmentName}` | Employees in a department (by name, via `@NamedQuery`) |
| GET | `/employees/unassigned` | Employees with no department |
| GET | `/employees/sort?field=name&direction=asc` | Sorted list (Exercise 6) |
| GET | `/employees/page-sort?page=0&size=5&sort=name` | Paginated + sorted (Exercise 6) |
| GET | `/employees/search-paged?keyword=al&page=0&size=5&sort=name` | Paginated search |
| GET | `/employees/projections/names/{departmentId}` | Interface projection (name/email only) |
| GET | `/employees/projections/department-view` | Interface projection with nested SpEL property |
| GET | `/employees/projections/summaries` | Class/DTO projection via constructor expression |
| POST | `/employees/batch` | Batch-insert employees (Hibernate batching demo) |

### Department

| Method | Endpoint | Description |
|---|---|---|
| GET | `/departments` | List all departments |
| GET | `/departments?page=0&size=5` | Paginated list |
| GET | `/departments/{id}` | Get one department |
| POST | `/departments` | Create a department |
| PUT | `/departments/{id}` | Update a department |
| DELETE | `/departments/{id}` | Delete a department |
| GET | `/departments/search?keyword=` | Search by name substring |
| GET | `/departments/by-name/{name}` | Exact lookup (case-insensitive, via `@NamedQuery`) |
| GET | `/departments/min-employees/{count}` | Departments with more than N employees |

---

## 8. Sample Requests & Responses

**Create an employee**

```http
POST /employees
Content-Type: application/json

{
  "name": "Karen White",
  "email": "karen.white@cognizant.com",
  "departmentId": 1
}
```

Response `201 Created`:
```json
{
  "id": 11,
  "name": "Karen White",
  "email": "karen.white@cognizant.com",
  "departmentId": 1,
  "departmentName": "Engineering"
}
```

**Get a page of employees sorted by name**

```http
GET /employees/page-sort?page=0&size=3&sort=name
```

Response `200 OK`:
```json
{
  "content": [
    { "id": 1, "name": "Alice Johnson", "email": "alice.johnson@cognizant.com", "departmentId": 1, "departmentName": "Engineering" },
    { "id": 2, "name": "Bob Smith", "email": "bob.smith@cognizant.com", "departmentId": 1, "departmentName": "Engineering" },
    { "id": 4, "name": "Carol Williams", "email": "carol.williams@cognizant.com", "departmentId": 2, "departmentName": "Human Resources" }
  ],
  "pageable": { "pageNumber": 0, "pageSize": 3, "sort": { "sorted": true } },
  "totalElements": 10,
  "totalPages": 4
}
```

**Class/DTO projection**

```http
GET /employees/projections/summaries
```

Response `200 OK`:
```json
[
  { "id": 1, "name": "Alice Johnson", "email": "alice.johnson@cognizant.com" },
  { "id": 2, "name": "Bob Smith", "email": "bob.smith@cognizant.com" }
]
```

**Resource not found**

```http
GET /employees/999
```

Response `404 Not Found`:
```json
{
  "timestamp": "2026-07-08T10:15:30",
  "status": 404,
  "error": "Not Found",
  "message": "Employee not found with id : '999'",
  "path": "/employees/999"
}
```

---

## 9. Expected Output

On `mvn spring-boot:run`, the console shows Spring Boot's banner, Hibernate initializing two persistence units (`primary`, `secondary`), `schema.sql`/`data.sql` executing against the primary H2 database (10 employees, 4 departments), and Tomcat starting on port 8080. All endpoints listed above are then reachable via `curl`, Postman, or a browser (for GET requests) at `http://localhost:8080`.

---

## 10. Tech Stack

* Java 8
* Spring Boot 2.7.18
* Spring Web (REST controllers)
* Spring Data JPA + Hibernate (ORM)
* H2 Database (in-memory)
* Lombok (boilerplate reduction)
* Bean Validation (`spring-boot-starter-validation`)
* Maven (build)

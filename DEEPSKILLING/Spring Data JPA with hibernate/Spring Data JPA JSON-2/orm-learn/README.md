# orm-learn

Cognizant Digital Nurture 5.0 — Spring Data JPA Hands-on Project

## Project Overview

This project implements all six Spring Data JPA hands-on exercises from the
Cognizant Digital Nurture 5.0 curriculum in a single Spring Boot application:

1. **Hands-on 1** — `Country` entity + Spring Data JPA query methods
   (contains, contains + order by asc, starts with).
2. **Hands-on 2** — `Stock` entity + query methods (Facebook Sept 2019,
   Google close price > 1250, top 3 highest volumes, lowest 3 Netflix prices).
3. **Hands-on 3** — `Employee`, `Department`, `Skill` entities with full JPA
   annotations, constructors, getters/setters, `toString()`.
4. **Hands-on 4** — `@ManyToOne` Employee → Department, plus
   `EmployeeService` / `DepartmentService` / `SkillService` with `get()`,
   `save()`, and test methods `testGetEmployee()`, `testAddEmployee()`,
   `testUpdateEmployee()`.
5. **Hands-on 5** — `@OneToMany(mappedBy = "department")` Department →
   Employees. LAZY was tried first (throws `LazyInitializationException`
   when the collection is accessed outside the session — see comments in
   `Department.java`), then corrected to `FetchType.EAGER`.
6. **Hands-on 6** — `@ManyToMany` Employee ↔ Skill via `@JoinTable`
   (`employee_skill`). LAZY was tried first (throws
   `LazyInitializationException` — see comments in `Employee.java`), then
   corrected to `FetchType.EAGER`. Includes `testGetEmployee()` (with
   skills) and `testAddSkillToEmployee()`.

## Folder Structure

```
orm-learn/
├── pom.xml
├── README.md
└── src
    └── main
        ├── java
        │   └── com/cognizant/ormlearn
        │       ├── OrmLearnApplication.java
        │       ├── model
        │       │   ├── Country.java
        │       │   ├── Stock.java
        │       │   ├── Employee.java
        │       │   ├── Department.java
        │       │   └── Skill.java
        │       ├── repository
        │       │   ├── CountryRepository.java
        │       │   ├── StockRepository.java
        │       │   ├── EmployeeRepository.java
        │       │   ├── DepartmentRepository.java
        │       │   └── SkillRepository.java
        │       ├── service
        │       │   ├── CountryService.java
        │       │   ├── StockService.java
        │       │   ├── EmployeeService.java
        │       │   ├── DepartmentService.java
        │       │   └── SkillService.java
        │       └── exception
        │           └── ResourceNotFoundException.java
        └── resources
            ├── application.properties
            ├── schema.sql
            └── data.sql
```

## Database Setup

### 1. Install / Start MySQL 8

Make sure MySQL 8 Server is running locally on port `3306`.

### 2. Update credentials

Edit `src/main/resources/application.properties` and replace:

```properties
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

with your actual MySQL username/password.

### 3. Database creation

The datasource URL already includes `createDatabaseIfNotExist=true`, so the
`ormlearn` schema is created automatically on first connection:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ormlearn?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&createDatabaseIfNotExist=true
```

### How to create the schema

`schema.sql` runs automatically on application startup (Spring Boot SQL
initialization is enabled via `spring.sql.init.mode=always`). It drops and
re-creates: `country`, `stock`, `department`, `employee`, `skill`,
`employee_skill`.

### How to import data

`data.sql` also runs automatically on startup (after `schema.sql`, and after
Hibernate validates the entities, thanks to
`spring.jpa.defer-datasource-initialization=true`). It seeds all sample rows
required for every test case in this project.

If you prefer to run the scripts manually instead:

```bash
mysql -u root -p ormlearn < src/main/resources/schema.sql
mysql -u root -p ormlearn < src/main/resources/data.sql
```

(and in that case set `spring.jpa.hibernate.ddl-auto=none` and
`spring.sql.init.mode=never` to avoid re-running them on every startup).

## How to Run the Project

```bash
mvn clean install
mvn spring-boot:run
```

On startup, `OrmLearnApplication` (a `CommandLineRunner`) automatically
executes every hands-on test method and prints the results to the console.

## Expected Outputs (summary)

- **Hands-on 1**: lists of countries matching each query method.
- **Hands-on 2**: Facebook Sept-2019 rows, Google rows above 1250, top 3
  highest-volume rows, lowest 3 Netflix closing-price rows.
- **Hands-on 4**: employee fetched by id, new employee saved with a
  generated id, employee salary updated.
- **Hands-on 5**: department printed together with its list of employees
  (no `LazyInitializationException` in the final EAGER version).
- **Hands-on 6**: employee printed together with its skill set, and the
  employee's skill set after adding a new skill.

## Screenshots

_Add console output / IDE screenshots here after running the project._

- [ ] Hands-on 1 output
- [ ] Hands-on 2 output
- [ ] Hands-on 4 output
- [ ] Hands-on 5 output (LazyInitializationException + corrected EAGER output)
- [ ] Hands-on 6 output (LazyInitializationException + corrected EAGER output)

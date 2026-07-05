# orm-learn

Cognizant Digital Nurture 5.0 — Deep Skilling Engineering Concepts
**Spring Data JPA Hands-on** solution project.

## Project description

A single Spring Boot + Maven project implementing all hands-on
exercises from the "Spring Data JPA" hands-on document:

| Hands-on | Topic | Where to look |
|---|---|---|
| 1 | Spring Data JPA quick example, Country entity/repository/service, logging | `model.Country`, `repository.CountryRepository`, `service.CountryService`, `OrmLearnApplication` |
| 2 | Hibernate XML Configuration (SessionFactory/Session/Transaction CRUD) | `hibernatexml.*` |
| 3 | Hibernate Annotation Configuration | `hibernateannotation.*` |
| 4 | JPA vs Hibernate vs Spring Data JPA comparison | `comparison.*` |
| 5 | Full country list population + Spring Data JPA query methods | `resources/data.sql`, `repository.CountryRepository` |
| 6 | Find country by code (+ `CountryNotFoundException`) | `service.CountryService#findCountryByCode` |
| 7 | Add country | `service.CountryService#addCountry` |
| 8 | Update country | `service.CountryService#updateCountry` |
| 9 | Delete country | `service.CountryService#deleteCountry` |

A REST API (`controller.CountryController`) is also included on top of
the above, exposing the Country CRUD/search operations over HTTP.

## Prerequisites

- JDK 8
- Maven 3.6+
- MySQL Server 8.0 (+ MySQL Workbench, optional)
- Eclipse / IntelliJ IDEA / VS Code (any one)
- Internet/proxy access to Maven Central to download dependencies

## MySQL setup / database creation

```sql
mysql -u root -p
mysql> create schema ormlearn;
```

`src/main/resources/application.properties` is already configured for:

```
spring.datasource.url=jdbc:mysql://localhost:3306/ormlearn
spring.datasource.username=root
spring.datasource.password=root
```

Update the username/password/host/port there if your local MySQL differs.

On startup, `spring.jpa.hibernate.ddl-auto=update` plus
`schema.sql`/`data.sql` (run because `spring.sql.init.mode=always`) will:
1. Create the `country` table if it doesn't already exist.
2. Load all ~249 countries into it (using `INSERT IGNORE`, so re-running
   on an already-populated table is safe).

If you'd rather manage the schema by hand exactly as Hands-on 1
describes, switch `spring.jpa.hibernate.ddl-auto` to `validate` and
create the table yourself first:

```sql
create table country(co_code varchar(2) primary key, co_name varchar(50));
insert into country values ('IN', 'India');
insert into country values ('US', 'United States of America');
```

## Maven commands

```bash
mvn clean install      # compile, run tests, package the jar
mvn clean package -DskipTests   # skip tests (useful if MySQL isn't running yet)
mvn spring-boot:run     # run the application directly
```

> If your network requires a proxy (as in the original exercise doc),
> add the proxy flags, e.g.:
> `mvn clean install -Dhttp.proxyHost=<host> -Dhttp.proxyPort=<port>`

## Importing into an IDE

### Eclipse
`File > Import > Maven > Existing Maven Projects` → browse to the
`orm-learn` folder → Finish.

### IntelliJ IDEA
`File > Open` → select the `orm-learn` folder (or its `pom.xml`) →
IntelliJ auto-detects it as a Maven project.

### VS Code
Install the "Extension Pack for Java" and "Spring Boot Extension Pack",
then `File > Open Folder` → select `orm-learn`. VS Code will pick up
the Maven project automatically.

## Running the application

- IDE: run `OrmLearnApplication.main()`.
- CLI: `mvn spring-boot:run` or `java -jar target/orm-learn.jar` (after `mvn clean package`).

On startup, `OrmLearnApplication.main()` automatically runs the
Hands-on 1/6/7/8/9 test methods (`testGetAllCountries`,
`testFindCountryByCode`, `testAddCountry`, `testUpdateCountry`,
`testDeleteCountry`) and logs their results via SLF4J
(`LOGGER.info`/`.debug`/`.error`), so you can see each exercise's
outcome directly in the console.

## Testing the REST APIs (Postman / curl)

Base URL: `http://localhost:8080`

| Method | Endpoint | Body | Description |
|---|---|---|---|
| GET | `/countries` | – | List all countries |
| GET | `/countries/{code}` | – | Get one country, e.g. `/countries/IN` |
| POST | `/countries` | `{"code":"ZZ","name":"Test Land"}` | Add a country |
| PUT | `/countries/{code}?name=New+Name` | – (or JSON body `{"name":"New Name"}`) | Update a country's name |
| DELETE | `/countries/{code}` | – | Delete a country |
| GET | `/countries/search/{name}` | – | Partial, case-insensitive name search, e.g. `/countries/search/stan` |

Example with curl:
```bash
curl http://localhost:8080/countries
curl http://localhost:8080/countries/IN
curl -X POST http://localhost:8080/countries -H "Content-Type: application/json" -d "{\"code\":\"ZZ\",\"name\":\"Test Land\"}"
curl -X PUT "http://localhost:8080/countries/ZZ?name=Renamed+Land"
curl -X DELETE http://localhost:8080/countries/ZZ
curl http://localhost:8080/countries/search/stan
```

## Expected output

Console log on startup (abbreviated):
```
... INFO  ... OrmLearnApplication ... Inside main
... INFO  ... CountryService ... Start - getAllCountries
... DEBUG ... CountryService ... countries=[Country [code=AF, name=Afghanistan], ...]
... INFO  ... CountryService ... End - getAllCountries
... INFO  ... OrmLearnApplication ... testFindCountryByCode PASSED
... INFO  ... OrmLearnApplication ... testAddCountry PASSED
... INFO  ... OrmLearnApplication ... testUpdateCountry PASSED
... INFO  ... OrmLearnApplication ... testDeleteCountry PASSED - country no longer found as expected
```

`GET /countries/IN` returns:
```json
{ "code": "IN", "name": "India" }
```

## Folder structure

```
orm-learn/
├── pom.xml
├── README.md
├── src
│   ├── main
│   │   ├── java/com/cognizant/ormlearn
│   │   │   ├── OrmLearnApplication.java
│   │   │   ├── model/Country.java
│   │   │   ├── repository/CountryRepository.java
│   │   │   ├── service/CountryService.java
│   │   │   ├── service/exception/CountryNotFoundException.java
│   │   │   ├── controller/CountryController.java
│   │   │   ├── hibernatexml/                  (Hands-on 2)
│   │   │   │   ├── Employee.java
│   │   │   │   ├── HibernateXmlConfigUtil.java
│   │   │   │   └── EmployeeXmlDao.java
│   │   │   ├── hibernateannotation/           (Hands-on 3)
│   │   │   │   ├── Employee.java
│   │   │   │   ├── HibernateAnnotationConfigUtil.java
│   │   │   │   └── EmployeeAnnotationDao.java
│   │   │   └── comparison/                    (Hands-on 4)
│   │   │       ├── Employee.java
│   │   │       ├── EmployeeRepository.java
│   │   │       ├── EmployeeService.java
│   │   │       └── EmployeeHibernateStyleDao.java
│   │   └── resources
│   │       ├── application.properties
│   │       ├── schema.sql
│   │       ├── data.sql
│   │       ├── hibernatexml/
│   │       │   ├── Employee.hbm.xml
│   │       │   └── hibernate.cfg.xml
│   │       ├── hibernateannotation/hibernate.cfg.xml
│   │       └── comparison/hibernate.cfg.xml
│   └── test/java/com/cognizant/ormlearn/OrmLearnApplicationTests.java
```

## Notes on Hands-on 2, 3 and 4

Hands-on 2 and 3 use their own standalone Hibernate `SessionFactory`
(built from a plain `hibernate.cfg.xml`, independent of Spring Boot's
auto-configured `EntityManagerFactory`) so the classic
`SessionFactory → Session → Transaction` workflow described in the
exercise is fully visible and not hidden behind Spring's abstractions.
Each has its own `Employee` class and its own database table
(`employee_xml`, `employee_annotation`) so they don't collide with each
other or with the Spring-managed `country` table.

Hands-on 4's `comparison` package puts a Spring Data JPA repository/
service and a plain-Hibernate DAO side by side against the *same*
`Employee` entity (`employee_comparison` table) so the amount of
boilerplate each approach requires can be compared directly — see the
Javadoc comments in `EmployeeHibernateStyleDao` and `EmployeeService`.

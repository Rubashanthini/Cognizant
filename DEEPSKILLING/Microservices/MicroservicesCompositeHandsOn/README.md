# MicroservicesCompositeHandsOn

A complete, professional, multi-module **Maven** project demonstrating a
microservices architecture with:

- **Spring Boot 3.2.5**
- **Spring Cloud 2023.0.1** ("Leaf" release train)
- **Netflix Eureka** for Service Discovery / Registration
- **Spring Cloud Gateway** for API Gateway routing, with a custom global
  `LoggingFilter` that logs every incoming request
- A shared **common-library** module holding cross-service DTOs

---

## 1. Project Structure

```
MicroservicesCompositeHandsOn/
│
├── pom.xml                     # Root aggregator/reactor POM
├── README.md                   # This file
├── docker-compose.yml          # Brings up the full stack in Docker
│
├── common-library/             # Shared DTOs (AccountDto, LoanDto, GreetingDto)
│   ├── pom.xml
│   ├── src/main/java/...
│   ├── src/main/resources/application.properties
│   ├── src/test/java/...
│   └── Dockerfile
│
├── eureka-discovery-server/     # Service Registry (port 8761)
│   ├── pom.xml
│   ├── src/main/java/...
│   ├── src/main/resources/application.properties
│   ├── src/test/java/...
│   └── Dockerfile
│
├── account-service/             # Account microservice (port 8080)
│   ├── pom.xml
│   ├── src/main/java/...
│   ├── src/main/resources/application.properties
│   ├── src/test/java/...
│   └── Dockerfile
│
├── loan-service/                # Loan microservice (port 8081)
│   ├── pom.xml
│   ├── src/main/java/...
│   ├── src/main/resources/application.properties
│   ├── src/test/java/...
│   └── Dockerfile
│
├── greet-service/                # "Hello World" microservice (port 8082)
│   ├── pom.xml
│   ├── src/main/java/...
│   ├── src/main/resources/application.properties
│   ├── src/test/java/...
│   └── Dockerfile
│
└── api-gateway/                  # Spring Cloud Gateway (port 9090)
    ├── pom.xml
    ├── src/main/java/...
    ├── src/main/resources/application.yml
    ├── src/test/java/...
    └── Dockerfile
```

---

## 2. Module Overview

| Module                     | Port | Responsibility                                                    |
|----------------------------|------|---------------------------------------------------------------------|
| `common-library`            | n/a  | Shared DTOs: `AccountDto`, `LoanDto`, `GreetingDto`                 |
| `eureka-discovery-server`   | 8761 | Netflix Eureka service registry                                    |
| `account-service`           | 8080 | `GET /accounts/{number}` – returns dummy account details            |
| `loan-service`              | 8081 | `GET /loans/{number}` – returns dummy loan details                  |
| `greet-service`             | 8082 | `GET /greet` – returns `"Hello World"`                              |
| `api-gateway`               | 9090 | Spring Cloud Gateway routing to the three services above via Eureka |

Each service registers itself with `eureka-discovery-server` using
`spring-cloud-starter-netflix-eureka-client`, under the logical service id
configured in its `spring.application.name` property (`account-service`,
`loan-service`, `greet-service`).

The `api-gateway` module load-balances to these services using the
`lb://SERVICE-NAME` URI scheme, with explicit routes defined in its
`application.yml`, and a `LoggingFilter` global filter (implementing
`GlobalFilter` + `Ordered`, with `HIGHEST_PRECEDENCE`) that logs the method,
path, and remote address of **every** incoming request, plus the resulting
HTTP status and elapsed time once the response completes.

---

## 3. Building the Project

This is a standard Maven reactor build. From the project root:

```bash
mvn clean install
```

This will, in dependency order:

1. Build and install `common-library` (a plain JAR, no Spring Boot repackage)
2. Build `eureka-discovery-server`
3. Build `account-service`, `loan-service`, `greet-service` (each depends on
   `common-library`)
4. Build `api-gateway`
5. Run all JUnit 5 tests for every module

> **Requirement**: Java 17+ and Maven 3.9+ must be installed and available
> on your `PATH`. All dependency versions are managed via Spring Boot's
> `spring-boot-starter-parent` (3.2.5) and the Spring Cloud BOM
> (`2023.0.1`), imported in the root `pom.xml`, so no individual starter
> needs an explicit version.

---

## 4. Running the Services Locally

Start each service in its own terminal, **in this order**, waiting for each
to finish starting before launching the next:

```bash
# 1. Eureka Discovery Server
cd eureka-discovery-server
mvn spring-boot:run

# 2. Account Service
cd account-service
mvn spring-boot:run

# 3. Loan Service
cd loan-service
mvn spring-boot:run

# 4. Greet Service
cd greet-service
mvn spring-boot:run

# 5. API Gateway
cd api-gateway
mvn spring-boot:run
```

Once all five applications are up:

| URL                                                              | Description                                  |
|-------------------------------------------------------------------|-----------------------------------------------|
| `http://localhost:8761`                                            | Eureka dashboard — should list all 4 registered clients |
| `http://localhost:9090/greet-service/greet`                        | Routed through the gateway to greet-service    |
| `http://localhost:9090/account-service/accounts/00987987973432`    | Routed through the gateway to account-service  |
| `http://localhost:9090/loan-service/loans/H00987987972342`         | Routed through the gateway to loan-service      |

You should also see a log line in the `api-gateway` console for every
request, produced by `LoggingFilter`, e.g.:

```
INFO  c.c.gateway.filter.LoggingFilter : Incoming request -> method: GET, path: /greet-service/greet, remoteAddress: ...
INFO  c.c.gateway.filter.LoggingFilter : Completed request -> path: /greet-service/greet, status: 200 OK, durationMs: 12
```

---

## 5. Running with Docker Compose

A `docker-compose.yml` is provided at the project root that builds and runs
all five runnable services (the sixth module, `common-library`, is a plain
JAR consumed at build time, not a standalone container):

```bash
docker compose up --build
```

This will:

1. Build each service's own multi-stage `Dockerfile` (Maven + JDK 17
   builder stage, followed by a slim `eclipse-temurin:17-jre-jammy`
   runtime stage)
2. Start `eureka-discovery-server` first and wait for its health check to
   pass
3. Start `account-service`, `loan-service`, and `greet-service`
4. Start `api-gateway` last, once the above are healthy/started

All the same URLs listed in section 4 above will be reachable on the host
machine, since each container's port is published 1:1 (e.g. `8761:8761`,
`8080:8080`, `9090:9090`, etc.).

To stop and remove all containers:

```bash
docker compose down
```

---

## 6. Sample Responses

**Account Service** — `GET /accounts/00987987973432`
```json
{ "number": "00987987973432", "type": "savings", "balance": 234343.0 }
```

**Loan Service** — `GET /loans/H00987987972342`
```json
{ "number": "H00987987972342", "type": "car", "loan": 400000.0, "emi": 3258.0, "tenure": 18 }
```

**Greet Service** — `GET /greet`
```json
{ "message": "Hello World" }
```

> These are intentionally static/dummy responses (no backend database is
> wired up), matching the scope of this hands-on exercise: the emphasis is
> on Eureka registration, service discovery, and API Gateway routing/logging
> rather than persistence.

---

## 7. Testing

Every module includes JUnit 5 tests:

- `common-library`: unit tests for `AccountDto`, `LoanDto`, `GreetingDto`
- `eureka-discovery-server`: Spring context load test
- `account-service`: MockMvc controller test + context load test
- `loan-service`: MockMvc controller test + context load test
- `greet-service`: MockMvc controller test + context load test
- `api-gateway`: `LoggingFilter` unit test + Spring context load test

Run all tests across every module with:

```bash
mvn test
```

---

## 8. Importing into an IDE

This project can be imported directly as a Maven project into:

- **IntelliJ IDEA** — `File > Open` and select the root `pom.xml`
- **Eclipse** — `File > Import > Maven > Existing Maven Projects`, then
  select the project root (Eclipse will pick up all six modules)
- **VS Code** — open the project root folder with the "Extension Pack for
  Java" installed; the Maven multi-module structure will be detected
  automatically

---

## 9. Technology Versions

| Component              | Version    |
|-------------------------|------------|
| Java                    | 17         |
| Spring Boot             | 3.2.5      |
| Spring Cloud            | 2023.0.1   |
| Maven                   | 3.9.x      |
| JUnit                   | 5 (JUnit Jupiter) |

No deprecated Spring APIs are used anywhere in this codebase.

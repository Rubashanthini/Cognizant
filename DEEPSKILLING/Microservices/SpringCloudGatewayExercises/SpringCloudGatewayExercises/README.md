# Spring Cloud Gateway Exercises

**Cognizant Digital Nurture 5.0 — Sample Hands-on Exercises on Edge Services and API Gateway with Spring Boot 3 and Spring Cloud**

## Project Overview

This project is a single, self-contained Spring Boot 3 Maven application that implements all three sample exercises on Edge Services and API Gateway using Spring Cloud:

1. **Exercise 1 — Edge Services (Routing and Filtering):** A Spring Cloud Gateway route forwards requests to a downstream service, and a custom global filter (`LoggingFilter`) logs every request that passes through the gateway.
2. **Exercise 2 — Load Balancing:** A gateway route uses the `lb://` URI scheme to forward requests to a logical service name (`example-service`), resolved via Spring Cloud LoadBalancer using a `RandomLoadBalancer` strategy.
3. **Exercise 3 — Resilience Patterns:** A gateway route is protected by a Resilience4j Circuit Breaker and Time Limiter, with a fallback endpoint that returns a graceful degraded response when the downstream call fails or times out.

To keep the project runnable with a single command (no separate microservices or service registry required), the "downstream service" that the gateway routes to is implemented in the same application, via `TestController`.

## Folder Structure

```
SpringCloudGatewayExercises/
├── pom.xml
├── README.md
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── cognizant
    │   │           └── gateway
    │   │               ├── GatewayApplication.java
    │   │               ├── config
    │   │               │      ├── GatewayConfig.java
    │   │               │      ├── LoadBalancerConfiguration.java
    │   │               │      └── ResilienceConfiguration.java
    │   │               ├── filter
    │   │               │      └── LoggingFilter.java
    │   │               └── controller
    │   │                      └── TestController.java
    │   │
    │   └── resources
    │          ├── application.yml
    │          └── logback-spring.xml
    │
    └── test
        └── java
            └── com
                └── cognizant
                    └── gateway
                        ├── GatewayRoutingTest.java
                        ├── LoadBalancerTest.java
                        ├── ResilienceTest.java
                        └── GatewayApplicationTests.java
```

## Technologies Used

| Technology | Version | Purpose |
|---|---|---|
| Java | 17 | Language runtime |
| Spring Boot | 3.2.5 | Application framework |
| Spring Cloud | 2023.0.1 | Cloud-native building blocks |
| Spring Cloud Gateway | (managed by Spring Cloud BOM) | Edge service / API Gateway |
| Spring Cloud LoadBalancer | (managed by Spring Cloud BOM) | Client-side load balancing |
| Resilience4j | 2.2.0 | Circuit Breaker / Time Limiter |
| Spring WebFlux | (managed by Spring Boot BOM) | Reactive web runtime (required by Gateway) |
| Maven | 3.8+ | Build tool |
| JUnit 5 | (managed by Spring Boot BOM) | Testing framework |

## Spring Cloud Gateway

The gateway is configured declaratively in `application.yml` and programmatically in `GatewayConfig`. It runs on **port 8080** by default.

### Routing

Three routes are defined in `application.yml`:

| Route ID | Path Predicate | Target URI | Purpose |
|---|---|---|---|
| `example_route` | `/example/**` | `http://localhost:8080` | Exercise 1 — basic routing |
| `load_balanced_route` | `/loadbalanced/**` | `lb://example-service` | Exercise 2 — load-balanced routing |
| `resilient_route` | `/slow/**` | `http://localhost:8080` | Exercise 3 — circuit-breaker-protected routing |

`GatewayConfig` additionally defines equivalent routes programmatically under `/programmatic/example/**` and `/programmatic/loadbalanced/**` to demonstrate the Java DSL style of route configuration.

### Filters

`LoggingFilter` implements `GlobalFilter` and `Ordered`, so it is automatically applied to **every** route in the gateway. For each request it logs:

- HTTP method
- Request URI
- Timestamp
- Response status and processing duration (once the downstream call completes)

Logs are written both to the console and to `logs/gateway-application.log` (see `logback-spring.xml`).

## Load Balancer

`LoadBalancerConfiguration` defines a `RandomLoadBalancer` bean that randomly selects a service instance for a given logical service name. Because there is no external service registry (e.g. Eureka) in this exercise, the pool of instances for `example-service` is declared statically in `application.yml` under `spring.cloud.discovery.client.simple.instances`.

To observe genuine load balancing across separate processes, start additional instances of this same application on different ports and add corresponding entries to the instance list:

```bash
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8081
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8082
```

## Resilience4j

### Circuit Breaker

`ResilienceConfiguration` defines the default Circuit Breaker configuration applied to all circuit breakers created via `ReactiveResilience4JCircuitBreakerFactory`:

- Sliding window size: 10 calls
- Failure rate threshold: 50%
- Wait duration in open state: 5 seconds
- Permitted calls in half-open state: 3

The `exampleCircuitBreaker` instance additionally overrides its settings in `application.yml` (`resilience4j.circuitbreaker.instances.exampleCircuitBreaker`).

### Time Limiter

A 4-second timeout is configured for the `exampleCircuitBreaker` instance. Downstream calls exceeding this duration are treated as failures and trigger the fallback.

### Fallback

The `resilient_route` gateway route applies the `CircuitBreaker` gateway filter with `fallbackUri: forward:/fallback/example`. When the downstream call fails or exceeds the time limit, the request is forwarded internally to `TestController#fallback()`, which returns a `200 OK` response with a `"status": "FALLBACK"` payload instead of propagating an error to the client.

## Maven Commands

Build the project and run all tests:

```bash
mvn clean install
```

Run only the tests:

```bash
mvn test
```

Skip tests during build:

```bash
mvn clean install -DskipTests
```

## Running the Application

```bash
mvn spring-boot:run
```

Or, after building the jar:

```bash
java -jar target/spring-cloud-gateway-exercises.jar
```

The application starts on **http://localhost:8080**.

### Try it out

```bash
# Exercise 1: basic routing
curl http://localhost:8080/example/status

# Exercise 2: load-balanced routing
curl http://localhost:8080/loadbalanced/status

# Exercise 3: resilience — fast call (succeeds normally)
curl http://localhost:8080/slow/1

# Exercise 3: resilience — slow call (exceeds 4s timeout, triggers fallback)
curl http://localhost:8080/slow/6

# Fallback endpoint directly
curl http://localhost:8080/fallback/example

# Actuator health (includes circuit breaker health)
curl http://localhost:8080/actuator/health
```

## Running Tests

```bash
mvn test
```

This runs four JUnit 5 test classes:

- **`GatewayRoutingTest`** — verifies routing and the global logging filter (Exercise 1).
- **`LoadBalancerTest`** — verifies the `RandomLoadBalancer` resolves instances and load-balanced routing works end-to-end (Exercise 2).
- **`ResilienceTest`** — verifies the Circuit Breaker/Time Limiter configuration and fallback behavior (Exercise 3).
- **`GatewayApplicationTests`** — verifies the full application context loads successfully.

## Expected Output

When the application starts successfully, you should see log output similar to:

```
...
Tomcat initialized with port(s): (none) 8080 (http/2)
...
Netty started on port 8080
Started GatewayApplication in X.XXX seconds
```

When a request is routed through the gateway, `LoggingFilter` produces output similar to:

```
Request: http://localhost:8080/example/status
2026-XX-XX ... DEBUG com.cognizant.gateway.filter.LoggingFilter - Incoming Request -> Method: [GET] URI: [http://localhost:8080/example/status] ...
2026-XX-XX ... DEBUG com.cognizant.gateway.filter.LoggingFilter - Completed Request -> URI: [http://localhost:8080/example/status] Status: [200 OK] Duration: [XX ms]
```

Calling `/slow/6` (which exceeds the 4-second Time Limiter timeout) returns:

```json
{
  "service": "example-service",
  "status": "FALLBACK",
  "message": "The downstream service is currently unavailable. Please try again later.",
  "timestamp": "2026-07-19T..."
}
```

## Import into Eclipse

1. Open Eclipse and go to **File → Import → Maven → Existing Maven Projects**.
2. Browse to the `SpringCloudGatewayExercises` root folder and select it.
3. Ensure `pom.xml` is detected, then click **Finish**.
4. Right-click the project → **Maven → Update Project** to resolve all dependencies.
5. Ensure the project JRE is set to Java 17 (**Project → Properties → Java Build Path**).

## Import into IntelliJ IDEA

1. Select **File → Open** and choose the `SpringCloudGatewayExercises` folder (the one containing `pom.xml`).
2. IntelliJ will automatically detect it as a Maven project and prompt to load it — click **Load Maven Project** (or it may happen automatically).
3. Go to **File → Project Structure → Project** and set the **SDK** to Java 17.
4. Wait for Maven to finish downloading dependencies (progress shown in the bottom status bar).
5. Run `GatewayApplication` directly, or use the Maven tool window to run `spring-boot:run`.

## Import into VS Code

1. Install the **Extension Pack for Java** and **Spring Boot Extension Pack** from the VS Code Marketplace.
2. Open the `SpringCloudGatewayExercises` folder via **File → Open Folder**.
3. VS Code will detect the `pom.xml` and automatically start resolving Maven dependencies (via the Java Language Server).
4. Use the **Spring Boot Dashboard** panel (or the Run/Debug icon above `GatewayApplication.main()`) to run the application.
5. Alternatively, open an integrated terminal and run `mvn spring-boot:run`.

---

**Project:** SpringCloudGatewayExercises
**Group ID:** `com.cognizant`
**Artifact ID:** `spring-cloud-gateway-exercises`
**Base Package:** `com.cognizant.gateway`

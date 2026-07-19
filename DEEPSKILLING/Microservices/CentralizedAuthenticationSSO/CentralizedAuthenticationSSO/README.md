# Centralized Authentication and SSO with Spring Boot 3 & Spring Cloud

## 1. Project Overview

This project is a complete, buildable Maven/Spring Boot 3 application that implements
the three Cognizant Digital Nurture 5.0 exercises on **Centralized Authentication and
SSO**:

| Exercise | Topic | Package / Class |
|---|---|---|
| 1 | OAuth2 Login / OpenID Connect | `config.SecurityConfig`, `controller.UserController` |
| 2 | OAuth2 Resource Server | `config.ResourceServerConfig`, `controller.SecureController` |
| 3 | Self-issued JWT authentication | `config.JwtConfig`, `config.JwtTokenProvider`, `filter.JwtAuthenticationFilter`, `service.AuthenticationService`, `controller.AuthenticationController` |

The original exercise material used `WebSecurityConfigurerAdapter`, which was deprecated
in Spring Security 5.7 and removed in Spring Security 6. Every exercise has been
re-implemented here using the current, non-deprecated approach: `SecurityFilterChain`
`@Bean` methods, `AuthenticationManager`, `PasswordEncoder`, `JwtDecoder` (via Resource
Server auto-configuration) and the modern JJWT 0.12.x builder/parser API.

## 2. OAuth2 Authentication (Exercise 1)

`GET /user` is guarded by an `oauth2Login()` filter chain. An unauthenticated browser
request is redirected to the configured provider (Google, by default) to sign in; once
authenticated, Spring Security establishes a session and `UserController` returns the
authenticated user's OIDC attributes.

## 3. OpenID Connect

Because the `openid` scope is requested during OAuth2 Login, Spring Security
automatically treats the flow as OpenID Connect, resolving an `OidcUser`/`OAuth2User`
whose attributes (`sub`, `name`, `email`, ...) come from the provider's ID Token / UserInfo
endpoint, as declared under `spring.security.oauth2.client.provider.google` in
`application.yml`.

## 4. JWT Authentication (Exercise 3)

`AuthenticationController` exposes `POST /api/auth/login`, which validates credentials
via `AuthenticationService` (backed by Spring Security's `AuthenticationManager`) and
returns a signed JWT from `JwtTokenProvider`. Subsequent calls to any `/api/**` endpoint
must include `Authorization: Bearer <token>`; `JwtAuthenticationFilter` validates the
token and populates the `SecurityContext` before the request reaches the controller.

## 5. Spring Security 6

All three filter chains are declared as `@Bean` methods returning `SecurityFilterChain`,
scoped with `securityMatcher(...)` and ordered with `@Order`, which is the supported
replacement for overriding `configure(HttpSecurity)` in a `WebSecurityConfigurerAdapter`
subclass.

## 6. SecurityFilterChain

| Order | Bean | Matches | Mechanism |
|---|---|---|---|
| 1 | `oauth2LoginFilterChain` | `/user`, `/login/**`, `/oauth2/**` | `oauth2Login()` |
| 2 | `resourceServerFilterChain` | `/secure/**` | `oauth2ResourceServer().jwt()` |
| 3 | `jwtFilterChain` | `/api/**` | Custom `JwtAuthenticationFilter` |

## 7. Authorization Server

This project acts as a **Resource Server**, not an Authorization Server; it expects an
external Authorization Server (Spring Authorization Server, Keycloak, Okta, etc.) to
issue the JWTs consumed by `/secure/**`. Point
`spring.security.oauth2.resourceserver.jwt.issuer-uri` at that server's base URL - Spring
Boot auto-configures a `JwtDecoder` from its OpenID Connect discovery document.

## 8. Resource Server

`ResourceServerConfig` declares the `/secure/**` chain. `SecureController#secure(Jwt)`
demonstrates Spring MVC resolving the decoded `Jwt` directly as a controller argument.

## 9. JWT Flow (Exercise 3, self-issued tokens)

```
Client                         AuthenticationController        JwtTokenProvider
  | POST /api/auth/login  ---->|                                |
  |  {username, password}      | authenticate() via             |
  |                             | AuthenticationManager -------->|
  |                             |<---- createToken(username) ----|
  |<---- 200 { accessToken } --|                                |
  |                                                              |
  | GET /api/protected                                          |
  |  Authorization: Bearer <token>                               |
  |----------------------------------------------------------->  |
  |         JwtAuthenticationFilter.validateToken()               |
  |         -> SecurityContext populated -> controller runs       |
```

## 10. OAuth2 Login Flow (Exercise 1)

```
Browser              SecurityFilterChain(oauth2Login)         Provider (Google)
  | GET /user  ------>|                                          |
  |    (unauthenticated) -- redirect -->  /oauth2/authorization/google
  |<----------------------------------------------------------- login page
  |  user signs in                                               |
  |------------------------------------------------------------->|
  |  redirect back to /login/oauth2/code/google with auth code   |
  |------------------->| exchanges code for tokens, builds OAuth2User
  |<---- 200 /user { sub, name, email, ... } ---------------------|
```

## 11. Project Structure

```
CentralizedAuthenticationSSO/
├── pom.xml
├── README.md
└── src
    ├── main
    │   ├── java/com/cognizant/auth
    │   │   ├── CentralizedAuthenticationApplication.java
    │   │   ├── config/
    │   │   │   ├── SecurityConfig.java
    │   │   │   ├── ResourceServerConfig.java
    │   │   │   ├── JwtConfig.java
    │   │   │   └── JwtTokenProvider.java
    │   │   ├── filter/
    │   │   │   └── JwtAuthenticationFilter.java
    │   │   ├── controller/
    │   │   │   ├── UserController.java
    │   │   │   ├── SecureController.java
    │   │   │   └── AuthenticationController.java
    │   │   ├── service/
    │   │   │   └── AuthenticationService.java
    │   │   └── dto/
    │   │       ├── LoginRequest.java
    │   │       └── JwtResponse.java
    │   └── resources/
    │       ├── application.yml
    │       └── application.properties
    └── test/java/com/cognizant/auth
        └── CentralizedAuthenticationApplicationTests.java
```

## 12. Technologies Used

- Java 17
- Spring Boot 3.2.5
- Spring Security 6
- Spring Web (MVC)
- OAuth2 Client (`spring-boot-starter-oauth2-client`)
- OAuth2 Resource Server (`spring-boot-starter-oauth2-resource-server`)
- `spring-security-oauth2-jose` (Nimbus JOSE/JWT support)
- Bean Validation (`spring-boot-starter-validation`)
- JJWT 0.12.5 (`jjwt-api`, `jjwt-impl`, `jjwt-jackson`)
- Maven Compiler & Surefire plugins
- JUnit 5 / Spring Boot Test / Spring Security Test

## 13. Running the Project

```bash
# Build
mvn clean install

# Run
mvn spring-boot:run
```

The application starts on `http://localhost:8080`.

> **Note:** `/user` (Exercise 1) requires real OAuth2 client credentials, and `/secure`
> (Exercise 2) requires a running Authorization Server at the configured `issuer-uri`.
> `/api/auth/login` and `/api/protected` (Exercise 3) work out of the box with the
> in-memory demo user.

## 14. Testing Endpoints

| Endpoint | Method | Auth required | Exercise |
|---|---|---|---|
| `/user` | GET | OAuth2 session | 1 |
| `/secure` | GET | External Bearer JWT | 2 |
| `/api/auth/login` | POST | None | 3 |
| `/api/protected` | GET | Self-issued Bearer JWT | 3 |

## 15. Sample Requests

**Login (Exercise 3):**

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"demo-user","password":"Password@123"}'
```

**Call a protected endpoint with the returned token:**

```bash
curl http://localhost:8080/api/protected \
  -H "Authorization: Bearer <accessToken from previous response>"
```

**Resource Server (Exercise 2), once an Authorization Server is configured:**

```bash
curl http://localhost:8080/secure \
  -H "Authorization: Bearer <token issued by your Authorization Server>"
```

## 16. Sample JWT

A token issued by `/api/auth/login` looks like:

```
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkZW1vLXVzZXIiLCJpYXQiOjE3MjE0MDAwMDAsImV4cCI6MTcyMTQwMzYwMH0.4Q2wq0m4d9y7v0m3F3s1r8k2j5h6b9c1d2e3f4a5b6c
```

Decoded payload:

```json
{
  "sub": "demo-user",
  "iat": 1721400000,
  "exp": 1721403600
}
```

## 17. Maven Commands

```bash
mvn clean install       # compile, test, package
mvn clean package        # package without reinstalling to local repo
mvn spring-boot:run      # run the application
mvn test                 # run tests only
```

## 18. Import into IntelliJ IDEA

1. `File > Open...` and select the `CentralizedAuthenticationSSO` folder (the one
   containing `pom.xml`).
2. IntelliJ detects the Maven project automatically; wait for indexing/dependency
   resolution to finish.
3. Set the Project SDK to Java 17 (`File > Project Structure > Project`).
4. Run `CentralizedAuthenticationApplication` from the Run configuration dropdown.

## 19. Import into Eclipse

1. `File > Import... > Maven > Existing Maven Projects`.
2. Browse to the `CentralizedAuthenticationSSO` folder and select `pom.xml`.
3. Ensure a Java 17 JRE/JDK is configured under `Window > Preferences > Java > Installed JREs`.
4. Right-click the project `Run As > Spring Boot App` (or `Maven Build...` with goal
   `spring-boot:run`).

## 20. Import into VS Code

1. Install the **Extension Pack for Java** and **Spring Boot Extension Pack**.
2. `File > Open Folder...` and select `CentralizedAuthenticationSSO`.
3. VS Code detects the Maven project and resolves dependencies automatically.
4. Use the Spring Boot Dashboard, or run
   `CentralizedAuthenticationApplication.java` via the `Run | Debug` code lens.

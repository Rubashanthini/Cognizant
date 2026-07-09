# spring-learn

Cognizant Digital Nurture 5.0 — **Spring REST Hands-on** solution project.

Group Id: `com.cognizant` · Artifact Id: `spring-learn` · Base Package: `com.cognizant.springlearn`

---

## 1. Project Overview

This project is a Spring Boot 2.7 application that demonstrates core Spring REST concepts:
building REST controllers, mixing XML bean configuration with annotation-based configuration,
path variables, custom exception handling with proper HTTP status codes, SLF4J logging, and
MockMVC-based testing. It runs an embedded Tomcat server on port **8083**.

## 2. Folder Structure

```
spring-learn/
├── pom.xml
├── README.md
└── src
    ├── main
    │   ├── java/com/cognizant/springlearn
    │   │   ├── SpringLearnApplication.java
    │   │   ├── controller/HelloController.java
    │   │   ├── controller/CountryController.java
    │   │   ├── service/CountryService.java
    │   │   ├── exception/CountryNotFoundException.java
    │   │   ├── model/Country.java
    │   │   └── util/              (reserved for future utility classes)
    │   └── resources
    │       ├── application.properties
    │       ├── country.xml
    │       └── date-format.xml
    └── test
        └── java/com/cognizant/springlearn/SpringLearnApplicationTests.java
```

## 3. REST API Overview

| Method | Endpoint             | Description                                   |
|--------|-----------------------|------------------------------------------------|
| GET    | `/hello`               | Returns the plain text `Hello World!!`         |
| GET    | `/country`             | Returns the India `Country` bean as JSON       |
| GET    | `/countries`           | Returns all countries as a JSON array          |
| GET    | `/countries/{code}`    | Returns a country matched by code (case-insensitive) |

## 4. HTTP Request & Response Explanation

**HTTP Request** — a message sent by a client (browser, Postman, curl) to a server asking it
to perform an action. It consists of:
- **Request URL** — the address of the resource, e.g. `http://localhost:8083/country`
- **Request Method** — the verb describing the desired action: `GET` (read), `POST` (create),
  `PUT`/`PATCH` (update), `DELETE` (remove)
- **Request Headers** — metadata about the request, e.g. `Accept: application/json`,
  `User-Agent`, `Content-Type` (for requests with a body)

**HTTP Response** — the message sent back by the server. It consists of:
- **HTTP Status Code** — a 3-digit number indicating the outcome, e.g. `200 OK`, `404 Not Found`,
  `500 Internal Server Error`
- **Response Headers** — metadata about the response, e.g. `Content-Type: application/json`,
  `Content-Length`
- **Response Body** — the actual payload returned (JSON in this project)

**Content-Type** — a header indicating the media type of the body (e.g. `application/json`,
`text/plain`), telling the receiver how to parse the content.

**User-Agent** — a request header identifying the client software making the request
(browser name/version, Postman, curl, etc.).

### Sample GET Request

```
GET /country HTTP/1.1
Host: localhost:8083
Accept: application/json
User-Agent: curl/8.4.0
```

### Sample HTTP Response

```
HTTP/1.1 200 OK
Content-Type: application/json
Content-Length: 78

{
  "code": "IN",
  "name": "India",
  "capital": "New Delhi",
  "population": 1428600000
}
```

## 5. Spring REST Concepts

- **`@RestController`** — combines `@Controller` and `@ResponseBody`; every method's return
  value is written directly into the HTTP response body rather than resolved as a view.
- **`@GetMapping`** — shorthand for `@RequestMapping(method = RequestMethod.GET)`, maps HTTP
  GET requests to a handler method.
- **`@PathVariable`** — binds a URI template variable (e.g. `{code}`) to a method parameter.
- **`@ResponseStatus`** — declares the HTTP status (and optional reason) that should be
  returned when an annotated exception is thrown and left uncaught by a controller.
- **`@ImportResource`** — imports one or more Spring XML bean configuration files into an
  otherwise annotation-driven Spring Boot application context.

## 6. Spring MVC Flow

1. A client sends an HTTP request to the embedded Tomcat server.
2. `DispatcherServlet` (Spring's front controller) receives the request.
3. `DispatcherServlet` consults `HandlerMapping` to find the controller method that matches
   the request URL and HTTP method.
4. The matched controller method executes, invoking service-layer logic as needed.
5. The method's return value (a `Country`, `List<Country>`, or `String`) is passed to an
   appropriate `HttpMessageConverter` (Jackson, for JSON).
6. The converter serializes the object into JSON and writes it to the response body along
   with the correct `Content-Type` header and HTTP status code.
7. The response travels back to the client.

## 7. How JSON Conversion Works

Because `spring-boot-starter-web` brings in the Jackson library on the classpath, and because
`CountryController`/`HelloController` are annotated with `@RestController`, Spring
automatically registers a `MappingJackson2HttpMessageConverter`. Whenever a handler method
returns a POJO (like `Country`) or a `List` of POJOs, this converter inspects the object's
getters via reflection and serializes each property into a corresponding JSON field —
no manual `ObjectMapper` code is required.

## 8. Running the Project

### Import into Eclipse
1. `File` → `Import` → `Maven` → `Existing Maven Projects`
2. Browse to the `spring-learn` folder and select it
3. Eclipse will download dependencies and build the project automatically

### Import into IntelliJ IDEA
1. `File` → `Open`
2. Select the `spring-learn` folder (containing `pom.xml`)
3. IntelliJ detects the Maven project and imports it automatically

### Import into VS Code
1. Install the "Extension Pack for Java" and "Spring Boot Extension Pack"
2. `File` → `Open Folder` → select `spring-learn`
3. VS Code will detect `pom.xml` and configure the Java project automatically

### Maven Commands

```bash
# Build the project, download dependencies, and run all tests
mvn clean install

# Run the application (starts embedded Tomcat on port 8083)
mvn spring-boot:run
```

## 9. Testing using Browser

With the application running, open a browser and navigate to:
- http://localhost:8083/hello
- http://localhost:8083/country
- http://localhost:8083/countries
- http://localhost:8083/countries/in

## 10. Testing using Postman

1. Create a new GET request
2. Set the URL to `http://localhost:8083/countries/in`
3. Click **Send**
4. Observe the JSON response body and `200 OK` status
5. Repeat with `http://localhost:8083/countries/xyz` to observe the `404 Not Found` response

## 11. Testing using Curl

```bash
curl http://localhost:8083/hello
curl http://localhost:8083/country
curl http://localhost:8083/countries
curl http://localhost:8083/countries/in
curl -i http://localhost:8083/countries/xyz   # -i shows the 404 status line
```

## 12. Running MockMVC Tests

```bash
mvn test
```

This executes `SpringLearnApplicationTests`, which verifies:
- The application context loads successfully
- `CountryController` is created and injected
- `GET /country` returns `200 OK` with `code = IN` and `name = India`
- `GET /countries/xyz` returns `404 Not Found` with reason `Country not found`

## 13. Expected Outputs

**GET /hello**
```
Hello World!!
```

**GET /country**
```json
{"code":"IN","name":"India","capital":"New Delhi","population":1428600000}
```

**GET /countries/xyz**
```
HTTP/1.1 404
(reason: Country not found)
```

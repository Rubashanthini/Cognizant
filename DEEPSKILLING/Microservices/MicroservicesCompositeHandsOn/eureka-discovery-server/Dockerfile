# =============================================================================
# Dockerfile - eureka-discovery-server
#
# Multi-stage build:
#   1. "builder" stage compiles the module using Maven + JDK 17
#   2. Final stage copies only the built jar into a lightweight JRE image
# =============================================================================
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /build

# Copy the parent reactor POM first (needed for dependencyManagement),
# then this module's own POM to leverage Docker layer caching for
# dependency resolution.
COPY pom.xml /build/pom.xml
COPY eureka-discovery-server/pom.xml /build/eureka-discovery-server/pom.xml

# Copy sources and package the application, skipping tests for a faster
# image build (tests are run separately during `mvn clean install`).
COPY eureka-discovery-server/src /build/eureka-discovery-server/src
WORKDIR /build/eureka-discovery-server
RUN mvn -q package -DskipTests

# -----------------------------------------------------------------------------
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app
COPY --from=builder /build/eureka-discovery-server/target/eureka-discovery-server.jar /app/eureka-discovery-server.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "/app/eureka-discovery-server.jar"]

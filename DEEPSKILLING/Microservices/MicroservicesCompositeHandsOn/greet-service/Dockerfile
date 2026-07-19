# =============================================================================
# Dockerfile - greet-service
#
# Multi-stage build: compile with Maven + JDK 17, run with a slim JRE image.
# The common-library module is built and installed into the local Maven
# repository first so that greet-service's dependency on it resolves during
# the package step.
# =============================================================================
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /build

COPY pom.xml /build/pom.xml
COPY common-library /build/common-library
COPY greet-service/pom.xml /build/greet-service/pom.xml
COPY greet-service/src /build/greet-service/src

# Install common-library into the local repo so greet-service can resolve it
WORKDIR /build/common-library
RUN mvn -q install -DskipTests

WORKDIR /build/greet-service
RUN mvn -q package -DskipTests

# -----------------------------------------------------------------------------
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app
COPY --from=builder /build/greet-service/target/greet-service.jar /app/greet-service.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "/app/greet-service.jar"]

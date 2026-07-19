# =============================================================================
# Dockerfile - account-service
#
# Multi-stage build: compile with Maven + JDK 17, run with a slim JRE image.
# The common-library module is built and installed into the local Maven
# repository first so that account-service's dependency on it resolves
# during the package step.
# =============================================================================
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /build

COPY pom.xml /build/pom.xml
COPY common-library /build/common-library
COPY account-service/pom.xml /build/account-service/pom.xml
COPY account-service/src /build/account-service/src

# Install common-library into the local repo so account-service can resolve it
WORKDIR /build/common-library
RUN mvn -q install -DskipTests

WORKDIR /build/account-service
RUN mvn -q package -DskipTests

# -----------------------------------------------------------------------------
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app
COPY --from=builder /build/account-service/target/account-service.jar /app/account-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/account-service.jar"]

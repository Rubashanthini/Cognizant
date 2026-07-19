# =============================================================================
# Dockerfile - loan-service
#
# Multi-stage build: compile with Maven + JDK 17, run with a slim JRE image.
# The common-library module is built and installed into the local Maven
# repository first so that loan-service's dependency on it resolves during
# the package step.
# =============================================================================
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /build

COPY pom.xml /build/pom.xml
COPY common-library /build/common-library
COPY loan-service/pom.xml /build/loan-service/pom.xml
COPY loan-service/src /build/loan-service/src

# Install common-library into the local repo so loan-service can resolve it
WORKDIR /build/common-library
RUN mvn -q install -DskipTests

WORKDIR /build/loan-service
RUN mvn -q package -DskipTests

# -----------------------------------------------------------------------------
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app
COPY --from=builder /build/loan-service/target/loan-service.jar /app/loan-service.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app/loan-service.jar"]

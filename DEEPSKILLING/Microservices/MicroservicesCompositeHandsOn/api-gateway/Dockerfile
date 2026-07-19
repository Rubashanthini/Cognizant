# =============================================================================
# Dockerfile - api-gateway
#
# Multi-stage build: compile with Maven + JDK 17, run with a slim JRE image.
# =============================================================================
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /build

COPY pom.xml /build/pom.xml
COPY api-gateway/pom.xml /build/api-gateway/pom.xml
COPY api-gateway/src /build/api-gateway/src

WORKDIR /build/api-gateway
RUN mvn -q package -DskipTests

# -----------------------------------------------------------------------------
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app
COPY --from=builder /build/api-gateway/target/api-gateway.jar /app/api-gateway.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "/app/api-gateway.jar"]

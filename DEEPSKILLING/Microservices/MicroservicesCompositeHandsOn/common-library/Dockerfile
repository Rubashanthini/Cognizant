# =============================================================================
# Dockerfile - common-library
#
# common-library is a plain shared JAR (DTOs only) consumed by the other
# services at build time; it has no main class and is never run as a
# standalone container in production. This Dockerfile is provided so the
# module can still be built and its artifact inspected/extracted in
# isolation if needed (e.g. in a CI pipeline stage).
# =============================================================================
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /build

# Copy the reactor pom first so the parent's dependency management is
# available, then copy this module's own pom and sources.
COPY pom.xml /build/pom.xml
COPY common-library/pom.xml /build/common-library/pom.xml
COPY common-library/src /build/common-library/src

WORKDIR /build/common-library
RUN mvn -q -f pom.xml -N install || true
RUN mvn -q package -DskipTests

# Final stage simply exposes the built jar for extraction; there is no
# ENTRYPOINT/CMD because this artifact is a library, not a runnable service.
FROM eclipse-temurin:17-jre-jammy
WORKDIR /library
COPY --from=builder /build/common-library/target/common-library.jar /library/common-library.jar
CMD ["echo", "common-library is a shared JAR and is not meant to be run directly."]

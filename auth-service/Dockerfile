# Build stage
FROM maven:3.9-eclipse-temurin-17-alpine AS build
WORKDIR /build

# Copy Maven files
COPY pom.xml .
COPY checkstyle.xml .

# Pre-fetch dependencies for better layer caching
RUN mvn -q -DskipTests dependency:go-offline

COPY src/ src/

# Build the application
RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy JAR from build stage
COPY --from=build /build/target/*.jar app.jar

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=40s --retries=3 \
  CMD wget --quiet --tries=1 --spider http://localhost:7001/actuator/health || exit 1

EXPOSE 7001
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

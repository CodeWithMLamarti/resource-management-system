# Use a lightweight base image for Java 17
FROM eclipse-temurin:17-jdk-alpine as builder

# Set working directory inside the container
WORKDIR /app

# Copy only necessary files to leverage Docker caching
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

# Grant execution permission for Maven wrapper and build the application
RUN chmod +x mvnw && ./mvnw -B package --file pom.xml -DskipTests

# ----------------------------------
# Production image for optimized app
FROM eclipse-temurin:17-jre-alpine

# Set environment variables
ENV APP_HOME=/app \
    JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

WORKDIR $APP_HOME

# Copy the built JAR from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the default port
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

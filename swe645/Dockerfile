# Use a lightweight base image with Java runtime
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/swe645-0.0.1-SNAPSHOT.jar swe645-0.0.1-SNAPSHOT.jar

# Expose the application's default port
EXPOSE 8080

# Command to run the Spring Boot application
CMD ["java", "-jar", "swe645-0.0.1-SNAPSHOT.jar"]

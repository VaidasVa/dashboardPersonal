# Use an official OpenJDK runtime as a parent image
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the jar file from the host to the container
COPY target/*.jar app.jar

# Expose the port Spring Boot runs on
EXPOSE 8088

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
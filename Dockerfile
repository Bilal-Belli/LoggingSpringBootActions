# Start from an official Java runtime
FROM openjdk:23-jdk-slim

# Set a working directory in the container
WORKDIR /app

# Copy the jar file into the container
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
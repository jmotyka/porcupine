# Use a Gradle image as the base image
FROM gradle:8-jdk21-graal-jammy AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy all source files
COPY . .

# Build .jar
RUN gradle build

# Expose the port your application will run on
EXPOSE 8080

# Specify the command to run on container startup
CMD ["java", "-jar", "/app/build/libs/manager-0.0.1-SNAPSHOT.jar"]
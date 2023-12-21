# Use a Gradle image as the base image
FROM gradle:8-jdk21-graal-jammy AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy all source files
COPY . .

# Build .jar
RUN gradle build

# Use a lightweight image as the final image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the builder stage to the final image
COPY --from=builder /app/build/libs/gradle_wrapper.jar /app/gradle_wrapper.jar

# Expose the port your application will run on
EXPOSE 8080

# Specify the command to run on container startup
CMD ["java", "-jar", "gradle_wrapper.jar"]
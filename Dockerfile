# Start with a base image containing Java runtime
FROM openjdk:21-jdk-slim

# The application's jar file
ARG JAR_FILE=build/libs/setrr-*.jar

# Add the application's jar to the container
ADD ${JAR_FILE} app.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]


# # Use a base image # openjdk:17-jdk-slim as base
# FROM openjdk:21-jdk-slim AS base
#
# # Set the working directory
# WORKDIR /app
#
# # Copy the Gradle build files and cache dependencies
# COPY build.gradle settings.gradle gradlew ./
# COPY gradle ./gradle
#
# # Download dependencies
# # RUN ./gradlew build -x test --parallel --continue
# RUN ./gradlew build -x test
#
# # Copy the source code
# COPY src ./src
#
# # Build the application
# # RUN ./gradlew build -x test # --parallel --continue
# RUN ./gradlew build -x test

# # Use a smaller base image for the final build
# FROM openjdk:17-jdk-slim
#
# # Set the working directory
# WORKDIR /app
#
# # Copy the built application from the previous stage
# COPY --from=base /app/build/libs/*.jar ./app.jar
#
# # Expose the application port
# EXPOSE 8080
#
# # Run the application
# ENTRYPOINT ["java", "-jar", "app.jar"]
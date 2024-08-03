# Stage 1: Build the application
FROM openjdk:17-jdk-slim AS build

WORKDIR /app
COPY build.gradle.kts settings.gradle.kts ./
COPY gradlew gradlew
COPY gradle gradle
COPY src src
RUN ./gradlew bootJar --no-daemon

# Stage 2: Create the runtime image
FROM openjdk:17-jdk-slim

WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
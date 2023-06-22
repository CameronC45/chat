# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17-slim AS builder
WORKDIR /app
COPY . /app
RUN mvn install -DskipTests

# Stage 2: Package the application into a minimal runtime image
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/notification-0.0.1-SNAPSHOT.jar /app/notification-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/notification-0.0.1-SNAPSHOT.jar"]

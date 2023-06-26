# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17-slim AS builder
WORKDIR /app
COPY . /app
RUN mvn install -DskipTests

# Stage 2: Package the application into a minimal runtime image
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/notification-0.0.1-SNAPSHOT.jar /app/notification-0.0.1-SNAPSHOT.jar
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
ENTRYPOINT ["/wait-for-it.sh", "rabbitmq:5672", "--", "java", "-jar", "/app/notification-0.0.1-SNAPSHOT.jar"]

FROM openjdk:21-jdk-slim

WORKDIR /app

# JAR file
COPY target/bandu-app.jar app.jar

# Spring Boot app port
EXPOSE 8080

# Run command
ENTRYPOINT ["java", "-jar", "app.jar"]
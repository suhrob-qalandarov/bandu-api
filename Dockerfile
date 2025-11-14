FROM eclipse-temurin:21-jdk

WORKDIR /app

# JAR file (serverga yuklangan fayl)
COPY zayrx-control.jar app.jar

# Spring Boot app port
EXPOSE 8080

# Run command
ENTRYPOINT ["java", "-jar", "app.jar"]
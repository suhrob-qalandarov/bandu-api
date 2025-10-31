FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/bandu-app.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
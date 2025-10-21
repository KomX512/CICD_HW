FROM openjdk:17-jdk-slim
COPY target/CICD_HW-0.0.1.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
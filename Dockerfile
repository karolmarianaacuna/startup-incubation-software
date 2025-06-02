FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/proyecto_integrador-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 80
ENTRYPOINT ["java", "-jar", "app.jar"]

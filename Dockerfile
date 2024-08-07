FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY build/libs/incidentapp-0.0.1-SNAPSHOT.jar /app/incidentapp.jar
ENTRYPOINT ["java", "-jar", "incidentapp.jar"]
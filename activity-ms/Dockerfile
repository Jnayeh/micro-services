FROM eclipse-temurin:17-jdk-alpine

WORKDIR /gateway-service

EXPOSE 8077

ADD target/*.jar activity-ms-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "activity-ms-0.0.1-SNAPSHOT.jar"]

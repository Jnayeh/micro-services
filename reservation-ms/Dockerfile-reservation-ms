FROM eclipse-temurin:17-jdk-alpine

WORKDIR /reservation-ms

EXPOSE 8077

ADD target/*.jar reservation-ms-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "reservation-ms-0.0.1-SNAPSHOT.jar"]

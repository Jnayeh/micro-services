FROM eclipse-temurin:17-jdk-alpine

WORKDIR /gateway-server

EXPOSE 8077

ADD target/*.jar gateway-server-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "gateway-server-0.0.1-SNAPSHOT.jar"]

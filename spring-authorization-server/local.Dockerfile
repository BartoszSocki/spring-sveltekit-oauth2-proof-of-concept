FROM openjdk:17-jdk-slim

WORKDIR /tmp
COPY /build/libs /tmp

ENV SPRING_PROFILES_ACTIVE=local

ENTRYPOINT ["java", "-jar", "/tmp/spring-authorization-server.jar"]

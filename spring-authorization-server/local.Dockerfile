FROM openjdk:17-jdk-slim

WORKDIR /tmp
COPY /build/libs /tmp

ENV SPRING_PROFILES_ACTIVE=local

ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,address=*:5005,server=y,suspend=n", "-jar", "/tmp/spring-authorization-server.jar"]

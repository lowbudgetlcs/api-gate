FROM gradle:8.9-jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon

FROM eclipse-temurin:17-jre-alpine
EXPOSE 1337:1337
RUN mkdir /opt/app
WORKDIR /opt/app
COPY --from=build /home/gradle/src/build/libs/*.jar api_gate.jar
ENTRYPOINT ["java", "-jar", "api_gate.jar"]
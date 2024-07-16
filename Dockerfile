FROM gradle:8.4-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon

FROM eclipse-temurin:17-jre-alpine
EXPOSE 1337:1337
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/api_gate.jar
ENTRYPOINT ["java", "-jar", "/app/api_gate.jar"]
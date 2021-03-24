FROM maven:latest as build-stage

COPY . /build

WORKDIR /build

RUN mvn clean package

FROM openjdk:latest as run-time

COPY --from=build-stage /build/target/Discovery-Server-*.jar /app/discovery-server.jar

WORKDIR /app

ENTRYPOINT ["java", "-jar", "discovery-server.jar"]

EXPOSE 4494
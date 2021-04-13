FROM maven:latest as build-stage

COPY . /build

WORKDIR /build

RUN mvn clean package

FROM openjdk:latest as run-time

COPY --from=build-stage /build/target/JMS-Example-*.jar /app/jms-example.jar

WORKDIR /app

ENTRYPOINT ["java", "-jar", "jms-example.jar"]

EXPOSE 8181
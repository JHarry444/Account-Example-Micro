FROM maven:latest as build-stage

COPY . /build

WORKDIR /build

RUN mvn clean package

FROM openjdk:latest as run-time

COPY --from=build-stage /build/target/Number-Generator-*.jar /app/number-gen.jar

WORKDIR /app

ENTRYPOINT ["java", "-jar", "number-gen.jar"]

EXPOSE 8082
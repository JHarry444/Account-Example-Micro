FROM maven:latest as build-stage

COPY . /build

WORKDIR /build

RUN mvn clean package

FROM openjdk:latest as run-time

COPY --from=build-stage /build/target/Prize-Generator-*.jar /app/prize-gen.jar

WORKDIR /app

ENTRYPOINT ["java", "-jar", "prize-gen.jar"]

EXPOSE 8083
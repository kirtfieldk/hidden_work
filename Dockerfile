FROM openjdk:13 as builder
MAINTAINER keith.r.kirtfield@kp.org
WORKDIR /app

ADD ./target/pg-db-api.jar pg-db-api.jar
ENTRYPOINT ["java", "-jar","pg-db-api.jar"]
EXPOSE 8080


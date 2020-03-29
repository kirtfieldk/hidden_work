
FROM openjdk:13 as builder
MAINTAINER keith.r.kirtfield@kp.org
WORKDIR /app

ADD ./target/Difference_Service.jar Difference_Service.jar
ENTRYPOINT ["java", "-jar","Difference_Service.jar"]
EXPOSE 8081

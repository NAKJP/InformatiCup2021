FROM openjdk:11-jre-slim

EXPOSE 8080

WORKDIR /usr/app

COPY ./build/libs/*.jar ./microservice.jar

ENTRYPOINT exec java $JAVA_OPTS -jar ./microservice.jar

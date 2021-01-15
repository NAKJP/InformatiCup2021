FROM openjdk:11-jre-slim

WORKDIR /usr/app

COPY out/artifacts/Informaticup2021_jar/Informaticup2021.jar ./informati2021.jar

ENTRYPOINT exec java -jar ./informati2021.jar
FROM openjdk:13-ea-7-jdk-alpine3.9
COPY target/repository-aggregator-service*.jar repository-aggregator-service.jar
ENV VERSION 1.0.0
CMD java ${JAVA_OPTS} -jar repository-aggregator-service.jar
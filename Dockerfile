FROM mcr.microsoft.com/java/jre-headless:11u2-zulu-alpine
COPY target/repository-aggregator-service*.jar repository-aggregator-service.jar
ENV VERSION 1.0.0
CMD java ${JAVA_OPTS} -jar repository-aggregator-service.jar
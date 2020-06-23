FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG ./target/car2go-0.0.1.jar
ARG SPRING_PROFILES_ACTIVE
ENV SPRING_PROFILES_ACTIVE ${SPRING_PROFILES_ACTIVE:-dev}
COPY ./target/car2go-0.0.1.jar car2go-0.0.1.jar

# init
RUN apk -U upgrade --update && \
   apk add --no-cache curl 

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/car2go-0.0.1.jar"]
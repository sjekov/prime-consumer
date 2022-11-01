FROM openjdk:11
MAINTAINER offerista.com
COPY target/prime-consumer-0.0.1-SNAPSHOT.jar prime-consumer-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/prime-consumer-0.0.1-SNAPSHOT.jar"]
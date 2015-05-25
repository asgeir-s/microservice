FROM java:8

MAINTAINER cluda

EXPOSE 8888

WORKDIR .

ADD microservice.jar microservice.jar

ENTRYPOINT ["java", "-jar", "microservice.jar"]
FROM amazoncorretto:17
ADD target/business-service-docker.jar business-service-docker.jar
ENTRYPOINT ["java", "-jar","business-service-docker.jar"]
EXPOSE 8090
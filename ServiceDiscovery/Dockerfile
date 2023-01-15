FROM amazoncorretto:17
ADD target/service-discovery-docker.jar service-discovery-docker.jar

ENTRYPOINT ["java", "-jar","service-discovery-docker.jar"]
EXPOSE 8080
FROM openjdk:11
ADD target/user-service-docker.jar user-service-docker.jar

ENTRYPOINT ["java", "-jar","user-service-docker.jar"]
EXPOSE 8080
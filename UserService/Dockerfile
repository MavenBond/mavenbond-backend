#
# Build stage
#
FROM maven:3.8.7-amazoncorretto-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml install -DskipTests

#
# Package stage
#
FROM openjdk:17
COPY --from=build /home/app/target/user-service-docker.jar /app/user-service-docker.jar
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar","user-service-docker.jar"]
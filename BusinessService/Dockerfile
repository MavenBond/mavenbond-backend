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
COPY --from=build /home/app/target/business-service-docker.jar /app/business-service-docker.jar
WORKDIR /app
EXPOSE 8090
ENTRYPOINT ["java", "-jar","business-service-docker.jar"]
RUN mvn clean package -DskipTests

FROM openjdk:19
ADD target/business-logic-service-docker.jar business-logic-service-docker.jar
ENTRYPOINT ["java", "-jar","business-logic-service-docker.jar"]
EXPOSE 8080
FROM openjdk:8-jdk-alpine
ADD target/tenpo-be-challenge-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
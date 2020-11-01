FROM openjdk:openjdk:11-jre-slim
VOLUME /tmp
ADD target/Task1-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
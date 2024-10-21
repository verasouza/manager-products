FROM amazoncorretto:17-alpine3.20-jdk
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8079
ENTRYPOINT ["java","-jar", "/app.jar"]
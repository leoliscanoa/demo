FROM openjdk:17.0
WORKDIR /opt/app
COPY target/app.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
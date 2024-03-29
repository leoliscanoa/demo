FROM maven:3.9.2-amazoncorretto-17 AS BUILD
LABEL authors="leoliscanoa"
ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"
WORKDIR /opt/demo
COPY pom.xml .
RUN mvn dependency:go-offline
COPY ./src ./src
RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:17.0
LABEL authors="leoliscanoa"
WORKDIR /opt/app
COPY --from=BUILD /opt/demo/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
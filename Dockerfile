FROM maven:3.8.6-eclipse-temurin-17 as builder
WORKDIR /
COPY mvnw pom.xml ./
COPY ./src ./src
RUN mvn clean install -DskipTests

FROM eclipse-temurin:17-jre-jammy
WORKDIR /
COPY --from=builder ./target/ems-spring-hibernate-0.0.1-SNAPSHOT.jar ems.jar
COPY ./src/main/resources/application.properties application.properties
ENTRYPOINT ["java", "-jar", "ems.jar", "--spring.config.location=application.properties"]

FROM openjdk:11-jdk as Builder
VOLUME /main-app
ARG JAR_FILE=target/CatEatBack.jar
COPY ${JAR_FILE} /CatEatBack.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/CatEatBack.jar"]
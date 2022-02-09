FROM openjdk:latest
COPY ./target/Group13_SEM_CW-0.1.0.1-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "Group13_SEM_CW-0.1.0.1-jar-with-dependencies.jar"]
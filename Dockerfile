FROM openjdk:latest
COPY ./target/Group13_SEM_CW.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "Group13_SEM_CW.jar", "db:3306", "30000"]
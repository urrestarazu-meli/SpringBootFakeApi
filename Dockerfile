FROM openjdk:8-jdk-slim
COPY "./build/libs/fake.api-0.0.1-SNAPSHOT.jar" "fake.api-0.0.1-SNAPSHOT.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","fake.api-0.0.1-SNAPSHOT.jar"]
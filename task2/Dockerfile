FROM maven

COPY . /app
WORKDIR /app
RUN mvn clean package

ENTRYPOINT ["java", "-jar", "target/task2-1.0.jar"]

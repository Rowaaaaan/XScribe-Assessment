FROM maven

COPY . /task1
WORKDIR /task1
RUN mvn clean package

ENTRYPOINT java -jar target/Task1-1.0.jar

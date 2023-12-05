# XScribe Programming Test 2 - Task 3
This is a Java implementation of the tasks in Task 3 - String Searching
for the XScribe Programming Test.

# Requirements
- [Java 8 JDK](https://www.java.com/download/ie_manual.jsp)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker](https://docs.docker.com/get-docker/)(Optional)

# Docker
To automatically manage dependencies and run application, you can run the application
within a docker container by going to the project directory, 
and pasting the following command in a terminal.

```
docker build -t task3 .
docker run -it task3
```

# Building

Alternatively, to compile the program into a jar file yourself,
run the command
```
mvn clean package
```

Maven will automatically pull the other dependencies and build the project.
The output can be found in `target/`.


To build and run the jar, in the project directory, pass the command
```
mvn clean package && java -jar target/task3-1.0.jar
```

# Testing
The project uses Maven Surefire and JUnit Jupiter to run tests.
All of the tests can be found in `tests/`, and can be run using the command
```
mvn test
```

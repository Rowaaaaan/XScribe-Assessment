# XScribe Programming Test 2 - Task 1
This is an implementation of the tasks in Task 1 for the XScribe Programming Test in Java.
It implements the tasks:
- 1-A - Logger Utility Class
- 1-C - Calculator Function that takes a string
- 1-D - Reverse Polish Notation Math expression evaluator

# Requirements
- [Java 8 JDK](https://www.java.com/download/ie_manual.jsp)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker](https://docs.docker.com/get-docker/)(Optional)

# Docker
To automatically manage dependencies and run application, you can run the application
within a docker container by going to the project directory, 
and pasting the following command in a terminal.

```
docker build -t task1 .
docker run -it task1
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
mvn clean package && java -jar target/Task1-1.0.jar
```

# Testing
The project uses Maven Surefire and JUnit Jupiter to run tests.
All of the tests can be found in `tests/`, and can be run using the command
```
mvn clean test
```

To run specific tests, you can add the groups argument and run
```
mvn clean test -Dgroups=<tag>
```

The available tags are:
- `RPNTest` to run tests for the RPN Calculator
- `MathParser` to run tests for the MathParser class
- `Logger` to run tests for the Logger utility class

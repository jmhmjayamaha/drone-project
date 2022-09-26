
## Requirements

For building and running the application you need:

- JDK 11
- Maven 3.x.x

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.musalasoft.application.MusalasoftDroneApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
Aslo, you can build the project using following command and run the jar file mannually

```shell
mvn clean install
```

```shell
java -jar target/musalasoft-drone-0.0.1-SNAPSHOT.jar
```

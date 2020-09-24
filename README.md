# Spring Boot application test

This application represents a simple test of the Spring Boot framework. It includes a API to handle tasks.

It's a 2 layer application: controller and repository. This application doesn't include a service layer due to the lack of functionality. It could be added in the future.

The application follows the Maven folder structure.

By default, the project starts at the port 8080.

## Project creation

The project has been created using spring initializr, using Maven, Java 8 and Spring Boot 2.3.4

## Postman

The folder postman contains a collection to test all the application endpoints from Postman.

## Test

The application includes unit test created with Mockito

## Code coverage and checkstyle

The project includes 2 plugins for generating code coverage and code style reports. 

## Lombock

Lombok is a java library that automatically generates code into your source code. This library can generate getters, setters, constructors,... It keeps the code clean and small.

## Missed features
Security
Validation
Add "createdBy" property
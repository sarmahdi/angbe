# angbe

Spring Boot Server 

The backend application for A & G. It is based on an existing Swagger definition.




## Overview  

This application is a SpringBoot microservice REST API based on a Swagger definition along with Spring Data. It uses an H2 in memory database
as a backend. For live integration test it uses RestEasy Client.

This application has four endpoints

### GET /status

provides a basic Http OK response with a short json saying the status is healthy


### GET /integrationTest

Given a URL it tests the two below services using sample data. It responds with a Json result consisting of two different
results, one each for one operation for below
URL: example of https://angbefromgithub.herokuapp.com



As per the microservices principles, the below two endpoints qualify as independent microservices and should be
independently deployable as well, so that once can be turned on and off without affecting the other but given the scope
of their functionality they are deployed as single application.

### GET /tasks/validateBrackets
 given a string which should contain at least one bracket "(){}[]" it will detect if the brackets are balanced or not

/todo endpoint
has three request types
### POST /todo
given a json string with a field "text" adds a todoItem in the H2 in memory database
This request can either return a todoItem, a validation error that the text was either null or empty string with spaces


### GET /todo/{id}

given an id retrieves the todoitem for that id.
This request can either return a todoItem, a validation error or not found error

PATCH /todo/{id}
Given an id and a subset of todoItem json it updates the todoItem for that id
This request can either return an updated todoItem, a validation error or not found error

## Running locally


Change default port value in application.properties
the port is defined in application.properties in server.port, currently it is set to 8080

It can be run as is but if it is a requirement to have a context path after http://localhost:8080, then the
property server.servlet.context-path can be changed from being "/" to "/test/1.0/"
it is set to "/" to be able to achieve the swagger-ui page on heroku when the application on heroku is accessed.
You can view the api documentation in swagger-ui by pointing to
http://localhost:8080/


In order to run the application there are two easy options:

Using maven, open command prompt in the root folder of the project after cloning from the github repo and run

mvn spring-boot:run


Using jar file, open the command prompt in the root folder of the project after cloning from guthub repo and run
mvn clean install
this will create the jar file then change directory where the jar is and run
$ java -jar angbe-0.0.1.jar

to access the appliction when running locally, open the browser and goto
http://localhost:8080/
(currently the conetxt path is "/" but if that is changed then add the context path after the url like "http://localhost:8080/test/1.0/" )


## Application on Heroku

This application is running on heroku as a heroku app linked with the github repository.
The link to the application is

https://angbefromgithub.herokuapp.com

Upon clicking the link it takes you to the swagger definition of the application.




Challenges:

Deploying the app online: With AWS, GoogleAppEngine and heroku, deciding which one to choose. After choosing heroku and
experiencing the simplicity of a basic deployment, "really , that simple?"




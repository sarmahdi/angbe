package com.sarm.angbe;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

/**
 * A delegate to be called by the {@link IntegrationTestApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 *
 * Implementing IntegrationTest : Even though they are not needed to be implemnted with its own tests and needed to execute the
 * JUnit automated integrated tests but currently the JUnit tests reside in the test package and as per standard practices
 * the tests are not deployed along with the package.
 * Or it should be implemented using SpringBoot Actuator starter.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-04-19T17:35:38.768Z")


public interface IntegrationTestApiDelegate {

    Logger log = LoggerFactory.getLogger(IntegrationTestApi.class);

    default Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    default Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    default Optional<String> getAcceptHeader() {
        return getRequest().map(r -> r.getHeader("Accept"));
    }

    /**
     * @see IntegrationTestApi#integrationTestGet
     */
    default ResponseEntity<IntegrationTestResult> integrationTestGet( String  url) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
//                    JUnitCore junit = new JUnitCore();
//                    junit.addListener(new TextListener(System.out));
//                    Result result = junit.run(TodoApiControllerTest.class);
                    BracersTestResult bracersTestResult = testBracers(url);
                    ToDoTestResult toDoTestResult = testTodo(url);
                    IntegrationTestResult integrationTestResult = new IntegrationTestResult();
                    integrationTestResult.addBracersItem(bracersTestResult);
                    integrationTestResult.addTodoItem(toDoTestResult);
                    integrationTestResult.isCorrect(true);
                    return createIntegrationTestResult(integrationTestResult);
//                    return new ResponseEntity<>(getObjectMapper().get().readValue("{  \"todo\" : [ \"\", \"\" ],  \"bracers\" : [ \"\", \"\" ],  \"isCorrect\" : "+ integrationTestResult.isIsCorrect() +"}", IntegrationTestResult.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default IntegrationTestApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    default BracersTestResult testBracers(String url){
        BracersTestResult bracersTestResult = null;

        try {
            ResteasyClient client = new ResteasyClientBuilder().build();

            ResteasyWebTarget target = client
                    .target(url+"tasks/validateBrackets")
                    .queryParam("input","{inputTemplate}")
                    .resolveTemplate("inputTemplate","234(aaa)324{wer[fr43e]wer}");

            Response response = target.request().accept("application/json").get();
//                    Entity.entity(null, "application/json"));

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            System.out.println("Server response : \n");
//            System.out.println(response.readEntity(String.class));

            String strResponse = response.readEntity(String.class);

            ObjectMapper mapper = new ObjectMapper();
            BalanceTestResult balanceTestResult = mapper.readValue(strResponse , BalanceTestResult.class);
             bracersTestResult = new BracersTestResult();
            bracersTestResult.expected(true);
            bracersTestResult.setInput(balanceTestResult.getInput());
            bracersTestResult.setResult(balanceTestResult.isIsBalanced());
            bracersTestResult.isCorrect(bracersTestResult.isExpected().equals(bracersTestResult.isResult()));
            response.close();

        } catch (Exception e) {

        log.error("exception occured in testing for bracers", e);
        }

        return bracersTestResult;
    }

    default ToDoTestResult testTodo(String url){
        ToDoTestResult toDoTestResult = null;
        ToDoItemAddRequest toDoItemAddRequest = new ToDoItemAddRequest();
        toDoItemAddRequest.setText("Integration test");
        try {
            ResteasyClient client = new ResteasyClientBuilder().build();

            ResteasyWebTarget target = client
                    .target(url+"todo");

            Response response = target.request().accept("application/json").post(
                    Entity.entity( toDoItemAddRequest , "application/json"));

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            System.out.println("Server response : \n");
//            System.out.println(response.readEntity(String.class));

            String strResponse = response.readEntity(String.class);

            ObjectMapper mapper = new ObjectMapper();
            ToDoItem toDoItem = mapper.readValue(strResponse , ToDoItem.class);
            toDoTestResult= new ToDoTestResult();
            toDoTestResult.setResult(toDoItem);
            toDoTestResult.setInput("POST "+  target.getUri() );

            response.close();

        } catch (Exception e) {

            log.error("exception occured in testing for Post TodoItem ", e);

        }
        return toDoTestResult;
    }

    void setRequest(HttpServletRequest request);

    default  ResponseEntity<IntegrationTestResult> createIntegrationTestResult(IntegrationTestResult integrationTestResult) throws IOException {

        String s = null;
        try {
            s = getObjectMapper().get().writeValueAsString(integrationTestResult);
        }
        catch (JsonProcessingException e) {
            log.error(" JsonProcessingException : Couldn't Process Json for content type ", e);
        }
        return new ResponseEntity<IntegrationTestResult>(getObjectMapper().get().readValue(s,IntegrationTestResult.class),HttpStatus.OK);

    }
}

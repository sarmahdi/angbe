package com.sarm.angbe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.*;
import org.apache.commons.validator.routines.UrlValidator;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.io.IOException;
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
    default ResponseEntity<? extends ToDoResponse> integrationTestGet( String  url) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
//            removing to be able to access through a browser easily
//            if (getAcceptHeader().get().contains("application/json")) {
                try {

                if (url == null || !StringUtils.hasText(url) ){

                    return createValidationError("url","params","URL is null or empty, cannot test integration with empty URL ",url);

                }else{

                    String[] schemes = {"http","https"};
                            UrlValidator  urlValidator = new UrlValidator(schemes,UrlValidator.ALLOW_LOCAL_URLS);
                    if (urlValidator.isValid(url)) {
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

                    } else {
                        return createValidationError("url","params","URL is malformed and is not valid ",url);
                    }
                }
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }

//            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default IntegrationTestApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    default BracersTestResult testBracers(String url){
        BracersTestResult bracersTestResult = null;

        try {
            ResteasyClient client = new ResteasyClientBuilder().build();

            ResteasyWebTarget target = client
                    .target(url+"/tasks/validateBrackets")
                    .queryParam("input","{inputTemplate}")
                    .resolveTemplate("inputTemplate","234(aaa)324{wer[fr43e]wer}");

            Response response = target.request().accept("application/json").get();

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }


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
                    .target(url+"/todo");

            Response response = target.request().accept("application/json").post(
                    Entity.entity( toDoItemAddRequest , "application/json"));

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

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

    /**
     * Creates a IntegrationTestResult Object
     * @param integrationTestResult
     * @return
     * @throws IOException
     */

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

    /**
     * Creates ToDoItemValidationError Object
     * @param param
     * @param location
     * @param msg
     * @param value
     * @return
     * @throws IOException
     */
    default ResponseEntity<ToDoItemValidationError> createValidationError(String param, String location, String msg, String value) throws IOException {
        String s = null;
        try {
            s = getObjectMapper().get().writeValueAsString(new  ToDoItemValidationError(location,param,msg,value, "Validation Error"));
        }
        catch (JsonProcessingException e) {
            log.error(" JsonProcessingException : Couldn't Process Json for content type ", e);
        }
        return new ResponseEntity<ToDoItemValidationError>(getObjectMapper().get().readValue(s,ToDoItemValidationError.class),HttpStatus.BAD_REQUEST);

    }
}

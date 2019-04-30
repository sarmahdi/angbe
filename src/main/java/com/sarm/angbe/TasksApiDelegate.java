package com.sarm.angbe;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.model.BalanceTestResult;
import io.swagger.model.ToDoItemValidationError;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.model.ToDoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * A delegate to be called by the {@link TasksApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-04-19T17:35:38.768Z")

public interface TasksApiDelegate {

    Logger log = LoggerFactory.getLogger(TasksApi.class);

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
     * This task validates if the paranthesese are balanced in a given input string. if it is not a valid string it
     * will return a validation error
     * @see TasksApi#tasksValidateBracketsGet
     */
    default ResponseEntity<? extends ToDoResponse> tasksValidateBracketsGet(String  input) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            //            removing to be able to access through a browser easily
//            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    if(!validateInput(input)){
                       return   createValidationError("input ","params"," Input string does not have a paranthesis ",input);

                    }else{
                        boolean isBalanced = checkIfBracketsBalance(input);
                        return new ResponseEntity<BalanceTestResult>(getObjectMapper().get().readValue("{  \"input\" :\" "+ input+"\",  \"isBalanced\" : "+isBalanced+"}", BalanceTestResult.class), HttpStatus.OK);

                    }
                   } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }
//            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default TasksApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * creates a validationError response
     * @param location
     * @param param
     * @param msg
     * @param value
     * @return
     */
    default ResponseEntity<ToDoItemValidationError> createValidationError(String param, String location, String msg, String value) throws IOException {
        String s = null;
        try {
            s = getObjectMapper().get().writeValueAsString(new  ToDoItemValidationError(location,param,msg,value, "Tasks Input String Validation Error"));
        }
        catch (JsonProcessingException e) {
            log.error(" JsonProcessingException : Couldn't Process Json for content type ", e);
        }
        return new ResponseEntity<ToDoItemValidationError>(getObjectMapper().get().readValue(s,ToDoItemValidationError.class),HttpStatus.BAD_REQUEST);

    }

    /**
     * validates the input string to have at least one paranthesis
     * @param input
     * @return
     */
    default Boolean validateInput(String input){
        if (input.contains("(") || input.contains(")") || input.contains("[") || input.contains("}")  || input.contains("{")  || input.contains("}")   )
        {
            return true;
        }else{
            return false;
        }
    }

    Boolean checkIfBracketsBalance(String input);

    void setRequest(HttpServletRequest request);

}

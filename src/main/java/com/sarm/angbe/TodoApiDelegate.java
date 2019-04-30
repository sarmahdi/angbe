package com.sarm.angbe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

/**
 * A delegate to be called by the {@link TodoApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-04-19T17:35:38.768Z")

public interface TodoApiDelegate {

    Logger log = LoggerFactory.getLogger(TodoApi.class);

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
     * facilitates a GET request
     * @see TodoApi#todoIdGet
     */
    default  ResponseEntity<? extends ToDoResponse>  todoIdGet(String  id) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{  \"createdAt\" : \"2017-10-13T01:50:58.735Z\",  \"id\" : 42.0,  \"text\" : \"Uulwi ifis halahs gag erh'ongg w'ssh.\",  \"isCompleted\" : false}", ToDoItem.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default TodoApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * facilitates a Patch request
     * @see TodoApi#todoIdPatch
     */
    default  ResponseEntity<? extends ToDoResponse>  todoIdPatch( String  strId,
         ToDoItemUpdateRequest  body) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{  \"createdAt\" : \"2017-10-13T01:50:58.735Z\",  \"id\" : 42.0,  \"text\" : \"Uulwi ifis halahs gag erh'ongg w'ssh.\",  \"isCompleted\" : false}", ToDoItem.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default TodoApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * Facilitates a post request
     * @see TodoApi#todoPost
     */
    default  ResponseEntity<? extends ToDoResponse>   todoPost( ToDoItemAddRequest  body) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{  \"createdAt\" : \"2017-10-13T01:50:58.735Z\",  \"id\" : 42.0,  \"text\" : \"Uulwi ifis halahs gag erh'ongg w'ssh.\",  \"isCompleted\" : false}", ToDoItem.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default TodoApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    void setRequest(HttpServletRequest request);

    /**
     * Creates a NoteFoundError Response
     * @param id
     * @return
     * @throws IOException
     */
    default  ResponseEntity<ToDoItemNotFoundError> createNotFoundError(Long id) throws IOException {

        String s = null;
        try {
            s = getObjectMapper().get().writeValueAsString(new ToDoItemNotFoundError("NotFoundError",id));
        }
        catch (JsonProcessingException e) {
            log.error(" JsonProcessingException : Couldn't Process Json for content type ", e);
        }
        return new ResponseEntity<ToDoItemNotFoundError>(getObjectMapper().get().readValue(s,ToDoItemNotFoundError.class),HttpStatus.NOT_FOUND);

    }

    /**
     * Creates a ValidationError response
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

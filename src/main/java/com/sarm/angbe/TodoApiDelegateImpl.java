package com.sarm.angbe;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.ToDoItem;
import io.swagger.model.ToDoItemAddRequest;
import io.swagger.model.ToDoItemUpdateRequest;
import io.swagger.model.ToDoResponse;
import io.swagger.model.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Service
public class TodoApiDelegateImpl implements TodoApiDelegate {

    HttpServletRequest request = null;

    @Autowired
    TodoItemRepository todoItemRepository;

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.of(new ObjectMapper());
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.of(request);
    }

    /**
     *  Facilitates a GET request
     *  It can throw a validation error if the id is non numeric
     *  or it can throw a notFoundError if the id is not used in the database and has no record for that id
     * @param strId
     * @return
     */
   @Override
    public ResponseEntity<? extends ToDoResponse> todoIdGet(String  strId) {
        Long id = null;
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            //            removing to be able to access through a browser easily
//            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    boolean isNumeric = strId.chars().allMatch( Character::isDigit );
                    if (! isNumeric || strId.isEmpty()){
                        return  createValidationError("id","params","id is either empty or is not a number so cannot retrieve a todo item ",strId);
                    }else{
                        id = Long.valueOf(strId);
                    }


                    Optional<ToDoItem> toDoItem = todoItemRepository.findById(id);
                    if(toDoItem.isPresent()){
                        return  new ResponseEntity<>(getObjectMapper().get().readValue("{  \"createdAt\" : \" "+toDoItem.map(ToDoItem::getCreatedAt).orElse("NA") + "\",  \"id\" : "+ toDoItem.map(ToDoItem::getId).map(String::valueOf).orElse( "0" )+",  \"text\" : \" "+ toDoItem.map(ToDoItem::getText).orElse("NA")+"\",  \"isCompleted\" : "+toDoItem.map(ToDoItem::isIsCompleted).orElse(false)+"}", ToDoItem.class), HttpStatus.OK);

                    }else{
                    return createNotFoundError(id);
                    }
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }catch (Exception ex){
                    log.error("Exception: "+ ex.getMessage());
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);

                }
//            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default TodoApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }


    /**
     * Facilitates the Patch request
     * Handles partial updates.
     * checks if the id supplied is a valid long Id
     * Checks if the id does exist in the system
     *
     * @see TodoApi#todoIdPatch
     */
    @Override
    public ResponseEntity<? extends ToDoResponse> todoIdPatch( String  strId,
                                                  ToDoItemUpdateRequest  body) {
        Long id = null;
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    boolean isNumeric = strId.chars().allMatch( Character::isDigit );
                    if (! isNumeric || strId.isEmpty()){
                        return   createValidationError("id","params","id is either empty or is not a number so cannot retrieve a todo item ",strId);
                    }else{
                        id = Long.valueOf(strId);
                    }

                    // retrieve the item by Id
                    Optional<ToDoItem> toDoItem = todoItemRepository.findById(id);
                    // check if the item is even present
                    if (toDoItem.isPresent()){
                        // do partial Update, let not the Null value override the original value
                        toDoItem.get().isCompleted( body.isIsCompleted()!=null ? body.isIsCompleted() : toDoItem.get().isIsCompleted());

                        if (body.getText()==(null) || body.getText().contentEquals("") || body.getText().isEmpty() || body.getText().trim().isEmpty() || !StringUtils.hasText(body.getText())){
                            toDoItem.get().setText( toDoItem.get().getText() );

                        }else {
                            toDoItem.get().setText( body.getText() );
                        }
                        todoItemRepository.saveAndFlush(toDoItem.get());
                        return  new ResponseEntity<>(getObjectMapper().get().readValue("{  \"createdAt\" : \" "+toDoItem.map(ToDoItem::getCreatedAt).orElse("NA") + "\",  \"id\" : "+ toDoItem.map(ToDoItem::getId).map(String::valueOf).orElse( "0" )+",  \"text\" : \" "+ toDoItem.map(ToDoItem::getText).orElse("NA")+"\",  \"isCompleted\" : "+toDoItem.map(ToDoItem::isIsCompleted).orElse(false)+"}", ToDoItem.class), HttpStatus.OK);

                    }else{
                        return createNotFoundError(id);

                    }
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default TodoApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * facilitates a POST request
     * Checks is the body is empty or just spaces before adding or raises a Validation error
     * @see TodoApi#todoPost
     */
    @Override
    public ResponseEntity<? extends ToDoResponse> todoPost( ToDoItemAddRequest  body) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {

                    if (body.getText()==(null) || body.getText().contentEquals("") || body.getText().isEmpty() || body.getText().trim().isEmpty() || !StringUtils.hasText(body.getText())){
                           return createValidationError("text","params","text is null or empty, cannot create a todo item with 0 characters, must have 1 non space character at least ",body.getText());

                    }else{
                        ToDoItem toDoItem = new ToDoItem();
                        toDoItem.setText(body.getText());
                        toDoItem.isCompleted(false);
                        toDoItem.setCreatedAt(new Date().toString());
                        ToDoItem toDoItemSaved  = todoItemRepository.save(toDoItem);
                        return new ResponseEntity<>(getObjectMapper().get().readValue("{  \"createdAt\" : \" "+ toDoItemSaved.getCreatedAt()+" \",  \"id\" : " +toDoItemSaved.getId()+ ",  \"text\" : \"  "+toDoItemSaved.getText()+"\",  \"isCompleted\" : false}", ToDoItem.class), HttpStatus.OK);

                    }

                   } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default TodoApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }


    @Override
    public  void setRequest(HttpServletRequest request){
        this.request = request;
    }

}

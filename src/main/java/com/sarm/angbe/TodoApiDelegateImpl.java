package com.sarm.angbe;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.ToDoItem;
import io.swagger.model.ToDoItemAddRequest;
import io.swagger.model.ToDoItemUpdateRequest;
import io.swagger.model.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.atomic.LongAdder;

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


   @Override
    public ResponseEntity<ToDoItem> todoIdGet( Long  id) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
//            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    Optional<ToDoItem> toDoItem = todoItemRepository.findById(id);
                  return  new ResponseEntity<>(getObjectMapper().get().readValue("{  \"createdAt\" : \" "+toDoItem.map(ToDoItem::getCreatedAt).orElse("NA") + "\",  \"id\" : "+ toDoItem.map(ToDoItem::getId).map(String::valueOf).orElse( "0" )+",  \"text\" : \" "+ toDoItem.map(ToDoItem::getText).orElse("NA")+"\",  \"isCompleted\" : "+toDoItem.map(ToDoItem::isIsCompleted).orElse(false)+"}", ToDoItem.class), HttpStatus.NOT_IMPLEMENTED);
//                    return new ResponseEntity<>(getObjectMapper().get().readValue("{  \"createdAt\" : \"2017-10-13T01:50:58.735Z\",  \"id\" : 42.0,  \"text\" : \"Uulwi ifis halahs gag erh'ongg w'ssh.\",  \"isCompleted\" : false}", ToDoItem.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }catch (Exception ex){
                    log.error("Exception: "+ ex.getMessage());
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

                }
//            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default TodoApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * @see TodoApi#todoIdPatch
     */
    @Override
    public ResponseEntity<ToDoItem> todoIdPatch( Long  id,
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
     * @see TodoApi#todoPost
     */
    @Override
    public ResponseEntity<ToDoItem> todoPost( ToDoItemAddRequest  body) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    ToDoItem toDoItem = new ToDoItem();
                    toDoItem.setText(body.getText());
                    toDoItem.isCompleted(false);
                    toDoItem.setCreatedAt(new Date().toString());
                    ToDoItem toDoItemSaved  = todoItemRepository.save(toDoItem);
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{  \"createdAt\" : \" "+ toDoItemSaved.getCreatedAt()+" \",  \"id\" : " +toDoItemSaved.getId()+ ",  \"text\" : \"  "+toDoItemSaved.getText()+"\",  \"isCompleted\" : false}", ToDoItem.class), HttpStatus.NOT_IMPLEMENTED);
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
    @Override
    public  void setRequest(HttpServletRequest request){
        this.request = request;
    }

}

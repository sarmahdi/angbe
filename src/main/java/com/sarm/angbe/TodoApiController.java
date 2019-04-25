package com.sarm.angbe;

import io.swagger.model.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-04-19T17:35:38.768Z")

@RestController
public class TodoApiController implements TodoApi {

    private final TodoApiDelegate delegate;


    @org.springframework.beans.factory.annotation.Autowired
    public TodoApiController(TodoApiDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public TodoApiDelegate getDelegate() {
        return delegate;
    }
}

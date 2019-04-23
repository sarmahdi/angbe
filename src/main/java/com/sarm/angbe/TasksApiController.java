package com.sarm.angbe;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-04-19T17:35:38.768Z")

@RestController
public class TasksApiController implements TasksApi {

    private final TasksApiDelegate delegate;

    @org.springframework.beans.factory.annotation.Autowired
    public TasksApiController(TasksApiDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public TasksApiDelegate getDelegate() {
        return delegate;
    }
}

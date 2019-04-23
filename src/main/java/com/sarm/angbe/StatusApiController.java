package com.sarm.angbe;

import org.springframework.stereotype.Controller;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-04-19T17:35:38.768Z")

@Controller
public class StatusApiController implements StatusApi {

    private final StatusApiDelegate delegate;

    @org.springframework.beans.factory.annotation.Autowired
    public StatusApiController(StatusApiDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public StatusApiDelegate getDelegate() {
        return delegate;
    }
}

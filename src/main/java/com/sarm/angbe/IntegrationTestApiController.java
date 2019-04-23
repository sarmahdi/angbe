package com.sarm.angbe;

import org.springframework.stereotype.Controller;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-04-19T17:35:38.768Z")

@Controller
public class IntegrationTestApiController implements IntegrationTestApi {

    private final IntegrationTestApiDelegate delegate;

    @org.springframework.beans.factory.annotation.Autowired
    public IntegrationTestApiController(IntegrationTestApiDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public IntegrationTestApiDelegate getDelegate() {
        return delegate;
    }
}

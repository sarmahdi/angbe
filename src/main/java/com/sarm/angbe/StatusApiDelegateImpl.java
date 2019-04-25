package com.sarm.angbe;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.StatusResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class StatusApiDelegateImpl implements StatusApiDelegate {

    HttpServletRequest request = null;

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.of(new ObjectMapper());
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.of(request);
    }

    @Override
    public  void setRequest(HttpServletRequest request){
        this.request = request;
    }

}

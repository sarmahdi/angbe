package com.sarm.angbe;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.ToDoItem;
import io.swagger.model.ToDoItemAddRequest;
import io.swagger.model.ToDoItemUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TodoApiDelegateImpl implements TodoApiDelegate {
    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    @Override
    public Optional<String> getAcceptHeader() {
        return Optional.empty();
    }

    @Override
    public ResponseEntity<ToDoItem> todoIdGet(BigDecimal id) {
        return null;
    }

    @Override
    public ResponseEntity<ToDoItem> todoIdPatch(BigDecimal id, ToDoItemUpdateRequest body) {
        return null;
    }

    @Override
    public ResponseEntity<ToDoItem> todoPost(ToDoItemAddRequest body) {
        return null;
    }
}

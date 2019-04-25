package com.sarm.angbe;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.BalanceTestResult;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.Stack;

@Service
public class TasksApiDelegateImpl implements TasksApiDelegate {

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
    public Boolean checkIfBracketsBalance(String input) {
            Stack<Character> stack  = new Stack<>();
            for(int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                if(c == '[' || c == '(' || c == '{' ) {
                    stack.push(c);
                }  else if(c == ')') {
                    if(stack.isEmpty() || stack.pop() != '(') {
                        return false;
                    }
                } else if(c == '}') {
                    if(stack.isEmpty() || stack.pop() != '{') {
                        return false;
                    }
                }else if(c == ']') {
                    if(stack.isEmpty() || stack.pop() != '[') {
                        return false;
                    }
                }

            }
            return stack.isEmpty();
        }

    @Override
    public void setRequest(HttpServletRequest request) {
        this.request= request;
    }


}

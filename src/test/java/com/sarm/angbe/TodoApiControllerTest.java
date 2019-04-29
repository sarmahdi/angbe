package com.sarm.angbe;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@Transactional
@SpringBootTest
//@WebMvcTest(value = TodoApiController.class, secure = false)
public class TodoApiControllerTest {
    HttpServletRequest httpRequest = Mockito.mock(HttpServletRequest.class);
    TodoApiController todoApiController;
    @Before
    public void setUp() throws Exception {
        todoApiDelegate.setRequest(httpRequest);
        org.mockito.Mockito.when(httpRequest.getHeader("Accept")).thenReturn("application/json");
        todoApiController = new TodoApiController(todoApiDelegate);
    }

    @After
    public void tearDown() throws Exception {
    }

//    @Autowired
//    private MockMvc mockMvc;

//    @MockBean
    @Autowired
    private TodoApiDelegate todoApiDelegate;

    @Test
    public void testTodo(){

        ToDoItemAddRequest toDoItemAddRequest = new ToDoItemAddRequest();
        toDoItemAddRequest.setText("MytodoPost");
        ResponseEntity<? extends ToDoResponse> responseEntityPost = todoApiController.todoPost(toDoItemAddRequest,httpRequest);
        assertThat(responseEntityPost.getBody(), instanceOf(ToDoItem.class)  );
        assertEquals(responseEntityPost.getStatusCode(), HttpStatus.OK);

        ResponseEntity<? extends ToDoResponse> responseEntityGet = todoApiController.todoIdGet("1",httpRequest);
        assertThat(responseEntityGet.getBody(), instanceOf(ToDoItem.class)  );
        assertEquals(((ToDoItem)responseEntityGet.getBody()).getText().trim(),"MytodoPost");
        assertEquals(responseEntityGet.getStatusCode(), HttpStatus.OK);


        ToDoItemUpdateRequest toDoItemUpdateRequest = new ToDoItemUpdateRequest();
        toDoItemUpdateRequest.setIsCompleted(true);
        ResponseEntity<? extends ToDoResponse> responseEntityPatch = todoApiController.todoIdPatch("1",toDoItemUpdateRequest,httpRequest);
        assertThat(responseEntityGet.getBody(), instanceOf(ToDoItem.class)  );
        assertEquals(((ToDoItem)responseEntityPatch.getBody()).getText().trim(),"MytodoPost");
        assertEquals(((ToDoItem)responseEntityPatch.getBody()).isIsCompleted(),true);
        assertEquals(responseEntityPatch.getStatusCode(), HttpStatus.OK);

    }

    @Test
    public void testTodoNotFoundError(){
        ResponseEntity<? extends ToDoResponse> responseEntityGet = todoApiController.todoIdGet("1",httpRequest);
        assertThat(responseEntityGet.getBody(), instanceOf(ToDoItemNotFoundError.class)  );
        assertEquals(responseEntityGet.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void testTodoNotValid(){
        ResponseEntity<? extends ToDoResponse> responseEntityGet = todoApiController.todoIdGet("1e",httpRequest);
        assertThat(responseEntityGet.getBody(), instanceOf(ToDoItemValidationError.class)  );
        assertEquals(responseEntityGet.getStatusCode(), HttpStatus.BAD_REQUEST);


        ToDoItemAddRequest toDoItemAddRequest = new ToDoItemAddRequest();
        toDoItemAddRequest.setText("    ");
        ResponseEntity<? extends ToDoResponse> responseEntityPost = todoApiController.todoPost(toDoItemAddRequest,httpRequest);
        assertThat(responseEntityPost.getBody(), instanceOf(ToDoItemValidationError.class)  );
        assertEquals(((ToDoItemValidationError)responseEntityPost.getBody()).getDetails().get(0).getParam(),"text");
        assertEquals(responseEntityPost.getStatusCode(), HttpStatus.BAD_REQUEST);

    }


//    @Test
//    public void testPost(){
//        String payload = "{\n" +
//                "  \"text\": \"myfirsttodo\",\n" +
//                "}";
//        ToDoItem mockTodoItem = new ToDoItem();
//        ResponseEntity<? extends ToDoResponse> responseEntity= null;
//
//        try {
//            responseEntity = new ResponseEntity<>(new ObjectMapper().readValue("{  \"createdAt\" : \"2017-10-13T01:50:58.735Z\",  \"id\" : 42.0,  \"text\" : \"Uulwi ifis halahs gag erh'ongg w'ssh.\",  \"isCompleted\" : false}", ToDoItem.class), HttpStatus.OK);
//
//
//
//        // studentService.addCourse to respond back with mockCourse
//        Mockito.when(
//                todoApiDelegate.todoPost(Mockito.any(ToDoItemAddRequest.class))).thenReturn(new ResponseEntity<>(new ObjectMapper().readValue("{  \"createdAt\" : \"2017-10-13T01:50:58.735Z\",  \"id\" : 42.0,  \"text\" : \"Uulwi ifis halahs gag erh'ongg w'ssh.\",  \"isCompleted\" : false}", ToDoItem.class), HttpStatus.OK));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        // Send course as body to /students/Student1/courses
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .post("/students/Student1/courses")
//                .accept(MediaType.APPLICATION_JSON).content(payload)
//                .contentType(MediaType.APPLICATION_JSON);
//
//        MvcResult result = null;
//        try {
//            result = mockMvc.perform(requestBuilder).andReturn();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        MockHttpServletResponse response = result.getResponse();
//
//
//    }


}
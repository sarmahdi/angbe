package com.sarm.angbe;

import io.swagger.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 *
 * test class for ToDoApiController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@Transactional
@SpringBootTest
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


    @Autowired
    private TodoApiDelegate todoApiDelegate;

    /**
     * tests a POST request then does a GET for the id 1 and then updates the id 1
     */
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

    /**
     * tests a NotFoundError Response
     */
    @Test
    public void testTodoNotFoundError(){
        ResponseEntity<? extends ToDoResponse> responseEntityGet = todoApiController.todoIdGet("1",httpRequest);
        assertThat(responseEntityGet.getBody(), instanceOf(ToDoItemNotFoundError.class)  );
        assertEquals(responseEntityGet.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    /**
     * Tests a Validation Error Response
     */
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




}
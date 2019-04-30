package com.sarm.angbe;

import io.swagger.model.BalanceTestResult;
import io.swagger.model.ToDoItemValidationError;
import io.swagger.model.ToDoResponse;
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
 * test Class for TasksApiController
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@Transactional
@SpringBootTest
public class TasksApiControllerTest {
    HttpServletRequest httpRequest = Mockito.mock(HttpServletRequest.class);
    TasksApiController tasksApiController;

    @Autowired
    TasksApiDelegate tasksApiDelegate;


    /**
     * tests the validateBrackets task
     */
    @Test
    public void testTasks(){

        ResponseEntity<? extends ToDoResponse> responseEntity = tasksApiController.tasksValidateBracketsGet("123(asd)",httpRequest );
        assertThat(responseEntity.getBody(), instanceOf(BalanceTestResult.class)  );
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testTasksNotValid(){

        ResponseEntity<? extends ToDoResponse> responseEntity = tasksApiController.tasksValidateBracketsGet("123werwer",httpRequest );
        assertThat(responseEntity.getBody(), instanceOf(ToDoItemValidationError.class)  );
        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }





    @Before
    public void setUp() throws Exception {
        tasksApiDelegate.setRequest(httpRequest);
        org.mockito.Mockito.when(httpRequest.getHeader("Accept")).thenReturn("application/json");
         tasksApiController = new TasksApiController(tasksApiDelegate);
    }

    @After
    public void tearDown() throws Exception {
    }
}
package org.agm.sbtest.api.tasks;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.agm.sbtest.entities.Task;
import org.agm.sbtest.repositories.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class TaskControllerTest {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskRepository repository;

    @Test
    public void findAllTest() {
        when(repository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<Iterable<Task>> response = taskController.findAll();

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().iterator().hasNext(), false);

        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
    }
}

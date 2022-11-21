package net.jsc.example.todo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ToDoServiceTest {

    @Autowired
    private ToDoRepository toDoRepository;

    @Test
    void getAllToDos(){
        ToDo toDoSample = new ToDo("ToDo Sample 1", true);
        toDoRepository.save(toDoSample);
        ToDoService toDoService = new ToDoService(toDoRepository);

        ToDo firstResult = toDoService.findAll().get(0);

        assertEquals(toDoSample.getText(), firstResult.getText());
        assertEquals(toDoSample.isCompleted(), firstResult.isCompleted());
        assertEquals(toDoSample.getId(), firstResult.getId());

    }
}

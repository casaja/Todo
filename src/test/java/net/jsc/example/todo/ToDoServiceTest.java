package net.jsc.example.todo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ToDoServiceTest {

    @Autowired
    private ToDoRepository toDoRepository;

    @AfterEach
    void tearDown() {
        toDoRepository.deleteAll();
    }

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

    @Test
    void saveAToDo() {
        ToDoService toDoService = new ToDoService(toDoRepository);
        ToDo todoSample = new ToDo("ToDO Sample 1", true);

        toDoService.save(todoSample);

        assertEquals(1.0, toDoRepository.count());
    }

    @Test
    void updateAToDo() {
        ToDo toDoSample = new ToDo(1L,"ToDo Sample 1", true);
        toDoRepository.save(toDoSample);

        ToDo toDoSampleUpdated = new ToDo(1L, "ToDo Sample Update 1", true);
        ToDoService toDoService = new ToDoService(toDoRepository);
        toDoService.update(toDoSampleUpdated);

        ToDo toDoUpdated = toDoRepository.findById(1L).get();
        assertEquals(toDoSample.getId(), toDoUpdated.getId());
        assertEquals(toDoSampleUpdated.getText(), toDoUpdated.getText());
        assertEquals(toDoSampleUpdated.isCompleted(), toDoUpdated.isCompleted());


    }
}

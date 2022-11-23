package net.jsc.example.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    @GetMapping("/todos")
    ResponseEntity<List<ToDo>> getAllToDos() {
        return new ResponseEntity<>(toDoService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/todos")
    ResponseEntity<ToDo> create(@RequestBody ToDo toDo) {
        return new ResponseEntity<>(toDoService.save(toDo), HttpStatus.CREATED);
    }

    @PutMapping("/todos")
    ResponseEntity<ToDo> update(@RequestBody ToDo toDo) {
        return new ResponseEntity<>(toDoService.update(toDo), HttpStatus.OK);
    }
}

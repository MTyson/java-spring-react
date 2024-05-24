package com.example.iwreactspring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.iwreactspring.model.TodoItem;
import com.example.iwreactspring.service.TodoService;

@RestController
//@CrossOrigin(origins = "http://34.132.155.86:3000") // Allow CORS requests from React on port 3000 (change if needed)
@CrossOrigin(origins = "https://iw-react-java-part3-frontend.vercel.app,https://iw-react-java-part3-frontend-vizl.vercel.app,http://35.232.114.55:3000")
public class MyController {

  @Autowired
  private TodoService todoService;

  @GetMapping("/todos")
  public ResponseEntity<List<TodoItem>> getTodos() {
    System.out.println("get todos: " + todoService.getTodos().size());
    return new ResponseEntity<>(todoService.getTodos(), HttpStatus.OK);
  }

  @PostMapping("/todos")
  public ResponseEntity<TodoItem> createTodo(@RequestBody TodoItem newTodo) {
    TodoItem todo = todoService.createTodo(newTodo);
    return new ResponseEntity<>(todo, HttpStatus.CREATED);
  }

  // Update (toggle completion) a TODO item
  @PutMapping("/todos/{id}")
  public ResponseEntity<TodoItem> updateTodoCompleted(@PathVariable String id) {
    System.out.println("BEGIN update: " + id);

    TodoItem todo = todoService.getTodo(id);
    if (todo != null) {
      todo.setCompleted(!todo.isCompleted());
      todoService.saveTodo(todo);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  @DeleteMapping("/todos/{id}")
  public ResponseEntity<Void> deleteTodo(@PathVariable String id) {
    System.out.println("BEGIN delete: " + id);
    boolean deleted = todoService.deleteTodo(id);
    System.out.println("deleted: " +deleted);
    if (deleted) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
 
}


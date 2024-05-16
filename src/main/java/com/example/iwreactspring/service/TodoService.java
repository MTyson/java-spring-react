package com.example.iwreactspring.service;

import java.util.List;
import java.util.ArrayList;
import com.example.iwreactspring.model.TodoItem;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.Document;

import com.example.iwreactspring.repository.TodoRepository;

@Service
public class TodoService {

  @Autowired
  private TodoRepository todoRepository;

  public List<TodoItem> getTodos() {
    // Use the injected TodoRepository to retrieve all todos from the database
    return todoRepository.findAll();
  }

public TodoItem createTodo(TodoItem newTodo) {
    TodoItem savedTodo = todoRepository.save(newTodo);
    return savedTodo;
  }
public TodoItem getTodo(String id) {
    // Use findById to retrieve the todo item with the given id
    return todoRepository.findById(id).orElse(null);
  }
 /* public boolean updateTodo(TodoItem todoItem) {
	  return true;
  }*/

  public boolean deleteTodo(String id) {
    // Check if the todo exists before deletion
    TodoItem todoToDelete = getTodo(id);
    if (todoToDelete != null) {
      todoRepository.deleteById(id);
      return true;
    } else {
      // Handle case where the todo with the given id doesn't exist
      return false;
    }
  }
  public TodoItem saveTodo(TodoItem todoItem) {
    // Leverage Spring Data MongoDB's save method
    TodoItem savedTodo = todoRepository.save(todoItem);
    return savedTodo;
  }
  
}


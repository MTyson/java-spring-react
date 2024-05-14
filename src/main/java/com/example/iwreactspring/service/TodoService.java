package com.example.iwreactspring.service;

import java.util.List;
import java.util.ArrayList;
import com.example.iwreactspring.model.TodoItem;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

  private static final List<TodoItem> items = new ArrayList<>();

  static {
    items.add(new TodoItem(0, "Watch the sunrise"));
    items.add(new TodoItem(1, "Read Swami Venkatesananda's Supreme Yoga"));
    items.add(new TodoItem(2, "Watch the mind"));
    items.add(new TodoItem(3, "Foobar"));
  }

  public List<TodoItem> getTodos() {
    return new ArrayList<>(items); // Return a copy to avoid modification of original list
  }

  public TodoItem getTodo(Integer id) {
    return items.stream()
        .filter(item -> item.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

  public TodoItem createTodo(TodoItem newTodo) {
    Integer nextId = items.stream().mapToInt(TodoItem::getId).max().orElse(0) + 1;
    newTodo.setId(nextId);
    items.add(newTodo);
    return newTodo;
  }

  public boolean updateTodo(TodoItem todoItem) {
    int index = items.indexOf(getTodo(todoItem.getId()));
    if (index != -1) {
      items.set(index, todoItem);
      return true;
    } else {
      return false;
    }
  }
  public boolean deleteTodo(Integer id) {
    for (int i = items.size() - 1; i >= 0; i--) {
      if (items.get(i).getId().equals(id)) {
        items.remove(i);
        return true; // Item found and deleted
       }
     }
    return false; // Item not found
  }
}


package com.example.iwreactspring.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "todo") // Optional: Specify the collection name
public class TodoItem {

  @Id
  private String id;

  private boolean completed;

  private String description;

  public TodoItem(String id, String description) {
    this.description = description;
    this.completed = false;
    this.id = id;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  public boolean isCompleted() {
    return completed;
  }

  public String getDescription() {
    return description;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return id + " " + (completed ? "[COMPLETED] " : "[ ] ") + description;
  }
}


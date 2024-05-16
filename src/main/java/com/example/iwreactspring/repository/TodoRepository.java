package com.example.iwreactspring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.iwreactspring.model.TodoItem;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends MongoRepository<TodoItem, String> {
    
    public long count();

}

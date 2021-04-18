package com.example.demo.todos;

import com.example.demo.error.ConflictException;
import com.example.demo.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TodoService {

    private List<Todo> data = new ArrayList<>(Arrays.asList(
            new Todo("1", "First", "First task"),
            new Todo("2", "Second", "Second task"),
            new Todo("3", "Therd", "Therd task"),
            new Todo("4", "Four", "Four task")));

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> findAll() {
    return todoRepository.findAll();
        //return data;
    }

    public Todo findById(String id) {
        try {
            todoRepository.findById(id);
        } catch (Exception e) {
            throw new NotFoundException("No record ID was found");
        }

        return null;
    }

    public Todo save(Todo todo) {
        if (todoRepository.findByTitle(todo.getTitle())!=null){
            throw new ConflictException("Another record with the same title exists");
        }
        return todoRepository.insert(todo);
    }

    public void delete(String id) {
        todoRepository.deleteById(id);
    }
}

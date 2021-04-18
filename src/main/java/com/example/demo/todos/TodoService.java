package com.example.demo.todos;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TodoService {

    private List<Todo> data = new ArrayList<>(Arrays.asList(
            new Todo(1, "First", "First task"),
            new Todo(2, "Second", "Second task"),
            new Todo(3, "Therd", "Therd task"),
            new Todo(4, "Four", "Four task")));

    public List<Todo> findAll() {
        return data;
    }

    public Todo findById(int id) {
        for (Todo m : data) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    public boolean save(Todo todo) {
        return data.add(todo);
    }

    public void delete(int id) {
        for (Todo m : data) {
            if (m.getId() == id) {
                data.remove(m);
            }
        }
    }
}

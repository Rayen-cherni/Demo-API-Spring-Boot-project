package com.example.demo.todos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/todos")
public class TodoController {

    //*** Soit on utiliser @AutoWired pour acceder a la Service Layer, soir on creer un constructor
    // dans TodoController et on passer le TodoService as parametre ****
    @Autowired
    private TodoService todoService;

    //** pour acceder a cette URL il faut taper "/api/v1/todos/" ***

    //@GetMapping(value = "/")
    //***** ici on dit si l'user tapper "/" comme URL, lancer cette fonction et de meme si l'user ne taper rien. ***
    @GetMapping(value = {"", "/"})
    public List<Todo> getAllTodo() {
        return todoService.findAll();
    }
    //@GetMapping(value = {"", "/"})
    //    public ResponseEntity<List<Todo>> getAllTodo() {
    //        List<Todo> result = todoService.findAll();
        //return new ResponseEntity<List<Todo>>(result,HttpStatus.OK);
    //    }


    @GetMapping(value = "/{id}")
    public Todo getTodoById(@PathVariable int id) {
        return todoService.findById(id);
    }
    //@GetMapping(value = "/{id}")
    //    public ResponseEntity<Todo> getTodoById(@PathVariable int id) {
    //        Todo result = todoService.findById(id);
    //  return new ResponseEntity<Todo>(result,HttpSTatus.OK);
    //    }

    //**** les variables necessaire pour creer la classe todo seront dispo dans le body request *****
    //Si vous besion d'utiliser cette methode n'oubliez pas d'ajouter le default constructor dans la classe todo
    //*** @Validated cad va faire une controle sur les validateurs de cette classe
    @PostMapping(value = "/createNewTodo")
    public Todo createNewTodo(@Validated @RequestBody Todo todo) {
        if (todoService.save(todo)) {
            return todo;
        } else {
            return null;
        }
    }
    //**** Dans ce cas, on va retouner une reponse configurable *******
    //@PostMapping(value = "/createNewTodo")
    //public ResponseEntity<Todo> createNewTodo(@Validated @RequestBody Todo todo) {
     // Todo result = todoService.save(todo);
    // return new ResponseEntity<Todo>(result,HttpStatus.CREATED);
    //}

    @DeleteMapping(value = "/deleteTodo/{id}")
    public void deleteTodo(@PathVariable int id){
        todoService.delete(id);
    }
    //public ResponseEntity<void> deleteTodo(@PathVariable int id){
    //        new ResponseEntity<void>(HttpStatus.NO_CONTENT);
    //    }
}

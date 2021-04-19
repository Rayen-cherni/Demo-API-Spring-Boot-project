package com.example.demo;

import com.example.demo.todos.Todo;
import com.example.demo.todos.TodoRepository;
import com.example.demo.todos.TodoService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;


//With @RunWith(SpringRunner.class) le projet mahush bch tsiro run ama bch najmo naamlo test
@RunWith(SpringRunner.class)
public class TodoServiceTest {

    // with @RunWith l autowired msh bch tekhdem khater spring boot ptoject fl background masarloush execution
    @Autowired
    private TodoService todoService;

    //Solution
    @TestConfiguration
    static class TodoServiceContextConfiguration {
        @Bean
        public TodoService todoService() {
            return new TodoService();
        }
    }

    //Vue que manesh aamlin run ll spring boot project o manes haamlin run ll DB, @MockBean eibara kotlo aamli enty
    // simulation hata b fakeData.
    @MockBean
    private TodoRepository todoRepository;

    @Test
    public void whenFindAll_ReturnTodoList() {
        //Mockup (Fake data)
        Todo todo1 = new Todo("1", "todo1", "todo1");
        Todo todo2 = new Todo("2", "todo2", "todo2");
        ArrayList<Todo> data = new ArrayList<Todo>();
        data.add(todo1);
        data.add(todo2);

        //Ici on va dire a TodoRepository prend le tableau data as DB
        BDDMockito.given( todoRepository.findAll()).willReturn(data);

        Assertions.assertThat(todoService.findAll()).isEqualTo(data).hasSize(2);
    }

    @Test
    public void whenFindById_returnTodoItem(){
        Todo todo = new Todo("1", "todo1", "todo1");
        BDDMockito.given( todoRepository.findById(anyString())).willReturn(Optional.ofNullable(todo));
        Assertions.assertThat(todoService.findById("0").getTitle()).containsIgnoringCase("todo");
    }

}

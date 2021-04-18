package com.example.demo.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping(value = "/")
    public String greeting(){
        return "Hello to my demo project";
    }

    //@PathVariable cad que le parametre sera passer dans l'URL
    //GETMapping est equivalent a RequestMapping
    @GetMapping(value = "/{name}")
    public String greetingWithName(@PathVariable String name){
        return String.format("Hello %s",name);
    }
}

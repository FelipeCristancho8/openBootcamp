package com.build;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hola")
public class Controller {

    @Value("${app.username}")
    private String username;

    @GetMapping()
    public String hola(){
        System.out.println(username);
        return "Hola mundo";
    }
}

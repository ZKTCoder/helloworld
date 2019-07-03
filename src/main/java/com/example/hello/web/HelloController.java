package com.example.hello.web;

import com.example.hello.model.User;
import com.example.hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    UserService userService;

    @RequestMapping("/hello")
    public String index() {
        User user = userService.findUser();
        return "Hello World " + user.getName() + "!";
    }

}

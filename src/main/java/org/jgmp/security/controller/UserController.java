package org.jgmp.security.controller;

import org.jgmp.security.domain.UserDto;
import org.jgmp.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public String register(@RequestBody UserDto user) {
        System.out.println("USER = = = = = =  = =" + user.getUserName());
        userService.registerUser(user.getUserName(), user.getPassword());
        return "ok";
    }

}

package org.jgmp.security.controller;

import java.util.List;

import org.jgmp.security.service.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private LoginAttemptService loginAttemptService;

    @GetMapping
    public List<String> getBlocked() {
        return loginAttemptService.getBlockedList();
    }

}

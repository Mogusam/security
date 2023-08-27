package org.jgmp.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String getRoot() {
        return "Root response";
    }

    @GetMapping("/about")
    public String getHome() {
        return "<H1> WELCOME  TO  HOME PAGE !!!</H1>";
    }

    @GetMapping("/info")
    public String getInfo() {
        return """
                <H1>
                    MVC APPLICATION
                </H1>
                """;
    }


}

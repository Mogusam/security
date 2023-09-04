package org.jgmp.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String getRoot() {
        return "Root response";
    }

    @GetMapping("/about")
    public String getHome(Model model) {
        return "index";
    }

    @GetMapping("/info")
    public String getInfo() {
        return "info";
//        return """
//                <H1>
//                   INFO  MVC APPLICATION
//                </H1>
//                """;
    }

    @GetMapping("/admin")
    public String getAdmin() {
        return "admin";
//        return """
//                <H1>
//                   ADMIN PAGE
//                </H1>
//                """;
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }
}

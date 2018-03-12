package com.codingdojo.portfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@Controller
public class PortfolioController {
    @RequestMapping("/")
    public String index() {
        return "intro.html";
    }

    @RequestMapping("/projects")
    public String projects() {
        return "projects.html";
    }

    @RequestMapping("/about")
    public String about() {
        return "about.html";
    }
}

package com.codingdojo.hellohuman.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/")
public class HelloController {
        @RequestMapping("/")
        public String home(@RequestParam(value="firstname",defaultValue="Human") String firstname, @RequestParam(value="lastname",defaultValue="Jones") String lastname, Model model) {
            model.addAttribute("firstname",firstname);
            model.addAttribute("lastname",lastname);
            return "hello";
        }
}

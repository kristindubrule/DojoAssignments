package com.codingdojo.platform.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import org.springframework.ui.Model;

@Controller
public class PlatformController {
    private HashMap<String,String> content = new HashMap<String,String>();

    public PlatformController() {
        content.put("0733","Setting up your servers");
        content.put("0345","Coding Forms");
        content.put("0342","Punch Cards");
        content.put("0348","Advanced Fortran Intro");
        content.put("2342","Fortran to Binary");
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/m/{chapter}/{type}/{number}")
    public String content(@PathVariable String type, @PathVariable String number, Model model) {
        String path;
        if (type.equalsIgnoreCase("0483")) {
            path = "assignment";
        } else {
            path = "lesson";
        }
        model.addAttribute("content",content.get(number));
        return path;
    }

}

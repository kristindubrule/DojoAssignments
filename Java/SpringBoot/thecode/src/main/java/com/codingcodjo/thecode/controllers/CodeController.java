package com.codingcodjo.thecode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CodeController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value="/checkcode", method = RequestMethod.POST)
    public String checkcode(@RequestParam(value="code") String codeguess, RedirectAttributes redirectAttributes) {
        if (codeguess.equals("bushido")) {
            return "correct";
        } else {
            redirectAttributes.addFlashAttribute("incorrect","You must try harder!");
            return "redirect:/";
        }
    }
}

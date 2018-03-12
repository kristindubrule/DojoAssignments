package com.codingdojo.counter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class CounterController {
    @RequestMapping("/")
    public String index(HttpSession session) {
        initializeCounter(session, 1);
        return "index";
    }

    @RequestMapping("/Counter")
    public String counter(HttpSession session) {
        initializeCounter(session,0);
        return "counter";
    }

    @RequestMapping("/by2")
    public String by2(HttpSession session) {
        initializeCounter(session, 2);
        return "by2";
    }

    @RequestMapping("/reset")
    public String reset(HttpSession session, HttpServletRequest request) {
        session.setAttribute("counter",0);
        String referer = request.getHeader("Referer");
        return "redirect:"+referer;
    }

    private void initializeCounter(HttpSession session, int increment) {
        Integer counter = (Integer)session.getAttribute("counter");
        if (counter == null)
            counter = increment;
        else if (increment != 0)
            counter += increment;
        session.setAttribute("counter",counter);
    }
}

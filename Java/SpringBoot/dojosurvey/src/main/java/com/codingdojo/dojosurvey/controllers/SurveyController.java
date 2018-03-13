package com.codingdojo.dojosurvey.controllers;

import com.codingdojo.dojosurvey.models.ModelAttrs;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import com.codingdojo.dojosurvey.models.ModelAttrs;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SurveyController {
    @RequestMapping(value="/",method= RequestMethod.GET)
    public String survey() {
        return "survey";
    }

    @RequestMapping(value="/",method= RequestMethod.POST)
    public String process(@RequestParam(value="name") String name, @RequestParam(value="location") String location,
                          @RequestParam(value="language") String language, @RequestParam(value="comments") String comments,
                          HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        session.setAttribute("surveyresponse",new ModelAttrs(name, location, language, comments));
        return "redirect:/results";
    }

    @RequestMapping(value="/results",method= RequestMethod.GET)
    public String results(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("surveyresponse"));
        model.addAttribute("surveyresponse",session.getAttribute("surveyresponse"));
        session.removeAttribute("surveyresponse");
        return "results";
    }
}


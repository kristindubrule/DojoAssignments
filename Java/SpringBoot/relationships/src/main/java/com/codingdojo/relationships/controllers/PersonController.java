package com.codingdojo.relationships.controllers;

import com.codingdojo.relationships.models.*;
import com.codingdojo.relationships.services.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import org.springframework.ui.Model;

@Controller
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping("/persons/new")
    public String newPerson(@ModelAttribute("errors") String errors, @ModelAttribute("person")Person person) {
        System.out.println("new person");
        return "new_person";
    }

    @PostMapping("/persons/new")
    public String addPerson(@Valid @ModelAttribute("person") Person person, RedirectAttributes redirectAttributes, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/persons/new";
        }
        else {
            personService.addPerson(person);
            model.addAttribute("person",person);
        }
        return "show_person";
    }

    @RequestMapping("/persons/{id}")
    public String showPerson(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Person person = personService.findPersonById(id);
        if (person == null) {
            redirectAttributes.addFlashAttribute("errors","No person found");
            return "redirect:/persons/new";
        } else {
            model.addAttribute(person);
        }
        return "show_person";
    }
}

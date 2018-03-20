package com.codingdojo.pageddojosninjas.controllers;

import com.codingdojo.pageddojosninjas.models.Dojo;
import com.codingdojo.pageddojosninjas.services.DojoService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import javax.validation.Valid;

@Controller
@RequestMapping("/dojos")
public class DojoController {
    private final DojoService dojoService;


    public DojoController(DojoService dojoService) {
        this.dojoService = dojoService;
    }

    @RequestMapping("")
    public String index() {
        return "index";
    }

    @RequestMapping("new")
    public String newDojo(@ModelAttribute("dojo") Dojo dojo, @ModelAttribute("errors") String errors) {
        return "new_dojo";
    }

    @PostMapping("new")
    public String addDojo(@Valid @ModelAttribute("dojo") Dojo dojo, BindingResult result) {
        if (result.hasErrors()) {
            return "new_dojo";
        } else {
            dojoService.addDojo(dojo);
            return "redirect:/dojos/new";
        }
    }

    @RequestMapping("{id}")
    public String showDojo(Model model, @PathVariable(name="id") Long id, RedirectAttributes redirectAttributes) {
        Dojo dojo = dojoService.findById(id);
        if (dojo == null) {
            redirectAttributes.addFlashAttribute("errors","No dojo found");
            return "redirect:/dojos/new";
        } else {
            model.addAttribute("dojo",dojo);
            return "dojo";
        }
    }
}

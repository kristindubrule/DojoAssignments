package com.codingdojo.dojosninjas.controllers;

import com.codingdojo.dojosninjas.models.Ninja;
import com.codingdojo.dojosninjas.services.DojoService;
import com.codingdojo.dojosninjas.services.NinjaService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import javax.validation.Valid;

@Controller
@RequestMapping("/ninjas")
public class NinjaController {
    private final NinjaService ninjaService;
    private final DojoService dojoService;

    public NinjaController(NinjaService ninjaService, DojoService dojoService) {
        this.ninjaService = ninjaService;
        this.dojoService = dojoService;
    }

    @RequestMapping("")
    public String index() {
        return "index";
    }

    @RequestMapping("new")
    public String newNinja(Model model, @ModelAttribute("ninja")Ninja ninja) {
        model.addAttribute("dojos",dojoService.allDojos());
        return "new_ninja";
    }

    @PostMapping("new")
    public String addNinja(@Valid @ModelAttribute("ninja")Ninja ninja, BindingResult result) {
        if (result.hasErrors()) {
            return "new_ninja";
        } else {
            ninjaService.addNinja(ninja);
            return "redirect:/ninjas/new";
        }
    }
}

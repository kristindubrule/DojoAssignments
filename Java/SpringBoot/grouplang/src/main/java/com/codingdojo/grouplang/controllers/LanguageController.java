package com.codingdojo.grouplang.controllers;

import com.codingdojo.grouplang.services.LanguageService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.codingdojo.grouplang.models.Language;
import javax.validation.Valid;

@Controller
public class LanguageController {
    private final LanguageService languageService;

    public LanguageController(LanguageService languageService) { this.languageService = languageService; }

    @RequestMapping("/languages")
    public String show(Model model) {
        model.addAttribute("languages",languageService.allLanguages());
        return "index";
    }

    @RequestMapping("/languages/{id}")
    public String showLanguage(@PathVariable("id") int id, Model model) {
        model.addAttribute("language",languageService.findLanguageByIndex(id));
        return "show";
    }

    @RequestMapping("/languages/new")
    public String newLanguage(@ModelAttribute("language") Language language) {
        return "new";
    }

    @PostMapping("/languages/new")
    public String addLanguage(@Valid @ModelAttribute("language") Language language, BindingResult result) {
        if (result.hasErrors()) {
            return "new";
        } else {
            languageService.addLanguage(language);
            return "redirect:/languages";
        }
    }

    @RequestMapping("/languages/update/{id}")
    public String editLanguage(Model model, @PathVariable("id") int id) {
        model.addAttribute("language",languageService.findLanguageByIndex(id));
        return "edit";
    }

    @PostMapping("/languages/update/{id}")
    public String updateLanguage(@Valid @ModelAttribute("language") Language language, @PathVariable("id") int id, BindingResult result) {
        System.out.println(language.getCreator());
        if (result.hasErrors()) {
            return "edit";
        } else {
            languageService.updateLanguage(id, language);
            return "redirect:/languages";
        }
    }

    @RequestMapping("languages/delete/{id}")
    public String destroyLanguage(@PathVariable("id") int id) {
        languageService.destroyLanguage(id);
        return "redirect:/languages";
    }
}

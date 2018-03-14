package com.codingdojo.grouplang.controllers;

import com.codingdojo.grouplang.services.LanguageService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.codingdojo.grouplang.models.Language;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class LanguageController {
    private final LanguageService languageService;

    public LanguageController(LanguageService languageService) { this.languageService = languageService; }

    @RequestMapping("/languages")
    public String show(Model model, @ModelAttribute("language") Language language, @ModelAttribute("errors") String errors) {
        model.addAttribute("languages",languageService.allLanguages());
        return "index";
    }

    @RequestMapping("/languages/{id}")
    public String showLanguage(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Language language = languageService.findLanguageById(id);
        if (language == null) {
            redirectAttributes.addFlashAttribute("errors","Language not found");
            return "redirect:/languages";
        } else {
            model.addAttribute("language", language);
        }
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
    public String editLanguage(Model model, @PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Language language = languageService.findLanguageById(id);
        if (language == null) {
            redirectAttributes.addFlashAttribute("errors","Language not found");
            return "redirect:/languages";
        } else {
            model.addAttribute("language", languageService.findLanguageById(id));
        }
        return "edit";
    }

    @PostMapping("/languages/update")
    public String updateLanguage(@Valid @ModelAttribute("language") Language language, BindingResult result) {
        if (result.hasErrors()) {
            return "edit";
        } else {
            languageService.updateLanguage(language);
            return "redirect:/languages";
        }
    }

    @RequestMapping("languages/delete/{id}")
    public String destroyLanguage(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        if (!languageService.destroyLanguage(id)) {
            redirectAttributes.addFlashAttribute("errors","Language not found");
        }
        return "redirect:/languages";
    }
}

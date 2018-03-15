package com.codingdojo.relationships.controllers;

import com.codingdojo.relationships.models.License;
import com.codingdojo.relationships.services.LicenseService;
import com.codingdojo.relationships.services.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import javax.validation.Valid;

@Controller
public class LicenseController {
    private final LicenseService licenseService;
    private final PersonService personService;

    public LicenseController(LicenseService licenseService, PersonService personService) {
        this.licenseService = licenseService;
        this.personService = personService;
    }

    @RequestMapping("/licenses/new")
    public String newLicense(@ModelAttribute("license") License license, Model model) {
        model.addAttribute("persons",personService.allPersons());
        return "new_license";
    }

    @PostMapping("/licenses/new")
    public String addLicense(@Valid @ModelAttribute("license") License license, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "new_license";
        } else {
            licenseService.addLicense(license);
            return "redirect:/persons/"+license.getPerson().getId();
        }
    }
}

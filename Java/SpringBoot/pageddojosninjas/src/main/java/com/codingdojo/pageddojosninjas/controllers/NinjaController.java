package com.codingdojo.pageddojosninjas.controllers;

import com.codingdojo.pageddojosninjas.models.Ninja;
import com.codingdojo.pageddojosninjas.services.DojoService;
import com.codingdojo.pageddojosninjas.services.NinjaService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import javax.validation.Valid;
import java.util.Optional;

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

    @RequestMapping(value={"pages","pages/{pageNumber}"})
    public String getNinjasPerPage(Model model, @PathVariable("pageNumber") Optional<Integer> pageNumber) {
        // we have to subtract 1 because the Pages iterable is 0 indexed. This is for our links to be able to show from 1...pageMax, instead of 0...pageMax class="keyword operator from-rainbow">- 1.
        Page<Ninja> ninjas = ninjaService.ninjasPerPage(((pageNumber.isPresent()) ? pageNumber.get() - 1 : 0));
        // total number of pages that we have
        int totalPages = ninjas.getTotalPages();
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("ninjas", ninjas);
        return "dojos";
    }
}

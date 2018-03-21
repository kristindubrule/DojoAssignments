package com.codingdojo.loginreg.controllers;

import com.codingdojo.loginreg.services.UserService;
import com.codingdojo.loginreg.validator.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.codingdojo.loginreg.models.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class Users {
    private final UserService userService;
    private final UserValidator userValidator;

    public Users(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "loginPage";
        }
        if (userService.noAdmins()) {
            userService.saveUserWithAdminRole(user);
        } else {
            userService.saveUserWithUserRole(user);
        }
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public String login(@ModelAttribute("user") User user, @RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model) {
        if(error != null) {
            model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
        }
        if(logout != null) {
            model.addAttribute("logoutMessage", "Logout Successful!");
        }
        return "loginPage";
    }

    @RequestMapping(value = {"/", "/home"})
    public String home(Principal principal, Model model) {
        // 1
        String username = principal.getName();
        model.addAttribute("currentUser", userService.findByUsername(username));
        return "homePage";
    }

    @RequestMapping("/dashboard")
    public String dashboard(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("currentUser", user);
        if (userService.isAdmin(user)) {
            model.addAttribute("users", userService.allUsers());
            model.addAttribute("admins", userService.admins());
            return "dashboard";
        } else {
            return "homePage";
        }
    }

    @RequestMapping("/delete/{id}")
    public String delete(Principal principal, @PathVariable("id") Long userId, RedirectAttributes redirectAttributes) {
        User user = userService.findByUsername(principal.getName());
        if (userService.isAdmin(user)) {
            userService.deleteById(userId);
        }
        return "redirect:/dashboard";
    }

    @RequestMapping("/make-admin/{id}")
    public String makeAdmin(Principal principal, @PathVariable("id") Long userId, RedirectAttributes redirectAttributes) {
        User user = userService.findByUsername(principal.getName());
        if (userService.isAdmin(user)) {
            userService.makeUserAdmin(userId);
        }
        return "redirect:/dashboard";
    }
}

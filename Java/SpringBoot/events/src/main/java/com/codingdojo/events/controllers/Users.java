package com.codingdojo.events.controllers;

import com.codingdojo.events.services.EventService;
import com.codingdojo.events.services.MessageService;
import com.codingdojo.events.services.StateService;
import com.codingdojo.events.services.UserService;
import com.codingdojo.events.validator.DateValidator;
import com.codingdojo.events.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.codingdojo.events.models.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.Validator;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class Users {
    private final UserService userService;
    private final UserValidator userValidator;
    private final DateValidator dateValidator;
    private final EventService eventService;
    private final MessageService messageService;
    private final StateService stateService;

    @Autowired
    @Qualifier("mvcValidator")
    Validator validator;

    public Users(UserService userService, UserValidator userValidator, EventService eventService, MessageService messageService, StateService stateService, DateValidator dateValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.eventService = eventService;
        this.messageService = messageService;
        this.stateService = stateService;
        this.dateValidator = dateValidator;
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "loginPage";
        }
        userService.saveUserWithUserRole(user);
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
        model.addAttribute("stateList",stateService.allStates());
        return "loginPage";
    }

    @RequestMapping(value = {"/", "/events"})
    public String home(Principal principal, Model model, @ModelAttribute("event") Event event) {
        User user = userService.findByUsername(principal.getName());
        if (user == null) {
            return "redirect:/login";
        } else {
            model.addAttribute("host",user);
            model.addAttribute("localEvents",eventService.userLocalEvents(user));
            model.addAttribute("otherEvents",eventService.otherEvents(user));
            model.addAttribute("userEvents",eventService.userEvents(user));
            model.addAttribute("stateList",stateService.allStates());
            model.addAttribute("currentUser",user);
            return "events";
        }
    }

    @RequestMapping("/events/{id}")
    public String showEvent(Principal principal, Model model, RedirectAttributes redirectAttributes, @ModelAttribute("message") Message message, @PathVariable("id") String eventId) {
        User user = userService.findByUsername(principal.getName());
        if (user == null) {
            return "redirect:/login";
        } else {
            Event event = eventService.eventById(Long.parseLong(eventId));
            if (event == null) {
                redirectAttributes.addFlashAttribute("errors", "No event found.");
                return "redirect:/events";
            } else {
                model.addAttribute("event", event);
                model.addAttribute("author",user);
                return "event_details";
            }
        }
    }

    @RequestMapping("/events/edit/{id}")
    public String editEvent(Principal principal, Model model, RedirectAttributes redirectAttributes, @PathVariable("id") String eventId) {
        User user = userService.findByUsername(principal.getName());
        if (user == null) {
            return "redirect:/login";
        } else {
            // Check for event existance & that user owns event
            Event event = eventService.eventById(Long.parseLong(eventId));
            if (user.getId() == event.getHost().getId()) {
                if (event == null) {
                    redirectAttributes.addFlashAttribute("errors", "No event found");
                    return "redirect:/events";
                } else {
                    model.addAttribute("event", event);
                    model.addAttribute("stateList",stateService.allStates());
                    return "event_edit";
                }
            } else {
                return "redirect:/events";
            }
        }
    }

    @PostMapping("/events/update")
    public String updateEvent(Principal principal, @Valid @ModelAttribute("event") Event event, BindingResult result, RedirectAttributes redirectAttributes) {
        User user = userService.findByUsername(principal.getName());
        if (user == null) {
            return "redirect:/login";
        } else {
            event = eventService.refillEvent(event,user);
            if (event == null) {
                redirectAttributes.addFlashAttribute("errors", "Unauthorized event edit.");
                return "redirect:/events";
            }
            else {
                validator.validate(event, result);
                dateValidator.validate(event, result);
                if (result.hasErrors()) {
                    return "event_edit";
                } else {
                    eventService.updateEvent(event);
                    return "redirect:/events";
                }
            }
        }
    }

    @PostMapping("/events/message")
    public String addMessage(Principal principal, @Valid @ModelAttribute("message") Message message, BindingResult result, Model model) {
        User user = userService.findByUsername(principal.getName());
        if (user == null) {
            return "redirect:/login";
        } else {
            if (result.hasErrors()) {
                model.addAttribute("event", eventService.eventById(message.getEvent().getId()));
                model.addAttribute("stateList",stateService.allStates());
                return "event_details";
            } else {
                messageService.addMessage(message);
                return "redirect:/events/"+message.getEvent().getId();
            }
        }
    }

    @PostMapping("/events/new")
    public String addEvent(Principal principal, @Valid @ModelAttribute("event") Event event, BindingResult result, Model model) {
        User user = userService.findByUsername(principal.getName());
        if (user == null) {
            return "redirect:/login";
        } else {
            dateValidator.validate(event, result);
            if (result.hasErrors()) {
                model.addAttribute("host",user);
                model.addAttribute("localEvents",eventService.userLocalEvents(user));
                model.addAttribute("otherEvents",eventService.otherEvents(user));
                model.addAttribute("userEvents",eventService.userEvents(user));
                model.addAttribute("stateList",stateService.allStates());
                model.addAttribute("currentUser",user);
                return "events";
            } else {
                event.setHost(user);
                eventService.addEvent(event);
                return "redirect:/events";
            }
        }
    }

    @RequestMapping("/events/join/{id}")
    public String joinEvent(Principal principal, Model model, RedirectAttributes redirectAttributes, @PathVariable("id") String eventId) {
        User user = userService.findByUsername(principal.getName());
        if (user == null) {
            return "redirect:/login";
        } else {
            Event event = eventService.eventById(Long.parseLong(eventId));
            if (event == null) {
                redirectAttributes.addFlashAttribute("errors", "No event found");
                return "redirect:/events";
            } else {
                eventService.joinEvent(event, user);
                return "redirect:/events";
            }
        }
    }

    @RequestMapping("/events/leave/{id}")
    public String leaveEvent(Principal principal, Model model, RedirectAttributes redirectAttributes, @PathVariable("id") String eventId) {
        User user = userService.findByUsername(principal.getName());
        if (user == null) {
            return "redirect:/login";
        } else {
            Event event = eventService.eventById(Long.parseLong(eventId));
            if (event == null) {
                redirectAttributes.addFlashAttribute("errors", "No event found");
                return "redirect:/events";
            } else {
                eventService.leaveEvent(event, user);
                return "redirect:/events";
            }
        }
    }

    @RequestMapping("/events/delete/{id}")
    public String deleteEvent(Principal principal, Model model, RedirectAttributes redirectAttributes, @PathVariable("id") String eventId) {
        User user = userService.findByUsername(principal.getName());
        if (user == null) {
            return "redirect:/login";
        } else {
            // Check for event existance & that user owns event
            Event event = eventService.eventById(Long.parseLong(eventId));
            if (user.getId() == event.getHost().getId()) {
                if (event == null) {
                    redirectAttributes.addFlashAttribute("errors", "No event found");
                    return "redirect:/events";
                } else {
                    eventService.deleteEvent(event);
                    return "redirect:/events";
                }
            } else {
                return "redirect:/events";
            }
        }
    }
}

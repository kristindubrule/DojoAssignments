package com.codingdojo.ninjagold.controllers;

import com.codingdojo.ninjagold.models.BuildingFactory;
import com.codingdojo.ninjagold.models.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.codingdojo.ninjagold.models.Building;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class NinjagoldController {
    @RequestMapping("/")
    public String index(HttpSession session) {
        Integer gold = (Integer)session.getAttribute("gold");
        if (gold == null) {
            gold = 0;
        }
        session.setAttribute("gold",gold);
        return "index";
    }

    @RequestMapping(value = "/process_money", method = RequestMethod.POST)
    public String process(@RequestParam(value="building") String building, HttpSession session) {
        BuildingFactory bf = new BuildingFactory();
        Building b = bf.getBuilding(building);
        Integer gold = (Integer)session.getAttribute("gold");
        ArrayList<Message> history = (ArrayList<Message>)session.getAttribute("history");
        if (gold == null) {
            gold = 0;
        }
        gold += b.visit();
        if (history == null) {
            history = new ArrayList<>();
        }
        history.add(b.getMessage());
        session.setAttribute("gold",gold);
        session.setAttribute("history",history);
        return "redirect:/";
    }

    @RequestMapping("/reset")
    public String reset(HttpSession session, HttpServletRequest request) {
        session.removeAttribute("gold");
        session.removeAttribute("history");
        return "redirect:/";
    }
}

package com.codingdojo.displaydate.controllers;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.joda.time.DateTime;
import org.joda.time.*;

import java.time.DayOfWeek;

@Controller
public class DateController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/date")
    // Sunday, the 21 of May, 2017
    public String dateFormat(Model model) {
        DateTimeFormatter dtf = new DateTimeFormatterBuilder()
                .appendDayOfWeekText()
                .appendLiteral(", the ")
                .appendDayOfMonth(2)
                .appendLiteral(" of ")
                .appendMonthOfYearText()
                .appendLiteral(", ")
                .appendYear(4,4)
                .toFormatter();
        model.addAttribute("formattedDate", dtf.print(new LocalDateTime()));
        return "date";
    }

    @RequestMapping("/time")
    // 11:30 PM
    public String timeFormat(Model model) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("hh:mm a");
        model.addAttribute("formattedTime",dtf.print(new LocalDateTime()));
        return "time";
    }
}

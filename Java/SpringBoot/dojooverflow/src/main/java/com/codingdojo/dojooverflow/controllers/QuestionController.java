package com.codingdojo.dojooverflow.controllers;

import com.codingdojo.dojooverflow.models.Answer;
import com.codingdojo.dojooverflow.models.Question;
import com.codingdojo.dojooverflow.services.AnswerService;
import com.codingdojo.dojooverflow.services.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;
import javax.validation.Valid;

@Controller
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;
    private final AnswerService answerService;

    public QuestionController(QuestionService questionService, AnswerService answerService) {
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @RequestMapping("")
    public String dashboard(@ModelAttribute("errors") String errors, Model model) {
        model.addAttribute("questions",questionService.allQuestions());
        return "dashboard";
    }

    @RequestMapping("{id}")
    public String showQuestion(@ModelAttribute("answer") Answer answer, @PathVariable("id") Long id, RedirectAttributes redirectAttributes, Model model) {
        Question question = questionService.findQuestionById(id);
        if (question == null) {
            redirectAttributes.addFlashAttribute("errors","No question found");
            return "redirect:/";
        } else {
            answer.setQuestion(question);
            return "show_question";
        }
    }

    @RequestMapping("/add_answer")
    public String addAnswer(@Valid @ModelAttribute("answer") Answer answer, BindingResult result) {
        if (result.hasErrors()) {
            answer.setQuestion(questionService.findQuestionById(answer.getQuestion().getId()));
            return "show_question";
        } else {
            answerService.addAnswer(answer);
            return "redirect:/question/"+answer.getQuestion().getId();
        }
    }

    @RequestMapping("new")
    public String newQuestion(@ModelAttribute("question") Question question) {
        return "new_question";
    }

    @PostMapping("new")
    public String addQuestion(@Valid @ModelAttribute("question") Question question, BindingResult result) {
        if (result.hasErrors()) {
            return "new_question";
        } else {
            questionService.addQuestion(question);
            return "redirect:/question/"+question.getId();
        }
    }

}

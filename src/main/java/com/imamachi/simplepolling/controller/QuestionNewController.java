package com.imamachi.simplepolling.controller;

import com.imamachi.simplepolling.form.QuestionForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/create")
public class QuestionNewController {

    @GetMapping("/questionNew")
    public String getHomePage(Model model){
        model.addAttribute("questionForm", new QuestionForm());
        return "/create/questionNew";
    }

    @PostMapping("/questionNew")
    public String postQuestion(@RequestParam(name = "submit") String submit,
                               @Validated @ModelAttribute QuestionForm questionForm,
                               BindingResult result,
                               Model model){
        return "/create/questionNew";
    }
}

package com.imamachi.simplepolling.controller;

import com.imamachi.simplepolling.form.QuestionForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/create")
public class QuestionNewController {

    @GetMapping("/questionNew")
    public String getHomePage(Model model){
        model.addAttribute("questionForm", new QuestionForm());
        return "/create/questionNew";
    }
}

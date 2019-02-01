package com.imamachi.simplepolling.controller;

import com.imamachi.simplepolling.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/questionnaire")
public class QuestionnaireController {

    private QuestionnaireService questionnaireService;

    @Autowired
    public QuestionnaireController(QuestionnaireService questionnaireService){
        this.questionnaireService = questionnaireService;
    }

    @GetMapping("/top")
    public String getTopPage(Model model){
        // アンケートのタイトルを取得する
        model.addAttribute("title", this.questionnaireService.getCurrentQuestionnaire());
        return "/questionnaire/top";
    }

    @PostMapping("/form")
    public String getForm(@RequestParam(name = "title") String title,
                          Model model){
        model.addAttribute("title", title);
        return "/questionnaire/form";
    }
}

package com.imamachi.simplepolling.controller;

import com.imamachi.simplepolling.model.CurrentQuestionnaire;
import com.imamachi.simplepolling.model.Question;
import com.imamachi.simplepolling.model.QuestionDetail;
import com.imamachi.simplepolling.service.QuestionService;
import com.imamachi.simplepolling.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/questionnaire")
public class QuestionnaireController {

    private QuestionnaireService questionnaireService;
    private QuestionService questionService;

    @Autowired
    public QuestionnaireController(QuestionnaireService questionnaireService,
                                   QuestionService questionService){
        this.questionnaireService = questionnaireService;
        this.questionService = questionService;
    }

    @GetMapping("/top")
    public String getTopPage(Model model){
        // アンケートの情報を取得
        CurrentQuestionnaire currentQuestionnaire = this.questionnaireService.getCurrentQuestionnaire();

        // アンケートのタイトルを取得する
        model.addAttribute("title", currentQuestionnaire.getQuestionnaire().getTitle());
        return "/questionnaire/top";
    }

    @PostMapping("/form")
    public String getForm(@RequestParam(name = "title") String title,
                          Model model){
        model.addAttribute("title", title);

        List<Question> questionList = questionService.getQuestionnaireInfo();
        model.addAttribute("questionList", questionList);

        return "/questionnaire/form";
    }
}

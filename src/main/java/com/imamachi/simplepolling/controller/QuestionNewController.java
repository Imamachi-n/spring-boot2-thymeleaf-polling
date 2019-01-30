package com.imamachi.simplepolling.controller;

import com.imamachi.simplepolling.form.QuestionForm;
import com.imamachi.simplepolling.form.QuestionRootForm;
import com.imamachi.simplepolling.model.Question;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/create")
public class QuestionNewController {

    @GetMapping("/questionNew")
    public String getHomePage(Model model){
        // 初期処理
        QuestionRootForm questionRootForm = new QuestionRootForm();
        ArrayList<QuestionForm> questionForms = new ArrayList<>();
        questionForms.add(new QuestionForm(Question.DocType.singleQ, true));
        questionForms.add(new QuestionForm(Question.DocType.multiQ, true));
        questionForms.add(new QuestionForm(Question.DocType.commentQ, false));
        QuestionForm teste = questionForms.get(0);
        questionRootForm.setQuestions(questionForms);

        model.addAttribute("questionRootForm", questionRootForm);
        return "/create/questionNew";
    }

//    @RequestParam(name = "submit") String submit,
    @PostMapping("/questionNew")
    public String postQuestion(@Validated @ModelAttribute QuestionRootForm questionRootForm,
                               BindingResult result,
                               Model model){
//        System.out.println(submit);
//        String docType = submit.split("_")[0];
//        String index = submit.split("_")[1];
        model.addAttribute("questionRootForm", questionRootForm);
        return "/create/questionNew";
    }
}

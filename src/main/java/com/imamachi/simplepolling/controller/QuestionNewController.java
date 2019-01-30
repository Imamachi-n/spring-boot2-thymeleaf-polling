package com.imamachi.simplepolling.controller;

import com.imamachi.simplepolling.form.QuestionDetailForm;
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

        ArrayList<QuestionDetailForm> questionDetailForms = new ArrayList<>();
        questionDetailForms.add(new QuestionDetailForm());
        questionDetailForms.add(new QuestionDetailForm());
        questionDetailForms.add(new QuestionDetailForm());

        ArrayList<QuestionDetailForm> questionDetailForms2 = new ArrayList<>();
        questionDetailForms2.add(new QuestionDetailForm());
        questionDetailForms2.add(new QuestionDetailForm());
        questionDetailForms2.add(new QuestionDetailForm());
        questionDetailForms2.add(new QuestionDetailForm());
        questionDetailForms2.add(new QuestionDetailForm());

        QuestionForm questionForm1 = new QuestionForm(Question.DocType.singleQ, true);
        questionForm1.setQuestionDetails(questionDetailForms);
        QuestionForm questionForm2 = new QuestionForm(Question.DocType.multiQ, true);
        questionForm2.setQuestionDetails(questionDetailForms2);
        QuestionForm questionForm3 = new QuestionForm(Question.DocType.commentQ, false);
        questionForm3.setQuestionDetails(questionDetailForms);

        questionForms.add(questionForm1);
        questionForms.add(questionForm2);
        questionForms.add(questionForm3);
        questionRootForm.setQuestions(questionForms);

        model.addAttribute("questionRootForm", questionRootForm);
        return "/create/questionNew";
    }

//    @RequestParam(name = "submit") String submit,
    @PostMapping("/questionNew")
    public String postQuestion(@RequestParam(name = "submit") String submit,
                               @Validated @ModelAttribute QuestionRootForm questionRootForm,
                               BindingResult result,
                               Model model){
        System.out.println(submit);
//        String docType = submit.split("_")[0];
//        String index = submit.split("_")[1];
        model.addAttribute("questionRootForm", questionRootForm);
        return "/create/questionNew";
    }
}

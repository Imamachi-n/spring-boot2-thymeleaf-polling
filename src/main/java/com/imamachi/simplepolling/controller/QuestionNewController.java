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
import java.util.List;

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
        model.addAttribute("accordionExpandIndex", 0);
        return "/create/questionNew";
    }

//    @RequestParam(name = "submit") String submit,
    @PostMapping("/questionNew")
    public String postQuestion(@RequestParam(name = "submit") String submit,
                               @RequestParam(name = "addDocType") String addDocType,
                               @Validated @ModelAttribute QuestionRootForm questionRootForm,
                               BindingResult result,
                               Model model){
        if(!submit.equals("submit")) {
            // Indexの取得
            System.out.println(submit);
            String type = submit.split(":")[0]; // 質問の追加もしくは項目の追加
            String action = submit.split(":")[1];   // 追加もしくは削除処理
            int index = Integer.parseInt(submit.split(":")[2]); // Index

            // TODO: １つしか項目がない場合は、削除できないようにする
            if(type.equals("content")) {
                if(action.equals("add")) {
                    List<QuestionDetailForm> questionDetails = questionRootForm.getQuestions().get(index).getQuestionDetails();
                    questionDetails.add(new QuestionDetailForm());
                    questionRootForm.getQuestions().get(index).setQuestionDetails(questionDetails);
                }else if(action.equals("delete")){
                    List<QuestionDetailForm> questionDetails = questionRootForm.getQuestions().get(index).getQuestionDetails();
                    questionDetails.remove(questionDetails.size() - 1);
                    questionRootForm.getQuestions().get(index).setQuestionDetails(questionDetails);
                }
            }else if (type.equals("question")){
                // 質問を追加
                if(action.equals("add")){
                    List<QuestionForm> questions = questionRootForm.getQuestions();
                    index = questions.size();
                    ArrayList<QuestionDetailForm> questionDetailForms = new ArrayList<>();
                    if(addDocType.equals("singleQ")) {
                        QuestionForm questionForm = new QuestionForm(Question.DocType.singleQ, true);
                        questionDetailForms.add(new QuestionDetailForm());
                        questionDetailForms.add(new QuestionDetailForm());
                        questionDetailForms.add(new QuestionDetailForm());
                        questionForm.setQuestionDetails(questionDetailForms);
                        questions.add(questionForm);
                    }else if(addDocType.equals("multiQ")){
                        QuestionForm questionForm = new QuestionForm(Question.DocType.multiQ, true);
                        questionDetailForms.add(new QuestionDetailForm());
                        questionDetailForms.add(new QuestionDetailForm());
                        questionDetailForms.add(new QuestionDetailForm());
                        questionForm.setQuestionDetails(questionDetailForms);
                        questions.add(questionForm);
                    }else if(addDocType.equals("commentQ")){
                        QuestionForm questionForm = new QuestionForm(Question.DocType.commentQ, true);
                        questionDetailForms.add(new QuestionDetailForm());
                        questionForm.setQuestionDetails(questionDetailForms);
                        questions.add(questionForm);
                    }

                    questionRootForm.setQuestions(questions);
                }else if(action.equals("delete")) {
                    List<QuestionForm> questionFroms = questionRootForm.getQuestions();
                    questionFroms.remove(index);
                    questionRootForm.setQuestions(questionFroms);
                }
            }

            model.addAttribute("accordionExpandIndex", index);
            model.addAttribute("questionRootForm", questionRootForm);
            return "/create/questionNew";
        }

        model.addAttribute("questionRootForm", questionRootForm);
        return "/create/questionNew";
    }
}

package com.imamachi.simplepolling.controller;

import com.imamachi.simplepolling.form.QuestionDetailForm;
import com.imamachi.simplepolling.form.QuestionForm;
import com.imamachi.simplepolling.form.QuestionRootForm;
import com.imamachi.simplepolling.model.Question;
import com.imamachi.simplepolling.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private QuestionnaireService questionnaireService;

    @Autowired
    public QuestionNewController(QuestionnaireService questionnaireService){
        this.questionnaireService = questionnaireService;
    }

    // アンケート画面への遷移（画面の初期処理）
    @GetMapping("/questionNew")
    public String getHomePage(Model model){
        // 新規作成アンケートのテンプレートを渡す
        model.addAttribute("questionRootForm", questionnaireService.getQuestionnaireTemplate());
        // アコーディオンの初期設定
        model.addAttribute("accordionExpandIndex", 0);
        return "/create/questionNew";
    }

    // アンケート内容の増減・アンケートの登録
    @PostMapping("/questionNew")
    public String postQuestion(@RequestParam(name = "submit") String submit,
                               @RequestParam(name = "addDocType") String addDocType,
                               @Validated @ModelAttribute QuestionRootForm questionRootForm,
                               BindingResult result,
                               Model model){
        // 登録処理
        if(submit.equals("submit")) {
            //

        // 質問の追加・削除、選択項目の追加・削除
        }else{
            // Indexの取得
            String type = submit.split(":")[0]; // 質問の追加、もしくは選択項目の追加
            String action = submit.split(":")[1];   // 追加、もしくは削除処理
            int index = Integer.parseInt(submit.split(":")[2]); // Index

            // 選択項目の処理
            if(type.equals("content")) {
                // 選択項目の追加
                if(action.equals("add")) {
                    List<QuestionDetailForm> questionDetails = questionRootForm.getQuestions().get(index).getQuestionDetails();
                    questionDetails.add(new QuestionDetailForm());
                    questionRootForm.getQuestions().get(index).setQuestionDetails(questionDetails);
                // 選択項目の削除
                }else if(action.equals("delete")){
                    List<QuestionDetailForm> questionDetails = questionRootForm.getQuestions().get(index).getQuestionDetails();
                    if(questionDetails.size() > 1){
                        questionDetails.remove(questionDetails.size() - 1);
                        questionRootForm.getQuestions().get(index).setQuestionDetails(questionDetails);
                    }
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
                        questionForm.setQuestionDetails(questionDetailForms);
                        questions.add(questionForm);
                    }else if(addDocType.equals("commentQ")){
                        QuestionForm questionForm = new QuestionForm(Question.DocType.commentQ, true);
                        questionDetailForms.add(new QuestionDetailForm());
                        questionForm.setQuestionDetails(questionDetailForms);
                        questions.add(questionForm);
                    }

                    questionRootForm.setQuestions(questions);

                // 質問の削除
                }else if(action.equals("delete")) {
                    List<QuestionForm> questionFroms = questionRootForm.getQuestions();
                    if(questionFroms.size() > 1) {
                        questionFroms.remove(index);
                        questionRootForm.setQuestions(questionFroms);
                    }
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

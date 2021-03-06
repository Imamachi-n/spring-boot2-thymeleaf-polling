package com.imamachi.simplepolling.controller;

import com.imamachi.simplepolling.form.QuestionRootForm;
import com.imamachi.simplepolling.service.QuestionService;
import com.imamachi.simplepolling.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/create")
public class QuestionNewController {

    private QuestionnaireService questionnaireService;
    private QuestionService questionService;

    @Autowired
    public QuestionNewController(QuestionnaireService questionnaireService,
                                 QuestionService questionService){
        this.questionnaireService = questionnaireService;
        this.questionService = questionService;
    }

    // アンケート画面への遷移（画面の初期処理）
    @GetMapping("/questionNew")
    public String getHomePage(Model model){
        // 新規作成アンケートのテンプレートを渡す
        model.addAttribute("questionRootForm", questionnaireService.getQuestionnaireTemplate());
        // アコーディオンの初期設定
        model.addAttribute("accordionExpandIndex", 0);
        model.addAttribute("isError", false);
        return "/create/questionNew";
    }

    // 確認画面から登録処理
    @PostMapping("/confirm")
    public String postConfirm(@Validated @ModelAttribute QuestionRootForm questionRootForm,
                              Model model){

        try {
            this.questionnaireService.registerQuestionnaire(questionRootForm);
        }catch(DataIntegrityViolationException e){
            model.addAttribute("accordionExpandIndex", 0);
            model.addAttribute("questionRootForm", questionRootForm);
            model.addAttribute("isError", true);
            model.addAttribute("errorMsg", "アンケートの登録に失敗しました。アンケート名が重複していないか確認してください。");
            return "/create/questionNew";
        }

        model.addAttribute("questionRootForm", questionnaireService.getQuestionnaireTemplate());
        model.addAttribute("accordionExpandIndex", 0);
        model.addAttribute("isError", false);
        model.addAttribute("successMsg", "登録が完了しました。");
        return "/create/questionNew";
    }

    // アンケート内容の増減・アンケートの登録
    @PostMapping("/questionNew")
    public String postQuestion(@RequestParam(name = "submit") String submit,
                               @RequestParam(name = "addDocType") String addDocType,
                               @Validated @ModelAttribute QuestionRootForm questionRootForm,
                               BindingResult result, RedirectAttributes attributes,
                               Model model){

        // 登録処理
        if(submit.equals("submit")) {
            // エラーハンドリング
            if(result.hasErrors()) {
//                attributes.addFlashAttribute("questionRootForm", questionRootForm);
                model.addAttribute("accordionExpandIndex", 0);
                model.addAttribute("questionRootForm", questionRootForm);
                model.addAttribute("isError", true);
                model.addAttribute("errorMsg", "入力していないフォームを埋めてください。");
                return "/create/questionNew";
            }

            model.addAttribute("questionRootForm", questionRootForm);
            return "/create/confirm";
        }

        // 質問の追加・削除、選択項目の追加・削除
        // Indexの取得
        String type = submit.split(":")[0]; // 質問の追加、もしくは選択項目の追加
        String action = submit.split(":")[1];   // 追加、もしくは削除処理
        int index = Integer.parseInt(submit.split(":")[2]); // Index

        // 選択項目の処理
        if(type.equals("content")) {
            // 選択項目の追加
            if(action.equals("add")) {
                questionService.addQuestionItem(questionRootForm, index);

            // 選択項目の削除
            }else if(action.equals("delete")){
                questionService.deleteQuestionItem(questionRootForm, index);
            }

        // 質問の処理
        }else if (type.equals("question")){
            // 質問を追加
            if(action.equals("add")){
                index = questionRootForm.getQuestions().size();

                if(addDocType.equals("singleQ")) {
                    questionService.addSingleQuestion(questionRootForm);

                }else if(addDocType.equals("multiQ")){
                    questionService.addMultiQuestion(questionRootForm);

                }else if(addDocType.equals("commentQ")){
                    questionService.addCommentQuestion(questionRootForm);
                }

            // 質問の削除
            }else if(action.equals("delete")) {
                questionService.deleteQuestion(questionRootForm, index);

            }
        }

        model.addAttribute("accordionExpandIndex", index);
        model.addAttribute("questionRootForm", questionRootForm);
        model.addAttribute("isError", false);
        return "/create/questionNew";
    }
}

package com.imamachi.simplepolling.controller;

import com.imamachi.simplepolling.form.QuestionRootForm;
import com.imamachi.simplepolling.model.Questionnaire;
import com.imamachi.simplepolling.service.EditService;
import com.imamachi.simplepolling.service.HomeService;
import com.imamachi.simplepolling.service.QuestionService;
import com.imamachi.simplepolling.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/edit")
public class EditController {

    private HomeService homeService;
    private QuestionnaireService questionnaireService;
    private EditService editService;
    private QuestionService questionService;

    @Autowired
    public EditController(HomeService homeService,
                          QuestionnaireService questionnaireService,
                          QuestionService questionService,
                          EditService editService){
        this.homeService = homeService;
        this.questionnaireService = questionnaireService;
        this.questionService = questionService;
        this.editService = editService;
    }

    @GetMapping("/questionEdit")
    public String initialize(Model model) {
        // 現在のアンケートを取得
        model.addAttribute("questionnaires", homeService.getQuestionnaires());
        model.addAttribute("currentQuestionnaireId", homeService.getCurrentQuestionnaire().getQuestionnaire().getQuestionnaireId());

        // 修正対象の表示
        model.addAttribute("showRevObject", false);

        // 選択したアンケートを取得
        model.addAttribute("questionRootForm", questionnaireService.getQuestionnaireTemplate());
        // アコーディオンの初期設定
        model.addAttribute("accordionExpandIndex", 0);
        model.addAttribute("isError", false);

        return "/edit/questionEdit";
    }

    @PostMapping("/edit")
    public String changeEditMode(@RequestParam(name = "questionnaireId") String questionnaireId,
                                 Model model){

        // 現在のアンケートを取得
        model.addAttribute("questionnaires", homeService.getQuestionnaires());
        Questionnaire questionnaire = editService.getSelectedQuestionnaire(Integer.parseInt(questionnaireId));
        model.addAttribute("currentQuestionnaireId", questionnaire.getQuestionnaireId());

        // 選択したアンケートを取得
        QuestionRootForm questionRootForm = editService.getQuestionForm(questionnaire);
        model.addAttribute("questionRootForm", questionRootForm);

        // アコーディオンの初期設定
        model.addAttribute("accordionExpandIndex", 0);
        model.addAttribute("isError", false);

        // 修正対象の表示
        model.addAttribute("showRevObject", true);

        return "/edit/questionEdit";
    }

    @PostMapping("/questionEdit")
    public String postEdit(@RequestParam(name = "submit") String submit,
                           @RequestParam(name = "addDocType") String addDocType,
                           @Validated @ModelAttribute QuestionRootForm questionRootForm,
                           BindingResult result, Model model) {

        // 登録処理
        if(submit.equals("submit")) {
            // エラーハンドリング
            if(result.hasErrors()) {
//                attributes.addFlashAttribute("questionRootForm", questionRootForm);
                model.addAttribute("accordionExpandIndex", 0);
                model.addAttribute("questionRootForm", questionRootForm);
                model.addAttribute("isError", true);
                model.addAttribute("errorMsg", "入力していないフォームを埋めてください。");
                return "/edit/questionNew";
            }

            model.addAttribute("questionRootForm", questionRootForm);
            return "/edit/confirm";
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

        // 現在のアンケートを取得
        model.addAttribute("questionnaires", homeService.getQuestionnaires());
        model.addAttribute("currentQuestionnaireId", homeService.getCurrentQuestionnaire().getQuestionnaire().getQuestionnaireId());

        // 修正対象の表示
        model.addAttribute("showRevObject", true);

        return "/edit/questionEdit";
    }

    @PostMapping("confirm")
    public String updateQuestionnaire(@Validated @ModelAttribute QuestionRootForm questionRootForm,
                                      Model model){

        editService.updateQuestionnaire(questionRootForm);
        return "redirect:/edit/questionEdit";
    }
}

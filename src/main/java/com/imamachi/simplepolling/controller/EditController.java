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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String postEdit(Model model) {

        // 選択したアンケートを取得
        model.addAttribute("questionRootForm", questionnaireService.getQuestionnaireTemplate());

        // アコーディオンの初期設定
        model.addAttribute("accordionExpandIndex", 0);
        model.addAttribute("isError", false);

        return "/edit/questionEdit";
    }
}

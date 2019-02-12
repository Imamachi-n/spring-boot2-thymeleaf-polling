package com.imamachi.simplepolling.controller;

import com.imamachi.simplepolling.form.ChartRootData;
import com.imamachi.simplepolling.model.Questionnaire;
import com.imamachi.simplepolling.service.ChartService;
import com.imamachi.simplepolling.service.HomeService;
import com.imamachi.simplepolling.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chart")
public class ChartController {

    private HomeService homeService;
    private ChartService chartService;
    private QuestionnaireService questionnaireService;

    @Autowired
    public ChartController(HomeService homeService,
                           ChartService chartService,
                           QuestionnaireService questionnaireService){
        this.homeService = homeService;
        this.chartService = chartService;
        this.questionnaireService = questionnaireService;
    }

    @GetMapping("/dashboard")
    public String getDashboard(Model model){

        // アンケートの取得
        List<Questionnaire> questionnaireList = homeService.getQuestionnaires();
        model.addAttribute("questionnaires", questionnaireList);
        Questionnaire questionnaire = homeService.getCurrentQuestionnaire().getQuestionnaire();

        // アンケートの表示
        setChartData(model, questionnaire);

        return "/chart/dashboard";
    }

    @PostMapping("/dashboard")
    public String getSelectedDashboard(@RequestParam(name = "questionnaireId") String questionnaireId,
                                       Model model){

        // アンケートの取得
        List<Questionnaire> questionnaireList = homeService.getQuestionnaires();
        model.addAttribute("questionnaires", questionnaireList);
        Questionnaire questionnaire = questionnaireService.getQuestionnaireById(Integer.parseInt(questionnaireId));

        // アンケートの表示
        setChartData(model, questionnaire);

        return "/chart/dashboard";
    }

    // アンケート情報の表示
    private void setChartData(Model model, Questionnaire questionnaire){
        // 現在表示するアンケート
        model.addAttribute("currentQuestionnaireId", questionnaire.getQuestionnaireId());

        // アンケート集計
        List<ChartRootData> chartRootData = new ArrayList<>();
        chartService.getQuestionnaireResult(questionnaire, chartRootData);

        model.addAttribute("chartRootData", chartRootData);
    }
}

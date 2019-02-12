package com.imamachi.simplepolling.controller;

import com.imamachi.simplepolling.form.ChartForm;
import com.imamachi.simplepolling.form.ChartRootData;
import com.imamachi.simplepolling.model.Questionnaire;
import com.imamachi.simplepolling.service.ChartService;
import com.imamachi.simplepolling.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chart")
public class ChartController {

    private HomeService homeService;
    private ChartService chartService;

    @Autowired
    public ChartController(HomeService homeService,
                           ChartService chartService){
        this.homeService = homeService;
        this.chartService = chartService;
    }

    @GetMapping("/dashboard")
    public String getDashboard(Model model){

        // アンケートの取得
        List<Questionnaire> questionnaireList = homeService.getQuestionnaires();
        Questionnaire questionnaire = homeService.getCurrentQuestionnaire().getQuestionnaire();
        model.addAttribute("questionnaires", questionnaireList);
        model.addAttribute("currentQuestionnaireId", questionnaire.getQuestionnaireId());

        // アンケート集計

        // テスト
        List<List<ChartForm>> chartForms = new ArrayList<>();
        List<String> qType = new ArrayList<>();
        List<String> chartQName = new ArrayList<>();
        List<List<ChartForm>> commentForms = new ArrayList<>();
        List<String> commentQName = new ArrayList<>();

        List<ChartRootData> chartRootData = new ArrayList<>();

        chartService.getQuestionnaireResult(questionnaire, chartForms, qType, chartQName, commentForms, commentQName, chartRootData);

        model.addAttribute("chartData", chartForms);
        model.addAttribute("chartType", qType);
        model.addAttribute("chartQName", chartQName);
        model.addAttribute("commentQName", commentQName);
        model.addAttribute("chartRootData", chartRootData);

        return "/chart/dashboard";
    }
}

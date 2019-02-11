package com.imamachi.simplepolling.controller;

import com.imamachi.simplepolling.form.ChartForm;
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

    @Autowired
    public ChartController(HomeService homeService){
        this.homeService = homeService;
    }

    @GetMapping("/dashboard")
    public String getDashboard(Model model){

        // アンケートの取得
        model.addAttribute("questionnaires", homeService.getQuestionnaires());
        model.addAttribute("currentQuestionnaireId", homeService.getCurrentQuestionnaire().getQuestionnaire().getQuestionnaireId());

        // テスト
        List<List<ChartForm>> chartForms = new ArrayList<>();
        List<ChartForm> tmpChartForms = new ArrayList<>();
        tmpChartForms.add(new ChartForm("test", "12"));
        tmpChartForms.add(new ChartForm("test2", "32"));
        tmpChartForms.add(new ChartForm("test3", "52"));
        chartForms.add(tmpChartForms);

        tmpChartForms = new ArrayList<>();
        tmpChartForms.add(new ChartForm("test", "53"));
        tmpChartForms.add(new ChartForm("test2", "34"));
        tmpChartForms.add(new ChartForm("test3", "13"));
        chartForms.add(tmpChartForms);

        tmpChartForms = new ArrayList<>();
        tmpChartForms.add(new ChartForm("test", "53"));
        tmpChartForms.add(new ChartForm("test2", "34"));
        tmpChartForms.add(new ChartForm("test3", "13"));
        chartForms.add(tmpChartForms);

        List<String> qType = new ArrayList<>();
        qType.add("singleQ");
        qType.add("multiQ");
        qType.add("singleQ");

        model.addAttribute("chartData", chartForms);
        model.addAttribute("chartType", qType);

        return "/chart/dashboard";
    }
}

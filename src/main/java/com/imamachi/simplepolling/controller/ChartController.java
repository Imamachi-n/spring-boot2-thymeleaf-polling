package com.imamachi.simplepolling.controller;

import com.imamachi.simplepolling.form.ChartRootData;
import com.imamachi.simplepolling.form.ChartSelectForm;
import com.imamachi.simplepolling.model.Questionnaire;
import com.imamachi.simplepolling.service.ChartService;
import com.imamachi.simplepolling.service.HomeService;
import com.imamachi.simplepolling.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    // コンボボックスの初期設定
    private void init(Model model){
        // 所属のリスト
        model.addAttribute("employeeList", questionnaireService.getEmployeeAll());

        // 部署のリスト
        model.addAttribute("departmentList", questionnaireService.getDepartmentAll());
    }

    @GetMapping("/dashboard")
    public String getDashboard(Model model){

        // 部署・職制コンボボックス
        init(model);

        // アンケートコンボボックス
        List<Questionnaire> questionnaireList = homeService.getQuestionnaires();
        model.addAttribute("questionnaires", questionnaireList);
        Questionnaire questionnaire = homeService.getCurrentQuestionnaire().getQuestionnaire();

        //
        model.addAttribute("chartSelectForm", new ChartSelectForm());

        // アンケートの表示
        setChartData(model, questionnaire, new ChartSelectForm());

        return "/chart/dashboard";
    }

    @PostMapping("/dashboard")
    public String getSelectedDashboard(@Validated @ModelAttribute ChartSelectForm chartSelectForm,
                                       Model model){

        // 部署・職制コンボボックス
        init(model);

        // アンケートコンボボックス
        List<Questionnaire> questionnaireList = homeService.getQuestionnaires();
        model.addAttribute("questionnaires", questionnaireList);
        Questionnaire questionnaire = questionnaireService.getQuestionnaireById(Integer.parseInt(chartSelectForm.getQuestionnaireId()));

        // アンケートの表示
        setChartData(model, questionnaire, chartSelectForm);

        return "/chart/dashboard";
    }

    // アンケート情報の表示
    private void setChartData(Model model, Questionnaire questionnaire, ChartSelectForm chartSelectForm){
        // 現在表示するアンケート・部署・職制
        model.addAttribute("currentQuestionnaireId", questionnaire.getQuestionnaireId());
        model.addAttribute("currentDepartment", chartSelectForm.getDepartmentName());
        model.addAttribute("currentEmployee", chartSelectForm.getEmployeeStatus());

        // アンケート集計
        List<ChartRootData> chartRootData = new ArrayList<>();
        chartService.getQuestionnaireResult(questionnaire, chartRootData, chartSelectForm);

        model.addAttribute("chartRootData", chartRootData);
    }
}

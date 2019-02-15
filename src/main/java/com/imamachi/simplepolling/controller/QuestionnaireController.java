package com.imamachi.simplepolling.controller;

import com.imamachi.simplepolling.form.EmployeeForm;
import com.imamachi.simplepolling.form.ResultRootForm;
import com.imamachi.simplepolling.model.CurrentQuestionnaire;
import com.imamachi.simplepolling.model.Employee;
import com.imamachi.simplepolling.model.Question;
import com.imamachi.simplepolling.service.QuestionService;
import com.imamachi.simplepolling.service.QuestionnaireService;
import com.imamachi.simplepolling.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/questionnaire")
public class QuestionnaireController {

    private QuestionnaireService questionnaireService;
    private QuestionService questionService;
    private ResultService resultService;

    @Autowired
    public QuestionnaireController(QuestionnaireService questionnaireService,
                                   QuestionService questionService,
                                   ResultService resultService){
        this.questionnaireService = questionnaireService;
        this.questionService = questionService;
        this.resultService = resultService;
    }

    // コンボボックスの初期設定
    private void init(Model model){
        // 所属のリスト
        model.addAttribute("employeeList", Arrays.asList(Employee.Status.一般社員,
                Employee.Status.管理職, Employee.Status.パートナー社員));

        // 部署のリスト
        model.addAttribute("departmentList", questionnaireService.getDepartmentAll());
    }

    // アンケートトップページ
    @GetMapping("/top")
    public String getTopPage(Model model){
        // アンケートの情報を取得
        CurrentQuestionnaire currentQuestionnaire = this.questionnaireService.getCurrentQuestionnaire();
        model.addAttribute("title", currentQuestionnaire.getQuestionnaire().getTitle());

        // 所属・部署のリスト
        init(model);
        model.addAttribute("departmentNameError", "");

        // アンケートのタイトルを取得する
        model.addAttribute("employeeForm", new EmployeeForm());
        model.addAttribute("isError", "");
        return "/questionnaire/top";
    }

    // アンケートフォームへの遷移
    @PostMapping("/top")
    public String getForm(@Validated @ModelAttribute EmployeeForm employeeForm,
                          BindingResult result,
                          Model model){

        // アンケートの情報を取得
        CurrentQuestionnaire currentQuestionnaire = this.questionnaireService.getCurrentQuestionnaire();
        model.addAttribute("title", currentQuestionnaire.getQuestionnaire().getTitle());

        if(employeeForm.getDepartmentName().equals("ERROR")){
            model.addAttribute("departmentNameError", "ERROR");
        }else{
            model.addAttribute("departmentNameError", "");
        }

        if(result.hasErrors() || employeeForm.getDepartmentName().equals("ERROR")){
            model.addAttribute("employeeForm", employeeForm);
            init(model);
            return "/questionnaire/top";
        }
//
//        // ユーザ名が入力されていない場合、再入力を求める
//        if(username.equals("")){
//            model.addAttribute("title", currentQuestionnaire.getQuestionnaire().getTitle());
//            model.addAttribute("isError", "社員番号（0A番号）を入力してください。");
//            return "/questionnaire/top";
//        }
//
//        // アンケートのタイトルを取得する
//        model.addAttribute("title", currentQuestionnaire.getQuestionnaire().getTitle());
//        model.addAttribute("username", username);

        List<Question> questionList = questionService.getQuestionnaireInfo();
        model.addAttribute("questionList", questionList);
        model.addAttribute("resultRootForm", new ResultRootForm());

        // エラーメッセージ
        addAttribute2Error(model, "", "");
        return "/questionnaire/form";
    }

    // アンケートフォームの送信
    @PostMapping("/form")
    public String postFrom(@Validated @ModelAttribute ResultRootForm resultRootForm,
                           Model model){
        // 空欄チェック
        if(resultService.existFormError((resultRootForm))){
            addAttribute2Form(model, resultRootForm);
            addAttribute2Error(model, "", "回答していない質問があります。");
            return "/questionnaire/form";
        }

        // アンケート結果の登録
        try {
            resultService.registerResult(resultRootForm);
        }catch(DataIntegrityViolationException e){
            System.out.println(e.getMessage());
            addAttribute2Form(model, resultRootForm);
            addAttribute2Error(model, "すでに同じ社員番号（OA番号）で登録があります。", "");
            return "/questionnaire/form";
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "/questionnaire/submitted";
    }

    // フォーム情報のパラメータ渡し
    private void addAttribute2Form(Model model, ResultRootForm resultRootForm){
        model.addAttribute("title", resultRootForm.getQuestionnaireTitle());
        model.addAttribute("username", resultRootForm.getUsername());

        List<Question> questionList = questionService.getQuestionnaireInfo();
        model.addAttribute("questionList", questionList);
        model.addAttribute("resultRootForm", resultRootForm);
    }

    // エラーメッセージのパラメータ渡し
    private void addAttribute2Error(Model model, String error, String warning){
        model.addAttribute("isError",  error);
        model.addAttribute("isWarning", warning);
    }
}

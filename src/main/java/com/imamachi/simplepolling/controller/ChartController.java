package com.imamachi.simplepolling.controller;

import com.imamachi.simplepolling.form.QuestionDetailForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/chart")
public class ChartController {

    @GetMapping("/dashboard")
    public String getDashboard(Model model){
        // テスト
        ArrayList<QuestionDetailForm> questionDetailForms = new ArrayList<>();
        questionDetailForms.add(new QuestionDetailForm());
        questionDetailForms.add(new QuestionDetailForm());
        questionDetailForms.add(new QuestionDetailForm());
        model.addAttribute("iteration", questionDetailForms);
        return "/chart/dashboard";
    }
}

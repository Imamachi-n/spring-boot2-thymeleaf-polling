package com.imamachi.simplepolling.controller;

import com.imamachi.simplepolling.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home")
public class HomeController {

    private HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService){
        this.homeService = homeService;
    }

    // 画面の初期表示
    @GetMapping("")
    public String getHomePage(Model model){
        // 現在のアンケートを取得
        model.addAttribute("questionnaires", homeService.getQuestionnaires());
        model.addAttribute("currentQuestionnaireId", homeService.getCurrentQuestionnaire().getQuestionnaire().getQuestionnaireId());

        return "/home";
    }

    // アンケートの変更
    @PostMapping("")
    public String setQuestionnaire(@RequestParam(name = "questionnaireId") String questionnaireId,
                                   Model model){
        if(homeService.saveQuestionnaire(Integer.parseInt(questionnaireId))){
            // OK
            System.out.println("OK!");
        }else {
            // Error
        }
        return "redirect:/home";
    }
}

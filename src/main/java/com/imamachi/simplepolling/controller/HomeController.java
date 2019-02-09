package com.imamachi.simplepolling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping("")
    public String getHomePage(){
        // 現在のアンケートを取得

        return "/home";
    }

    @PostMapping("")
    public String setQuestionnaire(){
        return "/home";
    }
}

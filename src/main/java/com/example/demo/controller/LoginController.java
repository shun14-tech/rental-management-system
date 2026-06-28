package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String index(){
        return "login";
    }

    @GetMapping("/login")
    public String showloginform(){
         System.out.println("login画面を表示します");
        return "login";
    }

    @GetMapping("/items")
    public String showItems(){
        System.out.println("list画面を表示します");
        return"items/list";
    }


}

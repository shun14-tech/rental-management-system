package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Item;
import com.example.demo.repository.UserRepository;

@Controller
public class LoginController {

    private final UserRepository userRepository;
   

    public LoginController(UserRepository userRepository){
        this.userRepository=userRepository;
      
    }

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
    public String showItems(Model model){
        System.out.println("list画面を表示します");

        List<Item> itemList=userRepository.findAll();

        
        model.addAttribute("items",itemList);


       return"items/list";
        
    }

    //---新規登録----
    @GetMapping("/admin/register")
    public String showRegister(){
        System.out.println("新規登録画面を表示します");
        return"admin/register";  
    }

    @PostMapping("/admin/register")
    public String Register(@RequestParam("name") String name,
@RequestParam("category") String category,
@RequestParam("totalQuantity") int totalQuantity,Model model){
    System.out.println("登録を開始します");
    System.out.println("商品名:"+name+" "+" 種類:"+category+" "+"個数:"+totalQuantity);

    userRepository.register(name,category,totalQuantity,totalQuantity);

    
    return"redirect:/items";

    }
///items/{id}
/// 
/// ---詳細画面----
    @GetMapping("/items/{id}")
    public String showdetail(@PathVariable("id") int id,Model model){
        System.out.println("詳細画面に到達しました");
        

        Optional<Item> item=userRepository.findById(id);
       // System.out.println(item.get().getName());
       model.addAttribute("item", item.get());

        return "items/detail";


    }





    //----レンタル画面-----
    @GetMapping("/admin/rentals")
    public String showRentals(){
        System.out.println("レンタル画面を表示します");
        return "admin/rentals";
    }



}

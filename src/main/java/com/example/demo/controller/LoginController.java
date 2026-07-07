package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Item;
import com.example.demo.entity.Rental;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.securityconfig.LoginUser;

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
        

        Optional<Item> item=userRepository.findByitemId(id);
       // System.out.println(item.get().getName());
       model.addAttribute("item", item.get());

        return "items/detail";

    }

    @PostMapping("/items/{id}/apply")//このidはitemid
    public String apply(@PathVariable("id") int id, @RequestParam("returnDeadline") String returnDeadline,
     Model model){
        System.out.println("applyします");
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null) {
        System.out.println("認証情報が取得できませんでした。");
    }


    Object principal = authentication.getPrincipal();
    
    if (principal instanceof LoginUser) {
        LoginUser userDetails = (LoginUser) principal;
      // ★ 今日の日付（申請日）を取得
            LocalDate requestDate = LocalDate.now(); 

            System.out.println("userId: " + userDetails.getUserId() + " username: " + userDetails.getUsername() + " 申請日: " + requestDate + " 返却期限: " + returnDeadline);

            // リポジトリメソッドに申請日も渡すように変更
            userRepository.request(userDetails.getUserId(), id, requestDate, returnDeadline);
    }
        return"redirect:/items";
    }

    //----レンタル画面-----
    @GetMapping("/admin/rentals")
    public String showRentals(Model model){
        System.out.println("レンタル画面を表示します");
        //  List<Item> itemList=userRepository.findAll();

        List<Rental> RentalList=userRepository.findRentalAll();
         // model.addAttribute("ITEM1",item.get().getName());
        //  model.addAttribute("items",itemList);
         
     for(Rental rental : RentalList){
        Optional<Item> item = userRepository.findByitemId(rental.getItem_id());
        // modelではなく、rentalオブジェクト自体に名前をセットする
        rental.setItemName(item.get().getName());
        
        Optional<User> user = userRepository.findByuserId(rental.getUser_id());
        // rentalオブジェクト自体に名前をセットする
        rental.setUserName(user.get().getName());
    }

    // ループが終わって、名前が全てセットされたリストを1回だけ渡す
    model.addAttribute("rentals", RentalList);
        System.out.println("レンタルに代入します");
       // model.addAttribute("rentals",RentalList);


        return "admin/rentals";
    }



}

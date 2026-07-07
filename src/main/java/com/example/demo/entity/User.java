package com.example.demo.entity;

public class User {
    private int user_id;
    private String mailaddress;
    private String password;
    private String name;
    private int role_id;
    private boolean delete_flag;
    
    public User(int user_id,String mailaddress,String password,String name,int role_id,boolean delete_flag){
        this.user_id=user_id;
        this.mailaddress=mailaddress;
        this.password=password;
        this.name=name;
        this.role_id=role_id;
        this.delete_flag=delete_flag;
    }
    public void setUserId(int user_id){
        this.user_id=user_id;

    }
    public int getUserId(){
        return user_id;
    }
    public void setMailaddress(String mailaddress){
        this.mailaddress=mailaddress;
    }
    public String getMailladdress(){
        return mailaddress;
    }

    public void setPassword(String passsword){
        this.password=passsword;
    }
    public String getPassword(){
        return password;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }
    //   private int role_id;
    //private boolean delete_flag;
    //

}

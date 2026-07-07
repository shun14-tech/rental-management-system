package com.example.demo.entity;

import java.util.List;

import org.jspecify.annotations.Nullable;

public class Rental {

   private int rentalId;
   private int user_id;
   private int item_id;
   private String requestDate;
   private String returnDeadline;
   private String status;

  
    private String userName;
    private String itemName;
  

   public Rental(int rentalId,int user_id,int item_id,String requestDate,String returnDeadline,String status){
      this.rentalId=rentalId;
      this.user_id=user_id;
      this.item_id=item_id;
      this.requestDate=requestDate;
      this.returnDeadline=returnDeadline;
      this.status=status;
       System.out.println("####レンタルIDは"+rentalId);

   }
   public Rental() {}


   public void setrentalId(int rentalId){
       System.out.println("####レンタルIDは"+rentalId);
      this.rentalId=rentalId;
   }
   public int getrentalid(){
      System.out.println("レンタルIDは"+rentalId);
      return rentalId;
   }

   public void setUser_id(int user_id){
      this.user_id=user_id;
   }

   public int getUser_id(){
      return user_id;
   }

   public void setItem_id(int item_id){
      this.item_id=item_id;
   }

   public int getItem_id(){
      return item_id;
   }
   // String requestDate,String returnDeadline,String status

   public void setRequestDate(String requestDate){
      this.requestDate=requestDate;
   }

   public String getRequestDate(){
      return requestDate;
   }

   public void setReturnDeadline(String returnDeadline){
      this.returnDeadline=returnDeadline;
   }

   public String getReturnDeadline(){
      return returnDeadline;
   }

   public void setStatus(String status){
      this.status=status;
   }

   public String getStatus(){
      return status;
   }
   //public void


    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
  


}

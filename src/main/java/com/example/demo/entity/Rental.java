package com.example.demo.entity;


public class Rental {

   private int rental_id;
   private int user_id;
   private int item_id;
   private String requestDate;
   private String returnDeadline;
   private String status;
  

   public Rental(int rental_id,int user_id,int item_id,String requestDate,String returnDeadline,String status){
      this.rental_id=rental_id;
      this.user_id=user_id;
      this.item_id=item_id;
      this.requestDate=requestDate;
      this.returnDeadline=returnDeadline;
      this.status=status;

   }


   public void setRental_id(int rental_id){
      this.rental_id=rental_id;
   }
   public int getRental_id(){
      return rental_id;
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


}

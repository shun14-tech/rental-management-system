package com.example.demo.entity;

public class Rental {

   private int rental_id;
   private int user_id;
   private int item_id;
   private String requestDate;
   private String returnDeadline;
   private String status;
  

   public Rental(String requestDate,String returnDeadline,String status){
      this.requestDate=requestDate;
      this.returnDeadline=returnDeadline;
      this.status=status;

   }

   //public void


}

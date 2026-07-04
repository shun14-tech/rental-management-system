package com.example.demo.entity;

public class Item {
    private int itemId;
    private String name;
    private String category;
    private int totalQuantity;
    private int availableQuantity;

    public Item(int itemId,String name,String category,int totalQuantity,int availableQuantity){
        this.itemId=itemId;
        this.name=name;
        this.category=category;
        this.totalQuantity=totalQuantity;
        this.availableQuantity=availableQuantity;
    }
    public void setItemId(int itemId){
        this.itemId=itemId;
    }
    public int getItemId(){
        return itemId;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
       // System.out.println("itemの名前を取得します");
        return name;
    }

    public void setCategory(String category){
        this.category=category;
    }

    public String getCategory(){
        return category;
    }

    public void setTotalQuantity(int totalQuantity){
        this.totalQuantity=totalQuantity;
    }
    public int getTotalQuantity(){
        return totalQuantity;
    }

    public void setAvailableQuantity(int availableQuantity){
        this.availableQuantity=availableQuantity;
    }

    public int getAvailableQuantity(){
        return availableQuantity;
    }

}

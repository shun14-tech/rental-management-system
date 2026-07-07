package com.example.demo.repository;

import com.example.demo.DemoApplication;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Item;
import com.example.demo.entity.Rental;
import com.example.demo.entity.User;

@Repository
public class UserRepository {
    
    private final DemoApplication demoApplication;
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate, DemoApplication demoApplication){
        this.jdbcTemplate=jdbcTemplate;
        this.demoApplication = demoApplication;
    }

    public List<Item>findAll(){
        String sql="SELECT*from items";
        System.out.println("findAllを呼び出しました");

        List<Item> itemList=jdbcTemplate.query(sql, (rs,rowNum)->{
            
            return new Item(
                rs.getInt("item_id"),
                rs.getString("name"),
                rs.getString("category"),
                rs.getInt("total_quantity"),
                rs.getInt("available_quantity"));
            
        });

        System.out.println("sqlを実行しました");

        return itemList;
        
    }

    public int register(String name,String category,int totalQuantity,int total_quantity){
        String sql="INSERT INTO items(name,category,total_quantity,available_quantity) VALUES(?,?,?,?)";
         System.out.println("insert文を実行します");
           return jdbcTemplate.update(sql, name, category,totalQuantity,totalQuantity);
    }

    public Optional<Item> findByitemId(int id){
        String sql="select*from items where item_id=?";
        System.out.println("idをもとに探します");
        RowMapper<Item> mapper=(rs, rowNum) -> new Item(
            rs.getInt("item_id"),
            rs.getString("name"),
            rs.getString("category"),
            rs.getInt("total_quantity"),
            rs.getInt("available_quantity"));


            try {
    
            Item  item= jdbcTemplate.queryForObject(sql, mapper, id);
            System.out.println("findByUserIdを実行します");
            return Optional.ofNullable(item);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }

    public void request(int userId, int itemId, LocalDate requestDate, String returnDeadline) {
   
        String sql = "INSERT INTO rentals (user_id, item_id, request_date, return_deadline, status) VALUES (?, ?, ?, ?, 'APPLIED')";
        
        System.out.println("insert文を実行します");
        
        jdbcTemplate.update(sql, userId, itemId, requestDate, returnDeadline);
    }

    public List<Rental> findRentalAll(){
        String sql="select*from rentals";
         System.out.println("findRentalAllを呼び出しました");
        
          List<Rental> RentalList=jdbcTemplate.query(sql, (rs,rowNum)->{
            
            return new Rental(
                rs.getInt("rental_id"),
                rs.getInt("user_id"),
                rs.getInt("item_id"),
                rs.getString("request_date"),
                rs.getString("return_deadline"),
                rs.getString("status"));
                
            
        });

        System.out.println("sqlを実行しました");

        return RentalList;
}//findByuserId

  public Optional<User> findByuserId(int userid){
        String sql="select*from users where user_id=?";
        System.out.println("idをもとに探します");
        RowMapper<User> mapper=(rs, rowNum) -> new User(
            rs.getInt("user_id"),
            rs.getString("mailaddress"),
            rs.getString("password"),
            rs.getString("name"),
            rs.getInt("role_id"),
            rs.getBoolean("delete_flag"));


            try {
    
            User  user= jdbcTemplate.queryForObject(sql, mapper, userid);
            System.out.println("findByUserIdを実行します");
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }


}

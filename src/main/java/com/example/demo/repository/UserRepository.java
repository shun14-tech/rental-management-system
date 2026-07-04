package com.example.demo.repository;

import com.example.demo.DemoApplication;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Item;

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

    public Optional<Item> findById(int id){
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

}

package com.example.demo.securityconfig;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginUserDetailsService implements UserDetailsService {

    private final JdbcTemplate jdbcTemplate;

    public LoginUserDetailsService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String mailaddress){
        String sql="select*from users where mailaddress=? AND delete_flag=FALSE";
        List<LoginUser> users=jdbcTemplate.query(sql, (rs,rowNum)->{
            int role_id=rs.getInt("role_id");
             String authority = role_id == 1 ? "ROLE_ADMIN" : "ROLE_USER";
            return new LoginUser(
                    rs.getInt("user_id"),
                    rs.getString("mailaddress"),
                    rs.getString("password"),
                    rs.getString("name"),
                    List.of(new SimpleGrantedAuthority(authority)));
        }, mailaddress);
          if (users.isEmpty()) {
              System.out.println("パスワードまたはメールアドレスが正しくありません");
            throw new UsernameNotFoundException("User not found: " + mailaddress);
          
        }
          System.out.println("パスワードとメールアドレスがどちらも一致しました");
      
        return users.get(0);
    }
            
        
    }

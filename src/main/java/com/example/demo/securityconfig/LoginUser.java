package com.example.demo.securityconfig;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class LoginUser implements UserDetails{

    private int userId;
    private String mailaddress;
    private String password;
    private String name;

    private final Collection<? extends GrantedAuthority> authorities;

    public LoginUser(int userId,String mailaddress,String password,String name,Collection<? extends GrantedAuthority>authorities){
        this.userId=userId;
        this.mailaddress=mailaddress;
        this.password=password;
        this.authorities=authorities;
        this.name=name;
    }

    
   @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
   public int getUserId(){
        return userId;
    }
   
  

    @Override
    public String getUsername() {
        return mailaddress; 
    }

    @Override
    public String getPassword() {
        return password; 
    }

    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }

}

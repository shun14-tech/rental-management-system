package com.example.demo.securityconfig;

import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http
        .authorizeHttpRequests(authorize->authorize
        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
        .requestMatchers("/login","/").permitAll()
        .requestMatchers("/admin/register","/admin/rentals").hasRole("ADMIN")
        .anyRequest().authenticated()
        ).formLogin(form->form
            .loginPage("/login")
           .usernameParameter("mailaddress")
            .passwordParameter("password")
            .defaultSuccessUrl("/items",true)
            .failureUrl("/login?error")
        ).logout(logout->logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/logout?logout")
            .permitAll()
        ).csrf(Customizer.withDefaults());
        return http.build();
        
    }

    @Bean
    public BCryptPasswordEncoder PasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}

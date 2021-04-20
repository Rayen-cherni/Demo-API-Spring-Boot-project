package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    //zed'ha bch najem nestaaml el authManager f AuthController
   @Bean
   @Override
    protected AuthenticationManager authenticationManager() throws Exception{
       return  super.authenticationManager();
   }
   @Bean
    public  AuthFilter authFilter(){
    return new AuthFilter();
   }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()  //on utilise cors && csrf si on a des cockiess
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //statless khater we don't need sessions, we ll use JWT
                    .and()
                .authorizeRequests() //lenna taati les URI eli ynajem l user yodkholelhom mn ghir ma yaati l credentials
                    .antMatchers("/api/v1/auth/**").permitAll()
                    .anyRequest().authenticated()  //lenna kotlo ay API ekher bch yodkholo l user lazmo login
                    .and()
                //.httpBasic();
                .addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}

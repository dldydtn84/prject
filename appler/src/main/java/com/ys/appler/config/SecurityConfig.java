package com.ys.appler.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                      .antMatchers("/css/**", "/script/**", "/");
    }
    @Override
    protected  void configure(HttpSecurity http) throws Exception{
        http
                .csrf()
                    .disable()
                .authorizeRequests()
                    .antMatchers("/login")
                        .permitAll()
                    .anyRequest()
                    .authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .successHandler(new LoginSuccessHandler())
                    .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/");
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        String userQuery="select id as username,  CONCAT('{noop}', pass) password, enabled from tbl_member where id =? ";
        String authQuery="select id as username, authority FROM tbl_member  where id=?";
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(userQuery)
                .authoritiesByUsernameQuery(authQuery);


    }

}

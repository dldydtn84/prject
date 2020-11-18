package com.ys.appler.config;

import com.ys.appler.config.oauth.PrincipalOauth2UserService;
import com.ys.appler.handler.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;


import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/css/**","/img/**", "/script/**", "/","/static/**","/user/singup","/user/idsearch","/user/pwsearch");
                /*.antMatchers("**");*/
                      //.antMatchers("/css/**", "/script/**", "/");
    }
    @Override
    protected  void configure(HttpSecurity http) throws Exception{
        http
                .csrf()
                    .disable()
                .authorizeRequests()
                    .antMatchers("/user/login")
                        .permitAll()
                    .anyRequest()
                    .authenticated()
                .and()
                .formLogin()
                    .loginPage("/user/login")
                    .successHandler(new LoginSuccessHandler())
                    .and()
                .oauth2Login()
                    .loginPage("/user/login")
                      .userInfoEndpoint()
                     .userService(principalOauth2UserService)
                    .and()
                .logout()
                    .logoutUrl("/user/logout")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true);
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        String userQuery="select userid as username,   password, enabled from tbl_member where userid =? ";
        String authQuery="select userid as username, authority FROM tbl_member  where userid=?";
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(userQuery)
                .authoritiesByUsernameQuery(authQuery)
                .passwordEncoder(passwordEncoder());


    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        firewall.setAllowSemicolon(true);
        return firewall;
    } //시큐리티 방화벽 해제. 추후 변경필요

}

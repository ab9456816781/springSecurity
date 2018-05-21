package com.mmall.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     *  基于内存 实现
     * 简单的case实现，只需要能够登入即可
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //可以指定多个用户
       //auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");
       auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                                                        .withUser("admin")
                                                        .password(new BCryptPasswordEncoder().encode("123456"))
                                                        .roles("ADMIN");
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                                                        .withUser("demo")
                                                        .password(new BCryptPasswordEncoder().encode("123456"))
                                                        .roles("user");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()//项目主路径可以放行
                .anyRequest().authenticated()//其他请求需要验证
                .and()
                .logout().permitAll()//登出的话放行
                .and()
                .formLogin();//form表单放行
        //关闭csrf
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**","/css/**","/image/**");//
    }
}

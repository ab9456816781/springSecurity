package com.mmall.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication//
@RestController
@EnableAutoConfiguration//springboot 建议只有这一个注解的类  ，
@EnableGlobalMethodSecurity(prePostEnabled = true)//配合使用  表示开启
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RequestMapping("/")
	public String home(){
		return "hello spring boot ";
	}


	@RequestMapping("/hello")
	public String hello(){
		return "hello world";
	}

    /**
     * hasRole  表示只能是 admin 用户才可以访问
     * PreAuthorize  这个注解需要配备
     * @return
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/roleAuth")
    public String role(){
        return "admin auth";
    }
}

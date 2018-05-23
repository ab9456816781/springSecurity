package com.mmall.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
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

	/**
	 *
	 * 访问控制使用
	 * or  and
	 * @PreAuthorize适合进入方法之前验证授权。 @PreAuthorize可以兼顾，角色/登录用户权限，参数传递给方法等等。
	 * @PostAuthorize 虽然不经常使用，检查授权方法之后才被执行，所以它适合用在对返回的值作验证授权。
	 * @param id
	 * @param username
	 * @return
	 */
	@PreAuthorize("#id<10 or principal.username.equals(#username)")
	@PostAuthorize("returnObject%2==0")
	public String test(Integer id , String username){
		return "test";
	}

	/**
	 * 过滤使用
	 * @param id
	 * @param username
	 * @return
	 */
	@PreFilter("filterObject%2==0")
	@PostFilter("filterObject%4==0")
	public String test2(Integer id , String username){
		return "test2";
	}

}

package com.mmall.demo;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * 自定义 密码加密
 */
public class MyPasswordEncoder implements PasswordEncoder {


    /**
     * 盐值  用于多重加密。。
     */
    private final static String SALT = "12345";
    /**
     *
     * @return
     */
    @Override
    public String encode(CharSequence password) {
        Md5PasswordEncoder MD5Encoder = new Md5PasswordEncoder();
        return MD5Encoder.encodePassword(password.toString(),SALT);
    }

    /**
     * 实现
     * @return
     */
    @Override
    public boolean matches(CharSequence password, String encodePassword) {
        Md5PasswordEncoder MD5Encode = new Md5PasswordEncoder();
        return MD5Encode.isPasswordValid(encodePassword,password.toString(),SALT);
    }

}
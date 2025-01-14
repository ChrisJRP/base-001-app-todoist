package com.cjrp.base_001_app_todoist.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String Password = "admin123456";
        String PasswordEncriptado = encoder.encode(Password);
        System.out.println(PasswordEncriptado);
    }
}

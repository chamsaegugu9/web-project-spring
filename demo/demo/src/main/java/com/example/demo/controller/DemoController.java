package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.DemoEntity;
import com.example.demo.service.DemoServiceImpl;
import com.example.demo.utils.CookieUtils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    DemoServiceImpl demoService;

    @RequestMapping({ "/", "" })
    public DemoEntity rootPath(HttpServletRequest request, HttpServletResponse response) {
        DemoEntity demoEntity = demoService.findById("helloworld");

        return demoEntity;
    }

    @RequestMapping("/hello")
    public String helloPath() {
        System.out.println("I am /hello");
        return new String("Hello");
    }

    @PostMapping("/cookie")
    public String cookiePath(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("cookie");

        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        System.out.println(cookies);

        if (cookies != null) {
            for (Cookie cookieF : cookies) {
                if (cookieF == null) {
                    break;
                }
                System.out.println(cookieF.getName() + ": " + cookieF.getValue());
                if (cookieF.getName().equals("DemoCookie")) {
                    cookie = cookieF;
                }
            }

        }

        // return cookie.getName() + cookie.getValue();
        return cookie == null ? null : cookie.getName() + "=" + cookie.getValue();
    }

    @PostMapping("/login")
    public String loginPath() {
        System.out.println("login");
        return new String("login");
    }

    @PostMapping("/logout")
    public String logoutPath() {
        System.out.println("logout");
        return new String("logout");
    }

    @PostMapping("/join")
    public String joinPatm() {
        System.out.println("join");
        return new String("join");
    }

    // @PostMapping("/auth")
    // public String postMethodName() {

    // System.out.println("wnpswkd");

    // return new String("이런 젠장");
    // }

}

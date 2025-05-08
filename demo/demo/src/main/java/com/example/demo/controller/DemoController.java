package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DemoLoginRequestDTO;
import com.example.demo.entity.DemoEntity;
import com.example.demo.service.DemoServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    DemoServiceImpl demoService;

    @Operation(summary = "demo", description = "hello demo")
    @RequestMapping({""})
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

    @PostMapping("/what")
    public String whatPath(@RequestBody DemoLoginRequestDTO demoLoginRequestDTO) {
        
        System.out.println("what");
        return new String("login");
    }

    @PostMapping("/login")
    public String loginPath(@io.swagger.v3.oas.annotations.parameters.RequestBody @Parameter(description = "로그인 요청 정보", required = true) DemoLoginRequestDTO demoLoginRequestDTO) {
        
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
}

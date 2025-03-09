package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@RestController
public class DemoController {
    @RequestMapping("/")
    public String requestMethodName(HttpServletRequest request, HttpServletResponse response) {

        return "Hello World";
    }
    
}   

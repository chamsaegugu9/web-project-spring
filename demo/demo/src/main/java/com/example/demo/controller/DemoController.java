package com.example.demo.controller;

import java.net.http.HttpResponse;
import java.util.Enumeration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

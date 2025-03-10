package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.DemoEntity;
import com.example.demo.service.DemoServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@RestController
public class DemoController {
    @Autowired
    DemoServiceImpl demoService;

    @RequestMapping("/")
    public DemoEntity requestMethodName(HttpServletRequest request, HttpServletResponse response) {
        // DemoEntity demoEntity = demoService.findByName("helloworld");
        DemoEntity demoEntity = DemoEntity.builder().name("hello").build();
        return demoEntity;
    }
    
}   

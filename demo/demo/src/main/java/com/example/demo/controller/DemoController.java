package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.DemoEntity;
import com.example.demo.service.DemoServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class DemoController {
    @Autowired
    DemoServiceImpl demoService;

    @RequestMapping("/")
    public DemoEntity rootPath(HttpServletRequest request, HttpServletResponse response) {
        DemoEntity demoEntity = demoService.findById("helloworld");

        return demoEntity;
    }

    @PostMapping("/login")
    public String loginPath(@RequestParam String param) {
        return new String();
    }

}

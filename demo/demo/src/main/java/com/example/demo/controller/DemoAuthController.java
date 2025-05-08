package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.DemoEntity;
import com.example.demo.service.DemoServiceImpl;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/demo/auth")
public class DemoAuthController {

    @Autowired
    DemoServiceImpl demoService;

    @PostMapping({ "/", "" })
    public String authPath(@RequestBody DemoEntity demoEntity) {
        System.out.println("I am demo/auth : " + demoEntity);
        return new String("아 모르겟다 ㄹㅇ");
    }

    @PostMapping("/hell")
    public String authHellPath() {
        System.out.println("I am demo/auth/hell");
        return new String("아 모르겟다");
    }

}

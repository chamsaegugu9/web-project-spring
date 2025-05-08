package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.DemoEntity;
import com.example.demo.repository.DemoRepositoryImpl;

@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    DemoRepositoryImpl demoRepository;

    @Autowired
    PasswordEncoder encoderPWD;

    public void init() {
        DemoEntity demoEntity = DemoEntity.builder()
                .id("Hello")
                .name("World")
                .email("Hello@World.com")
                .password(encoderPWD.encode("HelloWorld"))
                .build();
        demoRepository.save(demoEntity);
    }

    @Transactional
    public DemoEntity findByName(String name) {
        DemoEntity demoEntity = demoRepository.findByName(name).orElseGet(() -> {
            return DemoEntity.builder().name("not").build();
        });
        // DemoEntity demoEntity = DemoEntity.builder().name("hello").build();
        return demoEntity;
    }

    @Transactional
    public DemoEntity findById(String id) {
        DemoEntity demoEntity = demoRepository.findById(id).orElseGet(() -> {
            return DemoEntity.builder().id("not").build();
        });
        return demoEntity;
    }
}

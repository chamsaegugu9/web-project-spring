package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.DemoEntity;
import com.example.demo.repository.DemoRepositoryImpl;

@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    DemoRepositoryImpl demoRepository;

    public void init() {
        demoRepository.save(DemoEntity.builder()
                .id("helloworld")
                .name("hello")
                .email("hello@world.com")
                .password("helloworld123@")
                .build());
    }

    public DemoEntity findByName(String name) {
        DemoEntity demoEntity = demoRepository.findByName(name).orElseGet(() -> {
            return DemoEntity.builder().name("not").build();
        });
        // DemoEntity demoEntity = DemoEntity.builder().name("hello").build();
        return demoEntity;
    }

    public DemoEntity findById(String id) {
        DemoEntity demoEntity = demoRepository.findById(id).orElseGet(() -> {
            return DemoEntity.builder().id("not").build();
        });
        return demoEntity;
    }
}

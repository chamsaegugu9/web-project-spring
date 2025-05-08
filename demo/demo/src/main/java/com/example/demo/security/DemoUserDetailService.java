package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.DemoEntity;
import com.example.demo.service.DemoServiceImpl;

@Service
public class DemoUserDetailService implements UserDetailsService {

    @Autowired
    DemoServiceImpl demoService;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        DemoEntity demoEntity = demoService.findById(id);

        return User.builder().username(demoEntity.getId()).password(demoEntity.getPassword()).build();
    }
}

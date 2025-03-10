package com.example.demo.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DemoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private String userCode;

    @Column(nullable = false, unique = true)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @PrePersist
    public void prePersist(){
        if(userCode==null){
            userCode = UUID.randomUUID().toString();
        }
    }
}

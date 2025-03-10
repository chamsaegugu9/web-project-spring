package com.example.demo.entity;

import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
public class DemoEntity {
    @Id
    // @Column(updatable = false, nullable = false)
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private String userCode;

    @Column(nullable = false, unique = true, length = 15)
    // @Size(min = 10, max = 15)
    private String id;

    @Column(nullable = false, length = 20)
    // @Size(max = 20)
    private String name;

    @Column(nullable = false, unique = true)
    // @Email
    private String email;

    @Column(nullable = false)
    // @Size(min = 12, max = 15)
    private String password;

    @PrePersist
    public void prePersist(){
        if(userCode==null){
            userCode = UUID.randomUUID().toString();
        }
    }
}

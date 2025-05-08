package com.example.demo.entity;

import java.util.Arrays;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DemoEntity {
    @Id
    @Column(updatable = false, nullable = false)
    private String userCode;

    @Column(nullable = false, unique = true, length = 15)
    @Size(min = 5, max = 15)
    private String id;

    @Column(nullable = false, length = 20)
    @Size(max = 20)
    private String name;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column(nullable = false)
    // @Size(min = 12, max = 15)
    private String password;

    @PrePersist
    public void prePersist() {
        if (userCode == null) {
            userCode = UUID.randomUUID().toString();
        }
    }

    public String toString() {
        return Arrays.deepToString(new Object[] { userCode, id, name, email, password });
    }
}

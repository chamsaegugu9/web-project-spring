package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.DemoEntity;

@Repository
public interface DemoRepository extends JpaRepository<DemoEntity,String>{
    List<DemoEntity> findAllByName(String name);
    DemoEntity findByName(String name);
}
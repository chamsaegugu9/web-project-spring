package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.DemoEntity;

@Repository
public interface DemoRepositoryImpl extends JpaRepository<DemoEntity,String>{
    // Optional<List<DemoEntity>> findAllByName(String name);
    // Optional<DemoEntity> findByName(String name);
}
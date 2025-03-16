package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.DemoEntity;

@Repository
public interface DemoRepositoryImpl extends JpaRepository<DemoEntity,String>{
    Optional<List<DemoEntity>> findAllByName(String name);
    Optional<DemoEntity> findByName(String name);

    @Query(value = "SELECT * FROM demo_entity WHERE id = :id", nativeQuery = true)
    Optional<List<DemoEntity>> findAllById(@Param("id")String id);
    @Query(value = "SELECT * FROM demo_entity WHERE id = :id LIMIT 1", nativeQuery = true)
    Optional<DemoEntity> findById(@Param("id")String id);
}
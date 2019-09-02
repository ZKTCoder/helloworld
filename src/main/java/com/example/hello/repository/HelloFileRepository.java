package com.example.hello.repository;

import com.example.hello.model.HelloFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelloFileRepository extends JpaRepository<HelloFile, Integer> {
}

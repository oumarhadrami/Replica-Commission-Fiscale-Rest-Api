package com.mohamed.hadramy.springbootapp.repository;

import com.mohamed.hadramy.springbootapp.model.Ministre;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MinistreRepository extends JpaRepository<Ministre, Long>{
    
}

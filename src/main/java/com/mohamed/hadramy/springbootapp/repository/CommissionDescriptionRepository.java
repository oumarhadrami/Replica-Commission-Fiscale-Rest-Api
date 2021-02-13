package com.mohamed.hadramy.springbootapp.repository;

import com.mohamed.hadramy.springbootapp.model.CommissionDescription;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommissionDescriptionRepository extends JpaRepository<CommissionDescription, Long>{
    
}

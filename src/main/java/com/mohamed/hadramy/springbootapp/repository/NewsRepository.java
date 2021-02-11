package com.mohamed.hadramy.springbootapp.repository;

import com.mohamed.hadramy.springbootapp.model.News;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NewsRepository extends JpaRepository<News, Long> {
    Page<News> findAllByOrderByIdDesc(Pageable pageRequest);
}

package com.mohamed.hadramy.springbootapp.repository;

import java.util.Optional;

import com.mohamed.hadramy.springbootapp.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

}

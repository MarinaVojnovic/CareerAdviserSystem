package com.sbnz.career.adviser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.career.adviser.entity.User;


public interface UserRepository extends JpaRepository<User, Long>{

}
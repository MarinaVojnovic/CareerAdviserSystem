package com.sbnz.career.adviser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sbnz.career.adviser.entity.User;


public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);

	@Query(value = "select * from users inner join verfication_tokens using (id)", nativeQuery = true)
	User findByToken(String token);

}

package com.kagoji.atfarestfulapis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kagoji.atfarestfulapis.entity.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserName(String userName);
}

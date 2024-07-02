package com.example.springjpademo.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springjpademo.entity.UserEntity;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Long>{

	UserEntity findByUsername(String username);
	
	@SuppressWarnings("unchecked")
	UserEntity save(UserEntity userentity);
	
	List<UserEntity> findByAddressState(String state);
}

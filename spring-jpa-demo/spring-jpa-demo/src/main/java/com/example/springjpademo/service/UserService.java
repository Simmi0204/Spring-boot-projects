package com.example.springjpademo.service;

import java.util.List;

import com.example.springjpademo.entity.UserEntity;

public interface UserService {
	
	public UserEntity findByUsername(String username);
	
	public UserEntity save(String username,String password, String address1, String address2, String landmark, 
			String city, int pincode, String state, String country);

	public List<UserEntity> findByAddressState(String state);
}

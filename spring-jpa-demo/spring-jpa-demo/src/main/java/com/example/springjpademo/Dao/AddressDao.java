package com.example.springjpademo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springjpademo.entity.Address;

public interface AddressDao extends JpaRepository<Address, Long>{

	@SuppressWarnings("unchecked")
	public Address save(Address address);

}

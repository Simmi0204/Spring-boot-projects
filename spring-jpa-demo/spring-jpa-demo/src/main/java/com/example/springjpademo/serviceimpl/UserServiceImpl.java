package com.example.springjpademo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springjpademo.Dao.AddressDao;
import com.example.springjpademo.Dao.UserDao;
import com.example.springjpademo.entity.Address;
import com.example.springjpademo.entity.UserEntity;
import com.example.springjpademo.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;
	@Autowired
	AddressDao addressDao;

	@Override
	public UserEntity findByUsername(String userId) {
		UserEntity userentity=userDao.findByUsername(userId);
		
		return userentity;//returning that one object with username as given
	}

	@Override
	public UserEntity save(String username, String password, String address1, String address2, String landmark,
			String city, int pincode, String state, String country) {
		UserEntity userentity = new UserEntity();
		userentity.setUsername(username);
		userentity.setPassword(password);
		
		Address address=new Address();
		address.setAddressLine1(address1);
		address.setAddressLine2(address2);
		address.setLandmark(landmark);
		address.setCity(city);
		address.setPincode(pincode);
		address.setState(state);
		address.setCountry(country);
		addressDao.save(address);
		
		
		userentity.setAddress(address);
		
		userDao.save(userentity);
		return userentity;//returning after setting and saving
	}

	@Override
	public List<UserEntity> findByAddressState(String state) {
		List<UserEntity> stateWiseUser = userDao.findByAddressState(state);
		return stateWiseUser;
	}

}

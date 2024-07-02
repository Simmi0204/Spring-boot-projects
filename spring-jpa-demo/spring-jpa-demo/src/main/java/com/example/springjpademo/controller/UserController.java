package com.example.springjpademo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springjpademo.entity.UserEntity;
import com.example.springjpademo.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/login1")
	public String loginPage(ModelMap model, @RequestParam(required = false) String successMSG) {
		if (successMSG != null) {
			model.put("successMSG", successMSG);
		}
		return "login";
	}
	
	@PostMapping("/welcome")
	public String welcomePage(ModelMap model,@RequestParam String username, @RequestParam String password) {
		UserEntity userentity = userService.findByUsername(username);
		if (userentity != null && userentity.getPassword() != null && userentity.getPassword().equals(password)) {
			model.put("successMSG", "logged in successfully!");
			model.put("user", username);
			return "welcome";
		} else {
			model.put("errorMSG", "enter correct credentials");
			return "login";
		}
	}
	
	@GetMapping("/register")
	public String registrationpage() {
		return "register";
	}

	@PostMapping("/register")
    public String registerUser(ModelMap model,  @RequestParam("username") String userId,
			@RequestParam("password") String password, @RequestParam("addressLine1") String address1,
			@RequestParam("addressLine2") String address2, @RequestParam("landmark") String landmark,
			@RequestParam("city") String city, @RequestParam("pincode") int pincode,
			@RequestParam("state") String state, @RequestParam("country") String country) {
		 userService.save(userId, password, address1, address2, landmark, city, pincode, state, country);
    	model.put("successMsg", "User created successfully!!");
    	return "login";
    }
	
	@GetMapping("/info")
	public String infoPage() {
		return "info";
	}
	
	@PostMapping("/info")
	public String checkadmin(ModelMap model, @RequestParam("username") String username,
			@RequestParam("password") String password) {
		UserEntity userentity = userService.findByUsername(username);
		if (userentity.getPassword() != null && userentity.getUsername().equals("admin")
				&& userentity.getPassword().equals(password)) {
			model.put("successMSG", "HELLO admin , this page will show admin specific information!");
			return "adminaccess";
		} else {
			model.put("errorMSG", "enter correct credentials for admin");
			return "info";
		}

	}

	@PostMapping("/adminaccess")//these paths have something to do with the pages they are associated with 
	public String getUserByState(ModelMap model, @RequestParam("state") String state) {
		model.put("stateName", state);
		List<UserEntity> statewiseusers = userService.findByAddressState(state);
		model.put("userbystate", statewiseusers);
		return "data";

	}

}

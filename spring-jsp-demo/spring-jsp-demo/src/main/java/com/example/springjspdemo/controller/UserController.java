package com.example.springjspdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springjspdemo.constants.Constants;
import com.example.springjspdemo.controller.bean.User;
import com.example.springjspdemo.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
    // @RequestMapping(value = "/login", method = RequestMethod.GET)
    // OR
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
    
    @PostMapping("/login")
    public String welcomePage(ModelMap model, @RequestParam String userId, @RequestParam String password) {
        User user = userService.getUserByUserId(userId);
        if(user != null && user.getPassword().equals(password)) {
        	
            model.put("userId", userId);
            
            if(user.getRoleId() == Constants.ROLE_ADMIN) {
            	List<User> usersList = userService.getAllUsers();
            	model.put("usersList",usersList);
                return "admin";
            } 
            else {
                return "user";
            }
        }
        
        model.put("errorMsg", "Please provide the correct userid and password");
        return "login";
    }


    
    @GetMapping("/register")
    public String registerPage(Model model) {
    	User user = new User();
    	model.addAttribute("user", user);
		return "register";
    }
    
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, ModelMap model) {
    	int count = userService.createNewUser(user);
    	if(count != 1) {
    		model.put("errorMsg", "Some issue occured with registration");
    		return "register";
    	}
    	model.put("successMsg", "User created successfully!!");
    	return "login";
    }
}

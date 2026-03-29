package com.ecommerce.ecommerce_backend.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerce.ecommerce_backend.repository.UserRepository;

import com.ecommerce.ecommerce_backend.entity.User;
import com.ecommerce.ecommerce_backend.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    
	@Autowired
	private UserService userService;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private BCryptPasswordEncoder encoder;
	
	@PostMapping("/register")
	public User registerUser(@RequestBody User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return userService.registerUser(user);
	}
	
	@PostMapping("/login")
	public String loginUser(@RequestBody User user) {

	    User existingUser = userRepo.findByEmail(user.getEmail());

	    if (existingUser != null && 
	    		encoder.matches(user.getPassword(), existingUser.getPassword())) {

	        return "Login Successful";
	    }

	    return "Invalid Email or Password";
	}
}

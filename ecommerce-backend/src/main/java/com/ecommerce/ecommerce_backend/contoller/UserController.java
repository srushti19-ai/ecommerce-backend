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
import com.ecommerce.ecommerce_backend.utils.JwtUtil;

import java.util.UUID;
import java.util.Date;
import org.springframework.http.ResponseEntity;
import com.ecommerce.ecommerce_backend.dto.ForgotPasswordRequest;
import com.ecommerce.ecommerce_backend.dto.ResetPasswordRequest;

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
		return userService.registerUser(user);
		
	}
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/login")
	public String loginUser(@RequestBody User user) {

	    User existingUser = userRepo.findByEmail(user.getEmail());

	    if (existingUser != null && 
	    		encoder.matches(user.getPassword(), existingUser.getPassword())) {
            
	    	    String token = jwtUtil.generateToken(user.getEmail());
	    	    return token;
	        //return "Login Successful";
	    }  

	    return "Invalid Email or Password";
	}
	
	@PostMapping("/forgot-password")
	public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {

	    User user = userRepo.findByEmail(request.getEmail());

	    if (user == null) {
	        return ResponseEntity.status(404).body("User not found");
	    }

	    String token = UUID.randomUUID().toString();

	    user.setResetToken(token);
	    user.setTokenExpiry(new Date(System.currentTimeMillis() + 1000 * 60 * 10));

	    userRepo.save(user);

	    return ResponseEntity.ok("Reset token: " + token);
	}
	
	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {

	    User user = userRepo.findByResetToken(request.getToken());

	    if (user == null) {
	        return ResponseEntity.status(400).body("Invalid token");
	    }

	    if (user.getTokenExpiry().before(new Date())) {
	        return ResponseEntity.status(400).body("Token expired");
	    }

	    user.setPassword(encoder.encode(request.getNewPassword()));
	    user.setResetToken(null);
	    user.setTokenExpiry(null);

	    userRepo.save(user);

	    return ResponseEntity.ok("Password reset successful");
	}
}

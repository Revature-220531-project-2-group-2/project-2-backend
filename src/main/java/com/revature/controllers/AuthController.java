package com.revature.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.Credentials;
import com.revature.models.User;
import com.revature.services.UserService;

/**
 *  
 */
@RestController
@CrossOrigin(origins="*", allowedHeaders="*")
@RequestMapping("/login")
public class AuthController {

	private UserService userService;

	@Autowired
	public AuthController(UserService userService) {
		this.userService = userService;
	}


	@PostMapping
	 public ResponseEntity<User> login(@Valid Credentials creds){
		Optional<User> user = userService.processLogin(creds.getUsername(), creds.getPassword());
		// returns a status with nothing elsec
		if (!user.isPresent()) {
			return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
		} else {
			return ResponseEntity.ok(user.get());
		}
	}
}

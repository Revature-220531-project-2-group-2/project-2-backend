package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.User;
import com.revature.services.UserService;

/**
 * Responsible for all info related to the users
 *  
 * @author Kenneth Burke
 *
 */

@RestController  //adds @Controller and @ResponseBody annotations...converts
@RequestMapping("/users") //access the methods at http://localhost:5000/users

public class UserController {

	//DB -> DAO (Repo layer) -> Service Layer -> Controller
	
	private UserService userService;
	
	//@Autowired 
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	//add functionality to respond to GET request, POST, PUT, DELETE
	@GetMapping
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findUserById(@PathVariable("id") int id) {
		Optional<User> user = userService.getById(id);
		if(!user.isPresent()) {
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		} else {
			return ResponseEntity.ok(user.get());
		}
	}
	//register a user
	@PostMapping
	public User registerNewUser(@RequestBody User newUser) {
		return userService.processRegister(newUser);
	}
	
	//update a user
	@PutMapping
	public User updateUser(@RequestBody User updatedUser) {
		return userService.updateUser(updatedUser);
	}
}

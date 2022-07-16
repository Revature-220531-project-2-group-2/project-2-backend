package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.data.UserRepository;
import com.revature.models.CharSheet;
import com.revature.models.User;

@Service //STEREOTYPE Annotation which specifies the duties of this component
public class UserService {

	
	private UserRepository userRepo;
	
	//Spring Data will generate an implementation class UserRepositoryImpl Bean automatically and inject
	//it into an instance of UserService everytime UserService is called
	
	//@Autowired
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	
//TODO: get campaigns by user
	
	//Register a new user to the database
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public User processRegister(User newUser) {
		return userRepo.save(newUser);
	}
	
	

	public Optional<User> processLogin(String username, String password) {
		return userRepo.findUserByUsernameAndPwd(username, password);
	}
	

	public Optional<User> getById(int id){
		return userRepo.findById(id);
	}
	
	public Optional<User> getByUsername(String username){
		return userRepo.findUserByUsername(username);
		
	}

	//Get all Users from the db
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
	
	public User updateUser(User updatedUser) {
		return userRepo.save(updatedUser);
	}
	
	
	public void deleteUser(int id) {
		userRepo.deleteById(id);
	}


	public CharSheet addCharSheet(User u, CharSheet newCharSheet) {
		u.getCharacters().add(newCharSheet);
		userRepo.save(u);
		return newCharSheet;
	}
	
	


}

package com.revature.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.data.CharSheetRepository;
import com.revature.data.UserRepository;
import com.revature.models.CharSheet;
import com.revature.models.User;

@Service //STEREOTYPE Annotation which specifies the duties of this component
public class UserService {

	
	private UserRepository userRepo;
	private CharSheetRepository charRepo;
	
	//Spring Data will generate an implementation class UserRepositoryImpl Bean automatically and inject
	//it into an instance of UserService everytime UserService is called
	
	//@Autowired
	public UserService(UserRepository userRepo, CharSheetRepository charRepo) {
		this.userRepo = userRepo;
		this.charRepo = charRepo;
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
		System.out.println(userRepo.findAll().get(0));
		return userRepo.findAll();
	}
	
	public User updateUser(User updatedUser) {
		return userRepo.save(updatedUser);
	}
	
	
	public void deleteUser(int id) {
		userRepo.deleteById(id);
	}


	public CharSheet addCharSheet(User u, CharSheet newCharSheet) {
		System.out.println(newCharSheet);
		newCharSheet.setUser(u);

//		Set<CharSheet> mySheets = charRepo.findAllCharSheetsByUserId(u.getId());
//		mySheets.add(newCharSheet);
		charRepo.save(newCharSheet);
//		userRepo.save(u);
		return newCharSheet;
	}


	public void removeCharSheet(User user, CharSheet character) {
		user.getCharacters().remove(character);
		userRepo.save(user);
			
	}
	
	public CharSheet updateCharSheet(User u, CharSheet aCharSheet) {

		u.updateCharacter(aCharSheet);
		aCharSheet.addUser(u);
		userRepo.save(u);
//		userRepo.save(u);
		return aCharSheet;
	}
	
	public CharSheet deleteCharSheet(User u, CharSheet aCharSheet) {
		u.deleteCharacter(aCharSheet);
		aCharSheet.removeUser(u);
		userRepo.save(u);
		charRepo.deleteById(aCharSheet.getCharId());
//		userRepo.save(u);
		return aCharSheet;
	}
	

	


}

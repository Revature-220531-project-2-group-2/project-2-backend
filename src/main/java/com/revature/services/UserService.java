package com.revature.services;

import java.util.List;
import java.util.Optional;

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
		
		return userRepo.findAll().stream().filter(u -> u.getUsername().equals(username)).findFirst();		
	}

	//Get all Users from the db
	public List<User> getAllUsers(){
		System.out.println(userRepo.findAll().get(0));
		return userRepo.findAll();
	}
	
	public User updateUser(User updatedUser) {
		return userRepo.save(updatedUser);
	}
	
	
	//changed by kburke for testing purposes to return the User that has been deleted
	public Optional<User> deleteUser(int id) {
		return userRepo.deleteById(id);
	}


	public CharSheet addCharSheet(User u, CharSheet newCharSheet) {
		
		newCharSheet.setUser(u);
        u.addCharacter(newCharSheet);
//		Set<CharSheet> mySheets = charRepo.findAllCharSheetsByUserId(u.getId());
//		mySheets.add(newCharSheet);
		charRepo.save(newCharSheet);
		userRepo.save(u);
//		userRepo.save(u);
		return newCharSheet;
	}


	/**
	 * Removes a specific character from the the users character list
	 * @param user
	 * @param character
	 * @return The character that has been removed from the users character list
	 */
	public User removeCharSheet(User user, CharSheet character) {
		user.getCharacters().remove(character);
		return userRepo.save(user);
		
	}
	
	/**
	 * Adds a character to the user's list and updates the user
	 * @param u
	 * @param aCharSheet
	 * @return The updated user
	 */
	public User updateCharSheet(User u, CharSheet aCharSheet) {

		u.updateCharacter(aCharSheet);
		return userRepo.save(u);
		
	}
	

	

	


}

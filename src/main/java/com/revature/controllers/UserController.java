package com.revature.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Campaign;
import com.revature.models.CharSheet;
import com.revature.models.User;
import com.revature.services.CampaignService;
import com.revature.services.CharSheetService;
import com.revature.services.UserService;

/**
 * Responsible for all info related to the users
 *  
 *  
	 * User can:    
	 *            register *
	 *            be updated *
	 *            be deleted *
	 *            get a list of all users
	 *            create a new character *             "/{username}/add-character"
	 *            
	 *            update/save a specific character *   "/{username}/update-character"
	 * 			  delete a specific character *        "/{username}/remove-character"
	 *            retrieve their characters *          "/{username}/characters"
	 *            retrieve a specific character by id  "/{username}/characters/{id}"
	 *            or character name *                  "/{username}/characters/{char-name}"
	 *            u
	 *            
	 *            retrieve a list of their campaigns *    "/{username}/campaigns
	 *            add themselves to a specific campaign*  "/{username}/campaigns/join-campaign-{id}
	 *            can view all campaigns *                 "/{username}/view-all-campaigns"
	 

    
 * @author Kenneth Burke
 *
 */

@RestController  //adds @Controller and @ResponseBody annotations...converts
@RequestMapping("/users") //access the methods at http://localhost:5000/users

public class UserController {

	//DB -> DAO (Repo layer) -> Service Layer -> Controller
	
	private UserService userService;
	private CharSheetService charService;
	private CampaignService campService;
	
	//@Autowired 
	public UserController(UserService userService, CharSheetService charService, CampaignService campService) {
		this.userService = userService;
		this.charService = charService;
		this.campService = campService;
	}
//===========================================GENERAL USER MAPPINGS ============================================

	/**
	 * Register a user
	 * @param newUser
	 * @return
	 */
	@PostMapping
	public User registerNewUser(@RequestBody User newUser) {
		return userService.processRegister(newUser);
	}
	
	/**
	 * Update/Save a user
	 * @param updatedUser
	 * @return
	 */
	@PutMapping
	public User updateUser(@RequestBody User updatedUser) {
		return userService.updateUser(updatedUser);
	}
	
	//add functionality to respond to GET request, POST, PUT, DELETE
	/**
	 * Retrieves a list of all current users
	 * @return
	 */
	@GetMapping
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	/**
	 * Returns a user by their username
	 * @param username The username of the user being searched for;
	 * @return
	 */
	@GetMapping(value="/all")
	public ResponseEntity<User> findUserByUserName(@RequestBody String username){
		Optional<User> user = userService.getByUsername(username);
		if(!user.isPresent()) {
			
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}else {
			return ResponseEntity.ok(user.get()); 
		}
		
	}
	
	/**
	 * Returns a user by their id
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findUserById(@PathVariable("id") int id) {
		Optional<User> user = userService.getById(id);
		if(!user.isPresent()) {
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		} else {
			return ResponseEntity.ok(user.get());
		}
	}
	/**
	 * Deletes a user by their id
	 * @param id
	 */
	@DeleteMapping(value = "/{id}")
	public void removeUser(@PathVariable("id") int id) {
		userService.deleteUser(id);
	}
	
//=================================User Campaign Mappings =====================================================
	
	/**
	 * Returns the campaigns associated with a given username 
	 * @param username 
	 * @return 
	 */
	@GetMapping(value = "/{username}/campaigns")
	public ResponseEntity<List<Campaign>> getCampaignByUser(@PathVariable("username") String username){
		List<Campaign> campaigns = campService.getCampaignsByUsername(username);
		if(campaigns == null) {
			return new ResponseEntity<List<Campaign>>(HttpStatus.NO_CONTENT);
		}else {
			return ResponseEntity.ok(campaigns);
		}
	
	}

	/**
	 * Enable the user to join a campaign with the specified id;
	 * @param username
	 * @param campId
	 */
	@PostMapping(value = "/{username}/join-campaign-{id}")
	public ResponseEntity<Campaign> joinCampaign(@PathVariable("username") String username, 
							 @PathVariable("id") int campId
							 ){
	
		Optional<User> user = userService.getByUsername(username);
		Optional<Campaign> campaign = campService.findById(campId);
				
	    if(!campaign.isPresent()  || !user.isPresent()) {
	    	return new ResponseEntity<Campaign>(HttpStatus.BAD_REQUEST);
	    }else {
	    	user.get().getCampaigns().add(campaign.get());
	    	return ResponseEntity.ok(campaign.get());
	    }
	}
	/**
	 * View a list of all campaigns
	 * @return
	 */
	@GetMapping(value="/{username}/view-all-campaigns")
	public List<Campaign> viewAllCampaigns(){
	      return campService.getAllCampaigns();
	}
	

	//==================================Character mappings for the current User ===============================
	/**
	 * Add a character for the user
	 * @param newCharSheet
	 * @return
	 */
	@PostMapping(value="/{username}/add-character")
	public ResponseEntity<User> addNewCharacter(@PathVariable("username") String username, @RequestBody CharSheet newCharSheet) {
		System.out.println("\n\nusername  " + username + "\n\n");
		Optional<User> user = userService.getByUsername(username);
		if(!user.isPresent()) {
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}else {
			newCharSheet.setCharName(newCharSheet.getCharName().replace(" ", "-"));
		    userService.addCharSheet(user.get(), newCharSheet);
		    return ResponseEntity.ok(user.get());
		}
		    
		}
	
	/**
	 * Delete a character for the user
	 * @param delCharSheet
	 */
//TODO change this to entity
	@DeleteMapping(value="/{username}/remove-character-{id}")
	public void removeCharSheet(@PathVariable("username") String username, @PathVariable("id") int characterId) {
		Optional<CharSheet> characterOptional = charService.findById(characterId);
		CharSheet character = characterOptional.get();
		User u = userService.getByUsername(username).get();
		userService.deleteCharSheet(u, character);
//		charService.deleteCharSheetById(characterId);
	}
//	public void removeCharSheet(@PathVariable("username") String username, @RequestBody CharSheet delCharSheet) {
//		charService.deleteCharSheetById(delCharSheet.getCharId());	
//	}
	
	/**
	 * Update or Save the users character
	 * @param username
	 * @param updatedCharSheet
	 */
	@PutMapping(value="/{username}/update-character-{id}")
	public void updateCharSheet(@PathVariable("username") String username, 
			@PathVariable("id") int charId, @RequestBody CharSheet updatedCharSheet) {
		// some sort of logic to see that charId belongs to this user
		System.out.println(updatedCharSheet);
		User u = userService.getByUsername(username).get();
		userService.updateCharSheet(u, updatedCharSheet);
//		charService.updateCharSheet(updatedCharSheet);
	}
	
	/**
	 * Get all of the user's characters
	 * @param username
	 * @return
	 */
	@GetMapping(value="/{username}/characters")
	public Set<CharSheet> getUserCharacters(@PathVariable("username") String username){
		return charService.getCharactersByUsername(username);
	}
	
	/**
	 * Get a character by id
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{username}/characters/get-{id}")
	public ResponseEntity<CharSheet> findCharacterById(@PathVariable("id") int id) {
		Optional<CharSheet> character = charService.findById(id);
		if(!character.isPresent()) {
			return new ResponseEntity<CharSheet>(HttpStatus.NO_CONTENT);
		} else {
			return ResponseEntity.ok(character.get());
		}
	}
	
	/**
	 * Get a character by Username and Char name
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{username}/characters/name-{name}")
	public ResponseEntity<CharSheet> findCharacterByName(@PathVariable("username") String username,
			@PathVariable("name") String charName){
		Optional<CharSheet> character = charService.findByCharName(charName);
		if(!character.isPresent()) {
			
			return new ResponseEntity<CharSheet>(HttpStatus.NO_CONTENT);
		}else {
			return ResponseEntity.ok(character.get()); 
		}
	}
		
}
	
	
	


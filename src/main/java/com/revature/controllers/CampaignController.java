package com.revature.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Campaign;
import com.revature.models.Message;
import com.revature.models.User;
import com.revature.services.CampaignService;
import com.revature.services.UserService;

/**
 * CampaignController:
 *               can create a new campaign                      
 *               can get a list of all campaigns
 *               can get a specific campaign by id              "/{id} 
 *               can get a list of users attached to a campaign "/{id}/users"
 *               can add a user to a specific campaign          "/{id}/add-{username}"
 *               can remove a user from a specific campaign     "/{id}/remove-{username}"
 *               can add a message to the campaign              "/{id}/new-message
 *               can retrieve message list form the campaign    "/{id}/messages
 */
@RestController
@RequestMapping("/campaigns")
public class CampaignController {

	private CampaignService campServ;
	private UserService userServ;
	
	public CampaignController(CampaignService campServ, UserService userv) {
		this.campServ = campServ;
		this.userServ = userv;
	}
	
	@PostMapping("/{id}/new-message")
	public String addMessage(@PathVariable("id") int id, @RequestBody Campaign c, Message msg){
		
		return campServ.addMessage(c, msg);
	}

	@GetMapping("/{id}/messages")
	public List<Message> getAllMessages(int id){
		return campServ.getMessagesFromCampaign(id);
	}
	/**
	 * Get a list of current campaigns
	 * @return
	 */
	@GetMapping
	public List<Campaign> getAllCampaigns(){
		return campServ.getAllCampaigns();
	}
	
	/**
	 * Create and save a new Campaign
	 * @param newCampaign
	 * @return
	 */
	@PostMapping(value="/new-campaign")
	public Campaign  createNewCampaign(@RequestBody Campaign newCampaign) {
		String username = ((User)newCampaign.getUsers().toArray()[0]).getUsername();
		User u = userServ.getByUsername(username).get();
		newCampaign.getUsers().clear();
		newCampaign.addUser(u);
		return campServ.addCampaign(newCampaign);
	}

	/**
	 * Gets a campaign by its id
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	public ResponseEntity<Campaign> findCampaignById(@PathVariable("id") int id) {
		Optional<Campaign> campaign = campServ.findById(id);
		if(!campaign.isPresent()) {
			return new ResponseEntity<Campaign>(HttpStatus.NO_CONTENT);
		} else {
			return ResponseEntity.ok(campaign.get());
		}
	}
	/**
	 * Return a list of users attached to asoociated with a given campaign
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}/users")
	public Set<User> getUsersInCampaign(@PathVariable("id") int id){
		Optional<Campaign> campaign = campServ.getCampaignById(id);
		if(campaign.isPresent()) {
			return campaign.get().getUsers();
		}else { //return empty set	
			return new HashSet<User>();
		}
	}

	/**
	 * Adds a user to a campaign
	 * @param id The id of the campaign
	 * @param username The username of the user to be added
	 * @return 
	 */
	@PostMapping(value="/{id}/add-{username}")
	public ResponseEntity<Campaign> addUserToCampaign(@PathVariable("id") int id, @PathVariable("username") String username) {
		Optional<Campaign> campaign = campServ.getCampaignById(id);
		Optional<User> user = userServ.getByUsername(username);
		if(!campaign.isPresent() || !user.isPresent()) {
			return new ResponseEntity<Campaign>(HttpStatus.NO_CONTENT);
		}else {
			campServ.addUserToCampaign(user.get(), campaign.get());
			return ResponseEntity.ok(campaign.get());
		}
	}
	

	/**
	 * Removes a user to a campaign
	 * @param id The id of the campaign
	 * @param username The username of the user to be added
	 * @return 
	 */
	@PostMapping(value="/{id}/remove-{username}")
	public ResponseEntity<Campaign> removeUserFromCampaign(@PathVariable("id") int id, @PathVariable("username") String username) {
		Optional<Campaign> campaign = campServ.getCampaignById(id);
		Optional<User> user = userServ.getByUsername(username);
		if(!campaign.isPresent() || !user.isPresent()) {
			return new ResponseEntity<Campaign>(HttpStatus.NO_CONTENT);
		}else {
			campServ.removeUserFromCampaign(user.get(), campaign.get());
			return ResponseEntity.ok(campaign.get());
		}
	}
}

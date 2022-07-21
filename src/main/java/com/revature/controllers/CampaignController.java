package com.revature.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.CampaignHolder;
import com.revature.dto.MessageHolder;
import com.revature.models.Campaign;
import com.revature.models.Message;
import com.revature.models.User;
import com.revature.services.CampaignService;
import com.revature.services.MessageService;
import com.revature.services.UserService;

/**
 * CampaignController: can create a new campaign can get a list of all campaigns
 * can get a specific campaign by id "/{id} can get a list of users attached to
 * a campaign "/{id}/users" can add a user to a specific campaign
 * "/{id}/add-{username}" can remove a user from a specific campaign
 * "/{id}/remove-{username}" can add a message to the campaign
 * "/{id}/new-message can retrieve message list form the campaign
 * "/{id}/messages
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/campaigns")
public class CampaignController {

	private CampaignService campServ;
	private UserService userServ;
	private MessageService msgServ;

	public CampaignController(CampaignService campServ, UserService userv, MessageService msgServ) {
		this.campServ = campServ;
		this.userServ = userv;
		this.msgServ = msgServ;
	}

	@PostMapping("/{id}/new-message")
	public Message addMessage(@PathVariable("id") int id, @RequestBody MessageHolder msgHolder) {
 
		Message msg = new Message();
	    Campaign c = campServ.findById(id).get();
		
		msg.setCamp(c);
		msg.setMsg(msgHolder.getMsg());
		User user = userServ.getByUsername(msgHolder.getUsername()).get();
		msg.setOwner(user);
		msg.setTimeStampAsString(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
		msg.setUsername(user.getUsername());
		
		campServ.addMessage(c, msg);
		return msgServ.save(msg);
	}

	@GetMapping("/{id}/messages")
	public List<Message> getAllMessages(@PathVariable("id") int id) {
		return campServ.getMessagesFromCampaign(id);
	}

	/**
	 * Get a list of current campaigns
	 * 
	 * @return
	 */
	@GetMapping
	public List<Campaign> getAllCampaigns() {
		List<Campaign> camps = campServ.getAllCampaigns();
		camps.forEach(e -> System.out.println(e.getUsers()));
		System.out.println(camps);
		return campServ.getAllCampaigns();
	}

	/**
	 * Create and save a new Campaign
	 * Adds the creator of the campaign to the user list within the campaign
	 * @param newCampaign
	 * @return
	 */
	@PostMapping(value = "/new-campaign")
	public Campaign createNewCampaign(@RequestBody CampaignHolder campaignHolder) {

		Campaign newCampaign = new Campaign();
		newCampaign.setCampaignName(campaignHolder.getCampaignName());
		newCampaign.setMessages(new LinkedList<Message>());
		newCampaign.setUsers(new HashSet<User>());
		
		User creator = userServ.getByUsername(campaignHolder.getUsernameOfCreator()).get();
		
		newCampaign.getUsers().add(creator);
		creator.addCampaign(newCampaign);
	
		
		return campServ.addCampaign(newCampaign);
	}

	/**
	 * Gets a campaign by its id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Campaign> findCampaignById(@PathVariable("id") int id) {
		Optional<Campaign> campaign = campServ.findById(id);
		if (!campaign.isPresent()) {
			return new ResponseEntity<Campaign>(HttpStatus.NO_CONTENT);
		} else {
			return ResponseEntity.ok(campaign.get());
		}
	}

	/**
	 * Return a list of users attached to associated with a given campaign
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}/users")
	public Set<User> getUsersInCampaign(@PathVariable("id") int id) {
		Optional<Campaign> campaign = campServ.getCampaignById(id);
		if (campaign.isPresent()) {
			return campaign.get().getUsers();
		} else { // return empty set
			return new HashSet<User>();
		}
	}

	/**
	 * Adds a user to a campaign
	 * 
	 * @param id       The id of the campaign
	 * @param username The username of the user to be added
	 * @return
	 */
	@PostMapping(value = "/{id}/add-{username}")
	public ResponseEntity<Campaign> addUserToCampaign(@PathVariable("id") int id,
			@PathVariable("username") String username) {
		Optional<Campaign> campaign = campServ.getCampaignById(id);
		Optional<User> user = userServ.getByUsername(username);
		if (!campaign.isPresent() || !user.isPresent()) {
			return new ResponseEntity<Campaign>(HttpStatus.NO_CONTENT);
		} else {
			campServ.addUserToCampaign(user.get(), campaign.get());
			return ResponseEntity.ok(campaign.get());
		}
	}

	/**
	 * Removes a user to a campaign
	 * 
	 * @param id       The id of the campaign
	 * @param username The username of the user to be added
	 * @return
	 */
	@PostMapping(value = "/{id}/remove-{username}")
	public ResponseEntity<Campaign> removeUserFromCampaign(@PathVariable("id") int id,
			@PathVariable("username") String username) {
		Optional<Campaign> campaign = campServ.getCampaignById(id);
		Optional<User> user = userServ.getByUsername(username);
		if (!campaign.isPresent() || !user.isPresent()) {
			return new ResponseEntity<Campaign>(HttpStatus.NO_CONTENT);
		} else {
			campServ.removeUserFromCampaign(user.get(), campaign.get());
			return ResponseEntity.ok(campaign.get());
		}
	}
}

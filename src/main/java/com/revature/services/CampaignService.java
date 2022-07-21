package com.revature.services;


import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.revature.data.CampaignRepository;
import com.revature.data.MessagesRepository;
import com.revature.models.Campaign;
import com.revature.models.Message;
import com.revature.models.User;


@Service
public class CampaignService {


	private CampaignRepository campRepo;

	private MessagesRepository msgRepo;
	
	public CampaignService(CampaignRepository campRepo,  MessagesRepository msgRepo) {
		this.campRepo = campRepo;
		
		this.msgRepo = msgRepo;
	}
	
	public List<Campaign> getAllCampaigns(){
		return campRepo.findAll();
	}
	



	public Optional<Campaign> getCampaignById(int id) {
		return campRepo.findById(id);
	}
	public Campaign addUserToCampaign(User u, Campaign c) {
		c.addUser(u);
	
	
		return campRepo.save(c);
	
	}
	public Campaign removeUserFromCampaign(User u, Campaign c) {
	
		c.removeUser(u);
		return campRepo.save(c);
			
	}
	
	
	public Optional<Campaign> findById(int id) {
		return campRepo.findById(id);
	}
	
	public void deleteCampaign(int id) {
		campRepo.deleteById(id);
	}

	public Campaign addCampaign(Campaign cp) {
		return campRepo.save(cp);
	}
	
	public Campaign updateCampaign(Campaign cp) {
		return campRepo.save(cp);
	}
	
	public List<Campaign> getCampaignsByUser(User user){
		return campRepo.findAll().stream()
				.filter(c -> c.getUsers().contains(user)).collect(Collectors.toList());
	}

	/**
	 * Add a message to the campaign
	 * @param c
	 * @param msg
	 * @return The updated campaign
	 */
	public String addMessage(Campaign c, Message msg) {
		c.getMessages().add(msg);
	    campRepo.save(c);
	    return msgRepo.save(msg).getMsg();
	    
	    
	}
	
	/**
	 * Get a list of all messages associated with a campaign 
	 * @param campId The id of the campaign
	 * @return List of messages or empty list if no messages present
	 */
	public List<Message> getMessagesFromCampaign(int campId){
		Optional<Campaign> campaign = campRepo.findById(campId);
		
		return (campaign.isPresent()) ? campaign.get().getMessages() : new LinkedList<Message>();
	}
}

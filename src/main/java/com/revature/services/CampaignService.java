package com.revature.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.revature.data.CampaignRepository;
import com.revature.models.Campaign;
import com.revature.models.User;

@Service
public class CampaignService {

	private CampaignRepository campRepo;
	
	public Campaign updateCampaign(Campaign c) {
		return campRepo.save(c);
	}
	
	public List<Campaign> getAllCampaigns(){
		return campRepo.findAll();
	}
	
	public List<Campaign> getCampaignsByUser(User u){
		return campRepo.findAll().stream().filter(c -> c.getUsers().contains(u))
				.collect(Collectors.toList());
	}
	
	public Optional<Campaign> getCampaignById(int id) {
		return campRepo.findById(id);
	}
	public void removeUserFromCampaign(User u, Campaign c) {
		c.removeUser(u);
		campRepo.save(c);
		
	}
}

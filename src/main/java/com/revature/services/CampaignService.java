package com.revature.services;


import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.revature.data.CampaignRepository;
import com.revature.data.UserRepository;
import com.revature.models.Campaign;
import com.revature.models.User;


@Service
public class CampaignService {


	private CampaignRepository campRepo;
	private UserRepository userRepo;
	
	public CampaignService(CampaignRepository campRepo, UserRepository userRepo) {
		this.campRepo = campRepo;
		this.userRepo = userRepo;
	}
	
	public List<Campaign> getAllCampaigns(){
		return campRepo.findAll();
	}
	
	public Set<Campaign> getCampaignsByUsername(User u){
		return campRepo.findAll().stream().filter(c -> c.getUsers().contains(u))			
				.collect(Collectors.toSet());
				
	}

	public Optional<Campaign> getCampaignById(int id) {
		return campRepo.findById(id);
	}
	public void addUserToCampaign(User u, Campaign c) {
		u.getCampaigns().add(c);
		userRepo.save(u);
		c.addUser(u);
		campRepo.save(c);
	
	}
	public void removeUserFromCampaign(User u, Campaign c) {
	
		u.getCampaigns().remove(c);
		userRepo.save(u);
		c.removeUser(u);
		campRepo.save(c);
			
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
	
	public List<Campaign> getCampaignsByUsername(String username){
		return campRepo.findAll();

	}
}

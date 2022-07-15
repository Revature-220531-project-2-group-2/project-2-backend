package com.revature.services;


import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.revature.data.CampaignRepository;
import com.revature.models.Campaign;
import com.revature.models.User;


@Service
public class CampaignService {


	private CampaignRepository campRepo;
	
	public CampaignService(CampaignRepository campRepo) {
		this.campRepo = campRepo;
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
	public void removeUserFromCampaign(User u, Campaign c) {
		
		
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

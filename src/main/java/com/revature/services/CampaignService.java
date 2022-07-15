package com.revature.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.revature.data.CampaignRepository;
import com.revature.models.Campaign;

@Service
public class CampaignService {

	
	CampaignRepository campRepo;
	
	public CampaignService(CampaignRepository campRepo) {
		this.campRepo = campRepo;
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
	
	public void updateCampaign(Campaign cp) {
		campRepo.save(cp);
	}
	
	public Set<Campaign> getCharactersByUsername(String username){
		return campRepo.findCampaignByUserUsername(username);
	}
}

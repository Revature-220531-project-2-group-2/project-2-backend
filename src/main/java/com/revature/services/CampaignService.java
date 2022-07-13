package com.revature.services;

<<<<<<< HEAD
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
=======
import java.util.Optional;
import java.util.Set;
>>>>>>> e7aa904820d60b361a6011b428fff9a744c81256

import org.springframework.stereotype.Service;

import com.revature.data.CampaignRepository;
import com.revature.models.Campaign;
<<<<<<< HEAD
import com.revature.models.User;
=======
>>>>>>> e7aa904820d60b361a6011b428fff9a744c81256

@Service
public class CampaignService {

<<<<<<< HEAD
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
		
=======
	
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

	public void addCampaign(Campaign cp) {
		campRepo.save(cp);
	}
	
	public void updateCampaign(Campaign cp) {
		campRepo.save(cp);
	}
	
	public Set<Campaign> getCharactersByUsername(String username){
		return campRepo.findCampaignByUserUsername(username);
>>>>>>> e7aa904820d60b361a6011b428fff9a744c81256
	}
}

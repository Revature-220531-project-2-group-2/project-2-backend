package com.revature.data;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Campaign;
import com.revature.models.CharSheet;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer>{
	
	public Campaign findCampaignByUserId(int id);
	
	public Set<Campaign> findAllCharSheetsByUserId(int id);
	
	public Set<Campaign> findCampaignByUserUsername(String username);

}

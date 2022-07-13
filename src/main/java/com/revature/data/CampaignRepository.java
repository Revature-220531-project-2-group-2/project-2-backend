package com.revature.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Campaign;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer> {

	
	public List<Campaign> findAll();
	public List<Campaign> findCampaignByCampaignId(int id);
}

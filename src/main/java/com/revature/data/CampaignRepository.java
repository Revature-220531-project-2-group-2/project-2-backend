package com.revature.data;


import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Campaign;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer>{
	
	


}

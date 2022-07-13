package com.revature.data;

<<<<<<< HEAD
import java.util.List;
=======
import java.util.Set;
>>>>>>> e7aa904820d60b361a6011b428fff9a744c81256

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Campaign;
<<<<<<< HEAD

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer> {

	
	public List<Campaign> findAll();
	public List<Campaign> findCampaignByCampaignId(int id);
=======
import com.revature.models.CharSheet;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer>{
	
	public Campaign findCampaignByUserId(int id);
	
	public Set<Campaign> findAllCharSheetsByUserId(int id);
	
	public Set<Campaign> findCampaignByUserUsername(String username);

>>>>>>> e7aa904820d60b361a6011b428fff9a744c81256
}

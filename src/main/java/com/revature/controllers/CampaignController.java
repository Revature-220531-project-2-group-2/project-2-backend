package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Campaign;
import com.revature.services.CampaignService;

@RestController
@RequestMapping("/campaigns")
public class CampaignController {

	private CampaignService campServ;
	
	public CampaignController(CampaignService campServ) {
		this.campServ = campServ;
	}
	
	/**
	 * Get a list of current campaigns
	 * @return
	 */
	@GetMapping
	public List<Campaign> getAllCampaigns(){
		return campServ.getAllCampaigns();
	}
	
	/**
	 * Create and save a new Campaign
	 * @param newCampaign
	 * @return
	 */
	@PostMapping(value="/new-campaign")
	public Campaign  createNewCampaign(Campaign newCampaign) {
		return campServ.addCampaign(newCampaign);
	}
	/**
	 * Gets a campaign by its id
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	public ResponseEntity<Campaign> findCampaignById(@PathVariable("campaignId") int id) {
		Optional<Campaign> campaign = campServ.findById(id);
		if(!campaign.isPresent()) {
			return new ResponseEntity<Campaign>(HttpStatus.NO_CONTENT);
		} else {
			return ResponseEntity.ok(campaign.get());
		}
	}
}

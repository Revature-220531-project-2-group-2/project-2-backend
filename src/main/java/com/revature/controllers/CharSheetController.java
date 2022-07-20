package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.data.CharSheetRepository;
import com.revature.models.Campaign;
import com.revature.models.CharSheet;
import com.revature.services.CharSheetService;

/**
 *   characters should be able to:
 *                    get all characters *
 *                    get character by id *              "/id-{id}"
 *                    get character by character name *  "/char-name-{charname}
 *                    get their attributes  * "/{id}/attributes"                
 *                    update/save their attributes  //handled by the user controller
 *                    
 *
 */
@RestController
@CrossOrigin(origins="*", allowedHeaders="*")
@RequestMapping("/characters")
public class CharSheetController {

	private CharSheetRepository charRepo;
	private CharSheetService charServ;
	
	public CharSheetController(CharSheetRepository charRepo) {
		this.charRepo = charRepo;
	}
	
	/**
	 * Get all characters from the database
	 * @return
	 */
	@GetMapping
	public List<CharSheet> getAllCharacters(){
		return charRepo.findAll();
	}
	
	/**
	 * Get character by character name
	 * @param charName
	 * @return
	 */
	@GetMapping (value="/char-name-{charname}")
	public ResponseEntity<CharSheet> findCharSheeetByCharName(@PathVariable("charName") String charName){
		
		Optional<CharSheet> charSheet = charRepo.findCharSheetByCharName(charName);
		if(!charSheet.isPresent()) {
			return new ResponseEntity<CharSheet>(HttpStatus.NO_CONTENT);
		} else {
			return ResponseEntity.ok(charSheet.get());
		}
		
	}
	/**
	 * Get character by id
	 * @param id
	 * @return
	 */
	@GetMapping(value="/id-{id}")
	public ResponseEntity<CharSheet> findCharSheetById(@PathVariable("campaignId") int id) {
		Optional<CharSheet> charSheet = charRepo.findById(id);
		if(!charSheet.isPresent()) {
			return new ResponseEntity<CharSheet>(HttpStatus.NO_CONTENT);
		} else {
			return ResponseEntity.ok(charSheet.get());
		}
	}
	
	
	
	/**
	 * Create and save a new Campaign
	 * @param newCampaign
	 * @return
	 */
	@PostMapping
	public CharSheet  createNewCharSheet(CharSheet newCharSheet) {
		return charServ.addCharSheet(newCharSheet);
	}
	
		
	
}

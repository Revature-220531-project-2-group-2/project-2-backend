package com.revature.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.data.CharSheetRepository;
import com.revature.dto.CharSheetHolder;
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

	
	private CharSheetService charServ;
	
	public CharSheetController(CharSheetService charServ) {
		this.charServ = charServ;
	}
	
	/**
	 * Get all characters from the database
	 * @return
	 */
	@GetMapping
	public List<CharSheet> getAllCharacters(){
		return charServ.getAll();
	}
	
	/**
	 * Get character by character name
	 * @param charName
	 * @return
	 */
	@GetMapping (value="/char-name-{char-name}")
	public ResponseEntity<CharSheet> findCharSheeetByCharName(@PathVariable("char-name") String charName){
		
		Optional<CharSheet> charSheet = charServ.findByCharName(charName); 
				//charRepo.findAll().stream().filter(c -> c.getCharName().equals(charName)).findAny();
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
		Optional<CharSheet> charSheet = charServ.getCharacterById(id);
		if(!charSheet.isPresent()) {
			return new ResponseEntity<CharSheet>(HttpStatus.NO_CONTENT);
		} else {
			return ResponseEntity.ok(charSheet.get());
		}
	}
	
	
	@PutMapping(value="/update-{char-name}")
	public ResponseEntity<CharSheet> updateCharacter(@PathVariable("char-name") String charName, @RequestBody CharSheetHolder csh){
		Optional<CharSheet> charSheet = charServ.findByCharName(charName);
		if(!charSheet.isPresent()) {
			return new ResponseEntity<CharSheet>(HttpStatus.NO_CONTENT);
		} else {
			charServ.updateCharSheet(CharSheet.copyCharSheetInfoFromHolder(charSheet.get(), csh));
			return ResponseEntity.ok(charSheet.get());
		}
		
	}
	/**
	 * Create and save a new CharacterSheet
	 * @param newCampaign
	 * @return
	 */
	@PostMapping
	public CharSheet  createNewCharSheet(@RequestBody CharSheetHolder charSheetInfo) {
	
		return charServ.addCharSheet(makeCharSheetFromHolder(charSheetInfo));
	}
	
	
	private final CharSheet makeCharSheetFromHolder(CharSheetHolder charSheetInfo) {
		CharSheet charSheet;
		
		charSheet = (charSheetInfo == null ) ? new CharSheet() : charServ.getCharacterById(charSheetInfo.getId()).get() ;
		
		charSheet.setCharClass(charSheetInfo.getCharClass());
		charSheet.setCharisma(charSheetInfo.getCharisma());
		charSheet.setCharName(charSheetInfo.getCharName());
		charSheet.setConstitution(charSheetInfo.getConstitution());
		charSheet.setDexterity(charSheetInfo.getDexterity());
		List<String> equipment = (charSheetInfo.getEquipment() == null) ? new LinkedList<String>() : charSheetInfo.getEquipment();
		charSheet.setEquipment(equipment);
		charSheet.setIntelligence(charSheetInfo.getIntelligence());
		charSheet.setRace(charSheetInfo.getRace());
		charSheet.setSpells(charSheetInfo.getSpells());
		charSheet.setStrength(charSheetInfo.getStrength());
		charSheet.setUser(charSheetInfo.getUser());
		charSheet.setWisdom(charSheetInfo.getWisdom());
		
		return charSheet;
	}
	
}

package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.data.CharSheetRepository;
import com.revature.models.Attribute;
import com.revature.models.CharSheet;

/**
 *   characters should be able to:
 *                    get all characters *
 *                    get character by id * "/{id}"
 *                    get their attributes  * "/{id}/attributes"                
 *                    update/save their attributes  //handled by the user controller
 *                    
 *
 */
@RestController
@RequestMapping("/characters")
public class CharSheetController {

	private CharSheetRepository charRepo;
	public CharSheetController(CharSheetRepository charRepo) {
		this.charRepo = charRepo;
	}
	
	@GetMapping
	public List<CharSheet> getAllCharacters(){
		return charRepo.findAll();
	}
	
	
	@GetMapping(value="/{id}")
	public ResponseEntity<CharSheet> findCharSheetById(@PathVariable("id") int id) {
		Optional<CharSheet> charSheet = charRepo.findById(id);
		if(!charSheet.isPresent()) {
			return new ResponseEntity<CharSheet>(HttpStatus.NO_CONTENT);
		} else {
			return ResponseEntity.ok(charSheet.get());
		}
	}
	
	@GetMapping(value="/{id}/attributes")
	public ResponseEntity<Attribute> getAttributes(@PathVariable("id") int id){
		Optional<CharSheet> charSheet = charRepo.findById(id);
		if(!charSheet.isPresent()) {
			return new ResponseEntity<Attribute>(HttpStatus.NO_CONTENT);
		} else {
			return ResponseEntity.ok(charSheet.get().getAttributes());
		}
	}
	
		
	
}

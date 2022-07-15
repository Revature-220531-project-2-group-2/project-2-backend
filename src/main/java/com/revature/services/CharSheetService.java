package com.revature.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.revature.data.CharSheetRepository;
import com.revature.models.CharSheet;

@Service
public class CharSheetService {

	private CharSheetRepository charRepo;
	
	public CharSheetService(CharSheetRepository charRepo) {
		this.charRepo = charRepo;
	}
	
	public Optional<CharSheet> findById(int id) {
		return charRepo.findById(id);
	}
	
	/*
	 * public Optional<CharSheet> findByCharName(String name) { return
	 * charRepo.findCharSheetByCharName(name); }
	 */
	
	public Optional<CharSheet> findByCharName(String charName) {
		// TODO Auto-generated method stub
		return charRepo.findCharSheetByCharName(charName);
	}
	public void deleteCharSheetById(int id) {
		charRepo.deleteById(id);
	}

	public CharSheet addCharSheet(CharSheet cs) {
		return charRepo.save(cs);
	}
	
	public CharSheet updateCharSheet(CharSheet cs) {
		return charRepo.save(cs);
	}
	
	public Set<CharSheet> getCharactersByUsername(String username){
		return charRepo.findCharSheetsByUserUsername(username);
	}
	
	public CharSheet save(CharSheet cs) {
		return charRepo.save(cs);
	}


	
	
	
	
	
}
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
	
	public void deleteCharSheetById(int id) {
		charRepo.deleteById(id);
	}

	public void addCharSheet(CharSheet cs) {
		charRepo.save(cs);
	}
	
	public void updateCharSheet(CharSheet cs) {
		charRepo.save(cs);
	}
	
	public Set<CharSheet> getCharactersByUsername(String username){
		return charRepo.findCharSheetsByUserUsername(username);
	}
	
	
	
	
	
	
}

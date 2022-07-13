package com.revature.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.revature.data.AttributeRepository;
import com.revature.models.Attribute;

@Service
public class AttributeService {

	private AttributeRepository attRepo;
	
	public AttributeService(AttributeRepository attRepo) {
		this.attRepo = attRepo;
	}
	
	public Optional<Attribute> findById(int id) {
		return attRepo.findById(id);
	}
	
	public void deleteAttributeById(int id) {
		attRepo.deleteById(id);
	}

	public void addAttribute(Attribute at) {
		attRepo.save(at);
	}
	
	public void updateAttribute(Attribute at) {
		attRepo.save(at);
	}
	
	public Set<Attribute> getCharactersByUsername(String username){
		return attRepo.findAttributeByUserCharSheet(username);
	}
}

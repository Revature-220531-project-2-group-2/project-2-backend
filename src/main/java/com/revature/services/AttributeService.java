package com.revature.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.revature.data.AttributeRepository;
import com.revature.data.AttributesRepository;
import com.revature.models.Attribute;

@Service
public class AttributeService {

	private AttributesRepository attRepo;
	
	public AttributeService(AttributesRepository attRepo) {
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
	
	public Attribute updateAttribute(Attribute at) {
		return attRepo.save(at);
	}
	
	public Set<Attribute> getCharactersByUsername(String username){
		return attRepo.findAttributeByUserCharSheet(username);
	}
}

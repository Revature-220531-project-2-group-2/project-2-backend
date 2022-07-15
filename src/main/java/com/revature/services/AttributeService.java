package com.revature.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature.data.AttributesRepository;
import com.revature.data.CharSheetRepository;
import com.revature.models.Attribute;
import com.revature.models.CharSheet;

@Service
public class AttributeService {
	private AttributesRepository attribRepo;
	private CharSheetRepository charRepo;
	
	public AttributeService(AttributesRepository attribRepo, CharSheetRepository charRepo) {
		this.attribRepo = attribRepo;
		this.charRepo = charRepo;
	}
	
	public Optional<Attribute> getAttributesById(int attribId) {
		return attribRepo.findById(attribId);
	}
	
	public Attribute getAttributesByCharacterId(int charId){
		Optional<CharSheet> character = charRepo.findById(charId);
		if(!character.isPresent()) {
			return null;
		}else {
			return character.get().getAttributes();
		}
	}

	public Attribute getAttributesByCharacterName(String charName) {
		Optional<CharSheet> character = charRepo.findCharSheetByCharName(charName);
		if(!character.isPresent()) {
			return null;
		}else {
			return character.get().getAttributes();
		}
	}
	
	public Attribute updateAttribute(Attribute attribute) {
		return attribRepo.save(attribute);
	}
}

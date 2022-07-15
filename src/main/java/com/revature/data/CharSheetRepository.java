package com.revature.data;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.CharSheet;

@Repository
public interface CharSheetRepository extends JpaRepository<CharSheet, Integer>{

	
	public CharSheet findCharSheetByUserId(int id);
	
	public Set<CharSheet> findAllCharSheetsByUserId(int id);
	
	public Set<CharSheet> findCharSheetsByUserUsername(String username);
	
	public Optional<CharSheet> findCharSheetByCharName(String charName);
	
	
}
package com.revature.models;

import java.util.Optional;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.revature.services.CharSheetService;
import com.revature.services.UserService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class User {

	
	
	@Transient
	protected UserService userv;
	@Transient
	protected CharSheetService cserv;
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(nullable = false, unique = true)
	@NonNull
	private String username;

	@Column(nullable = false)
	@NonNull
	private String pwd;


	@Column(nullable = false)
	@NonNull
	private String email;
	
	@ElementCollection
	@CollectionTable(name="user_characters", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "char_sheets")
	private Set<CharSheet> characters;


	

	/**
	 * User can:  create a new character
	 *            save/update their character
	 * 			  delete a character
	 * 			
	 *            update their characters
	 *            retrieve their characters
	 *            retrieve a specific character
	 *  TODO          retrieve their campaigns
	 *  TODO          retrieve a specific campaign
	 *  TODO         add a character to a campaign
	 */
    
	/**
	 * Creates a new Character Sheet and adds it to the Set of Characters owned by the user
	 * @param newCharSheet The Character
	 */
    public void createNewCharacter(CharSheet newCharSheet) {
    	characters.add(newCharSheet);
        userv.updateUser(this);
        cserv.addCharSheet(newCharSheet);
       
	}
    
    public void deleteCharacter(CharSheet charSheet) {
    	characters.remove(charSheet);
    	userv.updateUser(this);
    	cserv.deleteCharSheetById(charSheet.getId());
    }
    
    public void updateCharacter(CharSheet updatedCharacter) {
    	characters.add(updatedCharacter);
    	userv.updateUser(this);
    	cserv.updateCharSheet(updatedCharacter);
    }
    
    public Set<CharSheet> getCharacters(){
    	return cserv.getCharactersByUsername(this.username);
    }
 
    public Optional<CharSheet> getCharacterById(int id) {
    	return cserv.findById(id);
    }
    
    /**
     * Adds a character to the characters owned by the user
     * @param cs The new character sheet to be added 
     * @return CharSheet The character that has been added to the character set
     */
    public CharSheet addCharacter(CharSheet cs) {
    	characters.add(cs);
    	userv.updateUser(this);
    	return cserv.updateCharSheet(cs);
    }
    public void saveCharacter(CharSheet cs) {
    	cserv.save(cs);
    }
    
  
    
 	
 
    
	// TODO campaign map
//	@OneToMany(mappedBy = "user")
//	private Campaign campaign;

	/*
	 * @ElementCollection private List<CharSheet> charSheets; // private
	 * List<Campaign> campaigns;
	 * 
	 * 
	 * public void addCharacter(CharSheet newCharSheet) {
	 * charSheets.add(newCharSheet); userv.updateUser(this); }
	 * 
	 * public void deleteCharacter(CharSheet character) {
	 * charSheets.remove(character); userv.deleteUser(character.getId()); }
	 * 
	 */
}

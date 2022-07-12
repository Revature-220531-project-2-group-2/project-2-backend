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

//	@OneToMany(mappedBy = "user")
	
	@ElementCollection
	@CollectionTable(name="user_characters", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "char_sheets")
	private Set<CharSheet> characters;

	@Column(nullable = false)
	@NonNull
	private String email;
	

	/**
	 * User can:  create a new character
	 * 			  delete a character
	 *            update their characters
	 *            retrieve their characters
	 *            retrieve a specific character
	 *  TODO          retrieve their campaigns
	 *  TODO          retrive a specific campaign
	 *  TODO          add themeselves to a campaign
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

package com.revature.models;

import java.util.List;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.revature.services.CampaignService;
import com.revature.services.CharSheetService;
import com.revature.services.UserService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class User {

	@Transient
	protected UserService userv;
	@Transient
	protected CharSheetService cserv;
	@Transient
	protected CampaignService campServ;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
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
	@CollectionTable(name = "user_characters", joinColumns = @JoinColumn(name = "owner_id"))
	@Column(name = "char_sheets")
	private Set<CharSheet> characters;

	@ManyToMany
	@JoinTable(name = "user_campaigns", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "campaign_id"))
	private List<Campaign> campaigns;

	/**
	 * User can: create a new character * save/update their character * delete a
	 * character *
	 * 
	 * retrieve their characters * retrieve a specific character by id or character
	 * name * retrieve their campaigns * retrieve a specific campaign by id add
	 * themselves to a campaign
	 */
	/*
	
	*//**
		 * Creates a new Character Sheet and adds it to the Set of Characters owned by
		 * the user
		 * 
		 * @param newCharSheet The Character
		 */
	/*
	 * public void createNewCharacter(CharSheet newCharSheet) {
	 * characters.add(newCharSheet); userv.updateUser(this);
	 * cserv.addCharSheet(newCharSheet);
	 * 
	 * }
	 * 
	 * public void deleteCharacter(CharSheet charSheet) {
	 * characters.remove(charSheet); userv.updateUser(this);
	 * cserv.deleteCharSheetById(charSheet.getId()); }
	 * 
	 * public void updateCharacter(CharSheet updatedCharacter) {
	 * characters.add(updatedCharacter); userv.updateUser(this);
	 * cserv.updateCharSheet(updatedCharacter); }
	 * 
	 * public Set<CharSheet> getCharacters(){ return
	 * cserv.getCharactersByUsername(this.username); }
	 * 
	 * public Optional<CharSheet> getCharacterById(int id) { return
	 * cserv.findById(id); }
	 * 
	 * public CharSheet getCharacterByName(String char_name) { return
	 * cserv.findByCharName(char_name); }
	 * 
	 *//**
		 * Adds a character to the characters owned by the user updates the user and
		 * persists the user changes to the database
		 * 
		 * @param cs The new character sheet to be added
		 * @return CharSheet The character that has been added to the character set
		 */
	/*
	 * public CharSheet addCharacter(CharSheet cs) { characters.add(cs);
	 * userv.updateUser(this); return cserv.updateCharSheet(cs); }
	 *//**
		 * Adds the character sheet to the user's character sheets (overwrites if it
		 * already exists) updates the db;
		 * 
		 * @param cs The character sheet to be saved to the db
		 */
	/*
	 * public void saveCharacter(CharSheet cs) { characters.add(cs);
	 * userv.updateUser(this); cserv.save(cs); }
	 * 
	 *//**
		 * Get a list of all campaigns currently in the database
		 * 
		 * @return
		 */
	/*
	 * public List<Campaign> getAllCampaigns(){
	 * 
	 * return campServ.getAllCampaigns();
	 * 
	 * }
	 * 
	 *//**
		 * Retrieve a campaign by a specific id
		 * 
		 * @param campaignId The id of the campaign to be retrieved
		 * @return
		 */
	/*
	 * public Optional<Campaign> getCampaignById(int campaignId) { return
	 * campServ.getCampaignById(campaignId); }
	 * 
	 *//**
		 * Retrieve a list of campaigns the user is currently participating in
		 * 
		 * @param u
		 * @return
		 */
	/*
	 * public Set<Campaign> getCampaignsByUser(User u){ return
	 * campServ.getCampaignsByUsername(u); }
	 * 
	 *//**
		 * Adds the user to a campaign, updates the user's campaign list and updates the
		 * data in the database
		 * 
		 * @param campaign The campaign the user joined
		 * @return
		 */
	/*
	 * public Campaign joinCampaign(Campaign campaign) { //add campaign to list of
	 * current campaigns this.campaigns.add(campaign);
	 * 
	 * //update the user data base userv.updateUser(this);
	 * 
	 * //add the user to the campaign campaign.addUser(this); //update the campaign
	 * database return campServ.updateCampaign(campaign);
	 * 
	 * 
	 * }
	 * 
	 *//**
		 * Removes the user from a campaign, updates the user's campaign list and
		 * updates the data in the database
		 * 
		 * @param campaign The campaign the user joined
		 * @return
		 *//*
			 * public void leaveCampaign(Campaign campaign) {
			 * 
			 * this.campaigns.remove(campaign); userv.updateUser(this);
			 * 
			 * campServ.removeUserFromCampaign(this, campaign);
			 * 
			 * }
			 * 
			 * 
			 * 
			 * 
			 */

}

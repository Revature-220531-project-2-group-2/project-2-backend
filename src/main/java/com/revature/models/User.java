package com.revature.models;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.revature.dto.UserHolder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="username")
@EqualsAndHashCode(exclude={"characters", "campaigns"}) 
@ToString(exclude= {"characters", "campaigns"})
@JsonIgnoreProperties(value = { "characters", "campaigns" })

public class User {

	
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

	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy ="user")
	private Set<CharSheet> characters;

	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "owner")
	private List<Message> messages;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "user_campaigns", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "campaign_id"))
	private List<Campaign> campaigns;


	public static  final User makeUserFromUserHolder(User user, UserHolder uhold) {
	     
			
			//user = (uhold.getId() == 0 ) ? new User() : userService.getById(uhold.getId()) ;
			List<Campaign> campaigns = (uhold.getCampaigns() == null) ? new LinkedList<Campaign>() : uhold.getCampaigns();
	        user.setCampaigns(campaigns);
	        Set<CharSheet> characters = (uhold.getCharacters() == null) ? new HashSet<CharSheet>() : uhold.getCharacters();
	        user.setCharacters(characters);
	        
	        user.setEmail(uhold.getEmail());
	        user.setPwd(uhold.getPwd());
	        user.setUsername(uhold.getUsername());

	        return user;
			
		}

	public void removeCampaign(Campaign c) {
		this.campaigns.remove(c);
	}
	
	public void addCampaign(Campaign c) {
		this.campaigns.add(c);
	}
	
	public void updateCampaign(Campaign c) {
		this.campaigns.removeIf(e -> e.getCampaignId() == c.getCampaignId());
		this.campaigns.add(c);
	}

	public void removeCharacter(CharSheet c) {
		this.characters.remove(c);
	}
	
	public void addCharacter(CharSheet c) {
		this.characters.add(c);
	}
	
	public void updateCharacter(CharSheet c) {
		this.characters.removeIf(e -> e.getCharId() == c.getCharId());
		this.characters.add(c);
	}
	
	public void deleteCharacter(CharSheet c) {
		
		this.characters.removeIf(e -> e.getCharId() == c.getCharId());

	}

	

}


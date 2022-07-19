package com.revature.models;

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
@EqualsAndHashCode(exclude={"char_sheet"}) 
@ToString(exclude= {"char_sheet", "campaigns"})
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

	//@ElementCollection
	//@CollectionTable(name = "user_characters", joinColumns = @JoinColumn(name = "owner_id"))
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy ="user")
	@Column(name = "char_sheet")
	private Set<CharSheet> characters;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "user_campaigns", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "campaign_id"))
	private List<Campaign> campaigns;

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


package com.revature.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.revature.services.CampaignService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="campaigns")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
//@EqualsAndHashCode(exclude={"owner"}) @ToString(exclude= {"owner"})
public class Campaign {

	
	
	@Transient
	CampaignService campServ;
	
	@Id
	@Column(name="campaigns_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int campaignId;
		
	@ManyToMany(mappedBy="campaigns")
	private  Set<User> users;

	
	public void addUser(User u) {
		users.add(u);
		campServ.updateCampaign(this);
	  
	}
	
	public void removeUser(User u) {
		users.remove(u);
		campServ.updateCampaign(this);
	}
	
}
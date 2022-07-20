package com.revature.models;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="campaigns")
@Data
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="campaignId")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude={"users","messages"})
@ToString(exclude= {"users","messages"})

public class Campaign {

			
	@Id
	@Column(name="campaign_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int campaignId;
	
	@Column(name="campaigns_name")
	private String campaignName;
		
	@JsonIgnore
	@ManyToMany(mappedBy="campaigns")
	private  Set<User> users;

	@JsonIgnore
	@OneToMany(mappedBy="camp")
	private List<Message> messages;
	
	
	
	
	public void removeUser(User u) {
		users.remove(u);
		u.getCampaigns().remove(this);
	}

	public void addUser(User u) {
		users.add(u);
		u.getCampaigns().add(this);
	}

	

	
}
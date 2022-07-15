package com.revature.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="campaigns")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="campaignId")
@EqualsAndHashCode(exclude={"users"}) @ToString(exclude= {"users"})
public class Campaign {

	
		
	@Id
	@Column(name="campaigns_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int campaignId;
		
	@ManyToMany(mappedBy="campaigns")
	private  Set<User> users;

	public void removeUser(User u) {
		users.remove(u);
		u.getCampaigns().remove(this);
	}

	public void addUser(User u) {
		// TODO Auto-generated method stub
		users.add(u);
		u.getCampaigns().add(this);
	}

	

	
}
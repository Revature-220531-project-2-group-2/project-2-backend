package com.revature.model;

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
import lombok.NonNull;
import lombok.ToString;

@Entity
@Table(name="campaigns")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@EqualsAndHashCode(exclude={"owner"}) @ToString(exclude= {"owner"})
public class Campaign {

	@Id
	@Column(name="campaigns_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String user;
	private String charactersheet;
	
	@ManyToMany(mappedBy="campaigns")
	private @NonNull Set<User> owner;

	public Campaign(String user, String charactersheet) {
		super();
		this.user = user;
		this.charactersheet = charactersheet;
	}
	
	
	
}

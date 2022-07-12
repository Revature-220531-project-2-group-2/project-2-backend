package com.revature.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Entity
@Table(name="attributes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@EqualsAndHashCode(exclude={"owner"}) @ToString(exclude= {"owner"})
public class Attribute {

	@Id
	@Column(name="attributes_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	private String strength;
	private String dexterity;
	private String constitution;
	private String intelligence;
	private String wisdom;
	private String charisma;
	
	@ManyToMany(mappedBy="attributes")
	private @NonNull Set<User> owner;
	
	public Attribute(String strength, String dexterity, String constitution, String intelligence, String wisdom,
			String charisma) {
		super();
		this.strength = strength;
		this.dexterity = dexterity;
		this.constitution = constitution;
		this.intelligence = intelligence;
		this.wisdom = wisdom;
		this.charisma = charisma;
	}	
}

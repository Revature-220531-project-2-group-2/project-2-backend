package com.revature.models;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class CharSheet{
	


	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="char_id")
	private int charId;
	
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_char", nullable=false)
	private User user;
	
	@Column(name="char_name")
	private String charName;
	
	private String race;
	
	@Column(name = "class")
	private String charClass;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name ="attribute_id")
	private Attribute attributes;
	
	

	@ElementCollection
	@CollectionTable(name="spells", joinColumns=@JoinColumn(name = "char_id" ))
	private Set<String> spells;
	

	@ElementCollection
	@CollectionTable(name="equipment", joinColumns=@JoinColumn(name = "char_id" ))
	private List<String> equipment;


	
	
}

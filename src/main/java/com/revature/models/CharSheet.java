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
import javax.persistence.JoinTable;
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
	private User user;
	
	@Column(name="char_name")
	private String charName;
	
	private String race;
	
	@Column(name = "class")
	private String charClass;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name="char_attrib", joinColumns = {@JoinColumn(name ="char_sheet_id", referencedColumnName = "char_id")},
	inverseJoinColumns = {@JoinColumn(name ="attrib_id", referencedColumnName="attribute_id" )})
	private Attribute attributes;
	
	

	@ElementCollection
	@CollectionTable(name="spells", joinColumns=@JoinColumn(name = "char_id" ))
	private Set<String> spells;
	

	@ElementCollection
	@CollectionTable(name="equipment", joinColumns=@JoinColumn(name = "char_id" ))
	private List<String> equipment;


	
	
}

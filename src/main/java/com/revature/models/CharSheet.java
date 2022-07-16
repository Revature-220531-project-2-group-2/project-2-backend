package com.revature.models;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "characters")
@Data @NoArgsConstructor @AllArgsConstructor
public class CharSheet{

    @Id
	@Column(name="char_name")
	private String charName;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id", nullable = false)
	private User user;
	
	

	private String race;
	
	@Column(name = "class")
	private String charClass;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name="char_attrib", 
		joinColumns = {@JoinColumn(name ="char_name", referencedColumnName = "char_name")},
	inverseJoinColumns = {@JoinColumn(name ="attrib_id", referencedColumnName="attribute_id" )})
	private Attribute attributes;
	

	@ElementCollection
	private Set<String> spells;
	

	@ElementCollection
	private List<String> equipment;


	
	
}

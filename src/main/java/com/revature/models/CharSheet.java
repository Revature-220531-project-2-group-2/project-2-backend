package com.revature.models;

import java.util.List;
import java.util.Map;
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

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="charId")
//@ToString(exclude= {"user"})
@EqualsAndHashCode(exclude={"user"})
public class CharSheet{
	//TODO start here and work with join table
	@Column(name="char_name")
	private String charName;
	

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="char_id")
	private int charId;
	
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	
	
//	@Column(unique = true, name="char_name")
//	private String charName;
//	
	private String race;
	
	@Column(name = "class")
	private String charClass;
	
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinTable(name="char_attrib", 
//		joinColumns = {@JoinColumn(name ="char_name", referencedColumnName = "char_name")},
//	inverseJoinColumns = {@JoinColumn(name ="attrib_id", referencedColumnName="attribute_id" )})
//	private Attribute attributes;
	
	/* Andrew's changes */ 
	
	private int strength;
	private int dexterity;
	private int constitution;
	private int intelligence;
	private int wisdom;
	private int charisma;
	
	/* End of Andrew's changes */
	
	

	@ElementCollection
	@CollectionTable(name="spells", joinColumns= {@JoinColumn(name = "char_id" )})
	private Set<String> spells;
	

	@ElementCollection
	@CollectionTable(name="equipment", joinColumns= { @JoinColumn(name = "char_id" )})
	private List<String> equipment;


	public void removeUser(User u) {
		user =null;
	}

	public void addUser(User u) {
		// TODO Auto-generated method stub
		user = u;
	}
	
}

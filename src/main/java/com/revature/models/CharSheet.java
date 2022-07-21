package com.revature.models;

import java.util.HashSet;
import java.util.LinkedList;
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
import javax.persistence.Table;

import com.revature.dto.CharSheetHolder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "characters")
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
	@JoinColumn(name="user_id", nullable = false)
	private User user;
	
	

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

	
	public static final CharSheet copyCharSheetInfoFromHolder(CharSheet charSheet, CharSheetHolder charSheetInfo) {
		
		charSheet.setCharClass(charSheetInfo.getCharClass());
		charSheet.setCharisma(charSheetInfo.getCharisma());
		charSheet.setCharName(charSheetInfo.getCharName().replace(" ", "-"));
		charSheet.setConstitution(charSheetInfo.getConstitution());
		charSheet.setDexterity(charSheetInfo.getDexterity());
		List<String> equipment = (charSheetInfo.getEquipment() == null) ? new LinkedList<String>() : charSheetInfo.getEquipment();
		charSheet.setEquipment(equipment);
		charSheet.setIntelligence(charSheetInfo.getIntelligence());
		charSheet.setRace(charSheetInfo.getRace());
		Set<String> spells = (charSheetInfo.getSpells() == null) ? new HashSet<String>() : charSheetInfo.getSpells();
		charSheet.setSpells(charSheetInfo.getSpells());
		charSheet.setStrength(charSheetInfo.getStrength());
		charSheet.setUser(charSheetInfo.getUser());
		charSheet.setWisdom(charSheetInfo.getWisdom());
		
		return charSheet;
	}
}

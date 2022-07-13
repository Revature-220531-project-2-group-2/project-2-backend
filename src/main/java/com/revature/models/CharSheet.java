package com.revature.models;

import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class CharSheet{
	

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="char_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="id")
	private User user;
	
	private String race;
	
	private String charClass;
	

	private int exp;
	
	private int max_hp;
	
	private int current_hp;
	
	private int str;
	
	private int dex;
	
	private int con;
	
	private int wis;
	
	private int cha;
	
	private int intel;
	
	@ElementCollection
	@CollectionTable(name="spells", joinColumns=@JoinColumn(name = "char_id" ))
	private Set<String> spells;
	


	@ElementCollection
	@CollectionTable(name="equipment", joinColumns=@JoinColumn(name = "char_id" ))
	private List<String> equipment;



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}


	
}

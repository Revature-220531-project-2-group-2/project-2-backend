package com.revature.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="attributes")
@Data
@NoArgsConstructor @AllArgsConstructor
public class Attribute {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "attribute_id")
	private int attribId;

	@OneToOne(mappedBy="attributes")
	private CharSheet charSheet;
	
	private int str;
	private int dex;
	private int con;
	private int intel;
	private int wis;
	private int cha;
		
	
}

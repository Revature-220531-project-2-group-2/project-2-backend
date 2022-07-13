package com.revature.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
//@Table(name="attributes")
@Data
@NoArgsConstructor @AllArgsConstructor
public class Attribute {

	@Id
	@Column(name="attributes_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	private int str;
	private int dex;
	private int con;
	private int intel;
	private int wis;
	private int cha;
		
	
}

package com.revature.models;

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
	
	
	private int str;
	private int dex;
	private int con;
	private int intel;
	private int wis;
	private int cha;
	
	@ManyToMany(mappedBy="attributes")
	private @NonNull Set<User> owner;

	public Attribute(int str, int dex, int con, int intel, int wis, int cha) {
		super();
		this.str = str;
		this.dex = dex;
		this.con = con;
		this.intel = intel;
		this.wis = wis;
		this.cha = cha;
	}
	
	
}

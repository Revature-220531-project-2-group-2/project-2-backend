package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude="camp")
@ToString(exclude= "camp")
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	@Column(name = "message_id")
	private int id;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="camp")
	private Campaign camp;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="created_by")
	private User owner;
	
	
	private String username;
	
	@Column(name = "message")
	private String msg;
	
	
	@Column(name = "time_stamp")
	private String timeStampAsString;
	
}

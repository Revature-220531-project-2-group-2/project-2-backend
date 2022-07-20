package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name="campaigns")
@Data

public class Message {
	

	@Column(name = "msg")
	private String msg;
	
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date utilTimeStamp;
	
}

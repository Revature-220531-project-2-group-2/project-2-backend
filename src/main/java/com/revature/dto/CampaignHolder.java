package com.revature.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.revature.models.Message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignHolder {

	@JsonIgnore
	private int creatorId;
	private String usernameOfCreator;  
	private String campaignName;
	
	@JsonIgnore
	private List<Message> msgs;
}

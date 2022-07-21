package com.revature.dto;

import java.util.List;
import java.util.Set;

import com.revature.models.Campaign;
import com.revature.models.CharSheet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserHolder {

	
	private int id;

	private String username;

	private String pwd;

	private String email;

	
	private Set<CharSheet> characters;

	
	private List<Campaign> campaigns;
}

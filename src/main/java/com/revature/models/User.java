package com.revature.models;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.revature.services.CampaignService;
import com.revature.services.CharSheetService;
import com.revature.services.UserService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class User {

	@Transient
	protected UserService userv;
	@Transient
	protected CharSheetService cserv;
	@Transient
	protected CampaignService campServ;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;

	@Column(nullable = false, unique = true)
	@NonNull
	private String username;

	@Column(nullable = false)
	@NonNull
	private String pwd;

	@Column(nullable = false)
	@NonNull
	private String email;

	//@ElementCollection
	//@CollectionTable(name = "user_characters", joinColumns = @JoinColumn(name = "owner_id"))
	@OneToMany(cascade=CascadeType.ALL, mappedBy ="user")
	@Column(name = "char_sheets")
	private Set<CharSheet> characters;

	@ManyToMany
	@JoinTable(name = "user_campaigns", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "campaign_id"))
	private List<Campaign> campaigns;



}


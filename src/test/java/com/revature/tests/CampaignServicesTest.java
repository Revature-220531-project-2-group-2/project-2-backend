package com.revature.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.data.CampaignRepository;
import com.revature.models.Campaign;
import com.revature.models.User;
import com.revature.services.CampaignService;

public class CampaignServicesTest {
	
	private CampaignRepository mockCampaignRepo;
	private CampaignService campServ;
	private Campaign dummyCampaign;
	private User user;
	private Set<User> users;
	
	@Before
	public void setup() {
		
		mockCampaignRepo = mock(CampaignRepository.class);
		
		campServ = new CampaignService(mockCampaignRepo);
	

		user = new User(12, "bobby", "password", "bob@mail.com", null, null);
		users = new HashSet<User>();
		users.add(user);
	    dummyCampaign = new Campaign(1, "camp1", users);
	}
	
	@After 
	public void tearDown() {
	
		mockCampaignRepo = null;
		campServ = null;
        users = null;
        user = null;
		
		dummyCampaign = null;
	}
	
	@Test
	public void testGetAllCampaigns() {
		List<Campaign> campaigns = new LinkedList<Campaign>();
		campaigns.add(dummyCampaign);
		
		when(mockCampaignRepo.findAll()).thenReturn(campaigns);
		
		assertEquals(campaigns, campServ.getAllCampaigns());
	}
	
	@Test 
	public void testGetCampaignsById() {
		when(mockCampaignRepo.findById(12)).thenReturn(Optional.of(dummyCampaign));
		assertEquals(dummyCampaign, campServ.getCampaignById(12).get() );
	}
	
	@Test
	public void testAddUserToCampaign() {
		List<Campaign> campaigns = new LinkedList<Campaign>();
		campaigns.add(dummyCampaign);
		User user = new User(12, "bobby", "password", "bob@mail.com", null, campaigns );;
		when(mockCampaignRepo.save(dummyCampaign)).thenReturn(dummyCampaign);
		assertEquals(dummyCampaign, campServ.addUserToCampaign(user, dummyCampaign));
	}
	
	@Test
	public void testRemoveUserFromCampaign() {
		List<Campaign> campaigns = new LinkedList<Campaign>();
		campaigns.add(dummyCampaign);
		User user = new User(12, "bobby", "password", "bob@mail.com", null, campaigns );;
		when(mockCampaignRepo.save(dummyCampaign)).thenReturn(dummyCampaign);
		assertEquals(dummyCampaign, campServ.removeUserFromCampaign(user, dummyCampaign));
	}
	
	@Test
	public void testFindById() {
		
		when(mockCampaignRepo.findById(1)).thenReturn(Optional.of(dummyCampaign));
		
		assertEquals(dummyCampaign, campServ.findById(1).get());
	}
	
	@Test
	public void testAddCampaign() {
		when(mockCampaignRepo.save(dummyCampaign)).thenReturn(dummyCampaign);
		assertEquals(dummyCampaign, campServ.addCampaign(dummyCampaign));
	}
	
	@Test
	public void testUpdateCampaign() {
		when(mockCampaignRepo.save(dummyCampaign)).thenReturn(dummyCampaign);
		assertEquals(dummyCampaign, campServ.updateCampaign(dummyCampaign));
	}
	
	@Test
	public void testGetCampaignsByUser() {
		List<Campaign> campaigns = new LinkedList<Campaign>();
		campaigns.add(dummyCampaign);
		when(mockCampaignRepo.findAll()).thenReturn(campaigns);
		
		assertEquals(campaigns, campServ.getCampaignsByUser(user));
	}
}

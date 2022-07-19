package com.revature.tests;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.data.CharSheetRepository;
import com.revature.data.UserRepository;
import com.revature.models.User;
import com.revature.services.UserService;

public class UserServiceTests {

	private UserService userv;
	
	private UserRepository mockUserRepo;
	private CharSheetRepository mockCharRepo;
	private Optional<User> dummyUser;
	
	@Before
	public void setup() {
		mockUserRepo = mock(UserRepository.class);
		mockCharRepo = mock(CharSheetRepository.class);
		
		userv = new UserService(mockUserRepo, mockCharRepo);
	}
	
	@After 
	public void tearDown() {
		userv = null;
		mockCharRepo = null;
		mockUserRepo = null;
		dummyUser = null;
				
	}
	
	//Test username and password are correct
	@Test
	public void testSuccessfulLogin() {
		dummyUser = Optional.of(new User(12, "bobby", "password", "bob@mail.com", null, null));
		//establish the params
		String username = "bobby";
		String pwd = "password1";
		
		when(mockUserRepo.findUserByUsernameAndPwd(username, pwd)).thenReturn(dummyUser);
				
		Optional<User> actualReturnedUser = userv.processLogin(username, pwd);
		Optional<User>  expectedUser = dummyUser;
		
		//expected, actual returned
		assertEquals(expectedUser, actualReturnedUser);
	}
	
	//Test username correct password incorrect
	@Test
	public void testIncorrectUsername() {
		dummyUser = Optional.of(new User(12, "bobby", "password", "bob@mail.com", null, null));
		//establish the params
		String username = "bobby";
		String pwd = "wrong";
		
		when(mockUserRepo.findUserByUsernameAndPwd(username, pwd)).thenReturn(null);
				
		Optional<User> actualReturnedUser = userv.processLogin(username, pwd);
		
		//expected, actual returned
		assertNull(actualReturnedUser);
	}
	//Test username incorrect and password correct
	@Test
	public void testIncorrectPassword() {
		
		dummyUser = Optional.of(new User(12, "bobby", "password", "bob@mail.com", null, null));
		//establish the params
		String username = "bobby1";
		String pwd = "password";
		
		when(mockUserRepo.findUserByUsernameAndPwd(username, pwd)).thenReturn(null);
				
		Optional<User> actualReturnedUser = userv.processLogin(username, pwd);
		
		//expected, actual returned
		assertNull(actualReturnedUser);
	}
	
	//=========================Get By ID =======================
	@Test  //valid id
	public void testGetById() {
		dummyUser = Optional.of(new User(12, "bobby", "password", "bob@mail.com", null, null));
		int id = 12;
		when(mockUserRepo.findById(12)).thenReturn(dummyUser);
		
		Optional<User> actualReturnedUser = userv.getById(id);
		Optional<User>  expectedUser = dummyUser;
		
		assertEquals(expectedUser, actualReturnedUser);
	}
	
	@Test  //no such id
	public void testNoSuchId() {
		dummyUser = Optional.of(new User(12, "bobby", "password", "bob@mail.com", null, null));
		
		when(mockUserRepo.findById(11)).thenReturn(null);
		
		Optional<User> actualReturnedUser = userv.getById(11);
		
		
		assertNull(actualReturnedUser);
	}
	
	//==========================Get By Username Tests ========

	@Test  //valid username
	public void testGetByUsername() {
		dummyUser = Optional.of(new User(12, "bobby", "password", "bob@mail.com", null, null));
		int id = 12;
		when(mockUserRepo.findUserByUsername("bobby")).thenReturn(dummyUser);
		
		Optional<User> actualReturnedUser = userv.getByUsername("bobby");
		Optional<User>  expectedUser = dummyUser;
		
		assertEquals(expectedUser, actualReturnedUser);
	}
	
	@Test  //no such username
	public void testNoSuchUser() {
		dummyUser = Optional.of(new User(12, "bobby", "password", "bob@mail.com", null, null));
		
		when(mockUserRepo.findUserByUsername("bob")).thenReturn(null);
		
		Optional<User> actualReturnedUser = userv.getByUsername("bob");
		
		
		assertNull(actualReturnedUser);
	}
	
	@Test //get All Users
	public void testGetAllUsers() {
		User user1= new User(12, "bobby", "password", "bob@mail.com", null, null);
		User user2 = new User(13, "davis", "password", "davis@dnd.com", null, null);
		List<User> users = new LinkedList<User>();		
		users.add(user1);
		users.add(user2);
		
		when(mockUserRepo.findAll()).thenReturn(users);
		assertEquals(users, userv.getAllUsers());
	}
	
	
	
	

}

package com.revature.tests;

import static org.junit.Assert.assertNull;
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

import com.revature.data.CharSheetRepository;
import com.revature.data.UserRepository;
import com.revature.models.CharSheet;
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
	@Test
	public void testProcessRegister() {
		User testUser = new User(12, "bobby", "password", "bob@mail.com", null, null, null);
		when(mockUserRepo.save(testUser)).thenReturn(testUser);
		assertEquals(testUser,userv.processRegister(testUser));
	}
	//Test username and password are correct
	@Test
	public void testSuccessfulLogin() {
		dummyUser = Optional.of(new User(12, "bobby", "password", "bob@mail.com", null, null, null));
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
		dummyUser = Optional.of(new User(12, "bobby", "password", "bob@mail.com", null, null, null));
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
		
		dummyUser = Optional.of(new User(12, "bobby", "password", "bob@mail.com", null, null, null));
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
		dummyUser = Optional.of(new User(12, "bobby", "password", "bob@mail.com", null, null, null));
		int id = 12;
		when(mockUserRepo.findById(12)).thenReturn(dummyUser);
		
		Optional<User> actualReturnedUser = userv.getById(id);
		Optional<User>  expectedUser = dummyUser;
		
		assertEquals(expectedUser, actualReturnedUser);
	}
	
	@Test  //no such id
	public void testNoSuchId() {
		dummyUser = Optional.of(new User(12, "bobby", "password", "bob@mail.com", null, null, null));
		
		when(mockUserRepo.findById(11)).thenReturn(null);
		
		Optional<User> actualReturnedUser = userv.getById(11);
		
		
		assertNull(actualReturnedUser);
	}
	
	//==========================Get By Username Tests ========

//	@Test  //valid username
//	public void testGetByUsername() {
//		dummyUser = Optional.of(new User(12, "bobby", "password", "bob@mail.com", null, null, null));
//		int id = 12;
//		when(mockUserRepo.findUserByUsername("bobby")).thenReturn(dummyUser);
//		
//		Optional<User> actualReturnedUser = userv.getByUsername("bobby");
//		Optional<User>  expectedUser = dummyUser;
//		
//		assertEquals(expectedUser, actualReturnedUser);
//	}
	
//	@Test  //no such username
//	public void testNoSuchUser() {
//		dummyUser = Optional.of(new User(12, "bobby", "password", "bob@mail.com", null, null, null));
//		
//		when(mockUserRepo.findUserByUsername("bob")).thenReturn(null);
//		
//		Optional<User> actualReturnedUser = userv.getByUsername("bob");
//		
//		User u = actualReturnedUser.get();
//		assertNull(u);
//	}
	//========================TEST GET ALL USERS ===========================
	@Test //get All Users
	public void testGetAllUsers() {
		User user1= new User(12, "bobby", "password", "bob@mail.com", null, null, null);
		User user2 = new User(13, "davis", "password", "davis@dnd.com", null, null, null);
		List<User> users = new LinkedList<User>();		
		users.add(user1);
		users.add(user2);
		
		when(mockUserRepo.findAll()).thenReturn(users);
		assertEquals(users, userv.getAllUsers());
	}
	
	
	@Test 
	public void testUpdateUser() {
	
		User changedUser= new User(12, "bobby", "password", "bob@mail.com", null, null, null);
		when(mockUserRepo.save(changedUser)).thenReturn(changedUser);
		
		assertEquals(changedUser, userv.updateUser(changedUser));
		
	}

	@Test 
	public void testDeleteUser() {
	
		User changedUser= new User(12, "bobby", "password", "bob@mail.com", null, null, null);
	
		
		when(mockUserRepo.deleteById(12)).thenReturn(Optional.of(changedUser));
		
		assertEquals(changedUser, userv.deleteUser(12).get());
		
	}
	
	@Test
	public void testAddCharSheet() {
		CharSheet character =  new CharSheet("dave",1,null, "elf", "druid", 3,4,5,6,7,8,null,null);
		Set<CharSheet> characters = new HashSet<CharSheet>();
		characters.add(character);
		User dummyUser= new User(12, "bobby", "password", "bob@mail.com", characters, null, null);
		when(mockCharRepo.save(character)).thenReturn(character);
		assertEquals(character, userv.addCharSheet(dummyUser, character));
	}
	@Test 
	public void testRemoveCharSheet() {
	
		Set<CharSheet> characters = new HashSet<CharSheet>();
		
		CharSheet character =  new CharSheet("dave",1,null, "elf", "druid", 3,4,5,6,7,8,null,null);
		characters.add(character);
		User dummyUser= new User(12, "bobby", "password", "bob@mail.com", characters, null, null);
		
		when(mockUserRepo.save(dummyUser)).thenReturn(dummyUser);
		
		assertEquals(dummyUser, userv.removeCharSheet(dummyUser,character));
		
	}
	
	
}


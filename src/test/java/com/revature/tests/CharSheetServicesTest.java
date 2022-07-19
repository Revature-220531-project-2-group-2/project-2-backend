package com.revature.tests;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.data.CharSheetRepository;
import com.revature.models.CharSheet;
import com.revature.models.User;
import com.revature.services.CharSheetService;

public class CharSheetServicesTest {

	
	
	private CharSheetRepository mockCharRepo;
	private CharSheetService charServ;
	private CharSheet dummyCharacter;
	
	@Before
	public void setup() {
		
		mockCharRepo = mock(CharSheetRepository.class);
		
		charServ = new CharSheetService(mockCharRepo);
	 
	    dummyCharacter = new CharSheet("dave",1,null, "elf", "druid", 3,4,5,6,7,8,null,null);
	}
	
	@After 
	public void tearDown() {
	
		mockCharRepo = null;
		
		dummyCharacter = null;
	}
	//==================FIND BY CHAR NAME ===========================
	@Test
	public void testFindByCharNameValid() {
	   when(mockCharRepo.findCharSheetByCharName("dave")).thenReturn(Optional.of(dummyCharacter));
	   
	   Optional<CharSheet> actualChar = charServ.findByCharName("dave") ;
	   CharSheet expectedChar = dummyCharacter;
	   
	   assertEquals(expectedChar, actualChar.get());
	}
	
	@Test
	public void testFindByCharNameInvalidName(){
	  when(mockCharRepo.findCharSheetByCharName("davis")).thenReturn(null);
	  
	  assertNull(charServ.findByCharName("davis"));
	}
	//===================TEST UpdateCharSheet
	 @Test
	 public void testUpdateCharSheet() {
		 CharSheet updated = dummyCharacter;
		 when(mockCharRepo.save(updated)).thenReturn(updated);
		 assertEquals(updated, charServ.updateCharSheet(updated));
	 }
	 
	
	//================ADD CHAR SHEEET============================
	@Test
	public void testAddCharSheetTest() {
		
		when( mockCharRepo.save(dummyCharacter)).thenReturn(dummyCharacter);
		
		assertEquals(dummyCharacter, charServ.addCharSheet(dummyCharacter));
	}
	
	
	//===================GET CHAR BY USERNAME ===========================
	@Test
	public void testGetCharactersByUsernameValid() {
		Set<CharSheet> characters = new HashSet<CharSheet>();
		characters.add(dummyCharacter);
		
		when(mockCharRepo.findCharSheetsByUserUsername("bobby")).thenReturn(characters);
		
	    Set<CharSheet> expected = characters;
	    Set<CharSheet> actual = charServ.getCharactersByUsername("bobby");
		assertEquals(expected, actual);
	}
	@Test
	public void testGetCharactersByUsernameNotValid() {
		Set<CharSheet> characters = new HashSet<CharSheet>();
		characters.add(dummyCharacter);
		
		when(mockCharRepo.findCharSheetsByUserUsername("hijinks")).thenReturn(null);
		
		assertNull(charServ.getCharactersByUsername("hijinks"));
	}
	
	@Test
	public void testSave() {
		when(mockCharRepo.save(dummyCharacter)).thenReturn(dummyCharacter);
		assertEquals(dummyCharacter, charServ.save(dummyCharacter));
	}
			
}

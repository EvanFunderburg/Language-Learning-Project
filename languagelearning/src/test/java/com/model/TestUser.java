package com.model;

import java.util.ArrayList;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class TestUser {
    User user = new User("John", "Cena", "jcena", "cantcme");
	UserList users = UserList.getInstance();
    ArrayList<User> userList = users.getUsers();

    @Before
	public void setup() {
		if(userList != null) {
			users.getUsers().clear();
		}
		DataWriter.saveUsers(userList);
	}
	
	@After
	public void tearDown() {
		if(userList != null) {
			users.getUsers().clear();
		}
		DataWriter.saveUsers(userList);
	}

	@Test
	public void testUpdateUserData() {
		user.updateUserData("Johnny", "Cena", "jcena", "cantcme");

		assertEquals("Johnny", user.getFirstName());
	}

	@Test
	public void testNullUpdateUserData() {
		user.updateUserData(null, null, null, null);

		assertEquals("John", user.getFirstName());
	}

	@Test
	public void testInvalidUpdateUserData() {
		user.updateUserData("John", "Cena", "ttomacka", null);


		assertNotEquals("ttomacka", user.getUsername());
	}

	@Test
	public void testAccuracyCalculationNormal() {
		boolean calculationCorrect = false;
		user.setQuestionsCorrect(14);
		user.setQuestionsWrong(2);
		if(user.calculateAccuracy() == 0.875) {
			calculationCorrect = true;
		} else {
			calculationCorrect = false;
		}

		assertTrue(calculationCorrect);
	}

	@Test
	public void testAccuracyCalculationWithZeros() {
		boolean calculationCorrect = false;
		user.setQuestionsCorrect(0);
		user.setQuestionsWrong(0);
		if(user.calculateAccuracy() == 0.75) {
			calculationCorrect = true;
		} else {
			calculationCorrect = false;
		}

		assertTrue(calculationCorrect);
	}

	@Test
	public void testAddLanguageTrack() {
		LanguageTrack french = new LanguageTrack(Language.FRA);
		user.addLanguageTrack(french);
		boolean hasLangTrack = false;
		if(user.getLanguageTracks().contains(french)) {
			hasLangTrack = true;
		}
		assertTrue(hasLangTrack);
	}

	@Test
	public void addNullLanguageTrack() {
		LanguageTrack nullTrack = new LanguageTrack(null);
		user.addLanguageTrack(nullTrack);
		boolean hasLangTrack = false;
		if(user.getLanguageTracks().contains(nullTrack)) {
			hasLangTrack = true;
		}
		assertTrue(hasLangTrack);
	}

	@Test
	public void testLogOut() {
		user.logout();
		assertFalse(user.loggedIn);
	}

	@Test
	public void testLogInandOut() {
		user.logout();
		user.facadeLogin("jcena", "cantcme");
		assertTrue(user.loggedIn);
	}

	@Test
	public void testInvalidPasswordLogInandOut() {
		user.logout();
		user.facadeLogin("jcena", "cantcm");
		assertFalse(user.loggedIn);
	}

	@Test
	public void testInvalidUsernameLogInandOut() {
		user.logout();
		user.facadeLogin("jcen", "cantcme");
		assertFalse(user.loggedIn);
	}

	
}
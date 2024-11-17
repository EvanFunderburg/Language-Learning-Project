package com.model;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


public class TestUserList {
    UserList users = UserList.getInstance();
    ArrayList<User> userList = users.getUsers();
	
	@Before
	public void setup() {
		if(userList != null) userList.clear();
		userList.add(new User("Walter", "White", "heisenberg", "password"));
		userList.add(new User("Jessie", "Pinkman", "JP", "password2"));
		DataWriter.saveUsers(userList);
	}
	
	@After
	public void tearDown() {
		UserList.getInstance().getUsers().clear();
		DataWriter.saveUsers(UserList.getInstance().getUsers());
	}
	
	
	@Test
	public void testHaveUserValidFirstItem() {
		User user = users.getUserByUN("heisenberg");
        boolean hasWalter = false;
        if(user != null) {
            hasWalter = true;
        }
		assertTrue(hasWalter);
	}
	
	@Test
	public void testHaveUserValidLastItem() {
		User user = users.getUserByUN("JP");
        boolean hasJessie = false;
        if(user != null) {
            hasJessie = true;
        }
		assertTrue(hasJessie);
	}
	
	@Test
	public void testHaveUserInValid() {
		User user = users.getUserByUN("joey");
        boolean hasJoe = true;
        if(user == null) {
            hasJoe = false;
        }
        assertFalse(hasJoe);
	}
	
	@Test
	public void testHaveUserEmpty() {
		User user = users.getUserByUN("");
        boolean hasEmpty = true;
        if(user == null) {
            hasEmpty = false;
        }
        assertFalse(hasEmpty);
	}
	
	@Test
	public void testHaveUserNull() {
		User user = users.getUserByUN(null);
        boolean hasNull = true;
        if(user == null) {
            hasNull = false;
        }
        assertFalse(hasNull);
	}

	@Test
	public void testGetUserByIDInvalidID() {
		User testUser = users.getUserByID(UUID.randomUUID());
		assertTrue(testUser == null);
	}

	@Test
	public void testGetUserByID() {
		User testUser = users.getUserByID(UUID.fromString("e96ec222-c098-4fd8-a678-9deea968e473"));
		assertTrue(testUser.getFirstName().equals("Evan"));
	}

	@Test
	public void testAddUser() {
		User testUser = new User("test", "test", "test", "test");
		users.addUser(testUser.getFirstName(), testUser.getLastName(), testUser.getUsername(), testUser.getPassword());
		assertTrue(users.getUserByUN("test").getFirstName() == "test");
	}

	@Test
	public void testAddUserEmptyString() {
		User testUser = new User("", "", "", "");
		users.addUser(testUser.getFirstName(), testUser.getLastName(), testUser.getUsername(), testUser.getPassword());
		assertTrue(users.getUserByUN("").getFirstName() == "");
	}

	@Test
	public void testAddUserNull() {
		User testUser = new User(null, null, null, null);
		assertFalse(users.addUser(testUser.getFirstName(), testUser.getLastName(), testUser.getUsername(), testUser.getPassword()));

	}

	@Test
	public void testRemoveUser() {
		users.addUser("test", "test", "test", "test");
		users.removeUser("test", "test", "test");
		assertFalse(users.getUserByUN("test") == null);
	}

	@Test
	public void testRemoveUserWithoutUserInList() {
		assertFalse(users.removeUser("test", "test", "test"));
	}

	@Test
	public void testIsEqual() {
		users.addUser("test", "test", "test", "test");
		assertTrue(users.isEqual(users.getUserByUN("test")));
	}

	@Test
	public void testIsEqualWithUserNotInList() {
		users.addUser("test", "test", "test", "test");
		assertFalse(users.isEqual(new User("t", "t", "t", "t")));
	}


}
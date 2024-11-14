package com.model;
import java.util.ArrayList;
import java.util.UUID;

/**
 * UserList Class
 * This class represents a list of users in the language learning application.
 * It contains methods to manage user data, track progress, and interact with the application.
 * @author Ritvik Neerattil
 * @author Aidan Van Voorhis
 * @author William Thacher
 * @author Evan Funderburg
 */
public class UserList {
    private ArrayList<User> users;
    private static UserList userList;

    /**
     * Constructor for UserList
     * Initializes the list of users by loading data from the data loader.
     */
    private UserList() {
        users = DataLoader.getUsers();
    }

    /**
     * Method to get instance of UserList
     * @return UserList instance
     * If the instance does not exist, it creates a new one.
     */
    public static UserList getInstance() {
        if (userList == null) {
            userList = new UserList();
        }
        return userList;
    }

    /**
     * Method to get user by username
     * @param userName Username of the user
     * @return User object
     * Returns the user with the specified username, or null if no such user exists.
     */
    public User getUserByUN(String userName) {
        for(int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(userName)) {
                return users.get(i);
            }
        }
        return null;
    }

    /**
     * Method to get user by ID
     * @param userID ID of the user
     * @return User object
     * Returns the user with the specified ID, or null if no such user exists.
     */
    public User getUserByID(UUID userID) {
        for(int i = 0; i < users.size(); i++) {
            if (users.get(i).getID().equals(userID)) {
                return users.get(i);
            }
        }
        return null;
    }

    /**
     * Method to add a new user
     * @param firstName First name of the user
     * @param lastName Last name of the user
     * @param username Username of the user
     * @param password Password of the user
     * @return True if user is added successfully, false otherwise
     * Adds a new user to the list and saves the list to the data writer.
     */
    public boolean addUser(String firstName, String lastName, String username, String password) {
        User user = new User(firstName, lastName, username, password);
        if(!isEqual(user)) {
            this.users.add(user);
            DataWriter.saveUsers(this.users);
            return true;
        }
        return false;
    }

    /**
     * Method to remove a user by ID
     * @param userID ID of the user to be removed
     * @return True if user is removed successfully, false otherwise
     * Removes a user from the list and saves the list to the data writer.
     */
    public boolean removeUser(UUID userID) {
        if(getUserByID(userID) != null) {
            //DataWriter.removeUsers(); ?
            return true;
        }
        return false;
    }

    /**
     * Method to remove a user by first name, last name and username
     * @param firstName First name of the user to be removed
     * @param lastName Last name of the user to be removed
     * @param username Username of the user to be removed
     * @return True if user is removed successfully, false otherwise
     * Removes a user from the list and saves the list to the data writer.
     */
    public boolean removeUser(String firstName, String lastName, String username) {
        if(getUserByUN(username) != null && getUserByUN(username).getFirstName().equals(firstName) && getUserByUN(username).getLastName().equals(lastName)) {
            //DataWriter.removeUsers(); ?
            return true;
        }
        return false;
    }
    
    /**
     * Checks if a user with the same username already exists in the list.
     * @param user The user to check for equality.
     * @return True if a user with the same username exists, false otherwise.
     */
    public boolean isEqual(User user){
        for(User aUser : this.users){
            if(aUser.getUsername().equals(user.getUsername())){
                return true;
            }
        }
        return false;
    }

    /**
     * Saves the list of users to the data writer.
     * This method is used to persist the user data to a storage.
     */
    public static void saveUsers() {
        UserList u = getInstance();
        DataWriter.saveUsers(u.users);
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    /*
    public static void main(String[] args) {
        UserList uL = getInstance();

        for(User u : uL.users){
            System.out.println(u);
        }
        // UserList.saveUsers();
    }
    */
}

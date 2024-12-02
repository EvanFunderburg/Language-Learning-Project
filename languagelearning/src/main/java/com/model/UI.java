package com.model;
import java.util.Scanner;

/**
 * This class represents the User Interface for the language learning application.
 * It provides methods to simulate user interactions such as signing up, logging in, 
 * completing lessons, and reviewing progress.
 * @author Ritvik Neerattil
 * @author Aidan Van Voorhis
 * @author William Thacher
 * @author Evan Funderburg
 */
public class UI {
    /**
     * The main method is the entry point of the application. It calls the scenario methods
     * to simulate user interactions.
     * 
     * @param args Command line arguments (not used in this application)
     */
    public static void main(String[] args) {
        // scenario1();
        scenario2();
        //scenario3();
    }
    /**
     * Simulates the first scenario where a user attempts to sign up with a username that is already taken.
     * The user is prompted to input their first name, last name, username, and password. If the username is taken,
     * the user is prompted to try again. Once a unique username is chosen, the account is created successfully.
     */
    public static void scenario1(){
        while(true){
            //forgot to loop this back when account fails, heads up cuz needed for PART1
            Scanner scan = new Scanner(System.in);
            System.out.println("please input a first name");
            String name = scan.next();
            System.out.println("please input a last name");
            String lname = scan.next();
            System.out.println("please input a username");
            String uname = scan.next();
            System.out.println("please input a password");
            String psw = scan.next();
            LanguageAppFacade facade = new LanguageAppFacade();
            if(facade.signUp(name,lname,uname,psw)){
                System.out.println("account made successfully");
                break;
            }
            else{
                System.out.println("error in signing up, try again");
            }
        }
    }
    /**
     * Simulates the second scenario where a user logs in, completes a lesson, and reviews their progress.
     * The user is prompted to input their username and password. If the login is successful, the user chooses a language,
     * starts a lesson, and reviews their progress. Finally, the user logs out.
     */
    public static void scenario2() {
        LanguageAppFacade facade = new LanguageAppFacade();
        Scanner scan = new Scanner(System.in);
        System.out.println("please input a username");
        String uname = "ttomacka";
        System.out.println("please input a password");
        String psw = "BabyGronk";
        if(facade.signIn(uname, psw)) {
            System.out.println("sign in successful");
        }
        else{
            System.out.println("sign in failure :(");
        }
        facade.chooseLanguage("german");
        System.out.println(facade.startLesson());
        System.out.println(facade.getCurrentQuestionString());
        //looks at progress screen (sees struggle word/phrase, current lesson, % of course)
        facade.checkProgress();
        facade.logout();
        scan.close();
    }

    /**
     * Simulates the third scenario where a user logs in, reviews their progress, and completes a struggle lesson.
     * The user is prompted to input their username and password. If the login is successful, the user reviews their progress,
     * including the words and phrases they are struggling with. The user then completes a lesson focused on these struggles.
     * Finally, the user logs out.
     */
    public static void scenario3() {
        LanguageAppFacade facade = new LanguageAppFacade();
        Scanner scan = new Scanner(System.in);
        System.out.println("please input a username");
        String uname = "ttomacka"; // scan.next();
        System.out.println("please input a password");
        String psw = "BabyGronk"; // scan.next();
        if(facade.signIn(uname, psw)){
            System.out.println("sign in successful");
        }
        else{
            System.out.println("sign in failure :(");
        }
        facade.chooseLanguage("german");
        facade.checkProgress();
        //Prints study sheet + does struggle question implimentation
        facade.printStudySheet();
        facade.startStruggleLesson();
        facade.checkProgress();
        facade.logout();
        scan.close();
    }
}

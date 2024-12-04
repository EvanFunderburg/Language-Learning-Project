package com.model;
import java.util.Scanner;

import com.narration.Narriator;

import java.util.ArrayList;

/**
 * Facade class that handles interactions between the user interface and the language learning system
 * @author Will Thacher
 * @author Ritvik Neerattil
 * @author Aidan Van Voorhis
 * @author Evan Funderburg
 */
public class LanguageAppFacade {
    /**The current instance of the Facade */
    private static LanguageAppFacade facade;
    /** The currently logged in user */
    private User user;
    /** List of all users in the system */
    private UserList userList;
    /** List of all questions in the system */
    private QuestionList questionList;
    /** List of all words in the system */
    private WordList wordList;
    /** Scanner for reading user input */
    Scanner scan = new Scanner(System.in);

    /**
     * Constructs a new LanguageAppFacade object
     */
    private LanguageAppFacade() {
        this.userList = UserList.getInstance();
        this.wordList = WordList.getInstance();
    }

    public static LanguageAppFacade getInstance(){
        if(facade == null)
            facade = new LanguageAppFacade();
        return facade;
    }

    /**
     * Attempts to sign up a new user with the given information
     * @param fName The user's first name
     * @param lName The user's last name 
     * @param uname The desired username
     * @param password The user's password
     * @return True if signup successful, false if username already exists
     */
    public boolean signUp(String fName, String lName, String uname, String password) {
        if (userList.addUser(fName, lName, uname, password)) {
            user = userList.getUserByUN(uname);
            return true;
        }
        System.out.println("That username is already in use");
        return false;
    }

    /**
     * Attempts to sign in an existing user
     * @param uname The username to sign in with
     * @param password The password to authenticate with
     * @return True if sign in successful, false otherwise
     */
    public boolean signIn(String uname, String password) {
        if(null != userList.getUserByUN(uname)) {
            if (userList.getUserByUN(uname).getPassword().equals(password)) {
                user = userList.getUserByUN(uname);
                user.facadeLogin(uname, password);
                return true;
            }
        }
        return false;
    }

    /**
     * Displays the current user's progress
     */
    public void checkProgress() {
        System.out.println("User Progress \n -----------------");
        System.out.println(user.showProgress());
    }

    /**
     * Gets the answer string for a question
     * @return The answer string
     */
    public String answerQuestionStr() {
        return "";
    }


    /**
     * Sets the user's chosen language for learning
     * @param lang The language to learn
     */
    public void chooseLanguage(String lang) {
        if(lang.toLowerCase().equals("german")) {
            for(LanguageTrack languageTrack : user.getLanguageTracks()) {
                if(languageTrack.getLearningLanguage() == Language.GER) {
                    user.setCurrentLanguageTrack(languageTrack);
                } else {
                    LanguageTrack newLanguageTrack = new LanguageTrack(Language.GER);
                    user.addLanguageTrack(newLanguageTrack);
                }
            }
        }
    }
    
    /**
     * Gets a string listing all languages being learned
     * @return Formatted string of languages
     */
    public String displayLanguages() {
        String s1 = "Current Languages being learned: \n";
        ArrayList<LanguageTrack> tracks = user.getLanguageTracks();
        for(LanguageTrack t: tracks) {
            s1 += t.getLearningLanguage().label + "(" + t.getLearningLanguage().toString() +")\n";
        }
        return s1;
    }

    /**
     * Adds a new language for the user to learn
     * @param language The language to add
     * @return True if language was added successfully, false if already exists
     */
    public boolean addLanguage(Language language) {
        ArrayList<LanguageTrack> tracks = new ArrayList<LanguageTrack>();
        for(LanguageTrack t: tracks) {
            // System.out.println("Within loop");
            if(t.getLearningLanguage().equals(language))
                return false;
        }
        user.addLanguageTrack(language);
        System.out.println("Language was added");
        return true;
    }

    /**
     * Starts a new lesson for the current user and returns the study sheet
     */
    public String startLesson() {
        return user.getCurrentLanguageTrack().getCurrentStage().getLesson().getStudySheet();
    }

    public String getCurrentQuestionString() {
        return user.getCurrentLanguageTrack().getCurrentStage().getLesson().getCurrentQuestion().getQuestionAsString();
    }

    public String getCurrentQuestionType() {
        return user.getCurrentLanguageTrack().getCurrentStage().getLesson().getCurrentQuestion().getQuestionType();
    }

    public String getAnswerChoices(){
        return user.getCurrentLanguageTrack().getCurrentStage().getLesson().getCurrentQuestion().getAnwersChoicesAsString();
    }

    public boolean answerCurrentQuestion(String answer) {
        boolean correct = user.getCurrentLanguageTrack().getCurrentStage().getLesson().getCurrentQuestion().promptUserResponse(answer);
        if(correct)
            user.getCurrentLanguageTrack().getCurrentStage().getLesson().questionCorrect();
        else
            user.getCurrentLanguageTrack().getCurrentStage().getLesson().questionIncorrect();
        return correct;
    }

    public boolean isLessonFinished(){
        return user.getCurrentLanguageTrack().getCurrentStage().getLesson().isLessonComplete();
    }

    public void resetLesson(){
        user.getCurrentLanguageTrack().getCurrentStage().getLesson().resetQuestions();
    }

    public int getLessonScore(){
        return user.getCurrentLanguageTrack().getCurrentStage().getLesson().getScore();
    }

    /*public void startLesson() {
        if (user != null) {
            System.out.println("Starting lesson ");
            Stage userStage = user.getCurrentLanguageTrack().getCurrentStage();
            System.out.println(userStage.getLesson().getContent());
            System.out.println(userStage.getLesson().getStudySheet());
            // Narriator.playSound(userStage.getLesson().getStudySheet());
            Narriator.playSound("Study Sheet");
            ArrayList<Question> phrases = userStage.getLesson().getQuestions();
            for(int i = 0; i < phrases.size(); i++){
                Phrase phrase = phrases.get(i).getPhrase();
                
                Narriator.playSound(phrase.getDefinition() + ";" + phrase.getPhraseStr());
            }
            for(Question q : userStage.getLesson().getQuestions()) {
                System.out.println(q.toString());
                Narriator.playSound(q.toString());
                String ans = scan.nextLine();
                if(q.promptUserResponse(ans)) {
                    System.out.println("Correct!");
                    user.setQuestionsCorrect(user.getQuestionsCorrect()+1);
                    user.removeFromStruggleList(q.getPhrase());
                } else {
                    System.out.println("Wrong :(");
                    user.setQuestionsWrong(user.getQuestionsWrong()+1);
                    user.addToStruggleList(q.getPhrase());
                }
            }
        } else {
            System.out.println("User not found. Please sign in first.");
        }
    }*/

    /**
     * Starts a lesson focused on phrases the user has struggled with
     */
    public void startStruggleLesson() {
        if (user != null) {
            System.out.println("Starting lesson ");
            // Stage userStage = user.getCurrentLanguageTrack().getCurrentStage();
            //userStage.setPhrases(user.getStruggleList());
            ArrayList<Phrase> strugList = user.getStruggleList();
            ArrayList<Question> quesList = new ArrayList<Question>();
            for(int i = 0; i < 10 && i < strugList.size(); i++) {
                quesList.add(Question.createQuestion(strugList.get(i), strugList.get(i).getStageLevel()));
            }
            // System.out.println(userStage.getLesson().getContent());
            for(Question q : quesList) {
                System.out.println(q.toString());
                Narriator.playSound(q.toString());
                String ans = scan.nextLine();
                if(q.promptUserResponse(ans)) {
                    System.out.println("Correct!");
                    user.setQuestionsCorrect(user.getQuestionsCorrect()+1);
                    user.removeFromStruggleList(q.getPhrase());
                } else {
                    System.out.println("Wrong :(");
                    user.setQuestionsWrong(user.getQuestionsWrong()+1);
                    user.addToStruggleList(q.getPhrase());
                }
            }
        } else {
            System.out.println("User not found. Please sign in first.");
        }
    }

    /**
     * Starts an interactive story reading session
     */
    public void readStory() {
        if(user != null ) {
            Stage stage = user.getLangaugeTrack(Language.GER).getCurrentStage();
            Story currentStory = stage.getStory();
            while (true) {              
                System.out.println(currentStory.toString(currentStory.getPage()));
                String inputAns = scan.nextLine();
                Question currentQuestion = currentStory.getPageQuestion(currentStory.getPage());
                if(currentQuestion.promptUserResponse(inputAns)) {
                    System.out.println("Correct!");
                } else {
                    System.out.println("Wrong :(");
                }

                System.out.println("Turn page forward(f) or close(c) ");
                String input = scan.nextLine();
                if(input.equals("f")) {
                    currentStory.turnPage();
                } else if(input.equals("c")) {
                    System.out.println("Book closed");
                    break;
                } else {
                    System.out.println("Invalid input");
                }
            }
            

        } else {
            System.out.println("User not found. Please sign in first.");
        }
    }

    /**
     * Logs out the current user
     * @return True if logout successful, false if no user logged in
     */
    public boolean logout() {
        if(user == null) {
            return false;
        }
        UserList.saveUsers();
        user.logout();
        user = null;
        return true;
    }

    /**
     * Creates and prints a study sheet for the current user
     */
    public void printStudySheet() {
        if(user != null) {
            user.makeStudySheet();
            System.out.println("Study sheet created");
        } else {
            System.out.println("Sign in to print study sheet");
        }
    }

    /**
     * Advances the user to the next stage
     */
    public void advanceStage(){
        user.getCurrentLanguageTrack().advanceStage();
    }

    /**
     * Sets the current user
     * @param user The user to set as current
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the current user
     * @return The current user
     */
    public User getUser() {
        return this.user;
    }
}

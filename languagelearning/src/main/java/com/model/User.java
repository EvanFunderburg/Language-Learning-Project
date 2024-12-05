package com.model;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Iterator;
/**
 * User Class
 * This class represents a user in the language learning application.
 * It contains methods to manage user data, track progress, and interact with the application.
 * @author Ritvik Neerattil
 * @author Aidan Van Voorhis
 * @author William Thacher
 * @author Evan Funderburg
 */
public class User {
    private UUID ID;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private int questionsCorrect;
    private int questionsWrong;
    private int currency;
    private ArrayList<LanguageTrack> languageTracks;
    private ArrayList<Phrase> struggleList;
    public boolean loggedIn;
    private LanguageTrack currentLanguageTrack;
    private Lesson currentLesson;
    private boolean lessonIsStruggle; // true if currentLesson is a struggle lesson

    
    /**
     * Constructor for creating a new user.
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public User(String firstName, String lastName, String username, String password) {
        this.ID = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.loggedIn = true;

        this.questionsCorrect = 0;
        this.questionsWrong = 0;
        this.languageTracks = new ArrayList<LanguageTrack>();
        this.languageTracks.add(new LanguageTrack(Language.GER));
        this.struggleList= new ArrayList<Phrase>();
    }

    /**
     * Constructor for creating a user from existing data.
     * @param userID The UUID of the user.
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public User(UUID userID, String username, String password){
        this.ID = userID;
        this.username = username;
        this.password = password;
        this.loggedIn = true;
        
        this.questionsCorrect = UserList.getInstance().getUserByUN(username).getQuestionsCorrect();
        this.questionsWrong = UserList.getInstance().getUserByUN(username).getQuestionsWrong();
        this.languageTracks = UserList.getInstance().getUserByUN(username).getLanguageTracks();
        this.struggleList = UserList.getInstance().getUserByUN(username).getStruggleList();
    }
    // constructor created to use in dataloader when adding users to userlist
    /**
     * Constructor for creating a user from existing data.
     * @param userID The UUID of the user.
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     * @param username The username of the user.
     * @param password The password of the user.
     * @param questionsCorrect The number of questions the user has answered correctly.
     * @param questionsWrong The number of questions the user has answered incorrectly.
     * @param currency The amount of currency the user has.
     * @param languageTracks The language tracks the user is learning.
     * @param struggleList The list of phrases the user is struggling with.
     */
    public User(UUID userID, String firstName, String lastName, String username, String password, int questionsCorrect, int questionsWrong, int currency, ArrayList<LanguageTrack> languageTracks, ArrayList<Phrase> struggleList){
        this.ID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        
        this.questionsCorrect = questionsCorrect;
        this.questionsWrong = questionsWrong;
        this.currency = currency;
        this.languageTracks = languageTracks;
        this.struggleList = struggleList;
    }

    /**
     * Method to add a language track to the user.
     * @param languageTrack The language track to be added.
     */
    public void addLanguageTrack(LanguageTrack languageTrack) {
        languageTracks.add(languageTrack);
        this.saveProgress();
    }

    /**
     * Method to display the user's progress.
     * @return A string containing the user's progress.
     */
    public String showProgress() {
        String returnStr = "Questions correct: " + this.questionsCorrect + "\nQuestions wrong: " + this.questionsWrong + 
        "\nAccuracy: " + this.calculateAccuracy() + "\nCurrent Stage" + this.currentLanguageTrack.getCurrentStageLevel() + 
        "\nDifficult Phrases\n";
        for(Phrase p : struggleList) {
            if(p != null) returnStr += p.toString();
        }

        return returnStr; 
    }

    /**
     * Method to log the user out.
     */
    public void logout() {
        UserList.saveUsers();
        loggedIn = false;
    }

    /**
     * Method to facilitate user login.
     * @param username The username of the user.
     * @param password The password of the user.
     * @return True if the login is successful, false otherwise.
     */
    public boolean facadeLogin(String username, String password) {
        return login(username, password);
    }

    /**
     * Private method to handle user login.
     * @param username The username of the user.
     * @param password The password of the user.
     * @return True if the login is successful, false otherwise.
     */
    private boolean login(String username, String password) {
        if (this.username.equals(username) && this.password.equals(password)) {
            this.loggedIn = true;
            return true;
        }
        this.loggedIn = false;
        return false;
    }
    
    /**
     * Method to update the user's data.
     * @param firstName The new first name of the user.
     * @param lastName The new last name of the user.
     * @param username The new username of the user.
     * @param password The new password of the user.
     */
    public void updateUserData(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }
     
    /**
     * Method to calculate the user's accuracy.
     * @return The user's accuracy as a percentage.
     */
    public double calculateAccuracy(){
        return Math.round((this.questionsCorrect*1.0 / (this.questionsCorrect + (this.questionsWrong*1.0))) * 100.0);
    }
    
    /**
     * Method to get a specific language track for the user.
     * @param language The language of the track to be retrieved.
     * @return The language track if it exists, null otherwise.
     */
    public LanguageTrack getLangaugeTrack(Language language) {
        for(LanguageTrack t : languageTracks) {
            if(t.getLearningLanguage().equals(language))
                return t;
        }
        return null;
    }
    
    /**
     * Method to add a language track to the user.
     * @param language The language of the track to be added.
     * @return True if the track is added, false if the track already exists.
     */
    public boolean addLanguageTrack(Language language) {
        for(LanguageTrack t : languageTracks) {
            if(t.getLearningLanguage().equals(language))
                return false;
        }
        languageTracks.add(new LanguageTrack(language));
        return true;
    } 
    /**
     * Method to save the user's progress.
     */
    public void saveProgress() {
        UserList.saveUsers();
    }

    /**
     * Getter for the user's UUID.
     * @return The UUID of the user.
     */
    public UUID getID() {
        return this.ID;
    }

    /**
     * Setter for the user's UUID.
     * @param ID The new UUID of the user.
     */
    public void setID(UUID ID) {
        this.ID = ID;
    }

    /**
     * Getter for the user's first name.
     * @return The first name of the user.
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Setter for the user's first name.
     * @param firstName The new first name of the user.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for the user's last name.
     * @return The last name of the user.
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Setter for the user's last name.
     * @param lastName The new last name of the user.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for the user's username.
     * @return The username of the user.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Setter for the user's username.
     * @param username The new username of the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for the user's password.
     * @return The password of the user.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Setter for the user's password.
     * @param password The new password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for the number of questions the user has answered correctly.
     * @return The number of questions the user has answered correctly.
     */
    public int getQuestionsCorrect() {
        return this.questionsCorrect;
    }

    /**
     * Setter for the number of questions the user has answered correctly.
     * @param questionsCorrect The new number of questions the user has answered correctly.
     */
    public void setQuestionsCorrect(int questionsCorrect) {
        this.questionsCorrect = questionsCorrect;
    }

    /**
     * Getter for the number of questions the user has answered incorrectly.
     * @return The number of questions the user has answered incorrectly.
     */
    public int getQuestionsWrong() {
        return this.questionsWrong;
    }

    /**
     * Setter for the number of questions the user has answered incorrectly.
     * @param questionsWrong The new number of questions the user has answered incorrectly.
     */
    public void setQuestionsWrong(int questionsWrong) {
        this.questionsWrong = questionsWrong;
    }

    /**
     * Getter for the user's currency.
     * @return The amount of currency the user has.
     */
    public int getCurrency() {
        return this.currency;
    }

    /**
     * Setter for the user's currency.
     * @param currency The new amount of currency the user has.
     */
    public void setCurrency(int currency) {
        this.currency = currency;
    }

    /**
     * Getter for the user's language tracks.
     * @return The language tracks the user is learning.
     */
    public ArrayList<LanguageTrack> getLanguageTracks() {
        return this.languageTracks;
    }

    /**
     * Setter for the user's language tracks.
     * @param languageTracks The new language tracks the user is learning.
     */
    public void setLanguageTracks(ArrayList<LanguageTrack> languageTracks) {
        this.languageTracks = languageTracks;
    }

    /**
     * Setter for the user's current language track.
     * @param languageTrack The new current language track of the user.
     */
    public void setCurrentLanguageTrack(LanguageTrack languageTrack) {
        this.currentLanguageTrack = languageTrack;
    }

    /**
     * Getter for the user's current language track.
     * @return The current language track of the user.
     */
    public LanguageTrack getCurrentLanguageTrack() {
        return this.currentLanguageTrack;
    }

    /**
     * Getter for the user's struggle list.
     * @return The list of phrases the user is struggling with.
     */
    public ArrayList<Phrase> getStruggleList() {
        return this.struggleList;
    }

    public Lesson getCurrentLesson() {
        return this.currentLesson;
    }   

    public void setCurrentLesson(Lesson lesson) {
        if(lesson != null)
            this.currentLesson = lesson;
    }
    
    public boolean isStruggleLesson() {
        return lessonIsStruggle;
    }

    public void setStruggleLesson(boolean lessonIsStruggle) {
        this.lessonIsStruggle = lessonIsStruggle;
    }

    /**
     * Overridden toString method to display user information.
     * @return A string containing the user's information.
     */
    @Override
    public String toString(){
        String flist= "";
        String lTracks ="";
        for(LanguageTrack b: this.languageTracks){
            lTracks = lTracks + b.toString();
        }
        String sList ="";
        for(Phrase b: this.struggleList){
            sList = sList + b.getPhraseStr() + "\n";
        }
        return "\n UUID: " + this.ID + "\n friends" + flist + "\n First then last name" + this.firstName + " " + this.lastName +
        "\n Username " + this.username + "\n password " + this.password + "\n questions correct over questions wrong " + this.questionsCorrect +"/"+this.questionsWrong
        + "\n currency: " + this.currency + "$ \n" + lTracks + "\nStruggleList\n" + sList;
    }
    /**
     * Method to add a phrase to the user's struggle list.
     * @param p The phrase to be added.
     */
    public void addToStruggleList(Phrase p){
        boolean phraseInList = false;
        for(Phrase phrase : this.struggleList) {
            if(phrase.toString().equals(p.toString()))
                phraseInList = true;
        }
        if(!phraseInList)
            this.struggleList.add(p);
    }
    /**
     * Method to remove a phrase from the user's struggle list.
     * @param p The phrase to be removed.
     */
    public void removeFromStruggleList(Phrase p){
        Iterator<Phrase> iter = this.struggleList.iterator();
        while (iter.hasNext()) {
            Phrase phrase = iter.next();
            if(phrase.toString().equals(p.toString())) {
                iter.remove();
            }
        }
    }
    /**
     * Method to show top five struggle words
     */
    public String showStruggleList() {
        String txt = "";
        for (int i = 0; i < this.struggleList.size() && i<5; i++) {
            txt += "\n";
            txt += "" + struggleList.get(i).getPhrase().get(0).getBaseLanguage().label + " " + struggleList.get(i).getPhrase().get(0).getLearningLanguage().label+"\n";  
            txt += (i + 1) + ". " + this.struggleList.get(i).getDefinition() + " : " + this.struggleList.get(i).toString() + "\n";
        }
        return txt;
    }

    /**
     * Method to create a study sheet for the user.
     * The study sheet is a text file that contains the user's struggle words and phrases.
     */
    public void makeStudySheet(){
        try {
            String txt = "Base Definition \t \t | Learning definition\n";
        for (int i = 0; i < this.struggleList.size(); i++) {
            txt += "\n";
            txt += "Base language: " + struggleList.get(i).getPhrase().get(0).getBaseLanguage().label + " Learning Language: " + struggleList.get(i).getPhrase().get(0).getLearningLanguage().label+"\n";  
            txt += (i + 1) + ". " + this.struggleList.get(i).getDefinition() + " : " + this.struggleList.get(i).toString() + "\n";
        }    
            FileWriter writer = new FileWriter("StudySheet.txt");
            writer.write(txt);
            writer.close();
        } 
        catch (Exception e) {
            System.out.println("oops");
        }
        
    }
}

package com.model;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Scanner;

/**
 * Class that manages the list of phrases in the language learning program
 * @author Will Thacher
 * @author Ritvik Neerattil
 * @author Aidan Van Voorhis
 * @author Evan Funderburg
 */
public class PhraseList {
    /** Singleton instance of PhraseList */
    private static PhraseList phraseList;
    /** List of all phrases in the system */
    private ArrayList<Phrase> phrases;

    /**
     * Private constructor that loads phrases from data storage
     */
    private PhraseList(){
        this.phrases = DataLoader.getPhrases();
    }

    /**
     * Gets the singleton instance of PhraseList
     * @return The PhraseList instance
     */
    public static PhraseList getInstance(){
        if(phraseList == null)
            phraseList = new PhraseList();
        return phraseList;
    }
    public ArrayList<Phrase> getPhrases(){
        return this.phrases;
    }
    /**
     * Saves all phrases to persistent storage
     */
    public static void savePhrases(){
        PhraseList p = getInstance();
        DataWriter.savePhrases(p.phrases);
    }

    /**
     * Gets a specific phrase by its UUID
     * @param phraseID The UUID of the phrase to find
     * @return The matching Phrase object, or null if not found
     */
    public Phrase getPhrase(UUID phraseID) {
        for(Phrase p : phrases){
            if(p.getUUID().equals(phraseID))
                return p;
        }
        // Uncoment once phrase is written
        return null;
    }

    /**
     * Gets a list of phrases that have a certain stage level
     * @param stageLevel Stage level of the phrases
     * @return ArrayList of all the phrases that are of a stage level
     */
    public ArrayList<Phrase> getPhrases(int stageLevel) {
        // come back to and finish once phrase has been written
        ArrayList<Phrase> phrasesStage = new ArrayList<Phrase>();
        for(Phrase p : phrases){
            if(p.getStageLevel() == stageLevel) // uncomment once phrase implemented
                phrasesStage.add(p); 
        }
        return phrasesStage;
    }

    /**
     * Parses a phrase string into a list of Word objects
     * @param question The phrase string to parse
     * @return ArrayList of Word objects making up the phrase
     */
    private static ArrayList<Word> parsePhrase(String question) {
        Scanner reader = new Scanner(System.in);
        ArrayList<Word> words = new ArrayList<Word>();
        String[] array = question.split(" ");
        WordList w = WordList.getInstance();
        for(String s : array) {
            if(w.search(Language.GER,s) == null){
                System.out.print("What is the english definition of " + s + ": ");
                String eng = reader.nextLine();
                w.addWord(Language.GER, Language.ENG, s, eng);
            }
            
            words.add(w.search(Language.GER, s));
        }
            
        return words;
    }

    /**
     * Main method for testing and adding phrases interactively
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        PhraseList phraseList = getInstance();
        int menu;
        String ques, def;
        do {
            System.out.print("What type of Question would you like to create\n\t1. Add Phrase" 
                                +"\n\t9. Quit and save"
                                +"\n\t10. Quit without saving"
                                +"\nEnter your choice here: ");
            menu = reader.nextInt();
            reader.nextLine();
            switch(menu){
                case 1:
                    System.out.println("Enter the text for your phrase(German) LOWERCASE:");
                    ques = reader.nextLine();
                    System.out.println("Enter the text for your phrase(English) LOWERCASE:");
                    def = reader.nextLine();
                    System.out.println("Enter the stage level:");
                    int level = reader.nextInt();
                    reader.nextLine();
                    ArrayList<Word> words = parsePhrase(ques);
                    Phrase ph = new Phrase(def,level,words,UUID.randomUUID());
                    phraseList.phrases.add(ph);
                break;

                

                case 9:
                    System.out.println("Thanks for adding some phrases and saving them");
                    PhraseList.savePhrases();
                    WordList.saveWords();
                break;

                case 10:
                    System.out.println("Thanks for adding some phrases and not saving them");
                break;

                default:
                System.out.println("Error Please choice a valid selection");
            }

        } while(menu != 9 && menu != 10);


    }
}

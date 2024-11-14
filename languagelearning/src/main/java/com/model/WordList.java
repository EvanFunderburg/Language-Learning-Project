package com.model;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Scanner;

/**
 * Represents a list of words for language learning.
 * 
 * @author Ritvik Neerattil
 * @author Aidan Van Voorhis
 * @author William Thacher
 * @author Evan Funderburg
 */
public class WordList {
    private static WordList wordlist;
    private ArrayList<Word> words;

    /**
     * Private constructor to initialize the WordList.
     * 
     * @author Will Thacher
     */
    private WordList(){
        this.words = DataLoader.getWords();
    }

    /**
     * Gets the singleton instance of WordList.
     * 
     * @return The WordList instance
     * @author Will Thacher
     */
    public static WordList getInstance(){
        if(wordlist == null)
            wordlist = new WordList();
        return wordlist;
    }

    /**
     * Searches for a word in the list based on language and word string.
     * 
     * @param language The language of the word
     * @param word The word to search for
     * @return The found Word object, or null if not found
     * @author Will Thacher
     */
    public Word search(Language language, String word){
        for(int i = 0;i<this.words.size();i++){
            if(this.words.get(i).equals(word,language))
                return this.words.get(i);
        }
        return null;
    }

    /**
     * Searches for a word in the list based on its UUID.
     * 
     * @param wordID The UUID of the word to search for
     * @return The found Word object, or null if not found
     * @author Will Thacher
     */
    public Word search(UUID wordID){
        for(int i = 0;i<this.words.size();i++){
            if(this.words.get(i).getUuid().equals(wordID))
                return this.words.get(i);
        }
        return null;
    }

    /**
     * Searches for a word in the list based on a Word object.
     * 
     * @param word The Word object to search for
     * @return The found Word object, or null if not found
     * @author Will Thacher
     */
    public Word search(Word word){
        for(int i = 0;i<this.words.size();i++){
            if(this.words.get(i).equals(word))
                return this.words.get(i);
        }
        return null;
    }

    /**
     * Finds all words starting with a specific letter.
     * 
     * @param letter The letter to search for
     * @return An ArrayList of Word objects starting with the given letter
     * @author Will Thacher
     */
    public ArrayList<Word> startingWithLetter(char letter){
        ArrayList<Word> startingwithLetter = new ArrayList<>();
        for(int i = 0;i<this.words.size();i++){
            if(this.words.get(i).getLearningDefinition().charAt(0)==letter)
                 startingwithLetter.add(this.words.get(i));
        }
        return startingwithLetter;
    }

    /**
     * Adds a new word to the list if it doesn't already exist.
     * 
     * @param word The Word object to add
     * @return true if the word was added, false if it already exists
     * @author Will Thacher
     */
    public boolean addWord(Word word){
        if(this.search(word)!=null){
            return false;
        }
        else{
            this.words.add(word);
            return true;
        }
    }

    /**
     * Adds a new word to the list if it doesn't already exist
     * 
     * @param learnLang Language of learnWord
     * @param baseLang Language of baseWord
     * @param learnWord the word in the language the user is learning
     * @param baseWord the word in the users base langauge
     * @return true if word was added, false if already exists and wasn't added
     */
    public boolean addWord(Language learnLang, Language baseLang, String learnWord, String baseWord){
        if(this.search(learnLang,learnWord)!=null){
            return false;
        }
        else{
            this.words.add(new Word(learnLang, baseLang, learnWord, baseWord));
            return true;
        }
    }

    /**
     * Removes a word from the list.
     * 
     * @param word The Word object to remove
     * @return The removed Word object, or null if not found
     * @author Will Thacher
     */
    public Word removeWord(Word word){
        for(int i = 0;i<this.words.size();i++){
            if(this.words.get(i).equals(word)){
                this.words.remove(i);
                return this.words.get(i);
            }
                
        }
        return null;
    }

    /**
     * Method to get a random word from the list.
     * 
     * @return A random Word object from the list
     */
    public Word getRandomWord(){
        return words.get((int)(Math.random()*words.size()));
    }

    /**
     * Static method to save the words in the WordList to a file.
     * 
     * @author Will Thacher
     */
    public static void saveWords(){
        WordList w = getInstance();
        DataWriter.saveWords(w.words);
    }

    public ArrayList<Word> getWords() {
        return this.words;
    }
    /*
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        WordList w = WordList.getInstance();
        System.out.print("How many words would you like to add: ");
        int num = reader.nextInt();
        reader.nextLine();
        for(int i = 0; i < num; i++){
            System.out.print("Enter the word in english: ");
            String english = reader.nextLine();
            System.out.print("Enter the word in german: ");
            String german = reader.nextLine();
            w.addWord(Language.GER, Language.ENG, german, english);
        }

        DataWriter.saveWords(w.words);
        
    }
    */
}
